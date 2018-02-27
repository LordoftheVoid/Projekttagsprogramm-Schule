package V2.DataBaseInternalClasses;


import V2.cMain;

import java.sql.SQLException;

/**
 * Created by Aaron on 02.02.2018.
 */
public abstract class AbstractDataBaseRepresentation implements InterfaceDataBaseRepresentation {

    private String uniquePseudoHash = "";
    private String[] nonHashdataBaseValues;

    AbstractDataBaseRepresentation(String uniquePseudoHash) {
        this.uniquePseudoHash = uniquePseudoHash;
        initNullValues();


        if (cMain.objDatabaseManagerGlobal.entryExists(this.getTableReference(), this.getHash())) {
            updateToDataBaseValues();
        } else {
            initDataBaseEntry();
        }

    }

    public String getDisplayableValue(int index) {
        return this.nonHashdataBaseValues[index];
    }

    public void setDisplayayableValue(int index, String newValue) {
        this.nonHashdataBaseValues[index] = newValue;
        savetoDataBase(index,newValue);
    }


    @Override
    public boolean isValidDataBaseEntry() {

        boolean isValid = true;

        String[] dataBaseValues = this.getNonHashdataBaseValues();

        try {
            for (String valueFromDataBase : dataBaseValues
                    ) {
                isValid = isValid && (!valueFromDataBase.equals(""));
            }
        } catch (NullPointerException e1) {
            isValid = false;
        }

        return isValid;
    }

    public String[] getNonHashdataBaseValues() {
        return this.nonHashdataBaseValues;
    }

    @Override
    public String getHash() throws NullPointerException {
        return this.uniquePseudoHash;
    }


    void setHash(String newHash) {
        this.uniquePseudoHash = newHash;
    }


    @Override
    public void deleteEntry() {
        try {
            cMain.objDatabaseManagerGlobal.deleteEntry(this.getTableReference(), this.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected abstract void savetoDataBase(int index, String newValue);


    private void initNullValues() {
        this.nonHashdataBaseValues = new String[this.getamountofDisplayableValues()];
        for (int arrayIndex = 0; arrayIndex < nonHashdataBaseValues.length; arrayIndex++) {
            nonHashdataBaseValues[arrayIndex] = "";
        }

    }


    private void updateToDataBaseValues() {
        this.initNullValues();
        String[] dataBaseValues = cMain.objDatabaseManagerGlobal.getallEntryValuesfromDataBase(this.getTableReference(), this.getHash());
        System.arraycopy(dataBaseValues, 1, this.nonHashdataBaseValues, 0, dataBaseValues.length - 1);
    }


    private void initDataBaseEntry() {
        try {
            cMain.objDatabaseManagerGlobal.createEntry(this.getTableReference(), this.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
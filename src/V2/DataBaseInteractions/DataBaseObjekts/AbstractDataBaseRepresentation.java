package V2.DataBaseInteractions.DataBaseObjekts;


import V2.Settings.Imports;

import java.sql.SQLException;

/**
 * Created by Aaron on 02.02.2018.
 */
public abstract class AbstractDataBaseRepresentation implements InterfaceDataBaseRepresentation {

    private String uniquePseudoHash = "";
    private String[] nonHashdataBaseValues;

    public AbstractDataBaseRepresentation(String uniquePseudoHash) {
        this.uniquePseudoHash = uniquePseudoHash;
        initNullValues();


        if (Imports.objDatabaseManagerGlobal.entryExists(this.getTableReference(), this.getHash())) {
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
    }


    @Override
    public boolean isValidDataBaseEntry() {
        /**TODO Verifizierung gegen die Daten, Tests auf Null etc
         */
        return false;
    }

    public String[] getNonHashdataBaseValues() {
        return this.nonHashdataBaseValues;
    }

    @Override
    public String getHash() throws NullPointerException {
        return this.uniquePseudoHash;
    }


    protected void setHash(String newHash) {
        this.uniquePseudoHash = newHash;
    }


    @Override
    public void deleteEntry() {
        try {
            Imports.objDatabaseManagerGlobal.deleteEntry(this.getTableReference(), this.getHash());
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
        String[] dataBaseValues = Imports.objDatabaseManagerGlobal.getallEntryValuesfromDataBase(this.getTableReference(), this.getHash());
        for (int valueIndex = 1; valueIndex < this.getamountofDisplayableValues(); valueIndex++) {
            this.nonHashdataBaseValues[valueIndex-1] = dataBaseValues[valueIndex];
        }
    }


    private void initDataBaseEntry() {
        try {
            Imports.objDatabaseManagerGlobal.createEntry(this.getTableReference(), this.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

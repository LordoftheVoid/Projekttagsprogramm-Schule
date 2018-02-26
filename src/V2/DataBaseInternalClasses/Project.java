package V2.DataBaseInternalClasses;


import V2.cMain;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.01.2018.
 */


public class Project extends AbstractDataBaseRepresentation {


    /**
     * TODO:  Vollständige Datenverifikation um das Setzen nicht erlaubter Werte zu unterbinden
     */

    private int assignedPupil = 0;


    public Project(String projectNumber) {
        super(projectNumber);
    }


    @Override
    public int getamountofDisplayableValues() {
        return 3;
    }


    @Override
    public String getTableReference() {
        return "Project";
    }

    @Override
    public String getHash() throws NullPointerException {
        return super.getHash();
    }


    @Override
    public boolean isValidDataBaseEntry() {

        boolean isValid = super.isValidDataBaseEntry();

        if(!isValid){
            return false;
        }else{
            String[] dataBaseValues = this.getNonHashdataBaseValues();

            for (int charIndex = 0; charIndex < dataBaseValues[0].length(); charIndex++) {
                isValid = isValid && Character.isDigit(dataBaseValues[0].charAt(charIndex));
            }

            for (int charIndex = 0; charIndex < dataBaseValues[2].length(); charIndex++) {
                isValid = isValid && Character.isDigit(dataBaseValues[2].charAt(charIndex));
            }

            return isValid;
        }

    }


    public void setDisplayayableValue(int index, String newValue) {
        super.setDisplayayableValue(index, newValue);
    }

    public void assignNewPupil() throws IndexOutOfBoundsException {
        if (!this.hasSlotsavailable()) throw new IndexOutOfBoundsException();
        this.assignedPupil++;
    }

    public void resetPupilCount() {
        this.assignedPupil = 0;
    }


    public boolean hasSlotsavailable() {
        return this.assignedPupil < Integer.valueOf(this.getDisplayableValue(2));
    }


    @Override
    protected void savetoDataBase(int index, String newValue) {
        try {
            cMain.objDatabaseManagerGlobal.updateNonIDValues("Project", this.getHash(), index, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

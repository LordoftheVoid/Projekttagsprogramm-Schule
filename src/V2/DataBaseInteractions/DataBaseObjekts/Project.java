package V2.DataBaseInteractions.DataBaseObjekts;


import V2.Settings.Imports;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.01.2018.
 */


public class Project extends AbstractDataBaseRepresentation {


    /**
     * TODO:  Vollständige Datenverifikation um das Setzen nicht erlaubter Werte zu unterbinden
     */

    private int assignedPupil = 0;

    //Constructor from the DataBase
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

        return true;
    }


    public void setDisplayayableValue(int index, String newValue) {
        super.setDisplayayableValue(index, newValue);
        savetoDataBase(index, newValue);
    }

    public void assignNewPupil() throws IndexOutOfBoundsException {
        if (this.hasReachedMaxCapacity()) throw new IndexOutOfBoundsException();
        this.assignedPupil++;
    }

    private boolean hasReachedMaxCapacity() {
        //Todo: Reimplement
        //return this.assignedPupil < Integer.valueOf(this.getInterAktionValues()[0]);
        return false;
    }

    @Override
    protected void savetoDataBase(int index, String newValue) {
        try {
            Imports.objDatabaseManagerGlobal.updateNonIDValues("Project", this.getHash(), index, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

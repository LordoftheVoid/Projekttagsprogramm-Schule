package V2.DataBaseInteractions.DataBaseObjekts;


import V2.Settings.Imports;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aaron on 22.01.2018.
 */


public class Pupil extends AbstractDataBaseRepresentation {


    /**
     * TODO:  Vollständige Datenverifikation um das Setzen nicht erlaubter Werte zu unterbinden
     */

    private boolean isAssigned = false;

    private int indexChoiceAssigned = 0;


    public Pupil(String idsString) {
        super(idsString);
    }

    public static String generateHash(String surName, String preName) {
        String concatenation = surName + preName;
        return String.valueOf(concatenation.hashCode());
    }

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


        for (int arrayIndex = 3; arrayIndex < this.getamountofDisplayableValues(); arrayIndex++) {
            isValid = isValid && Imports.objDatabaseManagerGlobal.entryExists("Project", this.getDisplayableValue(arrayIndex));
        }

        Set<String> uniqueProjectIDS = new HashSet<>();
        for (int arrayIndex = 3; arrayIndex < this.getamountofDisplayableValues(); arrayIndex++) {
            uniqueProjectIDS.add(this.getDisplayableValue(arrayIndex));
        }
        isValid = isValid && uniqueProjectIDS.size() == 4;

        //Todo: Eine Validitätsmethode, dafür ordentlich!
        return isValid;
    }

    @Override
    public String getDisplayableValue(int index) {
        return super.getDisplayableValue(index);
    }

    @Override
    public String getHash() throws NullPointerException {
        return super.getHash();
    }

    @Override
    public int getamountofDisplayableValues() {
        return 7;
    }


    @Override
    public String getTableReference() {
        return "Pupil";
    }


    @Override
    public void setDisplayayableValue(int index, String newValue) {
        super.setDisplayayableValue(index, newValue);
        this.updateHash();
    }


    private void updateHash() throws IllegalArgumentException {
        if (getNonHashdataBaseValues()[0].equals("") && getNonHashdataBaseValues()[1].equals("")) {
            throw new IllegalArgumentException();
        }
        String oldHash = this.getHash();
        this.setHash(generateHash(getNonHashdataBaseValues()[0], getNonHashdataBaseValues()[1]));

        try {
            Imports.objDatabaseManagerGlobal.updateIDValue(this.getTableReference(), oldHash, this.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void savetoDataBase(int index, String newValue) {
        //TODO: Prüfungen ?
        try {
            Imports.objDatabaseManagerGlobal.updateNonIDValues("Pupil", this.getHash(), index, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isAssigned() {
        return this.isAssigned;
    }

    public void assignToProject(int index) {
        this.isAssigned = true;
        this.indexChoiceAssigned = index;
    }

}

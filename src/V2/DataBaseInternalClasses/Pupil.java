package V2.DataBaseInternalClasses;


import V2.cMain;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aaron on 22.01.2018.
 */


public class Pupil extends AbstractDataBaseRepresentation {


    public Pupil(String idsString) {
        super(idsString);
    }

    public static String generateHash(String surName, String preName) {
        String concatenation = surName + preName;
        return String.valueOf(concatenation.hashCode());
    }

    public boolean isValidDataBaseEntry() {

        boolean isValid = super.isValidDataBaseEntry();

        if (!isValid) {
            return false;
        } else {

            for (int arrayIndex = 3; arrayIndex < this.getamountofDisplayableValues(); arrayIndex++) {
                isValid = isValid && cMain.objDatabaseManagerGlobal.entryExists("Project", this.getDisplayableValue(arrayIndex));
            }

            Set<String> uniqueProjectIDS = new HashSet<>();
            for (int arrayIndex = 3; arrayIndex < this.getamountofDisplayableValues(); arrayIndex++) {
                uniqueProjectIDS.add(this.getDisplayableValue(arrayIndex));
            }
            isValid = isValid && uniqueProjectIDS.size() == 4;

            //Todo: Eine Validitätsmethode, dafür ordentlich!
            return isValid;
        }
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
            cMain.objDatabaseManagerGlobal.updateIDValue(this.getTableReference(), oldHash, this.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void savetoDataBase(int index, String newValue) {
        //TODO: Prüfungen ?
        try {
            cMain.objDatabaseManagerGlobal.updateNonIDValues("Pupil", this.getHash(), index, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}

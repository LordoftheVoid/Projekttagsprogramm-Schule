package V2.DataBaseInteractions.DataBaseObjekts;


import V2.Settings.Imports;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.01.2018.
 */


public class Pupil extends DataBaseElementObject {


    /**TODO:  Vollständige Datenverifikation um das Setzen nicht erlaubter Werte zu unterbinden
     *
     *
     */



    static int amountIdentityValues = 3;

    static int amountInteraktionValues = 4;


    private String pseudoHash = "";

    public Pupil(String idsString) {
        super(idsString,amountIdentityValues,amountInteraktionValues);

        String[] valuesDataBase = Imports.objDatabaseManagerGlobal.getallEntryValuesfromDataBase("Pupil", idsString);

        for (int arrayIndex = 0; arrayIndex < amountIdentityValues; arrayIndex++) {
            this.setIdentityValue(valuesDataBase[arrayIndex+1], arrayIndex);
        }

        for (int arrayIndex = 3; arrayIndex <7  ; arrayIndex++) {
            this.setInteraktionValue(valuesDataBase[arrayIndex], arrayIndex-3);
        }
    }


    @Override
    public void updateHash() throws IllegalArgumentException {

        if (this.getPublicIdentityValues()[0].length() < 3 || this.getPublicIdentityValues()[1].length() < 3) {
            throw new IllegalArgumentException();
        }

        this.pseudoHash = "";
        for (int arrayIndex = 0; arrayIndex < 2; arrayIndex++) {
            for (int charIndex = 0; charIndex < 3; charIndex++) {
                this.pseudoHash = this.pseudoHash + this.getPublicIdentityValues()[arrayIndex].charAt(charIndex);
            }
        }
    }


    public boolean isValid() {
        boolean returnValue = true;
        for (int arrayIndex = 0; arrayIndex < this.getPublicIdentityValues().length; arrayIndex++) {
            if (this.getPublicIdentityValues()[arrayIndex] != null && !this.getPublicIdentityValues()[arrayIndex].equals("")) {
                returnValue = false;
            }
        }
        for (int arrayIndex = 0; arrayIndex < this.getInterAktionValues().length; arrayIndex++) {
            try {
                returnValue = Imports.objDatabaseManagerGlobal.entryExists("Project", String.valueOf(arrayIndex));
            } catch (SQLException e) {
                returnValue = false;
            }
        }
        return returnValue;
    }

    @Override
    public String getHash() throws NullPointerException {
        return this.pseudoHash;
    }


    public void setInteraktionValue(String arg, int index) throws IllegalArgumentException {
        if(arg !=null) {
            try {
                int arrayIndex = Integer.parseInt(arg);
            } catch (NumberFormatException e1) {
                throw new IllegalArgumentException();
            }
            super.setInteraktionValue(arg,index);
        }else{
            super.setInteraktionValue("",index);
        }

    }


    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        super.setIdentityValue(arg,index);
    }



}

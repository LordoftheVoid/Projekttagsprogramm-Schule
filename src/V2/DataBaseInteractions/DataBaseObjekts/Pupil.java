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
    }

    public Pupil(String surName, String preName) throws  IllegalArgumentException{
        super("",amountIdentityValues,amountInteraktionValues);

        if(surName.length() <3 || preName.length() <3){
            throw new IllegalArgumentException();
        }

        this.setIdentityValue(surName,0);
        this.setIdentityValue(preName,1);

        this.updateHash();
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

    @Override
    public void updateFromDataBase() {
        String[] valuesDataBase = Imports.objDatabaseManagerGlobal.getallEntryValuesfromDataBase("Pupil", this.uniquePseudoHash);

        for (int arrayIndex = 0; arrayIndex < amountIdentityValues; arrayIndex++) {
            this.setIdentityValue(valuesDataBase[arrayIndex+1], arrayIndex);
        }

        for (int arrayIndex = 3; arrayIndex <7  ; arrayIndex++) {
            this.setInteraktionValue(valuesDataBase[arrayIndex], arrayIndex-3);
        }
    }


    @Override
    public void generateDataBaseEntry() {
        try {
            Imports.objDatabaseManagerGlobal.createEntry("Pupil",this.pseudoHash);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO: Wohin mit dem Fehler ?
    }

    public boolean isValid() {
        //Todo: Zwei validitätsMethoden ? Wenn nicht, was dann ?


        boolean returnValue = true;
        for (int arrayIndex = 0; arrayIndex < this.getPublicIdentityValues().length; arrayIndex++) {
            if (this.getPublicIdentityValues()[arrayIndex] != null && !this.getPublicIdentityValues()[arrayIndex].equals("")) {
                returnValue = false;
            }
        }
        for (int arrayIndex = 0; arrayIndex < this.getInterAktionValues().length; arrayIndex++) {
            try {
                returnValue = Imports.objDatabaseManagerGlobal.entryExists("Pupil", String.valueOf(arrayIndex));
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
        this.savetoDataBase(arg,4+index);


    }


    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        super.setIdentityValue(arg,index);
        this.savetoDataBase(arg,1+index);
    }

    @Override
    protected void savetoDataBase(String newValue, int index)  {
        //TODO: Prüfungen ?

        try {
            Imports.objDatabaseManagerGlobal.updateEntry("Pupil",this.pseudoHash,index,newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

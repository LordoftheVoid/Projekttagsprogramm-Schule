package V2.DataBaseInteractions.DataBaseObjekts;


import V2.Settings.Imports;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.01.2018.
 */


public class Pupil extends DataBaseElementObject {


    /**
     * TODO:  Vollständige Datenverifikation um das Setzen nicht erlaubter Werte zu unterbinden
     */


    static int amountIdentityValues = 3;

    static int amountInteraktionValues = 4;


    private String pseudoHash = "";

    public Pupil(String idsString) {
        super(idsString, amountIdentityValues, amountInteraktionValues);
        this.updateFromDataBase(idsString);
    }


    public Pupil(String surName, String preName) throws IllegalArgumentException {
        super("", amountIdentityValues, amountInteraktionValues);


        if (surName.length() < 3 || preName.length() < 3) {
            throw new IllegalArgumentException("Die Namen waren zu kurz, sie müssen mindestens Länge drei haben");
        }
        this.setHashValuesonCreation(surName,0);
        this.setHashValuesonCreation(preName,1);
        this.updateHash();
    }


    private void updateFromDataBase(String pseudoHash){
        //Hash has to be transmitted since he is not set yet

        for (int i = 0; i < amountIdentityValues; i++) {
           this.setHashValuesonCreation(Imports.objDatabaseManagerGlobal.getValuefromDataBase("Pupil",pseudoHash,i+2),i);
        }
        for (int i = 0; i < amountInteraktionValues; i++) {
            this.setInteraktionValuefromDataBase(Imports.objDatabaseManagerGlobal.getValuefromDataBase("Pupil",pseudoHash,i+amountIdentityValues+2),i);
        }
    }



    private void setHashValuesonCreation(String arg, int index) {
        //Does not update hash, therefore dangerous, but necessary to set the values initially
        super.setIdentityValue(arg,index);
    }

    private void updateHash() throws IllegalArgumentException {

        if (this.getVisibleIdentityValues()[0].length() < 3 || this.getVisibleIdentityValues()[1].length() < 3) {
            throw new IllegalArgumentException("Der Versuch, den Schüler zu identifizieren schlug fehl");
        }

        this.pseudoHash = "";
        for (int arrayIndex = 0; arrayIndex < 2; arrayIndex++) {
            for (int charIndex = 0; charIndex < 3; charIndex++) {
                this.pseudoHash = this.pseudoHash + this.getVisibleIdentityValues()[arrayIndex].charAt(charIndex);
            }
        }
    }


    @Override
    public void generateDataBaseEntry() {
        try {
            Imports.objDatabaseManagerGlobal.createEntry("Pupil", this.pseudoHash);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //TODO: Wohin mit dem Fehler ?
    }

    @Override
    public void genericSetter(String newValue, int index) {
        if(index <amountIdentityValues){
            this.setIdentityValue(newValue,index);
        }else{
            this.setInteraktionValuefromDataBase(newValue,index);
        }
    }

    public boolean isValidDataBaseEntry() {
        //Todo: Zwei validitätsMethoden ? Wenn nicht, was dann ?

        boolean returnValue = true;
        for (int arrayIndex = 0; arrayIndex < this.getVisibleIdentityValues().length; arrayIndex++) {
            if (this.getVisibleIdentityValues()[arrayIndex] == null) {
                returnValue = false;
            }
            if (this.getVisibleIdentityValues()[arrayIndex].equals("")) {
                returnValue = false;
            }
        }
        for (int arrayIndex = 0; arrayIndex < this.getInterAktionValues().length; arrayIndex++) {
            returnValue = returnValue & Imports.objDatabaseManagerGlobal.entryExists("Project", String.valueOf(arrayIndex));
        }
        return returnValue;
    }

    @Override
    public String getHash() throws NullPointerException {
        return this.pseudoHash;
    }

    private void setInteraktionValuefromDataBase(String newValue, int index) throws IllegalArgumentException {
        super.setInteraktionValuetoDataBase(newValue,index);
    }


    public void setInteraktionValuetoDataBase(String newValue, int index) throws IllegalArgumentException {
        if (newValue == null) throw new IllegalArgumentException("Das Argumet war leer, erlaubt sind Ziffern von 0-9");
        for (int charIndex = 0; charIndex < newValue.length(); charIndex++) {
            if (!Character.isDigit(newValue.charAt(charIndex))) {
                throw new IllegalArgumentException("Ein Zeichen war keine Ziffer, erlaubt sind Ziffern 0-9");
            }
        }

        if (Imports.objDatabaseManagerGlobal.entryExists("Project", newValue)) {
            super.setInteraktionValuetoDataBase(newValue, index);
        } else {
            throw new IllegalArgumentException("Es existiert kein Projekt mit " + newValue);
        }

        this.savetoDataBase(newValue, 4 + index);
    }


    public void setIdentityValue(String newValue, int index) throws IllegalArgumentException {
        if (newValue == null)
            throw new IllegalArgumentException("Der Wert war null, erlaubt sind alle Wörter, Namen der Länge größer drei");
        if (newValue.equals(""))
            throw new IllegalArgumentException("Das Argument war leer, gefordert wird mindestens ein Buchstabe");
        if (index < 2) {
            if (newValue.length() < 3) {
                throw new IllegalArgumentException("Der Name war zu kurz, gefordert sind Namen der Länge mindestens drei ");
            }
        }
        super.setIdentityValue(newValue, index);
        this.updateHash();
        this.savetoDataBase(newValue, index);
    }

    @Override
    protected void savetoDataBase(String newValue, int index) {
        //TODO: Prüfungen ?
        try {
            Imports.objDatabaseManagerGlobal.updateEntry("Pupil", this.getHash(), index+2, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

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

        for (int i = 0; i < amountIdentityValues; i++) {
            this.setIdentityValue(valuesDataBase[i+1], i);
        }

        for (int i = 3; i <7  ; i++) {
            this.setInteraktionValue(valuesDataBase[i], i-3);
        }


        this.updateHash();

    }


    @Override
    public void updateHash() throws IllegalArgumentException {
        
        if (this.getPublicIdentityValues()[0].length() < 3 || this.getPublicIdentityValues()[1].length() < 3) {
            throw new IllegalArgumentException();
        }

        this.pseudoHash = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                this.pseudoHash = this.pseudoHash + this.getPublicIdentityValues()[i].charAt(j);
            }
        }
    }


    public boolean isValid() {
        boolean returnValue = true;
        for (int i = 0; i < this.getPublicIdentityValues().length; i++) {
            if (this.getPublicIdentityValues()[i] != null && !this.getPublicIdentityValues()[i].equals("")) {
                returnValue = false;
            }
        }
        for (int i = 0; i < this.getInterAktionValues().length; i++) {
            try {
                returnValue = Imports.objDatabaseManagerGlobal.entryExists("Project", String.valueOf(i));
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
                int i = Integer.parseInt(arg);
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

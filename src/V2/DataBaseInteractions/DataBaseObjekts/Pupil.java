package V2.DataBaseInteractions.DataBaseObjekts;


import V2.Settings.Imports;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.01.2018.
 */


public class Pupil extends DataBaseElementObject {


    static int amountIdentityValues = 3;

    static int amountInteraktionValues = 4;


    private String pseudoHash = "";

    public Pupil(String idsString) {
        super(idsString);

        String[] valuesDataBase = Imports.objDatabaseManagerGlobal.getEntryValuesfromDataBase("Pupil", idsString);

        for (int i = 0; i < amountIdentityValues; i++) {
            this.setIdentityValue(valuesDataBase[i], i);
        }

        for (int i = amountIdentityValues; i < amountIdentityValues + amountInteraktionValues; i++) {
            this.setInteraktionValue(valuesDataBase[i], i);
        }


        this.updateHash();
    }


    @Override
    public void updateHash() throws IllegalArgumentException {

        if (this.getIdentityValues()[0].length() < 3 || this.getIdentityValues()[1].length() < 3) {
            throw new IllegalArgumentException();
        }
        this.pseudoHash = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                this.pseudoHash = this.pseudoHash + this.getIdentityValues()[i].charAt(j);
            }
        }
    }


    public boolean isValid() {
        boolean returnValue = true;
        for (int i = 0; i < this.getIdentityValues().length; i++) {
            if (this.getIdentityValues()[i] != null && !this.getIdentityValues()[i].equals("")) {
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
}

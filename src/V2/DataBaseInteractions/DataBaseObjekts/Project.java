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

    static int amountIdentityValues = 2;

    static int amountInteraktionValues = 1;


    //Constructor from the DataBase
    public Project(String projectNumber) {
        super("", amountIdentityValues, amountInteraktionValues);
        this.setHash(projectNumber);
    }

    //Constructor from Outside Data
    public Project(String projectNumber, String teacherID) {
        super("", amountIdentityValues, amountInteraktionValues);
        this.setHash(projectNumber);
        this.generateDataBaseEntry();
        this.setIdentityValue(teacherID, 1);
    }


    @Override
    public void generateDataBaseEntry() {
        try {
            Imports.objDatabaseManagerGlobal.createEntry("Project", this.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isValidDataBaseEntry() {


        return true;
    }


    @Override
    public String getHash() throws NullPointerException {
        return super.getHash();
    }


    @Override
    public void deleteEntry() {
        try {
            Imports.objDatabaseManagerGlobal.deleteEntry("Project", this.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void genericSetter(String newValue, int index) {
        if (index < amountIdentityValues) {
            this.setIdentityValue(newValue, index);
        } else {
            this.setInteraktionValuetoDataBase(newValue, index);
        }
    }


    public void setInteraktionValuetoDataBase(String arg, int index) throws IllegalArgumentException {
        for (int charIndex = 0; charIndex < arg.length(); charIndex++) {
            if (!Character.isDigit(arg.charAt(charIndex))) {
                throw new IllegalArgumentException("Der Wert für die maximale Schüleranzahl war keine Zahl");
            }
        }
        super.setInteraktionValuetoDataBase(arg, index);
    }

    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        super.setIdentityValue(arg, index);
        this.savetoDataBase(arg, index);
    }

    @Override
    protected void savetoDataBase(String newValue, int index) {
        try {
            Imports.objDatabaseManagerGlobal.updateEntry("Project", this.getHash(), index, newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

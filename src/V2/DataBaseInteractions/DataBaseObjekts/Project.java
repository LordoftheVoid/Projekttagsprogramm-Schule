package V2.DataBaseInteractions.DataBaseObjekts;


import V2.Settings.Imports;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.01.2018.
 */
public class Project extends AbstractDataBaseRepresentation {


    /**TODO:  Vollständige Datenverifikation um das Setzen nicht erlaubter Werte zu unterbinden
     */

    static int amountIdentityValues = 2;

    static int amountInteraktionValues = 1;



    public Project(String id) {
        super(id, amountIdentityValues, amountInteraktionValues);
        this.setIdentityValue(id, 0);
    }





    @Override
    public void generateDataBaseEntry() {
        try {
            Imports.objDatabaseManagerGlobal.createEntry("Project",this.getHash());
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
            Imports.objDatabaseManagerGlobal.deleteEntry("Project",this.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void genericSetter(String newValue, int index) {
        if(index <amountIdentityValues){
            this.setIdentityValue(newValue,index);
        }else{
            this.setInteraktionValuetoDataBase(newValue,index);
        }
    }


    public void setInteraktionValuetoDataBase(String arg, int index) throws IllegalArgumentException {
        try{
            int resultArg = Integer.parseInt(arg);
            if(resultArg <0){
                throw new IllegalArgumentException();
            }
        }catch (NumberFormatException e1){
            throw new IllegalArgumentException();
        }
        super.setInteraktionValuetoDataBase(arg,index);

    }


    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        super.setIdentityValue(arg,index);
    }

    @Override
    protected void savetoDataBase(String newValue, int index) {
        try {
            Imports.objDatabaseManagerGlobal.updateEntry("Project",this.getHash(),index,newValue);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

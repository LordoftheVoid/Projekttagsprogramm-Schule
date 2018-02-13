package V2.DataBaseInteractions.DataBaseObjekts;


import java.sql.SQLException;

/**
 * Created by Aaron on 22.01.2018.
 */
public class Project extends DataBaseElementObject {


    /**TODO:  Vollständige Datenverifikation um das Setzen nicht erlaubter Werte zu unterbinden
     *
     *
     *
     */

    static int amountIdentityValues = 2;

    static int amountInteraktionValues = 1;


    public Project(String id, int amountIdentityValues, int amountInteraktionValues) {
        super(id, amountIdentityValues, amountInteraktionValues);

    }


    public void changeValue(String value, int colum) {

    }


    @Override
    public void setValue(int index, String value) throws IllegalArgumentException {


    }

    @Override
    public void savetoDataBase() throws SQLException {

    }

    @Override
    public boolean isValid() {
        return false;
    }



    @Override
    public String getHash() throws NullPointerException {
        return null;
    }

    @Override
    public void updateHash() throws IllegalArgumentException {

    }

    @Override
    public void deleteEntry() {

    }



    public void setInteraktionValue(String arg, int index) throws IllegalArgumentException {
        try{
            int resultArg = Integer.parseInt(arg);
            if(resultArg <0){
                throw new IllegalArgumentException();
            }
        }catch (NumberFormatException e1){
            throw new IllegalArgumentException();
        }
        super.setInteraktionValue(arg,index);

    }


    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        super.setIdentityValue(arg,index);
    }




}

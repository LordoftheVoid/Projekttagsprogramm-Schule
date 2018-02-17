package V2.DataBaseInteractions.DataBaseObjekts;


/**
 * Created by Aaron on 22.01.2018.
 */
public class Project extends DataBaseElementObject {


    /**TODO:  Vollständige Datenverifikation um das Setzen nicht erlaubter Werte zu unterbinden
     */

    static int amountIdentityValues = 2;

    static int amountInteraktionValues = 1;


    public Project(String id, int amountIdentityValues, int amountInteraktionValues) {
        super(id, amountIdentityValues, amountInteraktionValues);

    }





    @Override
    public void generateDataBaseEntry() {

    }

    @Override
    public boolean isValidDataBaseEntry() {
        return false;
    }



    @Override
    public String getHash() throws NullPointerException {
        return null;
    }



    @Override
    public void deleteEntry() {

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

    }


}

package V2.DataBaseInteractions.DataBaseObjekts;


/**
 * Created by Aaron on 02.02.2018.
 */
public abstract class DataBaseElementObject implements DataBaseElementInterFace {


    private String uniquePseudoHash;
    private String[] visibleIdentityValues;
    private String[] interAktionValues;


    public DataBaseElementObject(String uniquePseudoHash, int identityLength, int aktionLength) {
        this.uniquePseudoHash = uniquePseudoHash;
        this.visibleIdentityValues = new String[identityLength];
        for (int arrayIndex = 0; arrayIndex < visibleIdentityValues.length; arrayIndex++) {
            visibleIdentityValues[arrayIndex] ="";
        }
        this.interAktionValues = new String [aktionLength];
        for (int arrayIndex = 0; arrayIndex < interAktionValues.length; arrayIndex++) {
           interAktionValues[arrayIndex] ="";
        }
    }



    @Override
    public boolean isValidDataBaseEntry() {
        /**TODO Verifizierung gegen die Daten, Tests auf Null etc
         */

        return false;
    }

    public String[] getVisibleIdentityValues()  {
        return this.visibleIdentityValues;
    }

    @Override
    public String getHash() throws NullPointerException {
        return this.uniquePseudoHash;
    }


    @Override
    public void deleteEntry() {
        /**Todo Entfernt den Eintrag
         */
    }

    public String[] getInterAktionValues() {
        return this.interAktionValues;
    }

    public void setInteraktionValuetoDataBase(String arg, int index) throws IllegalArgumentException {
        this.interAktionValues[index]= arg;
    }

    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        this.visibleIdentityValues[index] = arg;
    }

    protected abstract void savetoDataBase(String newValue, int index);



}

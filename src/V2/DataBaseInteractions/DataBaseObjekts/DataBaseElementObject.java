package V2.DataBaseInteractions.DataBaseObjekts;


/**
 * Created by Aaron on 02.02.2018.
 */
public abstract class DataBaseElementObject implements DataBaseElementInterFace {


    String uniquePseudoHash;
    private String[] publicIdentityValues;
    private String[] interAktionValues;


    public DataBaseElementObject(String uniquePseudoHash, int identityLength, int aktionLength) {
        this.uniquePseudoHash = uniquePseudoHash;
        this.publicIdentityValues = new String[identityLength];
        for (int arrayIndex = 0; arrayIndex < publicIdentityValues.length; arrayIndex++) {
            publicIdentityValues[arrayIndex] ="";
        }
        this.interAktionValues = new String [aktionLength];
        for (int arrayIndex = 0; arrayIndex < interAktionValues.length; arrayIndex++) {
           interAktionValues[arrayIndex] ="";
        }
    }


    public void setValue(String value, int colum) {

        /**TODO
         * Je nach Index den Hash neu berechnen !!
         */
    }


    @Override
    public void setValue(int index, String value) throws IllegalArgumentException {

        /** TODO
         */
    }





    @Override
    public boolean isValid() {
        /**TODO Verifizierung gegen den Daten, Tests auf Null etc
         */

        return false;
    }

    public String[] getPublicIdentityValues() throws IndexOutOfBoundsException {

        /** TODO Umwandlung in eine anzeigbare Form
         */

        return this.publicIdentityValues;
    }

    @Override
    public String getHash() throws NullPointerException {

        /**Todo Ausgabe der Identifizierung
         */
        return null;
    }

    @Override
    public void updateHash() throws IllegalArgumentException {

        /**Todo Sonderfall um Werte zu updaten, da kritisch
         */

    }

    @Override
    public void deleteEntry() {

        /**Todo Entfernt den Eintrag
         */

    }

    public String[] getInterAktionValues() {
        return this.interAktionValues;
    }

    public void setInteraktionValue(String arg, int index) throws IllegalArgumentException {
        this.interAktionValues[index]="";
    }

    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        this.publicIdentityValues[index] = arg;
    }


    protected abstract void savetoDataBase(String newValue, int index);
}

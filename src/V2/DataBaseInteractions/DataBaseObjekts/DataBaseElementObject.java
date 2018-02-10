package V2.DataBaseInteractions.DataBaseObjekts;


import java.sql.SQLException;

/**
 * Created by Aaron on 02.02.2018.
 */
public abstract class DataBaseElementObject implements DataBaseElementInterFace {


    String id;
    private String[] identityValues;
    private String[] interAktionValues;


    public DataBaseElementObject(String id, int identityLength, int aktionLength) {
        this.id = id;
        this.identityValues = new String[identityLength];
        for (int i = 0; i < identityValues.length; i++) {
            identityValues[i] ="";
        }
        this.interAktionValues = new String [aktionLength];
        for (int i = 0; i < interAktionValues.length; i++) {
           interAktionValues[i] ="";
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

    public void savetoDataBase() throws SQLException {

        /**TODO Speicherung
         */
    }

    @Override
    public boolean isValid() {
        /**TODO Verifizierung gegen den Daten, Tests auf Null etc
         */

        return false;
    }

    public String[] getIdentityValues() throws IndexOutOfBoundsException {

        /** TODO Umwandlung in eine anzeigbare Form
         */

        return this.identityValues;
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
        if(arg !=null) {
            try {
                int i = Integer.parseInt(arg);
            } catch (NumberFormatException e1) {
                System.out.println("Arg is"+arg);
                throw new IllegalArgumentException();
            }
            this.interAktionValues[index]= arg;
        }else{
            this.interAktionValues[index]="";
        }

    }


    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        this.identityValues[index] = arg;
    }


}

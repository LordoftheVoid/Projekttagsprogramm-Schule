package V2.DataBaseInteractions.DataBaseObjekts;


import java.sql.SQLException;

/**
 * Created by Aaron on 02.02.2018.
 */
public abstract class DataBaseElementObject implements DataBaseElementInterFace {

    public int amountofColums;
    String id;
    private String[] identityValues;
    private String[] interAktionValues;


    public DataBaseElementObject(String id) {
        this.id = id;
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
        try {
            int i = Integer.parseInt(arg);
        } catch (NumberFormatException e1) {
            throw new IllegalArgumentException();
        }
    }


    public void setIdentityValue(String arg, int index) throws IllegalArgumentException {
        this.identityValues[index] = arg;
    }


}

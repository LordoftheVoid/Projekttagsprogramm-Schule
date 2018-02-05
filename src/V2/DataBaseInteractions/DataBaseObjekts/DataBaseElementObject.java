package V2.DataBaseInteractions.DataBaseObjekts;


import com.sun.javaws.exceptions.InvalidArgumentException;

import java.sql.SQLException;

/**
 * Created by Aaron on 02.02.2018.
 */
public class DataBaseElementObject implements DataBaseElementInterFace {

    public int amountofColums;



    public String[] values;

    String id;



    public void changeValue(String value,int colum){

        /**TODO
         * Je nach Index den Hash neu berechnen !!
         */
    }


    @Override
    public void setValue(int index, String value) throws InvalidArgumentException {


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

    @Override
    public String[] getValues() throws IndexOutOfBoundsException {

        /** TODO Umwandlung in eine anzeigbare Form
         */
        return new String[0];
    }

    @Override
    public String getHash() throws NullPointerException {

        /**Todo Ausgabe der Identifizierung
         */
        return null;
    }

    @Override
    public String updateHash() throws InvalidArgumentException {

        /**Todo Sonderfall um Werte zu updaten, da kritisch
         */
        return null;
    }

    @Override
    public void deleteEntry() {

        /**Todo Entfernt den Eintrag
         */

    }

}

package Version_1_Java.DatenBankenSchnittstellen;

import java.sql.*;

/**
 * Created by Aaron on 09.07.2017.
 */
public class cDatabaseManager {

    public Connection Datenbankverbindung;

    PreparedStatement constructionTable;

    PreparedStatement insertInto;


    PreparedStatement update_Entry;

    public void initialisierung() throws SQLException {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Datenbankverbindung = DriverManager.getConnection("jdbc:hsqldb:file:C:/Informatik/Datenbanken/Datenbanken rund um Schulprojekt/Test_Programm.db;shutdown=true", "sa", "");
        } catch (ClassNotFoundException e) {
            System.err.println("Keine Treiberklasse gefunden.");
        } catch (SQLException e) {
            e.printStackTrace();
        }




        boolean btableCreation=true;

        ResultSet existingTables= Datenbankverbindung.getMetaData().getTables(null,null, "%",null);
        while (existingTables.next()) {
            if(existingTables.getString(3).equals("SCHÜLER")){
                btableCreation=false;
            }
        }

        if(btableCreation) {
            constructionTable = Datenbankverbindung.prepareStatement("CREATE TABLE schüler (unique_id CHARACTER(4), preName VARCHAR(30), surName VARCHAR(30), grade VARCHAR,  pref1 INT, pref2 INT, pref3 INT, pref4 INT)");
            constructionTable.executeUpdate();
            Datenbankverbindung.commit();
        }else{
            System.out.println("Tabelle existiert");
        }




    }

    public void create_entry (String unique_id ) throws SQLException {

       insertInto = Datenbankverbindung.prepareStatement("INSERT INTO schüler  (unique_id) VALUES (?)");
       insertInto.setString(1,unique_id);

       insertInto.executeUpdate();

       Datenbankverbindung.commit();

    }

    public void update_entry (String old_unique_ID, String colum, String value) throws SQLException {

        update_Entry= Datenbankverbindung.prepareStatement("UPDATE schüler SET "+ colum +" = '"+value+"' WHERE unique_id= ?");

        update_Entry.setString(1,old_unique_ID);
        update_Entry.executeUpdate();


        Datenbankverbindung.commit();
    }


    public int row_count() throws SQLException {
            PreparedStatement row_count = Datenbankverbindung.prepareStatement("SELECT COUNT(*) FROM schüler");
            ResultSet number_of_rows =row_count.executeQuery();
            number_of_rows.next();
            return number_of_rows.getInt(1);
    }
    public boolean entry_check(String unique_id) throws SQLException {

        PreparedStatement id_check = Datenbankverbindung.prepareStatement("SELECT unique_id FROM schüler WHERE EXISTS(SELECT unique_id FROM schüler WHERE unique_id="+unique_id+" )");
        ResultSet entrys_with_specific_id =id_check.executeQuery();
        entrys_with_specific_id.next();
        return  entrys_with_specific_id.next();
    }

}

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
            Datenbankverbindung = DriverManager.getConnection("jdbc:hsqldb:file:C:/Informatik/Sonstige Dateien/Test_Programm.db;shutdown=true", "sa", "");
        } catch (ClassNotFoundException e) {
            System.err.println("Keine Treiberklasse gefunden.");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        boolean btableCreation=true;

        ResultSet existingTables= Datenbankverbindung.getMetaData().getTables(null,null, "%",null);
        while (existingTables.next()) {
            System.out.println(existingTables.getString(3));
            if(existingTables.getString(3).equals("SCHÜLER")){
                btableCreation=false;
            }
        }

        if(btableCreation) {
            constructionTable = Datenbankverbindung.prepareStatement("CREATE TABLE schüler (unique_id CHARACTER(4), preName VARCHAR(30), surName VARCHAR(30), preferences ARRAY)");
            constructionTable.executeUpdate();
            Datenbankverbindung.commit();
        }else{
            System.out.println("Tabelle existiert");
        }

    }

    public void create_entry (int unique_id ) throws SQLException {

       insertInto = Datenbankverbindung.prepareStatement("INSERT INTO schüler  (unique_id) VALUES (?)");
       insertInto.setInt(1,unique_id);

       insertInto.executeUpdate();

       Datenbankverbindung.commit();

    }




    public void update_entry (int old_unique_ID, int new_unique_ID, String new_pre_name, String new_sur_name, String grade) throws SQLException {


        update_Entry= Datenbankverbindung.prepareStatement("UPDATE schüler SET unique_id = ? WHERE unique_id=?");


        update_Entry.setInt(1,new_unique_ID);

        update_Entry.setInt(2,old_unique_ID);





        update_Entry.executeUpdate();

        Datenbankverbindung.commit();

    }

}

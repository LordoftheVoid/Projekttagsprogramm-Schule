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
            Datenbankverbindung= DriverManager.getConnection("jdbc:sqlite:C:/Informatik/Projekte/Projekttagprogramm Schule/Schueler_Datenbank_V1.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void create_entry (String unique_id ) throws SQLException {

       insertInto = Datenbankverbindung.prepareStatement("INSERT INTO schueler  (unique_id) VALUES (?)");
       insertInto.setString(1,unique_id);

       insertInto.executeUpdate();



    }

    public void update_entry (String old_unique_ID, String colum, String value) throws SQLException {

        update_Entry= Datenbankverbindung.prepareStatement("UPDATE schueler SET "+ colum +" = '"+value+"' WHERE unique_id= ?");

        update_Entry.setString(1,old_unique_ID);
        update_Entry.executeUpdate();


    }


    public int row_count() throws SQLException {
            PreparedStatement row_count = Datenbankverbindung.prepareStatement("SELECT COUNT(*) FROM schueler");
            ResultSet number_of_rows =row_count.executeQuery();
            number_of_rows.next();
            return number_of_rows.getInt(1);
    }

    public boolean entry_check(String unique_id) throws SQLException {

        PreparedStatement id_check = Datenbankverbindung.prepareStatement("SELECT * FROM schueler WHERE unique_id= ? ");
        id_check.setString(1, unique_id);
        ResultSet entrys_with_specific_id =id_check.executeQuery();
        return  entrys_with_specific_id.next();
    }

}

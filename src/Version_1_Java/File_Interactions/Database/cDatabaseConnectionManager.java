package Version_1_Java.File_Interactions.Database;

import Version_1_Java.cMain;
import org.sqlite.SQLiteConfig;

import java.sql.*;

/**
 * Created by Aaron on 09.07.2017.
 */




/*

Klasse, um conDatabase zu realisieren

 */
public class cDatabaseConnectionManager {

    private Connection conDatabase;


    boolean b_connection_running = false;

    public boolean v_initialization(String surlSource_tm) {

        if (surlSource_tm.equals("NO_FILE_FOUND")) {
            cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt initialisiert werden, bitte Name & " +
                    "Position kontrollieren und ggf. korrigieren \n Näheres siehe Benutzerhandbuch ");
            return false;
        } else {
            if (!b_connection_running) {
                try {
                    final String DRIVER = "org.sqlite.JDBC";
                    final String DB_URL = "jdbc:sqlite:" + surlSource_tm;
                    Class.forName(DRIVER);
                    conDatabase = null;
                    try {
                        SQLiteConfig config = new SQLiteConfig();
                        config.enforceForeignKeys(true);
                        conDatabase = DriverManager.getConnection(DB_URL, config.toProperties());
                    } catch (SQLException ex) {
                        cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt initialisiert werden, bitte Name & " +
                                "Position kontrollieren und ggf. korrigieren \n Näheres siehe Benutzerhandbuch ");
                        return false;
                    }
                    cMain.v_update_Textarea_Status(" \n Die Verbindung zur Datenbank steht korrekt \n ");
                    b_connection_running = true;
                    return true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                cMain.v_update_Textarea_Status(" Die Datenbankanbindung läuft ordnungsgemäß");
                return true;
            }
        }
    }


    public ResultSet read_entrys_one_attribute(String sTable_tm, String sColum_tm) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT " + sColum_tm + " FROM " + sTable_tm);
        return extract_entrys.executeQuery();

    }


    public ResultSet read_entrys_all_attributes(String sTable_tm) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT * FROM " + sTable_tm);
        return extract_entrys.executeQuery();

    }

    public ResultSet read_one_entry_one_attribute(String sTable_tm, String sColum_tm, String unique_id) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT " + sColum_tm + " FROM " + sTable_tm + " WHERE s_unique_ID = ?");
        extract_entrys.setString(1, unique_id);
        return extract_entrys.executeQuery();

    }


    public boolean create_entry(String sTable_tm, String unique_id) throws SQLException {

        try {
            PreparedStatement insertInto = conDatabase.prepareStatement("INSERT INTO " + sTable_tm + "  (s_unique_ID) VALUES (?)");
            insertInto.setString(1, unique_id);
            insertInto.executeUpdate();
        } catch (SQLException e_1) {
            return false;
        }
        return true;
    }


    public boolean update_entry(String s_table_tm, String s_unique_ID_tm, String s_colum_tm, String s_value_tm) {
        try {
            PreparedStatement update_Entry = conDatabase.prepareStatement("UPDATE " + s_table_tm + " SET " + s_colum_tm + " = '" + s_value_tm + "' WHERE s_unique_ID= ?");
            update_Entry.setString(1, s_unique_ID_tm);
            update_Entry.executeUpdate();
        } catch (SQLException e_1) {
            return e_1.getErrorCode() != 19;
        }
        return true;
    }


    public boolean delete_entry(String sTable_tm, String unique_ID) {
        boolean b_entry_existed;
        try {
            b_entry_existed = this.entry_check(sTable_tm, unique_ID);
            PreparedStatement delete_Entry = conDatabase.prepareStatement("DELETE FROM " + sTable_tm + " WHERE s_unique_ID= ?");
            delete_Entry.setString(1, unique_ID);
            delete_Entry.executeUpdate();
        } catch (SQLException e_1) {
            return false;
        }
        return b_entry_existed;
    }


    public int i_amout_of_entrys_in_Database(String sTable_tm) throws SQLException {
        if (this.b_connection_running) {
            PreparedStatement row_count = conDatabase.prepareStatement("SELECT COUNT(*) FROM " + sTable_tm);
            ResultSet number_of_rows = row_count.executeQuery();
            number_of_rows.next();
            return number_of_rows.getInt(1);
        } else {
            cMain.v_update_Textarea_Status("Es konnten keine Werte ausgelesen werden");
            return 0;
        }

    }

    public boolean entry_check(String sTable_tm, String unique_id) throws SQLException {
        PreparedStatement id_check = conDatabase.prepareStatement("SELECT * FROM " + sTable_tm + " WHERE s_unique_ID= ? ");
        id_check.setString(1, unique_id);
        ResultSet entrys_with_specific_id = id_check.executeQuery();
        return !entrys_with_specific_id.next();
    }

}

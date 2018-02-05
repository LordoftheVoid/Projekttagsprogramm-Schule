package V2.DataBaseInteractions;

import V2.cMain;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Aaron on 09.07.2017.
 */

/*
Klasse, um conDatabase zu realisieren
 */
public class DatabaseInterface {

    boolean b_connection_running = false;
    private Connection conDatabase;

    public void v_initialization(String surlSource_tm) throws NullPointerException {
        String[] fehlerArr = new String[1];
        fehlerArr[0] = "FEHLER \n Die Datenbank konnte nicht korrekt initialisiert werden, bitte Name & Position kontrollieren und ggf. korrigieren";
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
                    throw new NullPointerException();
                }
                b_connection_running = true;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public ResultSet readEntrysOneAttribut(String sTable_tm, String sColum_tm) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT " + sColum_tm + " FROM " + sTable_tm);
        return extract_entrys.executeQuery();

    }


    public ResultSet readEntrysAllAttributes(String sTable_tm) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT * FROM " + sTable_tm);
        return extract_entrys.executeQuery();

    }


    public ResultSet readEntryallAttributes(String sTable_tm, String unique_id) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT * FROM " + sTable_tm + " WHERE s_unique_ID = ?");
        extract_entrys.setString(1, unique_id);
        return extract_entrys.executeQuery();
    }

    public ResultSet readOneEntryOneAtribute(String sTable_tm, String sColum_tm, String unique_id) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT " + sColum_tm + " FROM " + sTable_tm + " WHERE s_unique_ID = ?");
        extract_entrys.setString(1, unique_id);
        return extract_entrys.executeQuery();

    }


    public void createEntry(String sTable_tm, String unique_id) throws SQLException {
        PreparedStatement insertInto = conDatabase.prepareStatement("INSERT INTO " + sTable_tm + "  (s_unique_ID) VALUES (?)");
        insertInto.setString(1, unique_id);
        insertInto.executeUpdate();
    }


    public void updateEntry(String s_table_tm, String s_unique_ID_tm, String s_colum_tm, String s_value_tm) throws SQLException {
        PreparedStatement update_Entry = conDatabase.prepareStatement("UPDATE " + s_table_tm + " SET " + s_colum_tm + " = '" + s_value_tm + "' WHERE s_unique_ID= ?");
        update_Entry.setString(1, s_unique_ID_tm);
        update_Entry.executeUpdate();
    }


    public void deleteEntry(String sTable_tm, String unique_ID) throws SQLException {
        PreparedStatement delete_Entry = conDatabase.prepareStatement("DELETE FROM " + sTable_tm + " WHERE s_unique_ID= ?");
        delete_Entry.setString(1, unique_ID);
        delete_Entry.executeUpdate();
    }


    public int amout_of_entrys_in_Database(String sTable_tm) throws SQLException {
        if (this.b_connection_running) {
            PreparedStatement row_count = conDatabase.prepareStatement("SELECT COUNT(*) FROM " + sTable_tm);
            ResultSet number_of_rows = row_count.executeQuery();
            number_of_rows.next();
            return number_of_rows.getInt(1);
        } else {
            cMain.updateStatus("Es konnten keine Werte ausgelesen werden");
            return 0;
        }

    }

    public boolean entry_check(String sTable_tm, String unique_id) throws SQLException {
        PreparedStatement id_check = conDatabase.prepareStatement("SELECT * FROM " + sTable_tm + " WHERE s_unique_ID= ? ");
        id_check.setString(1, unique_id);
        ResultSet entrys_with_specific_id = id_check.executeQuery();
        return !entrys_with_specific_id.next();
    }

    public ArrayList<String []> getValues( String tableReference) throws  SQLException{

        ArrayList<String []> returnValue = new ArrayList<>();

        ResultSet content = this.readEntrysAllAttributes(tableReference);

        ResultSetMetaData rsmd=content.getMetaData();


        /*
        System.out.println("columns: "+rsmd.getColumnCount());
        System.out.println("Column Name of 1st column: "+rsmd.getColumnName(2));
        System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(2));
            */

        while(content.next()){
            String[] currentArr = new String[rsmd.getColumnCount()];
            for (int i = 2; i < currentArr.length+1; i++) {
                currentArr[i-2]= content.getString(i);
            }
            returnValue.add(currentArr);
        }
        return  returnValue;
    }

}

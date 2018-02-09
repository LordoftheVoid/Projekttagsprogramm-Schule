package V2.DataBaseInteractions;

import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.HashMap;

/**
 * Created by Aaron on 09.07.2017.
 */

/*
Klasse, um conDatabase zu realisieren
 */
public class DatabaseInterface {

    boolean b_connection_running = false;
    private Connection conDatabase;
    private HashMap<Integer, String > pupilColums = new HashMap<>();
    private HashMap<Integer, String > projectColums = new HashMap<>();
    private HashMap<Integer, String> linkColums = new HashMap<>();
    private HashMap<String,HashMap<Integer,String>> tableColums = new HashMap<>();


    public void v_initialization(String surlSource_tm) throws NullPointerException {

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


                /**These  names have to be hardcoded at the moment
                 *
                 */
                this.tableColums.put("Pupil",pupilColums);
                this.tableColums.put("Project",projectColums);


                try {
                    ResultSet readColums = this.readEntrysAllAttributes("persons");
                    ResultSetMetaData metaData = readColums.getMetaData();
                    for (int i = 1; i < metaData.getColumnCount(); i++) {
                        pupilColums.put(i,metaData.getColumnName(i));
                    }
                    readColums = this.readEntrysAllAttributes("projects");
                    metaData = readColums.getMetaData();
                    for (int i = 1; i < metaData.getColumnCount(); i++) {
                        pupilColums.put(i,metaData.getColumnName(i));
                    }


                    /**TODO Read the Link-Table
                     *
                     *
                     */
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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


    public boolean entryExists(String sTable_tm, String unique_id) throws SQLException {
        PreparedStatement id_check = conDatabase.prepareStatement("SELECT * FROM " + sTable_tm + " WHERE s_unique_ID= ? ");
        id_check.setString(1, unique_id);
        ResultSet entrys_with_specific_id = id_check.executeQuery();
        return !entrys_with_specific_id.next();


    }

    public void deleteEntry(String sTable_tm, String unique_ID) throws SQLException {
        PreparedStatement delete_Entry = conDatabase.prepareStatement("DELETE FROM " + sTable_tm + " WHERE s_unique_ID= ?");
        delete_Entry.setString(1, unique_ID);
        delete_Entry.executeUpdate();
    }

    public String getValuefromDataBase ( String table, String entryID, int index){
        String result = "";
        try {
            ResultSet data = this.readOneEntryOneAtribute(table,tableColums.get(table).get(index),entryID);
            result = data.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public String [] getEntryValuesfromDataBase(String table, String entryID){
        String [] results = new String[tableColums.get(table).size()];

        try {
            ResultSet entry = this.readEntryallAttributes(table,entryID);
            for (int i = 0; i < tableColums.get(table).size(); i++) {
                results[i] = entry.getString(1+1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }





    private ResultSet readEntrysOneAttribut(String sTable_tm, String sColum_tm) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT " + sColum_tm + " FROM " + sTable_tm);
        return extract_entrys.executeQuery();

    }


    private ResultSet readEntrysAllAttributes(String sTable_tm) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT * FROM " + sTable_tm);
        return extract_entrys.executeQuery();

    }


    private ResultSet readEntryallAttributes(String sTable_tm, String unique_id) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT * FROM " + sTable_tm + " WHERE s_unique_ID = ?");
        extract_entrys.setString(1, unique_id);
        return extract_entrys.executeQuery();
    }


    private ResultSet readOneEntryOneAtribute(String sTable_tm, String sColum_tm, String unique_id) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT " + sColum_tm + " FROM " + sTable_tm + " WHERE s_unique_ID = ?");
        extract_entrys.setString(1, unique_id);
        return extract_entrys.executeQuery();

    }

}

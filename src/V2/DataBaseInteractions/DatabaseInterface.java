package V2.DataBaseInteractions;

import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
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
    private HashMap<Integer, String> pupilColums = new HashMap<>();
    private HashMap<Integer, String> projectColums = new HashMap<>();
    private HashMap<Integer, String> linkColums = new HashMap<>();
    private HashMap<String, HashMap<Integer, String>> tableColums = new HashMap<>();
    private HashMap<String, String> idColums = new HashMap<>();


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
                this.tableColums.put("Pupil", pupilColums);
                this.tableColums.put("Project", projectColums);


                try {
                    ResultSet readColums = this.readEntrysAllAttributes("Pupil");
                    ResultSetMetaData metaData = readColums.getMetaData();
                    idColums.put("Pupil", metaData.getColumnName(1));
                    for (int i = 1; i < metaData.getColumnCount(); i++) {
                        pupilColums.put(i, metaData.getColumnName(i));
                    }
                    readColums = this.readEntrysAllAttributes("Project");
                    metaData = readColums.getMetaData();
                    idColums.put("Project", metaData.getColumnName(1));
                    for (int i = 1; i < metaData.getColumnCount(); i++) {
                        projectColums.put(i, metaData.getColumnName(i));
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


    public void createEntry(String tableReference, String newEntryID) throws SQLException {
        System.out.println(this.tableColums.get(tableReference).get(1));
        PreparedStatement insertInto = conDatabase.prepareStatement("INSERT INTO " + tableReference + "  (" + this.tableColums.get(tableReference).get(1) + ") VALUES (?)");
        insertInto.setString(1, newEntryID);
        insertInto.executeUpdate();
    }

    public void updateEntry(String tableReference, String entryID, int columIndex, String newValue) throws SQLException {


        String sqlStatement = "UPDATE " + tableReference + " SET ";

        sqlStatement = sqlStatement  + this.tableColums.get(pupilColums).get(columIndex);

        sqlStatement = sqlStatement + " = " + newValue + " WHERE " + idColums.get(tableReference) + "= ?";

        PreparedStatement update_Entry = conDatabase.prepareStatement(sqlStatement);
        update_Entry.setString(1, entryID);
        update_Entry.executeUpdate();
    }

    public boolean entryExists(String tableReference, String unique_id) throws SQLException {
        PreparedStatement id_check = conDatabase.prepareStatement("SELECT * FROM " + tableReference + " WHERE " + idColums.get(tableReference) + " ? ");
        id_check.setString(1, unique_id);
        ResultSet entrys_with_specific_id = id_check.executeQuery();
        return !entrys_with_specific_id.next();


    }

    public void deleteEntry(String tableReference, String unique_ID) throws SQLException {
        PreparedStatement delete_Entry = conDatabase.prepareStatement("DELETE FROM " + tableReference + " WHERE " + idColums.get(tableReference) + "= ?");
        delete_Entry.setString(1, unique_ID);
        delete_Entry.executeUpdate();
    }

    public String getValuefromDataBase(String table, String entryID, int index) {
        String result = "";
        try {
            ResultSet data = this.readOneEntryOneAtribute(table, tableColums.get(table).get(index), entryID);
            result = data.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public String[] getallEntryValuesfromDataBase(String table, String entryID) {

        String[] results = new String[tableColums.get(table).size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = "";
        }
        try {
            ResultSet entry = this.readEntryallAttributes(table, entryID);

            while (entry.next()) {
                for (int j = 0; j < tableColums.get(table).size(); j++) {
                    results[j] = entry.getString(j + 1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


    public ArrayList<String> getEntryIDs(String table) {
        ArrayList<String> entryList = new ArrayList<>();
        try {
            ResultSet entrys = this.readEntrysOneAttribut(table, this.tableColums.get(table).get(1));

            while (entrys.next()) {
                entryList.add(entrys.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entryList;
    }


    private ResultSet readEntrysOneAttribut(String tableReference, String colum) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT " + colum + " FROM " + tableReference);
        return extract_entrys.executeQuery();

    }


    private ResultSet readEntrysAllAttributes(String tableReference) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT * FROM " + tableReference);
        return extract_entrys.executeQuery();

    }


    private ResultSet readEntryallAttributes(String tableReference, String entryID) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT * FROM " + tableReference + " WHERE " + idColums.get(tableReference) + " = ?");
        extract_entrys.setString(1, entryID);
        return extract_entrys.executeQuery();
    }


    private ResultSet readOneEntryOneAtribute(String tableReference, String colum, String entryID) throws SQLException {
        PreparedStatement extract_entrys = conDatabase.prepareStatement("SELECT " + colum + " FROM " + tableReference + " WHERE " + idColums.get(tableReference) + " = ?");
        extract_entrys.setString(1, entryID);
        return extract_entrys.executeQuery();

    }

}
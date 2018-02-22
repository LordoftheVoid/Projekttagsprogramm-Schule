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

    private Connection conDatabase;
    private HashMap<Integer, String> pupilColums = new HashMap<>();
    private HashMap<Integer, String> projectColums = new HashMap<>();
    private HashMap<Integer, String> linkColums = new HashMap<>();
    private HashMap<String, HashMap<Integer, String>> tableColums = new HashMap<>();
    private HashMap<String, String> idColums = new HashMap<>();


    public DatabaseInterface(String urlDataBaseFile) throws ClassNotFoundException {

        final String DRIVER = "org.sqlite.JDBC";
        final String DB_URL = "jdbc:sqlite:" + urlDataBaseFile;
        Class.forName(DRIVER);
        conDatabase = null;
        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            conDatabase = DriverManager.getConnection(DB_URL, config.toProperties());
        } catch (SQLException ex) {
            throw new NullPointerException();
        }

        try {
            pupilColums = generateColumNameList("Pupil");
            projectColums = generateColumNameList("Project");
            linkColums = generateColumNameList("Link");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.tableColums.put("Pupil", pupilColums);
        this.tableColums.put("Project", projectColums);

    }

    private HashMap<Integer, String> generateColumNameList(String tableReference) throws SQLException {
        HashMap<Integer, String> result = new HashMap<>();
        ResultSet readColums = this.readEntrysAllAttributes(tableReference);
        ResultSetMetaData metaData = readColums.getMetaData();
        idColums.put(tableReference, metaData.getColumnName(1));
        for (int keyIndices = 0; keyIndices < metaData.getColumnCount() - 1; keyIndices++) {
            result.put(keyIndices, metaData.getColumnName(keyIndices + 2));
        }
        return result;
    }


    public void releaseDataBaseConnection() {
        try {
            this.conDatabase.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void createEntry(String tableReference, String newEntryID) throws SQLException {
        PreparedStatement insertInto = conDatabase.prepareStatement("INSERT INTO " + tableReference + "  (" + this.idColums.get(tableReference) + ") VALUES (?)");
        insertInto.setString(1, newEntryID);
        insertInto.executeUpdate();
    }

    public void updateNonIDValues(String tableReference, String entryID, int columIndex, String newValue) throws SQLException {
        String sqlString = "UPDATE " + tableReference + " SET " + this.tableColums.get(tableReference).get(columIndex) + " = '" + newValue + "' WHERE " + idColums.get(tableReference) + "= ?";
        PreparedStatement update_Entry = conDatabase.prepareStatement(sqlString);
        update_Entry.setString(1, entryID);
        update_Entry.executeUpdate();
    }

    public void updateIDValue(String tableReference, String entryID, String newValue) throws SQLException {
        String sqlString = "UPDATE " + tableReference + " SET " + this.idColums.get(tableReference) + " = '" + newValue + "' WHERE " + idColums.get(tableReference) + "= ?";
        PreparedStatement update_Entry = conDatabase.prepareStatement(sqlString);
        update_Entry.setString(1, entryID);
        update_Entry.executeUpdate();
    }


    public boolean entryExists(String tableReference, String entryID) {
        try {
            String queryCheck = "SELECT *  from " + tableReference + " WHERE " + idColums.get(tableReference) + " = ?";
            PreparedStatement ps = conDatabase.prepareStatement(queryCheck);
            ps.setString(1, entryID);
            final ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void deleteEntry(String tableReference, String unique_ID) throws SQLException {
        PreparedStatement delete_Entry = conDatabase.prepareStatement("DELETE FROM " + tableReference + " WHERE " + idColums.get(tableReference) + "= ?");
        delete_Entry.setString(1, unique_ID);
        delete_Entry.executeUpdate();
    }

    public String getValuefromDataBase(String table, String entryID, int index) {
        String result = "";
        try {
            ResultSet values = this.readOneEntryOneAtribute(table, tableColums.get(table).get(index), entryID);
            while (values.next()) {
                result = values.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public String[] getallEntryValuesfromDataBase(String table, String entryID) {

        String[] results = new String[tableColums.get(table).size()];
        for (int arrayIndex = 0; arrayIndex < results.length; arrayIndex++) {
            results[arrayIndex] = "";
        }
        try {
            ResultSet entry = this.readEntryallAttributes(table, entryID);
            while (entry.next()) {
                for (int arrayIndex = 0; arrayIndex < tableColums.get(table).size(); arrayIndex++) {
                    results[arrayIndex] = entry.getString(arrayIndex + 1);
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
            ResultSet entrys = this.readEntrysOneAttribut(table, this.tableColums.get(table).get(0));

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

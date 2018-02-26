package V2.FileInteractions.Generators;

/**
 * Created by Aaron on 26.02.2018.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseGenerator {


    Connection connection;

    public void createNewDatabase(String baseUrl, String fileName) {

        String url = "jdbc:sqlite:" + baseUrl + fileName;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUpTable(String name) throws SQLException {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + name + " (	uniqueID Char PRIMARY KEY );";

        Statement stmt = connection.createStatement();
        stmt.execute(sql);

    }

    public void addColum(String table, String columName) throws SQLException {
        String sqlAlter = " ALTER TABLE " + table + " ADD COLUMN " + columName + " Char;";

        Statement stmt = connection.createStatement();
        stmt.execute(sqlAlter);

    }

}











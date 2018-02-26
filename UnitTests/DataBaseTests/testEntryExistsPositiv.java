package DataBaseTests;

import V2.FileInteractions.Readers.DatabaseInterface;
import org.junit.jupiter.api.Assertions;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.02.2018.
 */
class testEntryExistsPositiv {

    DatabaseInterface dataBaseConnection;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\Dateiumgebungen\\TestUmgebungen\\DataBaseNormValues.db";
        try {
            dataBaseConnection = new DatabaseInterface(dataBaseUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            dataBaseConnection.createEntry("Pupil", "TestID");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        try {
            dataBaseConnection.deleteEntry("Pupil", "TestID");
            dataBaseConnection.releaseDataBaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void entryExists() {
        Assertions.assertTrue(dataBaseConnection.entryExists("Pupil", "TestID"));
    }

}
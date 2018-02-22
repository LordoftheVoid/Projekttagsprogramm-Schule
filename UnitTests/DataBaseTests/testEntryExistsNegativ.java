package DataBaseTests;

import V2.DataBaseInteractions.DatabaseInterface;
import org.junit.jupiter.api.Assertions;

/**
 * Created by Aaron on 22.02.2018.
 */
class testEntryExistsNegativ {

    DatabaseInterface dataBaseConnection;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\Dateiumgebungen\\TestUmgebungen\\DataBaseNormValues.db";
        try {
            dataBaseConnection = new DatabaseInterface(dataBaseUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        dataBaseConnection.releaseDataBaseConnection();
    }

    @org.junit.jupiter.api.Test
    void entryExists() {
        Assertions.assertFalse(dataBaseConnection.entryExists("Pupil", "TestID"));
    }
}
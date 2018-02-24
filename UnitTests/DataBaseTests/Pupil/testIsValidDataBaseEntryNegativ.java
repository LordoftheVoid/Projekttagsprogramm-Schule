package DataBaseTests.Pupil;

import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.DataBaseInteractions.DatabaseInterface;
import V2.Settings.Imports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * Created by Aaron on 24.02.2018.
 */
class testIsValidDataBaseEntryNegativ {

    Pupil testObj;

    @BeforeEach
    void setUp() {

        String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\Dateiumgebungen\\TestUmgebungen\\DataBaseNormValues.db";
        try {
            Imports.objDatabaseManagerGlobal= new DatabaseInterface(dataBaseUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        testObj = new Pupil("TESTID");

    }

    @AfterEach
    void tearDown() {

        try {
            Imports.objDatabaseManagerGlobal.deleteEntry("Pupil","TESTID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Imports.objDatabaseManagerGlobal.releaseDataBaseConnection();
    }

    @Test
    void isValidDataBaseEntry() {
        Assertions.assertFalse(testObj.isValidDataBaseEntry());
    }

}
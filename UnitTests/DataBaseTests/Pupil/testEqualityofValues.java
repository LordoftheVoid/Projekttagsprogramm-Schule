package DataBaseTests.Pupil;

import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.FileInteractions.Readers.DatabaseInterface;
import V2.cMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.02.2018.
 */
class testEqualityofValues {

    Pupil testPupil;

    @BeforeEach
    void setUp() {
        String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\Dateiumgebungen\\TestUmgebungen\\DataBaseNormValues.db";
        try {
            cMain.objDatabaseManagerGlobal = new DatabaseInterface(dataBaseUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        testPupil = new Pupil("-1000");
        testPupil.setDisplayayableValue(0, "TestValue0");
        testPupil.setDisplayayableValue(1, "TestValue1");
        testPupil.setDisplayayableValue(2, "TestValue2");
    }

    @AfterEach
    void tearDown() {
        try {
            cMain.objDatabaseManagerGlobal.deleteEntry("Pupil", testPupil.getHash());
            cMain.objDatabaseManagerGlobal.releaseDataBaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getDisplayableValue() {
        Assertions.assertEquals(testPupil.getDisplayableValue(0), "TestValue0");
        Assertions.assertEquals(testPupil.getDisplayableValue(1), "TestValue1");
        Assertions.assertEquals(testPupil.getDisplayableValue(2), "TestValue2");
    }

}
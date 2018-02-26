package DataBaseTests.Project;

import V2.DataBaseInteractions.DataBaseObjekts.Project;
import V2.FileInteractions.Readers.DatabaseInterface;
import V2.Settings.Imports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.02.2018.
 */
class testEqualityofValues {

    Project testProject;

    @BeforeEach
    void setUp() {
        String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\Dateiumgebungen\\TestUmgebungen\\DataBaseNormValues.db";
        try {
            Imports.objDatabaseManagerGlobal = new DatabaseInterface(dataBaseUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        testProject = new Project("-1000");
        testProject.setDisplayayableValue(0, "TestValue0");
        testProject.setDisplayayableValue(1, "TestValue1");
    }

    @AfterEach
    void tearDown() {
        try {
            Imports.objDatabaseManagerGlobal.deleteEntry("Project", "-1000");
            Imports.objDatabaseManagerGlobal.releaseDataBaseConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getDisplayableValue() {
        Assertions.assertEquals(testProject.getDisplayableValue(0), "TestValue0");
        Assertions.assertEquals(testProject.getDisplayableValue(1), "TestValue1");
    }

}
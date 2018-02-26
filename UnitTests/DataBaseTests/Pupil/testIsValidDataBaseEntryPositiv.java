package DataBaseTests.Pupil;

import V2.DataBaseInteractions.DataBaseObjekts.Project;
import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.FileInteractions.Readers.DatabaseInterface;
import V2.Settings.Imports;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * Created by Aaron on 24.02.2018.
 */
class testIsValidDataBaseEntryPositiv {

    Pupil testObj;

    String[] projectIds = new String[4];


    @BeforeEach
    void setUp() {

        projectIds[0] = "22";
        projectIds[1] = "23";
        projectIds[2] = "24";
        projectIds[3] = "25";

        String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\Dateiumgebungen\\TestUmgebungen\\DataBaseNormValues.db";
        try {
            Imports.objDatabaseManagerGlobal = new DatabaseInterface(dataBaseUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        testObj = new Pupil("-123");

        for (int arrayIndex = 0; arrayIndex < projectIds.length; arrayIndex++) {
            Project newEntry = new Project(projectIds[arrayIndex]);
        }

        for (int valueIndex = 0; valueIndex < testObj.getamountofDisplayableValues(); valueIndex++) {
            testObj.setDisplayayableValue(valueIndex, "VALUE");
        }

        for (int valueIndex = 3; valueIndex < testObj.getamountofDisplayableValues(); valueIndex++) {
            testObj.setDisplayayableValue(valueIndex, projectIds[valueIndex - 3]);
        }


    }

    @AfterEach
    void tearDown() {

        try {
            Imports.objDatabaseManagerGlobal.deleteEntry("Pupil", testObj.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int arrayIndex = 0; arrayIndex < projectIds.length; arrayIndex++) {
            try {
                Imports.objDatabaseManagerGlobal.deleteEntry("Project", projectIds[arrayIndex]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Imports.objDatabaseManagerGlobal.releaseDataBaseConnection();

    }

    @Test
    void isValidDataBaseEntryPositiv() {
        Assertions.assertTrue(testObj.isValidDataBaseEntry());
    }

}
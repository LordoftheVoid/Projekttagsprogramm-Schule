package DataBaseTests.Pupil;

import V2.DataBaseInternalClasses.Project;
import V2.DataBaseInternalClasses.Pupil;
import V2.FileInteractions.Readers.DatabaseInterface;
import V2.cMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

/**
 * Created by Aaron on 24.02.2018.
 */
class testIsValidDataBaseEntryPositiv {

    private Pupil testObj;

    private String[] projectIds = new String[4];


    @BeforeEach
    void setUp() {

        projectIds[0] = "22";
        projectIds[1] = "23";
        projectIds[2] = "24";
        projectIds[3] = "25";

        String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\Data\\TestSpaces\\TestValues\\DataBaseNormValues.db";
        try {
            cMain.objDatabaseManagerGlobal = new DatabaseInterface(dataBaseUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        testObj = new Pupil("-123");

        for (String projectId : projectIds) {
            Project newEntry = new Project(projectId);
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
            cMain.objDatabaseManagerGlobal.deleteEntry("Pupil", testObj.getHash());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (String projectId : projectIds) {
            try {
                cMain.objDatabaseManagerGlobal.deleteEntry("Project", projectId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        cMain.objDatabaseManagerGlobal.releaseDataBaseConnection();

    }

    @Test
    void isValidDataBaseEntryPositiv() {
        Assertions.assertTrue(testObj.isValidDataBaseEntry());
    }

}
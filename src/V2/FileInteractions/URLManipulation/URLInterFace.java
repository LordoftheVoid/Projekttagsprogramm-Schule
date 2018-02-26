package V2.FileInteractions.URLManipulation;

import V2.FileInteractions.Generators.DataBaseGenerator;
import V2.cMain;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 26.02.2018.
 */
public class URLInterFace {


    public static String baseURL;
    public static boolean interAktionwithExcel;

    public URLInterFace(String[] systemWideArgs) {

        if (systemWideArgs.length == 2) {
            baseURL = systemWideArgs[0];
            interAktionwithExcel = Boolean.parseBoolean(systemWideArgs[1]);

        } else {
            try {
                File jarFile = new File(cMain.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
                baseURL = jarFile.getParent();
                interAktionwithExcel = true;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }


    }


    public void setupDirectories(String[] nameArgs) {
        for (int arrayIndex = 0; arrayIndex < nameArgs.length; arrayIndex++) {
            File dir = new File(baseURL + nameArgs[arrayIndex]);
            if (!dir.exists()) {
                dir.mkdir();
                cMain.updateStatus("Die Ordner wurden angelegt, bitte mit Datein füllen");
            } else {
                cMain.updateStatus("Die Ordner existierten bereits, Daten werden gelesen");
            }
        }

    }


    public String setupDataBaseURL() {
        CopyOnWriteArrayList<String> results = searchFilesRecursivly(baseURL, ".db");


        if (results.size() == 1) {
            return results.get(0);
        } else {

            DataBaseGenerator newDataBaseGenerator = new DataBaseGenerator();

            String dataBaseName = "Standard-Schüler-Projekt-DatenBank.db";

            String databaseURL = baseURL + "\\Datenbank-Ordner\\";
            newDataBaseGenerator.createNewDatabase(databaseURL, dataBaseName);


            try {
                newDataBaseGenerator.setUpTable("Link");
                newDataBaseGenerator.setUpTable("Pupil");
                newDataBaseGenerator.setUpTable("Project");
                newDataBaseGenerator.addColum("Link", "surNamePupil");
                newDataBaseGenerator.addColum("Link", "preNamePupil");
                newDataBaseGenerator.addColum("Link", "gradePupil");
                newDataBaseGenerator.addColum("Link", "choosenProjectID");
                newDataBaseGenerator.addColum("Link", "choosenPreferenceNumber");


                newDataBaseGenerator.addColum("Pupil", "surNamePupil");
                newDataBaseGenerator.addColum("Pupil", "preNamePupil");
                newDataBaseGenerator.addColum("Pupil", "grade");
                newDataBaseGenerator.addColum("Pupil", "preferenceZero");
                newDataBaseGenerator.addColum("Pupil", "preferenceOne");
                newDataBaseGenerator.addColum("Pupil", "preferenceTwo");
                newDataBaseGenerator.addColum("Pupil", "preferenceThree");


                newDataBaseGenerator.addColum("Project", "visibleID");
                newDataBaseGenerator.addColum("Project", "teacherID");
                newDataBaseGenerator.addColum("Project", "maxPupil");


            } catch (SQLException e) {
                e.printStackTrace();
            }


            return databaseURL + dataBaseName;
        }


    }


    public String getURLoutputDirectory(){
        return baseURL+ "\\Ergebnis-Ordner";
    }


    private CopyOnWriteArrayList<String> searchFilesRecursivly(String baseDirectory, String fileExtensionArg) {

        File initialFile = new File(String.valueOf(baseDirectory));

        File[] filesInDirectory = initialFile.listFiles();

        CopyOnWriteArrayList<String> filesFound = new CopyOnWriteArrayList<>();

        if (filesInDirectory != null) {
            for (File fil : filesInDirectory) {
                if (fil.isDirectory()) {
                    filesFound.addAll(searchFilesRecursivly(fil.getAbsolutePath(), fileExtensionArg));
                } else {
                    filesFound.add(fil.getAbsolutePath());
                }
            }
        }

        for (String loop_object : filesFound
                ) {
            if (!loop_object.contains(fileExtensionArg)) {
                filesFound.remove(loop_object);
            }
        }

        return filesFound;
    }


    public ArrayList<String> listExelURL() {
        CopyOnWriteArrayList<String> list;

        list = searchFilesRecursivly(baseURL, ".xls");

        ArrayList<String> results = new ArrayList<>();
        results.addAll(list);

        return results;

    }


}

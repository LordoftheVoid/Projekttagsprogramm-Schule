package V2.Settings;

import V2.DataBaseInteractions.DatabaseInterface;
import V2.cMain;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by Aaron on 22.01.2018.
 */
public class Imports {

    public static DatabaseInterface objDatabaseManagerGlobal;

    public static File fileJAR;


    public static void setupImport() throws NullPointerException {

        try {
            fileJAR = new File(cMain.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String dataBaseUrl = s_generate_Database_URL(fileJAR.getParent());

        try {
            objDatabaseManagerGlobal = new DatabaseInterface(dataBaseUrl);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    static String s_generate_Database_URL(String sparentURL) throws NullPointerException {

        String sDynamikDatabaseURL = sparentURL + "/" + "Datenbank-Ordner";
        File fileDatabaseDirectory = new File(sDynamikDatabaseURL);
        boolean bFileFound = false;
        for (File loop_obj_File : fileDatabaseDirectory.listFiles()) {
            if (loop_obj_File.getName().contains(".db")) {
                sDynamikDatabaseURL = sDynamikDatabaseURL + "/" + loop_obj_File.getName();
                bFileFound = true;
                break;
            }
        }

        if (bFileFound) {
            return sDynamikDatabaseURL;
        } else {
            throw new NullPointerException(null);
        }
    }
}

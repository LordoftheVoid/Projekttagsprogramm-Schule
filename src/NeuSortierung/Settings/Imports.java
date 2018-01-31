package NeuSortierung.Settings;

import NeuSortierung.DataBaseInteractions.DatabaseInterface;
import NeuSortierung.cMain;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */
public class Imports {

    public static final int iMaximalanzahl_Projekte = 4;
    public static ArrayList<Integer> projektNummernGlobal = new ArrayList<>();
    public static DatabaseInterface objDatabaseManagerGlobal;


    public static File fileJAR;


    public static void setupImport() throws NullPointerException {

        try {
            fileJAR = new File(cMain.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        objDatabaseManagerGlobal = new DatabaseInterface();
        objDatabaseManagerGlobal.v_initialization(s_generate_Database_URL(fileJAR.getParent()));
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

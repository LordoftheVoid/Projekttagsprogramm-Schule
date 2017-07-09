package Version_1_Java.DateiSchnittstellen;

import Version_1_Java.cMain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;

/**
 * Created by Aaron on 13.04.2017.
 */
public class cSpeicherDateiErzeugerSchueler {


    public cSpeicherDateiErzeugerSchueler(String sArg)  {

        String dateiName = "C:/Informatik/Projekte/Projekttagprogramm Schule/Input_Schueler.txt";
        Formatter form=null;
        try {
            form = new Formatter(dateiName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        FileOutputStream schreibeStrom =
                null;
        try {
            schreibeStrom = new FileOutputStream(dateiName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < sArg.length(); i++) {
            try {
                schreibeStrom.write((byte) sArg.charAt(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            schreibeStrom.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cMain.Ausgabe.setText("Es wurden die Schuelerdateien gelesen");
    }


}
















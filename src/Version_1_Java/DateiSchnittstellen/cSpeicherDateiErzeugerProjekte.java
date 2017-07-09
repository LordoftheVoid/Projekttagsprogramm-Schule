package Version_1_Java.DateiSchnittstellen;

import Version_1_Java.cMain;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Aaron on 15.04.2017.
 */
public class cSpeicherDateiErzeugerProjekte {


    public cSpeicherDateiErzeugerProjekte(String sArg) {


        String dateiName = "C:/Informatik/Projekte/Projekttagprogramm Schule/Input_Projekte.txt";
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
        cMain.Ausgabe.setText("Es wurden die Projektdateien gelesen");
    }










}

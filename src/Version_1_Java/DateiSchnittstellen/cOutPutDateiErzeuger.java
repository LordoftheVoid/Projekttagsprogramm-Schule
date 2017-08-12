package Version_1_Java.DateiSchnittstellen;

import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.c_Hashmap_pupils_to_List_extended;
import Version_1_Java.Objekte.cProjekt;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;

/**
 * Created by Aaron on 13.04.2017.
 */
public class cOutPutDateiErzeuger  {


    c_Hashmap_pupils_to_List_extended objSpeicher;



    public cOutPutDateiErzeuger(c_Hashmap_pupils_to_List_extended argMap) {

        this.objSpeicher=argMap;

    }



    public void DateiSchreiben(String sOutput, String sDateiName){

        String dateiName = "C:/Informatik/Projekte/Projekttagprogramm Schule/Input_Projekte.txt";
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
        for (int i = 0; i < sOutput.length(); i++) {
            try {
                schreibeStrom.write((byte) sOutput.charAt(i));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            schreibeStrom.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



        public void ExcelDateienschreiben() {


            for (cProjekt loop_objekt : objSpeicher.keySet()
                    ) {

                String outputFilename = "Projekt von " + loop_objekt.sLehrerkuerzel;

                File file = new File("C:/Informatik/" + outputFilename + ".xls");

                HSSFWorkbook wb = new HSSFWorkbook();
                    HSSFSheet s = wb.createSheet();
                    HSSFRow arrRow[] = new HSSFRow[objSpeicher.get(loop_objekt).size()];
                    HSSFCell arrCell[] = new HSSFCell[3];
                    for (int i = 0; i < arrRow.length; i++) {
                        arrRow[i] = s.createRow(i);
                        for (int k = 0; k < arrCell.length; k++) {
                            arrCell[k] = arrRow[i].createCell(k);
                            switch (k) {
                                case 0:
                                    arrCell[k].setCellValue(objSpeicher.get(loop_objekt).get(i).sVorname);
                                    break;
                                case 1:
                                    arrCell[k].setCellValue(objSpeicher.get(loop_objekt).get(i).sNachname);
                                    break;
                                case 2:
                                    arrCell[k].setCellValue(objSpeicher.get(loop_objekt).get(i).sKlassenstufe_mit_Buchstaben);
                                    break;
                            }
                        }
                    }

                    // end deleted sheet
                    FileOutputStream out = null;
                    try {
                        out = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    try {
                        wb.write(out);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
    }

}

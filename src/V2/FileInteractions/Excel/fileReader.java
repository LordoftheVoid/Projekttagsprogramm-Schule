package V2.FileInteractions.Excel;

import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.Settings.Imports;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Aaron on 11.02.2018.
 */
public class fileReader extends Thread {


    String dateiUrl = "";


    fileReader(String url) {
        this.dateiUrl = url;
        this.start();
    }


    String generateGrade(){
        String grade ="";
        int charIndexBackTracking = dateiUrl.length() - 1;

        while (dateiUrl.charAt(charIndexBackTracking) != '\\') {
            charIndexBackTracking--;
        }
        charIndexBackTracking++;

        for (; charIndexBackTracking < dateiUrl.length(); charIndexBackTracking++) {
            grade = grade + dateiUrl.charAt(charIndexBackTracking);
            if (dateiUrl.charAt(charIndexBackTracking) == '.') {
                break;
            }
        }

        return grade;
    }


    public void run() {
        String surName;
        String preName;


        HSSFWorkbook datei = null;

        try {
            datei = new HSSFWorkbook(new FileInputStream(this.dateiUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int arrayIndex = 0; arrayIndex < datei.getSheetAt(0).getLastRowNum(); arrayIndex++) {
            surName = datei.getSheetAt(0).getRow(arrayIndex).getCell(0).getStringCellValue();
            preName = datei.getSheetAt(0).getRow(arrayIndex).getCell(1).getStringCellValue();

            if (!surName.toLowerCase().equals("nachname")) {


                String newID = Pupil.generateHash(surName,preName);
                if(Imports.objDatabaseManagerGlobal.entryExists("Pupil",newID)){
                    //TODO: Sagen, das ein Schüler bereits exisitierte
                }else{
                    try {
                        Imports.objDatabaseManagerGlobal.createEntry("Pupil", newID);
                        Imports.objDatabaseManagerGlobal.updateEntry("Pupil", newID, 1, surName);
                        Imports.objDatabaseManagerGlobal.updateEntry("Pupil", newID, 2, preName);
                        Imports.objDatabaseManagerGlobal.updateEntry("Pupil", newID, 3, this.generateGrade());
                    } catch (SQLException e) {
                        /**Todo: Maulen das ein Schüler bereits exisitert, alle weiteren Fehlerursachen untersuchen
                         *    System.out.println( e.getMessage());
                         *
                         */

                    }
                }



            }
        }
    }
}
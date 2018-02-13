package V2.FileInteractions.Excel;

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
        for (int i_x = 0; i_x < datei.getSheetAt(0).getLastRowNum(); i_x++) {
            surName = datei.getSheetAt(0).getRow(i_x).getCell(0).getStringCellValue();
            preName = datei.getSheetAt(0).getRow(i_x).getCell(1).getStringCellValue();

            if (!surName.toLowerCase().equals("nachname")) {

                String newID ="";
                for (int charIndex = 0; charIndex < 3; charIndex++) {
                    newID = newID + surName.charAt(charIndex);
                }
                for (int charIndex = 0; charIndex < 3; charIndex++) {
                    newID = newID + preName.charAt(charIndex);
                }

                try {
                    Imports.objDatabaseManagerGlobal.createEntry("Pupil", newID);
                    Imports.objDatabaseManagerGlobal.updateEntry("Pupil", newID, 2, surName);
                    Imports.objDatabaseManagerGlobal.updateEntry("Pupil", newID, 3, preName);
                    Imports.objDatabaseManagerGlobal.updateEntry("Pupil", newID, 4, this.generateGrade());
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
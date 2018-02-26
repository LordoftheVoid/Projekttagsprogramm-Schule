package V2.FileInteractions.Readers;

import V2.DataBaseInternalClasses.Pupil;
import V2.cMain;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Aaron on 11.02.2018.
 */
public class ExcelFileReader extends Thread {


     private     String dateiUrl = "";


    public ExcelFileReader(String url) {
        this.dateiUrl = url;
        this.start();
    }


    String generateGrade() {
        String grade = "";
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


                String newID = Pupil.generateHash(surName, preName);
                if (cMain.objDatabaseManagerGlobal.entryExists("Pupil", newID)) {
                    cMain.updateStatus("Es wurde versucht, einen Schüler aus Excel anzulegen der bereits existiert");
                } else {
                    try {
                        cMain.updateStatus("Ein neuer Schüler wurde aus Exel importiert");
                        cMain.objDatabaseManagerGlobal.createEntry("Pupil", newID);
                        cMain.objDatabaseManagerGlobal.updateNonIDValues("Pupil", newID, 0, surName);
                        cMain.objDatabaseManagerGlobal.updateNonIDValues("Pupil", newID, 1, preName);
                        cMain.objDatabaseManagerGlobal.updateNonIDValues("Pupil", newID, 2, this.generateGrade());

                    } catch (SQLException e) {
                    }
                }


            }
        }
    }
}
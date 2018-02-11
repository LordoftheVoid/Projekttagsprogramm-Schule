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


    public void run() {
        String surName;
        String preName;
        String stufe = "";

        int i = dateiUrl.length() - 1;

        while (dateiUrl.charAt(i) != '\\') {
            i--;
        }
        i++;

        for (; i < dateiUrl.length(); i++) {
            stufe = stufe + dateiUrl.charAt(i);
            if (dateiUrl.charAt(i) == '.') {
                break;
            }
        }

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
                System.out.println("-----");
                System.out.println(surName);
                System.out.println(preName);
                System.out.println("-----");

                String newID ="";
                for (int j = 0; j < 3; j++) {
                    newID = newID + surName.charAt(j);
                }
                for (int j = 0; j < 3; j++) {
                    newID = newID + preName.charAt(j);
                }


                System.out.println("id SOLLTE SEIN"+newID);
                try {
                    Imports.objDatabaseManagerGlobal.createEntry("Pupil", newID);
                    Imports.objDatabaseManagerGlobal.updateEntry("Pupil", newID, 2, surName);
                    Imports.objDatabaseManagerGlobal.updateEntry("Pupil", newID, 3, surName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
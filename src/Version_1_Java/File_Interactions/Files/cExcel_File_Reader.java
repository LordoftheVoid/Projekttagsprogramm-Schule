package Version_1_Java.File_Interactions.Files;

/**
 * Created by Aaron on 10.08.2017.
 */


import Version_1_Java.cImports;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class cExcel_File_Reader {

    private ArrayList<String> listEntryIDs = new ArrayList();

    public CopyOnWriteArrayList<String> dateiListe;


     CopyOnWriteArrayList<String> suchDateiURLs(String s_tm_starting_directory) {

        File file_initial_file = new File(String.valueOf(s_tm_starting_directory));

        File[] arr_files_in_subdirectory = file_initial_file.listFiles();

        CopyOnWriteArrayList<String> files_found_subdirectory = new CopyOnWriteArrayList<>();

        if (arr_files_in_subdirectory != null) {
            for (File fil : arr_files_in_subdirectory) {
                if (fil.isDirectory()) {
                    files_found_subdirectory.addAll(suchDateiURLs(fil.getAbsolutePath()));
                } else {
                    files_found_subdirectory.add(fil.getAbsolutePath());
                }
            }
        }

        for (String loop_object : files_found_subdirectory
                ) {
            if (!loop_object.contains(".xls")) {
                files_found_subdirectory.remove(loop_object);
            }
        }

        return files_found_subdirectory;
    }




     void readFile (String url){

        HSSFWorkbook obj_data_file_xls = null;

        try {
            obj_data_file_xls = new HSSFWorkbook(new FileInputStream(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Integer> listnewPersonYCoordinate = new ArrayList<>();

        for (int i_x = 0; i_x < obj_data_file_xls.getSheetAt(0).getLastRowNum(); i_x++) {
            String rowID = "";
            for (int k_y = 0; k_y < 2; k_y++) {
                for (int charAT = 0; charAT < 2; charAT++) {
                    rowID = rowID +  obj_data_file_xls.getSheetAt(0).getRow(i_x).getCell(k_y).getStringCellValue().charAt(charAT);
                }
            }
            if(!listEntryIDs.contains(rowID)){
                listnewPersonYCoordinate.add(i_x);
            }

        }

        for (Integer objList:listnewPersonYCoordinate
             ) {
            String rowID = "";
            for (int k_y = 0; k_y < 2; k_y++) {
                for (int charAT = 0; charAT < 2; charAT++) {
                    rowID = rowID + obj_data_file_xls.getSheetAt(0).getRow(objList).getCell(k_y).getStringCellValue().charAt(charAT);
                }
            }
            try {
                cImports.objDatabaseManagerGlobal.createEntry("persons",rowID);
                cImports.objDatabaseManagerGlobal.updateEntry("persons", rowID, "s_pre_Name",obj_data_file_xls.getSheetAt(0).getRow(objList).getCell(1).getStringCellValue());
                cImports.objDatabaseManagerGlobal.updateEntry("persons", rowID, "s_sur_Name",obj_data_file_xls.getSheetAt(0).getRow(objList).getCell(0).getStringCellValue());
                listEntryIDs.add(rowID);
            } catch (SQLException e) {

            }
        }

    }

        public void updateDatenbank (String urlOrdner) throws NullPointerException {
        this.dateiListe = suchDateiURLs(urlOrdner);
            for (String eintrag:this.dateiListe
                 ) {
                this.readFile(eintrag);
            }
        }

}


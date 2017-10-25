package Version_1_Java.File_Interactions.Files;

/**
 * Created by Aaron on 10.08.2017.
 */


import Version_1_Java.File_Interactions.Database.cDatabaseConnectionManager;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class cExcel_File_Reader {


    public cExcel_File_Reader(cDatabaseConnectionManager obj_tm_DatabaseManager_Main) {
        this.objDatabaseManager_Reader = obj_tm_DatabaseManager_Main;

        try {
            ResultSet   setGivenEntrys = objDatabaseManager_Reader.readEoAttr("persons","s_unique_ID");
            while(setGivenEntrys.next()){
              this.listEntryIDs.add(setGivenEntrys.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<String> listEntryIDs = new ArrayList();

    private cDatabaseConnectionManager objDatabaseManager_Reader;

    public CopyOnWriteArrayList<String> list_of_filenames_with_xls;

    public int personsFound = 0;


    public CopyOnWriteArrayList<String> list_search_for_xls_Files(String s_tm_starting_directory) {

        File file_initial_file = new File(String.valueOf(s_tm_starting_directory));

        File[] arr_files_in_subdirectory = file_initial_file.listFiles();

        CopyOnWriteArrayList<String> files_found_subdirectory = new CopyOnWriteArrayList<>();

        if (arr_files_in_subdirectory != null) {
            for (File fil : arr_files_in_subdirectory) {
                if (fil.isDirectory()) {
                    files_found_subdirectory.addAll(list_search_for_xls_Files(fil.getAbsolutePath()));
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




    public void readFile (String url){


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
                this.personsFound++;
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
                objDatabaseManager_Reader.createEntry("persons",rowID);
                objDatabaseManager_Reader.updateEntry("persons", rowID, "s_pre_Name",obj_data_file_xls.getSheetAt(0).getRow(objList).getCell(1).getStringCellValue());
                objDatabaseManager_Reader.updateEntry("persons", rowID, "s_sur_Name",obj_data_file_xls.getSheetAt(0).getRow(objList).getCell(0).getStringCellValue());
                listEntryIDs.add(rowID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }






}


package Version_1_Java.DateiSchnittstellen;

/**
 * Created by Aaron on 10.08.2017.
 */


import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Objekte.cpupils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

public class cExcel_Reader {


   public cExcel_Reader(cDatabaseManager objDatabaseManager_Main){
        this.objDatabaseManager_Reader=objDatabaseManager_Main;
    }


    private cDatabaseManager objDatabaseManager_Reader;


   public  CopyOnWriteArrayList<String> list_of_filenames_with_xls;

   private String [][] arrcontext_file;

   private boolean b_data_found=true;



   public  CopyOnWriteArrayList<cpupils> list_of_pupils_from_files= new CopyOnWriteArrayList<>();


    public  CopyOnWriteArrayList<String> filename_search_xls(String starting_directory)  {

        File file_initial_file = new File(String.valueOf(starting_directory));

        File [] arr_files_in_subdirectory = file_initial_file.listFiles();

        CopyOnWriteArrayList<String> files_found_subdirectory = new CopyOnWriteArrayList<>();

        if (arr_files_in_subdirectory != null) {
            for (File fil : arr_files_in_subdirectory) {
                if (fil.isDirectory()) {
                    files_found_subdirectory.addAll(filename_search_xls(fil.getAbsolutePath()));
                } else {
                    files_found_subdirectory.add(fil.getAbsolutePath());
                }
            }
        }

        for (String loop_object:files_found_subdirectory
             ) {
            if(!loop_object.contains(".xls")){
                files_found_subdirectory.remove(loop_object);
            }
        }

        if(files_found_subdirectory.size()==0){
            b_data_found=false;
        }


        return files_found_subdirectory;
    }




    public void read_file_extracting_pupils (String url){

        HSSFWorkbook obj_data_file_xls=null;

        try {
             obj_data_file_xls = new HSSFWorkbook(new FileInputStream(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

         arrcontext_file = new String[2][obj_data_file_xls.getSheetAt(0).getLastRowNum()];

        for (int i_x = 0; i_x < arrcontext_file.length; i_x++) {
            for (int k_y = 0; k_y < arrcontext_file[i_x].length; k_y++) {
                arrcontext_file[i_x][k_y]=obj_data_file_xls.getSheetAt(0).getRow(k_y).getCell(i_x).getStringCellValue();
            }
        }
    }

    public void update_Database_from_list (){


        if(b_data_found) {
            for (int i_entry_counter = 0; i_entry_counter < arrcontext_file[0].length; i_entry_counter++) {
                String unique_id = "";
                for (int i = 0; i < 2; i++) {
                    for (int k = 0; k < 2; k++) {
                        unique_id = unique_id + arrcontext_file[i][i_entry_counter].charAt(k);
                    }
                }
                try {

                    if (objDatabaseManager_Reader.entry_check("pupils", unique_id)) {
                        objDatabaseManager_Reader.create_entry("pupils", unique_id);
                        objDatabaseManager_Reader.update_entry("pupils", unique_id, "preName", arrcontext_file[0][i_entry_counter]);
                        objDatabaseManager_Reader.update_entry("pupils", unique_id, "surName", arrcontext_file[1][i_entry_counter]);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}


package Version_1_Java.DateiSchnittstellen;

/**
 * Created by Aaron on 10.08.2017.
 */


import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Objekte.cpupils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

public class cExcel_Reader {


   public cExcel_Reader(cDatabaseManager obj_tm_DatabaseManager_Main){
        this.objDatabaseManager_Reader=obj_tm_DatabaseManager_Main;
    }


    public cDatabaseManager objDatabaseManager_Reader;


   public  CopyOnWriteArrayList<String> list_of_filenames_with_xls;



   public  CopyOnWriteArrayList<cpupils> list_of_pupils_from_files= new CopyOnWriteArrayList<>();


    public  CopyOnWriteArrayList<String> list_search_for_xls_Files(String s_tm_starting_directory)  {

        File file_initial_file = new File(String.valueOf(s_tm_starting_directory));

        File [] arr_files_in_subdirectory = file_initial_file.listFiles();

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

        for (String loop_object:files_found_subdirectory
             ) {
            if(!loop_object.contains(".xls")){
                files_found_subdirectory.remove(loop_object);
            }
        }
        return files_found_subdirectory;
    }





    public void read_file_extracting_pupils (String url){

        HSSFWorkbook data_file_xls=null;

        try {
             data_file_xls = new HSSFWorkbook(new FileInputStream(url));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Cell active_cell= data_file_xls.getSheetAt(0).getRow(0).getCell(0);

        int i_row_counter=0;

        boolean b_content_at_cell_i=true;

        while(b_content_at_cell_i){
            try {
                list_of_pupils_from_files.add(new cpupils(active_cell.toString(), data_file_xls.getSheetAt(0).getRow(i_row_counter).getCell(1).toString()));

                i_row_counter++;
                active_cell= data_file_xls.getSheetAt(0).getRow(i_row_counter).getCell(0);

            }catch (NullPointerException e1){
                b_content_at_cell_i=false;
            }

        }

    }

    public void v_update_Database_from_list(){

        for (cpupils loop_opject:list_of_pupils_from_files
             ) {


            String unique_id= "";
            for (int i = 0; i < 2; i++) {
                unique_id = unique_id + loop_opject.sVorname.charAt(i);
            }
            for (int k = 0; k < 2; k++) {
                unique_id = unique_id + loop_opject.sNachname.charAt(k);
            }


            try {

                if(!objDatabaseManager_Reader.entry_check("pupils",unique_id)){
                    objDatabaseManager_Reader.create_entry("pupils", unique_id);
                    objDatabaseManager_Reader.update_entry("pupils", unique_id, "preName", loop_opject.sVorname);
                    objDatabaseManager_Reader.update_entry("pupils", unique_id, "surName", loop_opject.sNachname);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }





        }



    }







}


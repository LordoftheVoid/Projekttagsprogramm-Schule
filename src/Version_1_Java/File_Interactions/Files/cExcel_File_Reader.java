package Version_1_Java.File_Interactions.Files;

/**
 * Created by Aaron on 10.08.2017.
 */


import Version_1_Java.File_Interactions.Database.c_Database_Manager;
import Version_1_Java.cMain;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

public class cExcel_File_Reader {



   public cExcel_File_Reader(c_Database_Manager obj_tm_DatabaseManager_Main){
        this.objDatabaseManager_Reader=obj_tm_DatabaseManager_Main;

    }


    private c_Database_Manager objDatabaseManager_Reader;


   public  CopyOnWriteArrayList<String> list_of_filenames_with_xls;

   private CopyOnWriteArrayList< String [][]> list_arr_data_in_File= new CopyOnWriteArrayList<>();

   private boolean b_data_found=true;




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

        String [][] arr_data_in_File= new String[3][obj_data_file_xls.getSheetAt(0).getLastRowNum()];

        for (int i_x = 0; i_x < arr_data_in_File.length; i_x++) {
            for (int k_y = 0; k_y < arr_data_in_File[i_x].length; k_y++) {
                try {
                    arr_data_in_File[i_x][k_y] = obj_data_file_xls.getSheetAt(0).getRow(k_y).getCell(i_x).getStringCellValue();
                }catch (NullPointerException e1){
                    arr_data_in_File[i_x][k_y] ="";
                }
            }
        }
        list_arr_data_in_File.add(arr_data_in_File);
    }

    public void v_update_Database_from_list(){
        if(b_data_found) {
            for (String [][] loop_obj_arr:list_arr_data_in_File
                 ) {
                for (int i_entry_counter = 0; i_entry_counter < loop_obj_arr[0].length; i_entry_counter++) {
                    String unique_id = "";
                    for (int i = 0; i < 2; i++) {
                        for (int k = 0; k < 2; k++) {
                            unique_id = unique_id + loop_obj_arr[i][i_entry_counter].charAt(k);
                        }
                    }
                    try {
                        if (objDatabaseManager_Reader.entry_check("persons", unique_id)) {
                            objDatabaseManager_Reader.create_entry("persons", unique_id);
                            objDatabaseManager_Reader.update_entry("persons", unique_id, "s_pre_Name", loop_obj_arr[0][i_entry_counter]);
                            objDatabaseManager_Reader.update_entry("persons", unique_id, "s_sur_Name", loop_obj_arr[1][i_entry_counter]);
                            objDatabaseManager_Reader.update_entry("persons", unique_id, "s_grade", loop_obj_arr[2][i_entry_counter]);
                        }
                    } catch (SQLException e) {
                        cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
                    }

                }
            }

        }

    }

    public int i_amount_of_pupils(){
        int i_sum=0;
        if(b_data_found){
            for (String [][] list_obj_arr:list_arr_data_in_File
                    ) {
                i_sum=i_sum+ list_obj_arr.length;
            }
        }
        return i_sum;
    }




}


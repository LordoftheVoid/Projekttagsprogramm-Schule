package Version_1_Java.File_Interactions.Files;


import Version_1_Java.File_Interactions.Database.cDatabaseConnectionManager;
import Version_1_Java.File_Interactions.Directories.c_Directory_Creator;
import Version_1_Java.Lists.cHash_Map_ID_projects_to_List_ID_pupils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aaron on 13.04.2017.
 */
public class c_Output_File_Generator {


    cHash_Map_ID_projects_to_List_ID_pupils obj_output_Map;
    cDatabaseConnectionManager objDatabaseManager_Input;


    public c_Output_File_Generator(cHash_Map_ID_projects_to_List_ID_pupils argMap_tm, cDatabaseConnectionManager obj_Database_Manager_Main) {
        obj_output_Map = argMap_tm;
        objDatabaseManager_Input = obj_Database_Manager_Main;

    }

    static int iAmountofUsages = 0;

    public void v_write_xls_Files(String sTargetDirectory) {
        iAmountofUsages++;

        c_Directory_Creator objDirectory_Creator = new c_Directory_Creator();

        objDirectory_Creator.v_creation(sTargetDirectory, "Zuweisung_" + String.valueOf(iAmountofUsages));


        for (String s_loop_objekt : obj_output_Map.keySet()
                ) {

            ResultSet set_Ouput_Data = null;
            String outputFilename = null;


            try {
                set_Ouput_Data = objDatabaseManager_Input.read_one_entry_one_attribute("projects", "s_teacher_ID", s_loop_objekt);
                outputFilename = "Projekt von " + set_Ouput_Data.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }


            File file = new File(sTargetDirectory + "/Zuweisung_" + String.valueOf(iAmountofUsages) + "/" + outputFilename + ".xls");

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet s = wb.createSheet();
            HSSFRow arrRow[] = new HSSFRow[obj_output_Map.get(s_loop_objekt).size()];
            HSSFCell arrCell[] = new HSSFCell[3];
            for (int i = 0; i < arrRow.length; i++) {
                arrRow[i] = s.createRow(i);
                for (int k = 0; k < arrCell.length; k++) {
                    arrCell[k] = arrRow[i].createCell(k);
                    try {
                        switch (k) {
                            case 0:
                                set_Ouput_Data = objDatabaseManager_Input.read_one_entry_one_attribute("persons", "s_sur_Name", obj_output_Map.get(s_loop_objekt).get(i));
                                break;
                            case 1:
                                set_Ouput_Data = objDatabaseManager_Input.read_one_entry_one_attribute("persons", "s_pre_Name", obj_output_Map.get(s_loop_objekt).get(i));
                                break;
                            case 2:
                                set_Ouput_Data = objDatabaseManager_Input.read_one_entry_one_attribute("persons", "s_grade", obj_output_Map.get(s_loop_objekt).get(i));
                                break;

                        }
                        arrCell[k].setCellValue(set_Ouput_Data.getString(1));

                    } catch (SQLException e) {
                        e.printStackTrace();
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
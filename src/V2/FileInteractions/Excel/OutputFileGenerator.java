package V2.FileInteractions.Excel;


/**
 * Created by Aaron on 13.04.2017.
 */
public class OutputFileGenerator {

/*
    cHash_Map_ID_projects_to_List_ID_pupils obj_output_Map;


    public OutputFileGenerator(cHash_Map_ID_projects_to_List_ID_pupils argMap_tm) {
        obj_output_Map = argMap_tm;

    }

    static int iAmountofUsages = 0;

    public void v_write_xls_Files(String sTargetDirectory) {
        iAmountofUsages++;

        DirectoryCreator objDirectory_Creator = new DirectoryCreator();

        objDirectory_Creator.v_creation(sTargetDirectory, "Zuweisung_" + String.valueOf(iAmountofUsages));


        for (String s_loop_objekt : obj_output_Map.keySet()
                ) {

            ResultSet set_Ouput_Data = null;
            String outputFilename = null;


            try {
                set_Ouput_Data = Imports.objDatabaseManagerGlobal.readOneEntryOneAtribute("projects", "s_teacher_ID", s_loop_objekt);
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
                                set_Ouput_Data = Imports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons", "s_sur_Name", obj_output_Map.get(s_loop_objekt).get(i));
                                break;
                            case 1:
                                set_Ouput_Data = Imports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons", "s_pre_Name", obj_output_Map.get(s_loop_objekt).get(i));
                                break;
                            case 2:
                                set_Ouput_Data = Imports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons", "s_grade", obj_output_Map.get(s_loop_objekt).get(i));
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

*/
}
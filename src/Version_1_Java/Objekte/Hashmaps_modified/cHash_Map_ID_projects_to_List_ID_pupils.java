package Version_1_Java.Objekte.Hashmaps_modified;

import Version_1_Java.DatenBankenSchnittstellen.c_Database_Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aaron on 12.08.2017.
 */



public class cHash_Map_ID_projects_to_List_ID_pupils extends HashMap <String, ArrayList<String>> {

    c_Database_Manager obj_Databasemanager_list;


    public int i_sum_of_preferences=0;

    public int i_amount_of_pupils=0;


    public cHash_Map_ID_projects_to_List_ID_pupils(c_Database_Manager obj_tm_Databasemanager_Main) {
        this.obj_Databasemanager_list = obj_tm_Databasemanager_Main;
    }



    public void v_setup_from_Database(){
        try {
            ResultSet set_IDs_from_Database = obj_Databasemanager_list.read_entrys_one_attribute("projects", "s_unique_ID");
            while (set_IDs_from_Database.next()){
                this.put(set_IDs_from_Database.getString(1), new ArrayList<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}

package Version_1_Java.Objekte.Hashmaps_modified;

import Version_1_Java.DatenBankenSchnittstellen.c_Database_Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Aaron on 12.08.2017.
 */
public class cHash_Map_ID_max_pupils extends HashMap <String, Integer> {


    c_Database_Manager obj_Databasemanager_list;


    public cHash_Map_ID_max_pupils( c_Database_Manager obj_tm_DatabaseManager_main) {
        obj_Databasemanager_list=obj_tm_DatabaseManager_main;
    }


    public void v_update_from_Database(){
        try {
             ResultSet set_IDs =obj_Databasemanager_list.read_entrys_one_attribute("projects","s_unique_ID");
             while(set_IDs.next()){
                 ResultSet i_specific_max_amount = obj_Databasemanager_list.read_one_entry_one_attribute("projects","i_max_pupils",set_IDs.getString(1));
                 while(i_specific_max_amount.next()){
                     this.put(set_IDs.getString(1), Integer.valueOf(i_specific_max_amount.getString(1)));
                 }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

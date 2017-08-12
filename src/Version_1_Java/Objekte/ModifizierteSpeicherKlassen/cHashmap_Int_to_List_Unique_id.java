package Version_1_Java.Objekte.ModifizierteSpeicherKlassen;

import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Objekte.cProjekt;
import Version_1_Java.Objekte.cpupils;
import Version_1_Java.cMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 11.08.2017.
 */
public class cHashmap_Int_to_List_Unique_id {


    cDatabaseManager objDatabaseManageer_list;



    public cHashmap_Int_to_List_Unique_id(cDatabaseManager objDatabaseManageer_main) {
        objDatabaseManageer_list=objDatabaseManageer_main;
    }

    CopyOnWriteArrayList<cpupils> list_pupils_without_projekt= new CopyOnWriteArrayList<>();



    CopyOnWriteArrayList<cpupils> list_pupils_with_projekt= new CopyOnWriteArrayList<>();


    CopyOnWriteArrayList<cProjekt> list_project_empty = new CopyOnWriteArrayList<>();


    CopyOnWriteArrayList<cProjekt> list_project_full = new CopyOnWriteArrayList<>();




    public void arrangement (int i_Max_amount_of_people_leftover){


        String[] arrcolums = new String[4];
        arrcolums[0] = "pref0";
        arrcolums[1] = "pref1";
        arrcolums[2] = "pref2";
        arrcolums[3] = "pref3";


        for (int i = 0; i < cMain.iMaximalanzahl_Projekte; i++) {


            cpupils obj_random_choosen;
            if (list_pupils_without_projekt.size() > 1) {
                obj_random_choosen = list_pupils_with_projekt.get((int) (Math.random() * list_pupils_with_projekt.size()));
            } else {
                obj_random_choosen = list_pupils_with_projekt.get(0);
            }



            int i_projekt = 0;

            try {
                ResultSet  set_choosenprojekt=  objDatabaseManageer_list.read_one_entry_one_attribute("pupils",arrcolums[i],obj_random_choosen.unique_ID);
                 i_projekt= Integer.parseInt(set_choosenprojekt.getString(1));
            } catch (SQLException e) {
                e.printStackTrace();
            }


            cProjekt obj_prefered_project = null;

            for (cProjekt loop_objekt:list_project_empty
                 ) {
                if(loop_objekt.unique_id==i_projekt){
                    obj_prefered_project=loop_objekt;
                    break;
                }
            }


            if(obj_prefered_project.iamout_of_pupils<obj_prefered_project.iMaximalepupilsanzahl){
                obj_prefered_project.iamout_of_pupils++;
                list_pupils_without_projekt.remove(obj_random_choosen);
                list_pupils_with_projekt.add(obj_random_choosen);

            }else{

            }



        }






    }







}

package Version_1_Java;

import Version_1_Java.DateiSchnittstellen.cExcel_File_Reader;
import Version_1_Java.DatenBankenSchnittstellen.c_Database_Manager;
import Version_1_Java.Interfaces.cOutput_Frame;
import Version_1_Java.Interfaces.c_Projekt_Frame_Input;
import Version_1_Java.Interfaces.c_Pupils_Frame_Input;
import Version_1_Java.Objekte.Hashmaps_modified.cHash_Map_ID_max_pupils;
import Version_1_Java.Objekte.Hashmaps_modified.cHash_Map_ID_projects_to_List_ID_pupils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Aaron on 29.03.2017.
 */
public class cMain {




    public static  int i_amount_of_pupils_in_database =0;
    public static  int i_amount_of_projekts_in_database =0;


    public static final int iMaximalanzahl_Projekte=4;

    /*
    Achtung, nur teilweise abhängig realisiert!!!!

        */





    public static void main(String args[]) {

        c_Database_Manager obj_Database_manager_Main = new c_Database_Manager();

        try {
            obj_Database_manager_Main.v_initialization();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        cExcel_File_Reader obj_File_Reader_Excel = new cExcel_File_Reader(obj_Database_manager_Main);




        obj_File_Reader_Excel.list_of_filenames_with_xls =  obj_File_Reader_Excel.list_search_for_xls_Files("C:/Informatik/Html");


        for ( String loop_objekt_s:obj_File_Reader_Excel.list_of_filenames_with_xls
             ) {
            obj_File_Reader_Excel.read_file_extracting_pupils(loop_objekt_s);
        }

        obj_File_Reader_Excel.v_update_Database_from_list();




        try {
            i_amount_of_pupils_in_database = obj_Database_manager_Main.v_i_amout_of_entrys_in_Database("pupils");
            i_amount_of_projekts_in_database = obj_Database_manager_Main.v_i_amout_of_entrys_in_Database("projects");
        } catch (SQLException e) {
            e.printStackTrace();
        }







        c_Pupils_Frame_Input obj_pupils_Input = new c_Pupils_Frame_Input(obj_Database_manager_Main);
        c_Projekt_Frame_Input obj_Projekt_Input = new c_Projekt_Frame_Input(obj_Database_manager_Main);
        cOutput_Frame obj_Output = new cOutput_Frame(obj_Database_manager_Main);


        JFrame obj_Frame_Main = new JFrame("Projekttagsverwaltungsprogramm Version 1.0");
        obj_Frame_Main.setVisible(true);
        obj_Frame_Main.setBounds(0, 0, 1000, 1000);
        obj_Frame_Main.getContentPane().setLayout(null);


        JButton btn_pupils_Frame = new JButton("Schüler-Eingabe-Feld aufrufen");
        btn_pupils_Frame.setVisible(true);
        btn_pupils_Frame.setBounds(0, 0, 300, 300);
        obj_Frame_Main.getContentPane().add(btn_pupils_Frame);





        btn_pupils_Frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                obj_pupils_Input.setVisible(true);
                obj_pupils_Input.setEnabled(true);


                try {
                    obj_pupils_Input.v_update_Frame_from_Database();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        JButton btn_projekt_Frame = new JButton("Projekt-Eingabe-Feld aufrufen");
        btn_projekt_Frame.setVisible(true);
        btn_projekt_Frame.setBounds(300, 0, 300, 300);
        obj_Frame_Main.getContentPane().add(btn_projekt_Frame);

        btn_projekt_Frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                obj_Projekt_Input.setVisible(true);
                obj_Projekt_Input.v_update_Frame_from_Database();





            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {


            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });


        JButton btn_Frame_Output = new JButton("Kalkulation und Ausgabe der moeglichen Projektverteilungen");
        btn_Frame_Output.setVisible(true);
        btn_Frame_Output.setBounds(300, 300, 300, 20);
        obj_Frame_Main.getContentPane().add(btn_Frame_Output);
        btn_Frame_Output.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                obj_Output.setVisible(true);
                obj_Output.setEnabled(true);


                cHash_Map_ID_projects_to_List_ID_pupils objHashmap_projects_pupils = new cHash_Map_ID_projects_to_List_ID_pupils(obj_Database_manager_Main);

                cHash_Map_ID_max_pupils objHashmap_max_pupils= new cHash_Map_ID_max_pupils(obj_Database_manager_Main);

                objHashmap_max_pupils.v_update_from_Database();
                objHashmap_projects_pupils.v_setup_from_Database();


                String[] arrcolums = new String[4];
                arrcolums[0] = "i_pref0";
                arrcolums[1] = "i_pref1";
                arrcolums[2] = "i_pref2";
                arrcolums[3] = "i_pref3";


                CopyOnWriteArrayList<String> list_ID_pupils = new CopyOnWriteArrayList<>();

                try {
                    ResultSet   set_pupils_ID = obj_Database_manager_Main.read_entrys_one_attribute("pupils", "s_unique_ID");
                    while (set_pupils_ID.next()) {
                        list_ID_pupils.add(set_pupils_ID.getString(1));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


                /*
                Achtung, Frage nach Datenbankspeicherung
                 */


                HashMap <String, Integer> objHash_Map_ID_amount_people = new HashMap<>();

                for (String s_loop_object:objHashmap_max_pupils.keySet()
                        ) {
                    objHash_Map_ID_amount_people.put(s_loop_object,0);
                }

                ArrayList <String> arrlist_ID_without_projects= new ArrayList<>();


                for (int i_preference_counter = 0; i_preference_counter < cMain.iMaximalanzahl_Projekte; i_preference_counter++) {

                    String s_random_pupil_ID= "";

                    int i_random_counter;

                    while(list_ID_pupils.size()>0) {
                        if (list_ID_pupils.size() > 1) {
                            i_random_counter = (int) (Math.random() * list_ID_pupils.size());
                            s_random_pupil_ID = list_ID_pupils.get(i_random_counter);
                        } else {
                            s_random_pupil_ID=list_ID_pupils.get(0);
                        }

                        String s_prefence_specific_pupil="";
                        try {
                            ResultSet set_prefence_specific_id  =  obj_Database_manager_Main.read_one_entry_one_attribute("pupils",arrcolums[i_preference_counter],s_random_pupil_ID);
                            s_prefence_specific_pupil=set_prefence_specific_id.getString(1);
                        } catch (SQLException e2) {
                            e2.printStackTrace();
                        }

                        if (objHash_Map_ID_amount_people.get(s_prefence_specific_pupil) < objHashmap_max_pupils.get( s_prefence_specific_pupil)) {
                            objHash_Map_ID_amount_people.replace(s_prefence_specific_pupil, objHash_Map_ID_amount_people.get( s_prefence_specific_pupil), objHash_Map_ID_amount_people.get(s_prefence_specific_pupil) + 1);
                            objHashmap_projects_pupils.get(s_prefence_specific_pupil).add(s_random_pupil_ID);
                            objHashmap_projects_pupils.i_sum_of_preferences = objHashmap_projects_pupils.i_sum_of_preferences + i_preference_counter;
                            objHashmap_projects_pupils.i_amount_of_pupils=objHashmap_projects_pupils.i_amount_of_pupils+1;


                        } else {
                            arrlist_ID_without_projects.add(s_random_pupil_ID);
                        }
                        list_ID_pupils.remove(s_random_pupil_ID);
                    }

                    list_ID_pupils.addAll(arrlist_ID_without_projects);



                    if(i_preference_counter<cMain.iMaximalanzahl_Projekte-1) {
                        arrlist_ID_without_projects.clear();
                    }else{
                        objHashmap_projects_pupils.put("-1",new ArrayList<>());
                        objHashmap_projects_pupils.get("-1").addAll(arrlist_ID_without_projects);
                        objHashmap_projects_pupils.i_amount_of_pupils=objHashmap_projects_pupils.i_amount_of_pupils+arrlist_ID_without_projects.size();
                    }
                }



                obj_Output.v_update_from_List_and_Database(objHashmap_projects_pupils);


                /*
                cOutPutDateiErzeuger objDateiSchreiber = new cOutPutDateiErzeuger(SpeicherBesteLoesung);
                objDateiSchreiber.ExcelDateienschreiben();
                */
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }
}

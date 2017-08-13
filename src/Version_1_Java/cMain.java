package Version_1_Java;

import Version_1_Java.DateiSchnittstellen.cExcel_File_Reader;
import Version_1_Java.DateiSchnittstellen.c_Output_File_Generator;
import Version_1_Java.DatenBankenSchnittstellen.c_Database_Manager;
import Version_1_Java.Interfaces.cOutput_Frame;
import Version_1_Java.Interfaces.c_Projekt_Frame_Input;
import Version_1_Java.Interfaces.c_Pupils_Frame_Input;
import Version_1_Java.Objekte.Hashmaps_modified.cHash_Map_ID_projects_to_List_ID_pupils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;


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
        obj_Frame_Main.setBounds(500, 0, 1000, 1000);
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
                objHashmap_projects_pupils.v_setup_from_Database();
                objHashmap_projects_pupils.v_arrangement();
                boolean b_constant_people=false;

                int i_counter=0;

                cHash_Map_ID_projects_to_List_ID_pupils obj_best_solution= new cHash_Map_ID_projects_to_List_ID_pupils(obj_Database_manager_Main);

                obj_best_solution.putAll(objHashmap_projects_pupils);
                obj_best_solution.i_sum_of_preferences=objHashmap_projects_pupils.i_sum_of_preferences;

                while (!b_constant_people) {

                    objHashmap_projects_pupils.v_arrangement();

                    if (objHashmap_projects_pupils.get("-1").size() <= obj_best_solution.get("-1").size()) {

                        if (objHashmap_projects_pupils.i_sum_of_preferences < obj_best_solution.i_sum_of_preferences) {
                            
                            obj_best_solution.clear();
                            obj_best_solution.putAll(objHashmap_projects_pupils);
                            obj_best_solution.i_sum_of_preferences = objHashmap_projects_pupils.i_sum_of_preferences;

                            i_counter = 0;
                        } else {
                            i_counter++;
                        }
                    } else {
                        i_counter++;
                    }
                    if (i_counter == 15) {
                        b_constant_people = true;
                    }

                }
                System.out.println(obj_best_solution + " beste  Lösungen "+ obj_best_solution.get("-1").size()+" Summe der Präferenzen war"+ obj_best_solution.i_sum_of_preferences);




                obj_Output.v_update_from_List_and_Database(obj_best_solution);

                c_Output_File_Generator obj_File_Generator = new c_Output_File_Generator(obj_best_solution,obj_Database_manager_Main);
                obj_File_Generator.v_write_xls_Files();

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

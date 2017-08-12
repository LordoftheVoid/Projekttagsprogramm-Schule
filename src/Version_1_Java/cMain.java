package Version_1_Java;

import Version_1_Java.DateiSchnittstellen.cExcel_Reader;
import Version_1_Java.DateiSchnittstellen.cOutPutDateiErzeuger;
import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Interfaces.cOutput_Frame;
import Version_1_Java.Interfaces.c_Projekt_Frame_Input;
import Version_1_Java.Interfaces.c_Pupils_Frame_Input;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.c_Array_List_extended_projekts;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.c_Array_List_extended_pupils;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.c_Hashmap_pupils_to_List_extended;

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

    public static void main(String args[]) {

        cDatabaseManager obj_Database_manager_Main = new cDatabaseManager();

        try {
            obj_Database_manager_Main.v_initialization();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        cExcel_Reader obj_File_Reader_Excel = new cExcel_Reader(obj_Database_manager_Main);

        obj_File_Reader_Excel.list_of_filenames_with_xls =  obj_File_Reader_Excel.list_search_for_xls_Files("C:/Informatik/Html");


        for ( String loop_objekt_s:obj_File_Reader_Excel.list_of_filenames_with_xls
             ) {
            obj_File_Reader_Excel.read_file_extracting_pupils(loop_objekt_s);
        }

        obj_File_Reader_Excel.v_update_Database_from_list();




        try {
            i_amount_of_pupils_in_database = obj_Database_manager_Main.i_Reihenmenge_in_Datenbank("pupils");
            i_amount_of_projekts_in_database = obj_Database_manager_Main.i_Reihenmenge_in_Datenbank("projekte");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        c_Array_List_extended_pupils listpupils_in_Programm = new c_Array_List_extended_pupils();
        c_Array_List_extended_projekts listProjekte_in_Programm = new c_Array_List_extended_projekts();


        c_Pupils_Frame_Input obj_pupils_Input = new c_Pupils_Frame_Input(obj_Database_manager_Main);
        c_Projekt_Frame_Input obj_Projekt_Input = new c_Projekt_Frame_Input(obj_Database_manager_Main);
        cOutput_Frame obj_Output = new cOutput_Frame();


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

                    /*

                    Wichtige Catch-Stelle
                     */
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


                c_Hashmap_pupils_to_List_extended[] arr_Maps_Pupils = new c_Hashmap_pupils_to_List_extended[10];
                c_Hashmap_pupils_to_List_extended SpeicherBesteLoesung = arr_Maps_Pupils[0];

                for (int i = 0; i < arr_Maps_Pupils.length; i++) {
                    arr_Maps_Pupils[i] = new c_Hashmap_pupils_to_List_extended(listProjekte_in_Programm);
                    arr_Maps_Pupils[i].Erstellung(listpupils_in_Programm);
                    try {
                        if (arr_Maps_Pupils[i].getListpupilsohneProjekte().size() < SpeicherBesteLoesung.getListpupilsohneProjekte().size()) {
                            SpeicherBesteLoesung = arr_Maps_Pupils[i];
                        }
                    } catch (NullPointerException nPointerexc) {
                        SpeicherBesteLoesung = arr_Maps_Pupils[i];
                    }
                }


                obj_Output.Darstellung_der_pupils(SpeicherBesteLoesung);


                cOutPutDateiErzeuger objDateiSchreiber = new cOutPutDateiErzeuger(SpeicherBesteLoesung);


                objDateiSchreiber.ExcelDateienschreiben();


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

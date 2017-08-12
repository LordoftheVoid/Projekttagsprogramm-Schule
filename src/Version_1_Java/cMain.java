package Version_1_Java;

import Version_1_Java.DateiSchnittstellen.cExcel_File_Reader;
import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Interfaces.cOutput_Frame;
import Version_1_Java.Interfaces.c_Projekt_Frame_Input;
import Version_1_Java.Interfaces.c_Pupils_Frame_Input;
import Version_1_Java.Objekte.cProjekt;
import Version_1_Java.Objekte.cpupils;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        cDatabaseManager objDatabaseManager_main = new cDatabaseManager();

        try {
            objDatabaseManager_main.initialisierung();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        cExcel_File_Reader objExcelReader = new cExcel_File_Reader(objDatabaseManager_main);

        objExcelReader.list_of_filenames_with_xls =  objExcelReader.filename_search_xls("C:/Informatik/Html");


        for ( String loop_objekt:objExcelReader.list_of_filenames_with_xls
             ) {
            objExcelReader.read_file_extracting_pupils(loop_objekt);
        }

        objExcelReader.update_Database_from_list();




        try {
            i_amount_of_pupils_in_database = objDatabaseManager_main.i_Reihenmenge_in_Datenbank("pupils");
            i_amount_of_projekts_in_database = objDatabaseManager_main.i_Reihenmenge_in_Datenbank("projekte");
        } catch (SQLException e) {
            e.printStackTrace();
        }








        /*

            Methode Kalkulation
         */









        c_Pupils_Frame_Input obj_pupils_Input = new c_Pupils_Frame_Input(objDatabaseManager_main);
        c_Projekt_Frame_Input obj_Projekt_Input = new c_Projekt_Frame_Input(objDatabaseManager_main);
        cOutput_Frame obj_Output = new cOutput_Frame();


        JFrame obj_Main_Frame = new JFrame("Projekttagsverwaltungsprogramm Version 1.0");
        obj_Main_Frame.setVisible(true);
        obj_Main_Frame.setBounds(0, 0, 1000, 1000);
        obj_Main_Frame.getContentPane().setLayout(null);


        JButton Button_pupils_Frame_Aktivierung = new JButton("pupils-Eingabe-Feld aufrufen");
        Button_pupils_Frame_Aktivierung.setVisible(true);
        Button_pupils_Frame_Aktivierung.setBounds(0, 0, 300, 300);
        obj_Main_Frame.getContentPane().add(Button_pupils_Frame_Aktivierung);

        Button_pupils_Frame_Aktivierung.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                obj_pupils_Input.setVisible(true);
                obj_pupils_Input.setEnabled(true);


                try {
                    obj_pupils_Input.update_des_Interface();
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


        JButton Button_Projekt_Frame_Aktivierung = new JButton("Projekt-Eingabe-Feld aufrufen");
        Button_Projekt_Frame_Aktivierung.setVisible(true);
        Button_Projekt_Frame_Aktivierung.setBounds(300, 0, 300, 300);
        obj_Main_Frame.getContentPane().add(Button_Projekt_Frame_Aktivierung);

        Button_Projekt_Frame_Aktivierung.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                obj_Projekt_Input.setVisible(true);


                try {
                    obj_Projekt_Input.update_des_Interface(objDatabaseManager_main);
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


        JButton Kalkulation = new JButton("Kalkulation und Ausgabe der moeglichen Projektverteilungen");
        Kalkulation.setVisible(true);
        Kalkulation.setBounds(300, 300, 300, 20);
        obj_Main_Frame.getContentPane().add(Kalkulation);
        Kalkulation.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                obj_Output.setVisible(true);
                obj_Output.setEnabled(true);


                CopyOnWriteArrayList<cpupils> list_cpupils = new CopyOnWriteArrayList<>();

                ResultSet set_pupils = null;
                try {
                    set_pupils = objDatabaseManager_main.read_entrys_one_attribute("pupils", "unique_id");


                    while (set_pupils.next()) {
                        list_cpupils.add(new cpupils(set_pupils.getString(1)));
                    }


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }




        /*

        Kritischer Eintrag bezüglich 4
         */

                String[] arrcolums = new String[4];
                arrcolums[0] = "pref0";
                arrcolums[1] = "pref1";
                arrcolums[2] = "pref2";
                arrcolums[3] = "pref3";

                for (cpupils loop_object : list_cpupils
                        ) {
                    for (int i_x = 0; i_x < loop_object.arrPraeferenzen.length; i_x++) {
                        ResultSet selected_attribute;
                        try {
                            selected_attribute = objDatabaseManager_main.read_one_entry_one_attribute("pupils", arrcolums[i_x], loop_object.unique_ID);
                            loop_object.arrPraeferenzen[i_x] = Integer.parseInt(selected_attribute.getString(1));
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

                /*
                Projekte
                 */

                CopyOnWriteArrayList<cProjekt> list_projects = new CopyOnWriteArrayList<>();


                ResultSet set_projects = null;
                try {
                    set_pupils = objDatabaseManager_main.read_entrys_one_attribute("projekte", "unique_id");

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                try {
                    while (set_pupils.next()) {
                        list_projects.add(new cProjekt(set_projects.getString(1)));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


                for (cProjekt loop_object : list_projects
                        ) {
                    ResultSet selected_attribute;
                    try {
                        selected_attribute = objDatabaseManager_main.read_one_entry_one_attribute("projekte", "max_pupils", loop_object.unique_id);
                        loop_object.iMaximalepupilsanzahl = Integer.parseInt(selected_attribute.getString(1));
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }





                /*



                obj_Output.Darstellung_der_pupils(SpeicherBesteLoesung);


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

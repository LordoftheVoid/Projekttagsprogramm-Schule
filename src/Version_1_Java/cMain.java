package Version_1_Java;

import Version_1_Java.DateiSchnittstellen.cExcel_Reader;
import Version_1_Java.DateiSchnittstellen.cOutPutDateiErzeuger;
import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Interfaces.cOutput_Frame;
import Version_1_Java.Interfaces.c_Projekt_Frame_Input;
import Version_1_Java.Interfaces.c_Pupils_Frame_Input;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertProjekte;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertpupils;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cErweiterteHashMapProjektepupilsListe;

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

        cDatabaseManager objDatabaseManager_main = new cDatabaseManager();

        try {
            objDatabaseManager_main.initialisierung();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        cExcel_Reader objExcelReader = new cExcel_Reader(objDatabaseManager_main);

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


        cArrayListErweitertpupils listpupils_in_Programm = new cArrayListErweitertpupils();
        cArrayListErweitertProjekte listProjekte_in_Programm = new cArrayListErweitertProjekte();


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
                    obj_pupils_Input.update_des_Interface(objDatabaseManager_main);
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


        JButton Button_Projekt_Frame_Aktivierung = new JButton("Projekt-Eingabe-Feld aufrufen");
        Button_Projekt_Frame_Aktivierung.setVisible(true);
        Button_Projekt_Frame_Aktivierung.setBounds(300, 0, 300, 300);
        obj_Main_Frame.getContentPane().add(Button_Projekt_Frame_Aktivierung);

        Button_Projekt_Frame_Aktivierung.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                obj_Projekt_Input.setVisible(true);
                obj_Projekt_Input.update_der_Daten(listProjekte_in_Programm);
                obj_Projekt_Input.update_des_Interfaces(objDatabaseManager_main);


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


                cErweiterteHashMapProjektepupilsListe[] arrMapspupils = new cErweiterteHashMapProjektepupilsListe[10];
                cErweiterteHashMapProjektepupilsListe SpeicherBesteLoesung = arrMapspupils[0];

                for (int i = 0; i < arrMapspupils.length; i++) {
                    arrMapspupils[i] = new cErweiterteHashMapProjektepupilsListe(listProjekte_in_Programm);
                    arrMapspupils[i].Erstellung(listpupils_in_Programm);
                    try {
                        if (arrMapspupils[i].getListpupilsohneProjekte().size() < SpeicherBesteLoesung.getListpupilsohneProjekte().size()) {
                            SpeicherBesteLoesung = arrMapspupils[i];
                        }
                    } catch (NullPointerException nPointerexc) {
                        SpeicherBesteLoesung = arrMapspupils[i];
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

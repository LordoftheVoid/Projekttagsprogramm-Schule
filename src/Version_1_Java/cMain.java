package Version_1_Java;

import Version_1_Java.DateiSchnittstellen.cOutPutDateiErzeuger;
import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Interfaces.cOutput_Frame;
import Version_1_Java.Interfaces.c_Projekt_Frame_Input;
import Version_1_Java.Interfaces.c_Schueler_Frame_Input;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertProjekte;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertSchueler;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cErweiterteHashMapProjekteSchuelerListe;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;


/**
 * Created by Aaron on 29.03.2017.
 */
public class cMain {




    public static  int i_Schueler_Menge_in_Datenbank =0;
    public static  int i_Projekt_Menge_in_Datenbank =0;

    public static void main(String args[]) {


        cDatabaseManager objDatabaseManager_main = new cDatabaseManager();

        try {
            objDatabaseManager_main.initialisierung();
            i_Schueler_Menge_in_Datenbank = objDatabaseManager_main.i_Reihenmenge_in_Datenbank("schueler");
            i_Projekt_Menge_in_Datenbank = objDatabaseManager_main.i_Reihenmenge_in_Datenbank("projekte");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        cArrayListErweitertSchueler listSchueler_in_Programm = new cArrayListErweitertSchueler();
        cArrayListErweitertProjekte listProjekte_in_Programm = new cArrayListErweitertProjekte();


        c_Schueler_Frame_Input obj_Schueler_Input = new c_Schueler_Frame_Input(objDatabaseManager_main);
        c_Projekt_Frame_Input obj_Projekt_Input = new c_Projekt_Frame_Input(objDatabaseManager_main);
        cOutput_Frame obj_Output = new cOutput_Frame();


        JFrame Haupt_Frame = new JFrame("Projekttagsverwaltungsprogramm Version 1.0");
        Haupt_Frame.setVisible(true);
        Haupt_Frame.setBounds(0, 0, 1000, 1000);
        Haupt_Frame.getContentPane().setLayout(null);


        JButton Button_Schueler_Frame_Aktivierung = new JButton("Schueler-Eingabe-Feld aufrufen");
        Button_Schueler_Frame_Aktivierung.setVisible(true);
        Button_Schueler_Frame_Aktivierung.setBounds(0, 0, 300, 300);
        Haupt_Frame.getContentPane().add(Button_Schueler_Frame_Aktivierung);

        Button_Schueler_Frame_Aktivierung.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                obj_Schueler_Input.setVisible(true);
                obj_Schueler_Input.setEnabled(true);

                try {
                    obj_Schueler_Input.update_des_Interface(objDatabaseManager_main);
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
        Haupt_Frame.getContentPane().add(Button_Projekt_Frame_Aktivierung);

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
        Haupt_Frame.getContentPane().add(Kalkulation);
        Kalkulation.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                obj_Output.setVisible(true);
                obj_Output.setEnabled(true);


                cErweiterteHashMapProjekteSchuelerListe[] arrMapsSchueler = new cErweiterteHashMapProjekteSchuelerListe[10];
                cErweiterteHashMapProjekteSchuelerListe SpeicherBesteLoesung = arrMapsSchueler[0];

                for (int i = 0; i < arrMapsSchueler.length; i++) {
                    arrMapsSchueler[i] = new cErweiterteHashMapProjekteSchuelerListe(listProjekte_in_Programm);
                    arrMapsSchueler[i].Erstellung(listSchueler_in_Programm);
                    try {
                        if (arrMapsSchueler[i].getListSchuelerohneProjekte().size() < SpeicherBesteLoesung.getListSchuelerohneProjekte().size()) {
                            SpeicherBesteLoesung = arrMapsSchueler[i];
                        }
                    } catch (NullPointerException nPointerexc) {
                        SpeicherBesteLoesung = arrMapsSchueler[i];
                    }
                }


                obj_Output.Darstellung_der_Schueler(SpeicherBesteLoesung);


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

package Version_1_Java;

import Version_1_Java.DateiSchnittstellen.cOutPutDateiErzeuger;
import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Interfaces.cOutputKontrollfeld;
import Version_1_Java.Interfaces.cProjektInput;
import Version_1_Java.Interfaces.cSchuelerInput;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertProjekte;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertSchueler;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cErweiterteHashMapProjekteSchuelerListe;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Aaron on 29.03.2017.
 */
public class cMain {


   public static JTextArea Ausgabe;


    public static  int iamountofEntrys=0;

    public static void main(String args[])  {






        cDatabaseManager objDatabaseManager = new cDatabaseManager();

        try {
            objDatabaseManager.initialisierung();
            iamountofEntrys=objDatabaseManager.row_count();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        URL location = cMain.class.getProtectionDomain().getCodeSource().getLocation();

        File Eingabeort= new File(location.getFile());

        /*
            Speicherung des Inputs, Kalkulation nutzt diese beiden Listen als Input
         */


        cArrayListErweitertSchueler listSchueler = new cArrayListErweitertSchueler();
        cArrayListErweitertProjekte listProjekte = new cArrayListErweitertProjekte();



        cSchuelerInput obj_Schueler_Input = new cSchuelerInput(objDatabaseManager);
        cProjektInput obj_Projekt_Input =new cProjektInput();
        cOutputKontrollfeld obj_Output = new cOutputKontrollfeld();






        JFrame Hauptinterface= new JFrame("Projekttagsverwaltungsprogramm Version 1.0");
        Hauptinterface.setVisible(true);
        Hauptinterface.setBounds(0,0,1000,1000);
        Hauptinterface.getContentPane().setLayout(null);



        Ausgabe= new JTextArea();


        Ausgabe.setVisible(true);
        Ausgabe.setBounds(0,500,300,300);
        Hauptinterface.getContentPane().add(Ausgabe);



        Timer tUpdate_der_Daten= new Timer();
        tUpdate_der_Daten.schedule(new TimerTask() {
            @Override
            public void run() {
                obj_Projekt_Input.update_der_Daten(listProjekte);
                obj_Schueler_Input.update_der_Daten(listSchueler);

            }
        },0,10000);

        /*
        Achtung 100, ist Test!
         */





        JButton Button_Schueler_Interface= new JButton("Schueler-Eingabe-Feld aufrufen");
        Button_Schueler_Interface.setVisible(true);
        Button_Schueler_Interface.setBounds(0,0,300,300);
        Hauptinterface.getContentPane().add(Button_Schueler_Interface);

        Button_Schueler_Interface.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
              obj_Schueler_Input.setVisible(true);
              obj_Schueler_Input.setEnabled(true);

                try {
                    obj_Schueler_Input.update_des_Interface(objDatabaseManager.Datenbankverbindung);
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




        JButton Button_Projekt_Interface= new JButton("Projekt-Eingabe-Feld aufrufen");
        Button_Projekt_Interface.setVisible(true);
        Button_Projekt_Interface.setBounds(300,0,300,300);
        Hauptinterface.getContentPane().add(Button_Projekt_Interface);

        Button_Projekt_Interface.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                    obj_Projekt_Input.setVisible(true);
                    obj_Projekt_Input.update_der_Daten(listProjekte);
                    obj_Projekt_Input.update_des_Interfaces(listProjekte);



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

















        JButton Kalkulation= new JButton("Kalkulation und Ausgabe der moeglichen Projektverteilungen");
        Kalkulation.setVisible(true);
        Kalkulation.setBounds(300,300,300,20);
        Hauptinterface.getContentPane().add(Kalkulation);
        Kalkulation.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                obj_Output.setVisible(true);
                obj_Output.setEnabled(true);


                cErweiterteHashMapProjekteSchuelerListe[] arrMapsSchueler = new cErweiterteHashMapProjekteSchuelerListe[10];
                cErweiterteHashMapProjekteSchuelerListe SpeicherBesteLoesung=arrMapsSchueler[0];

                for (int i = 0; i < arrMapsSchueler.length; i++) {
                    arrMapsSchueler[i]= new cErweiterteHashMapProjekteSchuelerListe(listProjekte);
                    arrMapsSchueler[i].Erstellung(listSchueler);
                    try {
                        if (arrMapsSchueler[i].getListSchuelerohneProjekte().size() < SpeicherBesteLoesung.getListSchuelerohneProjekte().size()) {
                            SpeicherBesteLoesung = arrMapsSchueler[i];
                        }
                    }catch (NullPointerException nPointerexc){
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

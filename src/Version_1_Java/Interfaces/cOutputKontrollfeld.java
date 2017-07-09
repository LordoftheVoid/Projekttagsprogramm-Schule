package Version_1_Java.Interfaces;


import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cErweiterteHashMapProjekteSchuelerListe;
import Version_1_Java.Objekte.cProjekt;
import Version_1_Java.Objekte.cSchueler;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Aaron on 30.03.2017.
 */
public class cOutputKontrollfeld extends JFrame {




    JTextField[] []arrFelderOutput;
    JTextField [][] arrFelderMenue= new JTextField[4][1];



    public void Darstellung_der_Schueler(cErweiterteHashMapProjekteSchuelerListe objSpeicherLoesung){

        arrFelderOutput = new JTextField[4][objSpeicherLoesung.iLaengeallerListen()];
        for(int i_x=0; i_x< arrFelderOutput.length;i_x++){
            for (int k_y=0;k_y<arrFelderOutput[i_x].length;k_y++){
                arrFelderOutput[i_x][k_y]= new JTextField();
                arrFelderOutput[i_x][k_y].setVisible(true);
               this.getContentPane().add(arrFelderOutput[i_x][k_y]);
               arrFelderOutput[i_x][k_y].setBounds(arrFelderMenue[i_x][0].getX(),arrFelderMenue[0][0].getHeight()+20*k_y, arrFelderMenue[i_x][0].getWidth(),20);
            }

        }

        ArrayList <cSchueler> listSchüler= new ArrayList<>();

        for (cProjekt Schleifenobjekt:objSpeicherLoesung.keySet()
                ) {
           listSchüler.addAll(objSpeicherLoesung.get(Schleifenobjekt));
        }


        for(int i_x=0; i_x< arrFelderOutput.length;i_x++){
            for (int k_y=0;k_y<arrFelderOutput[i_x].length;k_y++){
                switch (i_x){
                    case 0:
                        arrFelderOutput[i_x][k_y].setText(listSchüler.get(k_y).sVorname);
                        break;
                    case 1:
                        arrFelderOutput[i_x][k_y].setText(listSchüler.get(k_y).sNachname);
                        break;
                    case 2:
                        arrFelderOutput[i_x][k_y].setText(listSchüler.get(k_y).sKlassenstufe_mit_Buchstaben);
                        break;
                    case 3:
                        try {
                            arrFelderOutput[i_x][k_y].setText(listSchüler.get(k_y).Zugewiesenes_Projekt.sLehrerkuerzel);
                        }catch (NullPointerException e_1){
                            arrFelderOutput[i_x][k_y].setText("Dieser Schüler hat kein Projekt!");
                        }
                        break;
                }
            }
        }
    }


    public cOutputKontrollfeld(){


        this.setBounds(1500,500,500,500);
        this.setTitle("Zuordnung der Schueler zu Projekten  ");

        this.getContentPane().setLayout(null);



        for (int i_x=0;i_x<arrFelderMenue.length;i_x++){
            for(int k_y=0;k_y<arrFelderMenue[i_x].length;k_y++){
                arrFelderMenue[i_x][k_y]= new JTextField();
                arrFelderMenue[i_x][k_y].setVisible(true);
                this.getContentPane().add(arrFelderMenue[i_x][k_y]);

                switch(i_x){
                    case 0:
                        arrFelderMenue[i_x][k_y].setBounds(0,0,220,20);
                        arrFelderMenue[i_x][k_y].setText("Vorname");
                        break;

                    case 1:
                        arrFelderMenue[i_x][k_y].setBounds(220,0,170,20);
                        arrFelderMenue[i_x][k_y].setText("Nachname ");
                        break;

                    case 2:
                        arrFelderMenue[i_x][k_y].setBounds(390,0,50,20);
                        arrFelderMenue[i_x][k_y].setText("Klasse");
                        break;

                    case 3:
                        arrFelderMenue[i_x][k_y].setBounds(440,0,300,20);
                        arrFelderMenue[i_x][k_y].setText("Betreuender Lehrer");
                        break;
                }
            }
        }
    }
}

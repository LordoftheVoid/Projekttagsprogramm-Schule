package Version_1_Java.Interfaces;


import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertProjekte;
import Version_1_Java.Objekte.cProjekt;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Aaron on 30.03.2017.
 */
public class cProjektInput extends JFrame {


  static  JTextField[] []arrFelderInput;
    static JTextField [] arrFelderMenue= new JTextField[3];



    public  static void Suche (String arrStrings []){

        ArrayList< JTextField []> listArrYX_Anordnung= new ArrayList<>();
        class classarrBoolean{
            boolean [] arrBoolean;
            classarrBoolean(int iGröße){
                arrBoolean= new boolean[iGröße];
            }
            int iMengeanWertendesTypsTrue(){
                int iMengeanBooleans=0;
                for (boolean Schleifenelement:this.arrBoolean
                        ) {
                    if(Schleifenelement){
                        iMengeanBooleans++;
                    }
                }
                return iMengeanBooleans;
            }
        }

        HashMap<Integer, classarrBoolean> mapIntegerBooleanArray= new HashMap<>();
        int iLängeListe=0;
        for (int i = 0; i <arrFelderInput[0].length ; i++) {
            listArrYX_Anordnung.add(new JTextField[arrFelderInput.length]);
            for (int kueberX = 0; kueberX <arrFelderInput.length ; kueberX++) {
                listArrYX_Anordnung.get(iLängeListe)[kueberX]=arrFelderInput[kueberX][i];
            }
            iLängeListe++;
        }

        for (int i = 0; i < listArrYX_Anordnung.size() ; i++) {
            mapIntegerBooleanArray.put(i,new classarrBoolean(arrFelderInput.length));
        }

        for (int iueberY = 0; iueberY < listArrYX_Anordnung.size(); iueberY++) {
            for (int kueberX = 0; kueberX <listArrYX_Anordnung.get(iueberY).length ; kueberX++) {
                try {
                    if (listArrYX_Anordnung.get(iueberY)[kueberX].getText().contains(arrStrings[kueberX])) {
                        mapIntegerBooleanArray.get(iueberY).arrBoolean[kueberX]=true;
                    }
                }catch(NullPointerException e_1){
                    mapIntegerBooleanArray.get(iueberY).arrBoolean[kueberX]=true;
                }
            }
        }
        ArrayList<Integer> PositionenSpeicher= new ArrayList<>();

        for (Integer Schleifenobjekt :mapIntegerBooleanArray.keySet()
                ) {
            if(mapIntegerBooleanArray.get(Schleifenobjekt).iMengeanWertendesTypsTrue()==arrFelderInput.length){
                PositionenSpeicher.add(Schleifenobjekt);
            }
        }


        for (int i = 0; i <arrFelderInput.length ; i++) {
            for (int j = 0; j <arrFelderInput[i].length ; j++) {
                arrFelderInput[i][j].setVisible(false);
            }
        }

        for (int kueberY = 0; kueberY <PositionenSpeicher.size() ; kueberY++) {
            for (int iueberX = 0; iueberX <arrFelderInput.length ; iueberX++) {
                listArrYX_Anordnung.get(PositionenSpeicher.get(kueberY))[iueberX].setBounds(arrFelderMenue[iueberX].getX(),50+20*kueberY,arrFelderMenue[iueberX].getWidth(),arrFelderMenue[iueberX].getHeight());
                listArrYX_Anordnung.get(PositionenSpeicher.get(kueberY))[iueberX].setVisible(true);
            }
        }
    }















    public void update_der_Daten(cArrayListErweitertProjekte  list_main) {


            list_main.clear();



                /*

                Wertepruefung


                 */





                for (int k_y = 0; k_y < arrFelderMenue.length; k_y++) {
                       try{
                           list_main.add(new cProjekt(arrFelderInput[1][k_y].getText(),Integer.valueOf(arrFelderInput[0][k_y].getText()),Integer.valueOf(arrFelderInput[2][k_y].getText())));
                       }catch(NullPointerException | NumberFormatException e_1){

                       }
                }




        }



        public void update_des_Interfaces(cArrayListErweitertProjekte listuebergebeneProjekte){
                for(int i=0;i<listuebergebeneProjekte.size();i++){
                    arrFelderInput[0][i].setText(""+listuebergebeneProjekte.get(i).iProjektnummer);
                    arrFelderInput[1][i].setText(""+listuebergebeneProjekte.get(i).sLehrerkuerzel);
                    arrFelderInput[2][i].setText(""+listuebergebeneProjekte.get(i).iMaximaleSchueleranzahl);
            }
        }


    public cProjektInput(){



        JTextField [] arrSuchmaskenMenue=new JTextField[3];
        JTextField [] arrSuchmasken= new JTextField[3];


        for (int i = 0; i <arrSuchmasken.length ; i++) {
            arrSuchmasken[i]= new JTextField();
            this.getContentPane().add(arrSuchmasken[i]);

            arrSuchmasken[i].setBounds(300+150*i,50,150,20);
            arrSuchmasken[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }
                @Override
                public void keyPressed(KeyEvent e) {
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    String [] arrArgumente= new String[arrSuchmasken.length];
                    for (int j = 0; j < arrArgumente.length; j++) {
                        if(!Objects.equals(arrSuchmasken[j].getText(), "")){
                            arrArgumente[j]=arrSuchmasken[j].getText();
                        }else{
                            arrArgumente[j]=null;
                        }
                    }
                    Suche(arrArgumente);




                }
            });


        }
        for (int i = 0; i < arrSuchmaskenMenue.length; i++) {
            arrSuchmaskenMenue[i]= new JTextField();
            this.getContentPane().add(arrSuchmaskenMenue[i]);
            arrSuchmaskenMenue[i].setBounds(300+150*i,0,150,20);
            switch (i){
                case 0:
                    arrSuchmaskenMenue[i].setText("Projektnummersuche");
                    break;
                case 1:
                    arrSuchmaskenMenue[i].setText("Lehrerkuerzelsuche");
                    break;
                case 2:
                    arrSuchmaskenMenue[i].setText("Schueleranzahlsuche");
                    break;
            }
        }






        this.setBounds(1500,500,500,500);
        this.setTitle("Fenster zur Eingabe der Projekte  ");

        this.getContentPane().setLayout(null);

        for (int i_x=0;i_x<arrFelderMenue.length;i_x++){
                arrFelderMenue[i_x]= new JTextField();
                arrFelderMenue[i_x].setVisible(true);
                this.getContentPane().add(arrFelderMenue[i_x]);
                switch(i_x){
                    case 0:
                        arrFelderMenue[i_x].setBounds(0,0,90,20);
                        break;

                    case 1:
                        arrFelderMenue[i_x].setBounds(90,0,90,20);
                        break;

                    case 2:
                        arrFelderMenue[i_x].setBounds(180,0,100,20);
                        break;

                    case 3:
                        arrFelderMenue[i_x].setBounds(280,0,210,20);
                        break;
                }


        }


        arrFelderMenue[0].setText("Projektnumer");
        arrFelderMenue[1].setText("Lehrerkuerzel");
        arrFelderMenue[2].setText("Schueleranzahl");



        arrFelderInput= new JTextField[3][100];
        for (int i_x=0;i_x<arrFelderInput.length;i_x++){
            for(int k_y=0;k_y<arrFelderInput[i_x].length;k_y++){
                arrFelderInput[i_x][k_y]= new JTextField();
                this.getContentPane().add(arrFelderInput[i_x][k_y]);
                arrFelderInput[i_x][k_y].setVisible(true);
                if(i_x==0) {
                    arrFelderInput[i_x][k_y].setText(""+k_y);
                }
                if(i_x==0) {
                    arrFelderInput[i_x][k_y].setBounds(0, 20 * k_y + arrFelderMenue[0].getHeight(), arrFelderMenue[i_x].getWidth(), 20);
                }else{
                    arrFelderInput[i_x][k_y].setBounds(arrFelderMenue[i_x-1].getX()+arrFelderMenue[i_x-1].getWidth(), 20 * k_y + arrFelderMenue[0].getHeight(), arrFelderMenue[i_x].getWidth(), 20);
                }
            }
        }

    }

}

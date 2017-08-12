package Version_1_Java.Interfaces;


import Version_1_Java.DatenBankenSchnittstellen.c_Database_Manager;
import Version_1_Java.Objekte.Hashmaps_modified.cHash_Map_ID_projects_to_List_ID_pupils;

import javax.swing.*;

/**
 * Created by Aaron on 30.03.2017.
 */
public class cOutput_Frame extends JFrame {




    JTextField [][]arrFelderOutput;
    JTextField [] arrFelderMenue= new JTextField[4];


    c_Database_Manager obj_Database_Manager_Output;


    public void v_update_from_List_and_Database(cHash_Map_ID_projects_to_List_ID_pupils objlist_main){


        arrFelderOutput= new JTextField[4][objlist_main.i_amount_of_pupils];


        for (int i_x = 0; i_x < arrFelderOutput.length; i_x++) {
            for (int k_y = 0; k_y < arrFelderOutput[i_x].length; k_y++) {
                arrFelderOutput[i_x][k_y]= new JTextField();

                this.getContentPane().add(arrFelderOutput[i_x][k_y]);

                arrFelderOutput[i_x][k_y].setVisible(true);

                if (i_x == 0) {
                    arrFelderOutput[i_x][k_y].setBounds(0, 20 * k_y + arrFelderMenue[0].getHeight(), arrFelderMenue[i_x].getWidth(), 20);
                } else {
                    arrFelderOutput[i_x][k_y].setBounds(arrFelderMenue[i_x - 1].getX() + arrFelderMenue[i_x - 1].getWidth(), 20 * k_y + arrFelderMenue[0].getHeight(), arrFelderMenue[i_x].getWidth(), 20);
                }

            }
        }





    }


    public cOutput_Frame( c_Database_Manager obj_tm_DatabaseManager_Main){

        obj_Database_Manager_Output= obj_tm_DatabaseManager_Main;

        this.setBounds(1500,500,500,500);
        this.setTitle("Zuordnung der Schüler zu Projekten  ");

        this.getContentPane().setLayout(null);


        for (int i_x=0;i_x<arrFelderMenue.length;i_x++){
                arrFelderMenue[i_x]= new JTextField();
                arrFelderMenue[i_x].setVisible(true);
                this.getContentPane().add(arrFelderMenue[i_x]);

                switch(i_x){
                    case 0:
                        arrFelderMenue[i_x].setBounds(0,0,220,20);
                        arrFelderMenue[i_x].setText("Nachname");
                        break;

                    case 1:
                        arrFelderMenue[i_x].setBounds(220,0,170,20);
                        arrFelderMenue[i_x].setText("Vorname ");
                        break;

                    case 2:
                        arrFelderMenue[i_x].setBounds(390,0,50,20);
                        arrFelderMenue[i_x].setText("Klasse");
                        break;

                    case 3:
                        arrFelderMenue[i_x].setBounds(440,0,300,20);
                        arrFelderMenue[i_x].setText("Betreuender Lehrer");
                        break;
                }
            }
        }
}



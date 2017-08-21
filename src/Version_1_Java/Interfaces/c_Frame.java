package Version_1_Java.Interfaces;

import Version_1_Java.DatenBankenSchnittstellen.c_Database_Manager;
import Version_1_Java.cMain;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 14.08.2017.
 */
public class c_Frame  extends JFrame{



    private static JTextField [] arr_Search_Input= new JTextField[cMain.iMaximalanzahl_Projekte];
    private static JTextField [] arr_Search_Menue= new JTextField[cMain.iMaximalanzahl_Projekte];
    private static JTextField [] arr_Colum_Heads = new JTextField[cMain.iMaximalanzahl_Projekte];


    

    private c_Database_Manager objDatabaseManager_Input;


    CopyOnWriteArrayList < cmodTextField []>  list_entrys_rows= new CopyOnWriteArrayList<>();

    String s_Main_Table;




    public c_Frame( CopyOnWriteArrayList<String> list_colum_names_Frame, CopyOnWriteArrayList<String> list_colum_names_Database, int i_X, int k_Y, String s_Table_tm, c_Database_Manager obj_Database_Manager_tm) {

        this.objDatabaseManager_Input=obj_Database_Manager_tm;

        this.s_Main_Table = s_Table_tm;
        this.setVisible(true);
        this.setBounds(0, 0, 500, 500);
        this.getContentPane().setLayout(null);

        for (int i_x = 0; i_x < arr_Colum_Heads.length; i_x++) {
            arr_Colum_Heads[i_x] = new JTextField();
            arr_Colum_Heads[i_x].setVisible(true);
            this.getContentPane().add(arr_Colum_Heads[i_x]);
            arr_Colum_Heads[i_x].setText(list_colum_names_Frame.get(i_x));
            switch (i_x) {
                case 0:
                    arr_Colum_Heads[i_x].setBounds(0, 0, 90, 20);
                    break;

                case 1:
                    arr_Colum_Heads[i_x].setBounds(90, 0, 90, 20);
                    break;

                case 2:
                    arr_Colum_Heads[i_x].setBounds(180, 0, 100, 20);
                    break;

                case 3:
                    arr_Colum_Heads[i_x].setBounds(280, 0, 210, 20);
                    break;
            }
        }

        for (int i = 0; i < arr_Search_Menue.length; i++) {
            arr_Search_Menue[i] = new JTextField();
            this.getContentPane().add(arr_Search_Menue[i]);
            arr_Search_Menue[i].setBounds(700 + 150 * i, 0, 150, 20);
            arr_Search_Menue[i].setText(list_colum_names_Frame.get(i) + "-Suche");
            arr_Search_Input[i] = new JTextField();
            this.getContentPane().add(arr_Search_Input[i]);
            arr_Search_Input[i].setBounds(700 + 150 * i, 50, 150, 20);
            arr_Search_Input[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {

                    /*

                    Achtung, Input wichtig!!
                     */


                }
            });


        }

        /*

        Erschaffung des Fields aus Update
         */

        ResultSet set_entrys = null;
        try {
            set_entrys = objDatabaseManager_Input.read_entrys_all_attributes(this.s_Main_Table);

            int i_Row_Counter = 0;

            while (set_entrys.next()) {
                list_entrys_rows.add(new cmodTextField[arr_Colum_Heads.length]);
                {
                    int i_array_counter = 0;
                    for (cmodTextField loop_object : list_entrys_rows.get(i_Row_Counter)
                            ) {
                        loop_object = new cmodTextField();
                        loop_object.v_initiation(arr_Colum_Heads[i_array_counter].getX(), 20 * i_Row_Counter, arr_Colum_Heads[i_array_counter].getWidth(), 20);
                        loop_object.sunique_ID_Textfieldrow = set_entrys.getString(1);
                        loop_object.bcorrect_unique_ID = true;
                    }
                }
                list_entrys_rows.get(i_Row_Counter)[0].setText(set_entrys.getString(3));
                list_entrys_rows.get(i_Row_Counter)[1].setText(set_entrys.getString(2));
                list_entrys_rows.get(i_Row_Counter)[2].setText(set_entrys.getString(4));
                list_entrys_rows.get(i_Row_Counter)[3].setText(set_entrys.getString(5));
                list_entrys_rows.get(i_Row_Counter)[4].setText(set_entrys.getString(6));
                list_entrys_rows.get(i_Row_Counter)[5].setText(set_entrys.getString(7));
                list_entrys_rows.get(i_Row_Counter)[6].setText(set_entrys.getString(8));
                i_Row_Counter++;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }






    }



















    public void v_search(String s_content){


    }

    public void v_sort(){



    }












}

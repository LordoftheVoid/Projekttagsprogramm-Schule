package Version_1_Java.Interfaces;

import Version_1_Java.DatenBankenSchnittstellen.c_Database_Manager;

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


    private JTextField [] arr_Search_Input;
    private JTextField [] arr_Search_Menue;
    private JTextField [] arr_Colum_Heads;


    private c_Database_Manager objDatabaseManager_Input;



    CopyOnWriteArrayList <CopyOnWriteArrayList<cmodTextField>> list_entrys_rows= new CopyOnWriteArrayList<>();


    String s_Main_Table;




    public c_Frame(int i_X, int k_Y, String s_Table_tm, c_Database_Manager obj_Database_Manager_tm) {

        this.objDatabaseManager_Input=obj_Database_Manager_tm;
        this.s_Main_Table = s_Table_tm;
        this.setVisible(true);
        this.setBounds(i_X, k_Y, 500, 500);
        this.getContentPane().setLayout(null);


    }



    public void v_set_custom_Head( CopyOnWriteArrayList <String> list_s_values){

        arr_Colum_Heads= new JTextField[list_s_values.size()];

        for (int i_x = 0; i_x < arr_Colum_Heads.length; i_x++) {
            arr_Colum_Heads[i_x] = new JTextField();
            arr_Colum_Heads[i_x].setText(list_s_values.get(i_x));
            arr_Colum_Heads[i_x].setVisible(true);
            this.getContentPane().add(arr_Colum_Heads[i_x]);
            arr_Colum_Heads[i_x].setBounds(90*i_x, 0, 90, 20);

        }
    }

    public void v_set_custom_Search(CopyOnWriteArrayList <String> list_s_values) {

        arr_Search_Menue = new JTextField[list_s_values.size()];
        arr_Search_Input = new JTextField[list_s_values.size()];

        for (int i = 0; i < arr_Search_Menue.length; i++) {
            arr_Search_Menue[i] = new JTextField();
            arr_Search_Menue[i].setText(list_s_values.get(i)+ "-Suche");

            this.getContentPane().add(arr_Search_Menue[i]);
            arr_Search_Menue[i].setBounds(arr_Colum_Heads.length*90 + 100 + 120 * i, 0, 120, 20);
            arr_Search_Input[i] = new JTextField();
            this.getContentPane().add(arr_Search_Input[i]);
            arr_Search_Input[i].setBounds(arr_Colum_Heads.length* 90 + 100 + 120 * i, 50, 120, 20);
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
    }



    public void v_set_Search_Tags(){

    }



    public void v_Setup_Listener(int i_amount_ID_Listeners_tm){
        for (CopyOnWriteArrayList<cmodTextField> loop_object_list:list_entrys_rows
             ) {
            int i_Counter=0;
            for (cmodTextField loop_object_Field :loop_object_list
                 ) {
                if(i_Counter<i_amount_ID_Listeners_tm) {
                    loop_object_Field.addKeyListener(new cmodKeyListener_ID(objDatabaseManager_Input,list_entrys_rows,this.s_Main_Table, this.getContentPane()));
                }
                i_Counter++;
                loop_object_Field.addKeyListener(new cmodKeyListener_NON_ID(objDatabaseManager_Input,this.s_Main_Table));
            }
        }
    }


    public void v_generate_rows(int i_length, CopyOnWriteArrayList<String> list_Column_Names) {

        ResultSet set_entrys = null;
        CopyOnWriteArrayList<String> list_IDs = new CopyOnWriteArrayList<>();

        try {
            set_entrys = objDatabaseManager_Input.read_entrys_one_attribute(this.s_Main_Table, "s_unique_ID");
            while (set_entrys.next()) {
                list_IDs.add(set_entrys.getString(1));
            }

            for (int i = 0; i < list_IDs.size(); i++) {
                list_entrys_rows.add(new CopyOnWriteArrayList<>());
                for (int k = 0; k < i_length; k++) {
                    list_entrys_rows.get(i).add(new cmodTextField());
                    list_entrys_rows.get(i).get(k).v_initiation(90 * k, 50 + 20 * i, 90, 20, this.getContentPane());
                    list_entrys_rows.get(i).get(k).i_inside_Field_X =k;
                    list_entrys_rows.get(i).get(k).bcorrect_unique_ID=true;
                    list_entrys_rows.get(i).get(k).sunique_ID_Textfieldrow=list_IDs.get(i);
                }
            }
            {
                int i_row_Counter = 0;
                for (CopyOnWriteArrayList<cmodTextField> list_loop : list_entrys_rows
                        ) {
                    for (int i = 0; i < list_Column_Names.size(); i++) {
                        set_entrys = objDatabaseManager_Input.read_one_entry_one_attribute(this.s_Main_Table, list_Column_Names.get(i), list_IDs.get(i_row_Counter));
                        list_loop.get(i).setText(set_entrys.getString(1));
                    }
                    i_row_Counter++;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        list_entrys_rows.add(new CopyOnWriteArrayList<>());
        for (int i = 0; i < i_length; i++) {
            list_entrys_rows.get(list_entrys_rows.size()-1).add(new cmodTextField());
            list_entrys_rows.get(list_entrys_rows.size()-1).get(i).v_initiation(90*i,list_entrys_rows.get(list_entrys_rows.size()-2).get(0).getY()+20,90,20,this.getContentPane());
            list_entrys_rows.get(list_entrys_rows.size()-1).get(i).setText("Test");
            list_entrys_rows.get(list_entrys_rows.size()-1).get(i).setText("");
            list_entrys_rows.get(list_entrys_rows.size()-1).get(i).i_inside_Field_X =i;
            list_entrys_rows.get(list_entrys_rows.size()-1).get(i).bcorrect_unique_ID=false;
        }

    }






    public void v_search(String s_content){


    }

    public void v_sort(){


    }
}

package Version_1_Java.Frame_Related;

import Version_1_Java.File_Interactions.Database.c_Database_Manager;
import Version_1_Java.Lists.cHash_Map_ID_projects_to_List_ID_pupils;
import Version_1_Java.cMain;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 14.08.2017.
 */
public class c_Frame  extends JFrame{


    private c_mod_Text_Field[] arr_Search_Input;
    private JTextField [] arr_Search_Menue;
    private JTextField [] arr_Colum_Heads;
    private JButton [] arr_sort_Buttons;
    private boolean [] arr_b_Sort_direction;
    CopyOnWriteArrayList<Integer> list_y_coordinates_visible_rows= new CopyOnWriteArrayList<>();



    private c_Database_Manager objDatabaseManager_Input;

    final int  i_width_gobal= 90;




    CopyOnWriteArrayList <CopyOnWriteArrayList<c_mod_Text_Field>> list_Fields_X_Direction = new CopyOnWriteArrayList<>();


    String s_Main_Table;




    public c_Frame(String s_Table_tm, c_Database_Manager obj_Database_Manager_tm) {

        this.objDatabaseManager_Input=obj_Database_Manager_tm;
        this.s_Main_Table = s_Table_tm;
        this.getContentPane().setLayout(null);

    }

    public void v_show_Frame(int i_X, int k_Y){

        this.setVisible(true);
        this.setBounds(i_X, k_Y, 500, 500);
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
        arr_Search_Input = new c_mod_Text_Field[list_s_values.size()];

        for (int i = 0; i < arr_Search_Menue.length; i++) {
            arr_Search_Menue[i] = new JTextField();
            arr_Search_Menue[i].setText(list_s_values.get(i)+ "-Suche");

            this.getContentPane().add(arr_Search_Menue[i]);
            arr_Search_Menue[i].setBounds(arr_Colum_Heads.length*90 + 100 + 120 * i, 0, 120, 20);
            arr_Search_Input[i] = new c_mod_Text_Field();
            arr_Search_Input[i].i_inside_Field_X=i;
            arr_Search_Input[i].setText("");

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
                    v_search();
                }
            });


        }
    }



    public void v_add_new_empty_row (CopyOnWriteArrayList<String> list_colum_Names){
        for (int i_X = 0; i_X < list_Fields_X_Direction.size(); i_X++) {
            c_mod_Text_Field obj_loop= new c_mod_Text_Field();
            obj_loop.v_initiation( i_width_gobal*i_X,list_Fields_X_Direction.get(i_X).size()*20+50,90,20,this.getContentPane());
            obj_loop.setText("Test");
            obj_loop.setText("");
            obj_loop.i_inside_Field_X =i_X;
            obj_loop.i_inside_Field_Y =list_Fields_X_Direction.get(i_X).size();
            obj_loop.bcorrect_unique_ID=false;
            obj_loop.s_colum_identifier=list_colum_Names.get(i_X);
            list_Fields_X_Direction.get(i_X).add(obj_loop);
        }
    }


    public void v_Setup_Listener(int i_amount_ID_Listeners_tm){
        int i_Counter=0;

        for (CopyOnWriteArrayList<c_mod_Text_Field> loop_object_list: list_Fields_X_Direction
             ) {
            i_Counter++;
            for (c_mod_Text_Field loop_object_Field :loop_object_list
                 ) {
                if(i_Counter<=i_amount_ID_Listeners_tm) {
                    loop_object_Field.addKeyListener(new cmodKeyListener_ID(objDatabaseManager_Input, list_Fields_X_Direction,this.s_Main_Table, this.getContentPane()));
                }
                loop_object_Field.addKeyListener(new cmodKeyListener_NON_ID(objDatabaseManager_Input,this.s_Main_Table));
            }
        }
    }


    public void v_generate_rows_from_Database(int i_amount_of_colums, CopyOnWriteArrayList<String> list_Column_Names) {

        ResultSet set_entrys;
        CopyOnWriteArrayList<String> list_IDs = new CopyOnWriteArrayList<>();

        try {
            set_entrys = objDatabaseManager_Input.read_entrys_one_attribute(this.s_Main_Table, "s_unique_ID");
            while (set_entrys.next()) {
                list_IDs.add(set_entrys.getString(1));
            }
            for (int i_X = 0; i_X < i_amount_of_colums; i_X++) {
                list_Fields_X_Direction.add(new CopyOnWriteArrayList<>());
                for (int k_Y = 0; k_Y < list_IDs.size(); k_Y++) {
                    list_Fields_X_Direction.get(i_X).add(new c_mod_Text_Field());
                    list_Fields_X_Direction.get(i_X).get(k_Y).v_initiation(90 * i_X, 50 + 20 * k_Y, 90, 20, this.getContentPane());
                    list_Fields_X_Direction.get(i_X).get(k_Y).i_inside_Field_X =i_X;
                    list_Fields_X_Direction.get(i_X).get(k_Y).i_inside_Field_Y =k_Y;
                    list_Fields_X_Direction.get(i_X).get(k_Y).bcorrect_unique_ID=true;
                    list_Fields_X_Direction.get(i_X).get(k_Y).s_unique_ID_Textfieldrow=list_IDs.get(k_Y);
                    list_Fields_X_Direction.get(i_X).get(k_Y).s_colum_identifier=list_Column_Names.get(i_X);
                }

            }
            for (int i = 0; i < list_IDs.size(); i++) {
                list_y_coordinates_visible_rows.add(i);
            }

            int i_X = 0;
            for (CopyOnWriteArrayList<c_mod_Text_Field> list_loop : list_Fields_X_Direction
                    ) {
                for (int k_Y = 0; k_Y < list_loop.size(); k_Y++) {


                    set_entrys = objDatabaseManager_Input.read_one_entry_one_attribute(this.s_Main_Table, list_Column_Names.get(i_X), list_IDs.get(k_Y));

                    list_loop.get(k_Y).setText("Test");
                    list_loop.get(k_Y).setText("");
                    list_loop.get(k_Y).setText(set_entrys.getString(1));
                }
                i_X++;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.v_add_new_empty_row(list_Column_Names);
    }







    public void v_search(){
        int i_amount_empty_fields=0;

        for (CopyOnWriteArrayList<c_mod_Text_Field> loop_list:list_Fields_X_Direction
                ) {
            for (c_mod_Text_Field loop_Field:loop_list
                    ) {
                loop_Field.setLocation(100000,100000);
                loop_Field.setVisible(false);
            }
        }
        list_y_coordinates_visible_rows.clear();


        CopyOnWriteArrayList <Integer> list_solutions= new CopyOnWriteArrayList<>();
        int i_X_first_Entry=0;
        for ( c_mod_Text_Field loop_Field_Inp:arr_Search_Input
                ) {
            if (loop_Field_Inp.getText().equals("")) {
                i_amount_empty_fields++;
            }else{
                i_X_first_Entry=loop_Field_Inp.i_inside_Field_X;
                break;
            }
        }
        for (c_mod_Text_Field loop_obj_Field:list_Fields_X_Direction.get(i_X_first_Entry)
                ) {
            boolean b_equal_for_all=true;
            if(loop_obj_Field.getText().length()>0){
                for (int i = 0; i < arr_Search_Input[i_X_first_Entry].getText().length(); i++) {
                    if(Character.toLowerCase(loop_obj_Field.getText().charAt(i))!= Character .toLowerCase(arr_Search_Input[i_X_first_Entry].getText().charAt(i))){
                        b_equal_for_all=false;
                        break;
                    }
                }
            }else{
                b_equal_for_all=false;
            }
            if(b_equal_for_all){
                list_solutions.add(loop_obj_Field.i_inside_Field_Y);
            }
        }

        for (int i = i_X_first_Entry; i < arr_Search_Input.length ; i++) {
            for (Integer loop_obj_int:list_solutions
                 ) {
                if(!list_Fields_X_Direction.get(i).get(loop_obj_int).getText().contains(arr_Search_Input[i].getText())){
                    list_solutions.remove(loop_obj_int);
                }
            }
        }
        if(list_solutions.isEmpty()){
            cMain.v_update_Textaread_Status("Zu dieser Suche gibt es keinerlei Ergebnisse, bitte andere Parameter wählen oder Einträge modifizieren.");
        }

        {
            list_y_coordinates_visible_rows.addAll(list_solutions);
            int k_Y = 0;
            for (Integer loop_obj_int : list_solutions
                    ) {
                int i_X = 0;
                for (CopyOnWriteArrayList<c_mod_Text_Field> loop_obj_list : list_Fields_X_Direction
                        ) {
                    loop_obj_list.get(loop_obj_int).setVisible(true);
                    loop_obj_list.get(loop_obj_int).setLocation(i_X * i_width_gobal, 50 + k_Y * 20);
                    i_X++;
                }
                k_Y++;
            }
        }
        if(i_amount_empty_fields==arr_Search_Input.length){

            int i_X=0;
            for (CopyOnWriteArrayList<c_mod_Text_Field> loop_list:list_Fields_X_Direction
                 ) {
                int k_Y=0;
                for (c_mod_Text_Field loop_Field:loop_list
                     ) {
                    loop_Field.setLocation(i_X*  i_width_gobal,50 +20*k_Y);
                    loop_Field.setVisible(true);
                    list_y_coordinates_visible_rows.add(k_Y);
                    k_Y++;
                }
                i_X++;
            }
        }
    }



    public void v_sort_setup(){
        arr_sort_Buttons = new JButton[arr_Search_Input.length];
        arr_b_Sort_direction= new boolean[arr_sort_Buttons.length];
        for (int i = 0; i < arr_Search_Input.length; i++) {
            arr_sort_Buttons[i]= new JButton();
            this.getContentPane().add(arr_sort_Buttons[i]);
            arr_sort_Buttons[i].setBounds(arr_Search_Input[i].getX(),arr_Search_Input[i].getY()+50,90,90);
            arr_sort_Buttons[i].setBorder(new LineBorder( Color.black,1 ));
            arr_sort_Buttons[i].setVisible(true);
            arr_sort_Buttons[i].setText(" A ... Z");
            arr_b_Sort_direction[i]=true;
            arr_sort_Buttons[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    for (int j = 0; j < arr_sort_Buttons.length; j++) {

                        if(arr_sort_Buttons[j].equals(e.getSource())){
                            v_sort(arr_b_Sort_direction[j],j);
                        }
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

        }
    }


    public void v_sort(boolean b_direction_tm, int i_X_colum){


        TreeMap<String,CopyOnWriteArrayList<Integer> > map_Tree= new TreeMap<>();

        for (Integer loop_obj_i:list_y_coordinates_visible_rows
             ) {
            if(map_Tree.containsKey(list_Fields_X_Direction.get(i_X_colum).get(loop_obj_i).getText())){
                map_Tree.get(list_Fields_X_Direction.get(i_X_colum).get(loop_obj_i).getText()).add(list_Fields_X_Direction.get(i_X_colum).get(loop_obj_i).i_inside_Field_Y);
            }else{
                map_Tree.put(list_Fields_X_Direction.get(i_X_colum).get(loop_obj_i).getText(), new CopyOnWriteArrayList<>());
                map_Tree.get(list_Fields_X_Direction.get(i_X_colum).get(loop_obj_i).getText()).add(list_Fields_X_Direction.get(i_X_colum).get(loop_obj_i).i_inside_Field_Y);
            }
        }



        if(b_direction_tm){
            int k_Y=0;
            for (String loop_obj_s:map_Tree.keySet()
                 ) {
                for (Integer loop_obj_i:map_Tree.get(loop_obj_s)
                     ) {
                    int i_X=0;
                    for (CopyOnWriteArrayList<c_mod_Text_Field> loop_obj_list:list_Fields_X_Direction
                         ) {
                        loop_obj_list.get(loop_obj_i).setLocation(i_X*i_width_gobal, 50+ k_Y*20);
                        i_X++;
                    }
                    k_Y++;
                }
            }

        }else{
            int k_Y=list_y_coordinates_visible_rows.size();
            for (String loop_obj_s:map_Tree.keySet()
                    ) {
                for (Integer loop_obj_i:map_Tree.get(loop_obj_s)
                        ) {
                    int i_X=0;
                    for (CopyOnWriteArrayList<c_mod_Text_Field> loop_obj_list:list_Fields_X_Direction
                            ) {
                        loop_obj_list.get(loop_obj_i).setLocation(i_X*i_width_gobal, 50+ k_Y*20);
                        i_X++;
                    }
                    k_Y--;
                }
            }
        }
        arr_b_Sort_direction[i_X_colum]= !arr_b_Sort_direction[i_X_colum];
        if(arr_b_Sort_direction[i_X_colum]) {
            arr_sort_Buttons[i_X_colum].setText(" A ... Z");
        }else{
            arr_sort_Buttons[i_X_colum].setText("Z ... A");
        }
    }








    public void v_update_from_List_and_Database(cHash_Map_ID_projects_to_List_ID_pupils objlist_main) {
        int i_amount_of_people = 0;
        for (String s_loop_object : objlist_main.keySet()
                ) {
            i_amount_of_people = i_amount_of_people + objlist_main.get(s_loop_object).size();
        }


        /*

        Achtung Hardcode! 5 ist wo anders definiert!
         */

        for (int i_X = 0; i_X < 5; i_X++) {
            list_Fields_X_Direction.add(new CopyOnWriteArrayList<>());
            for (int k_Y = 0; k_Y < i_amount_of_people; k_Y++) {
                list_Fields_X_Direction.get(i_X).add(new c_mod_Text_Field());
                list_Fields_X_Direction.get(i_X).get(k_Y).v_initiation(90 * i_X, 50 + 20 * k_Y, 90, 20, this.getContentPane());
                list_Fields_X_Direction.get(i_X).get(k_Y).i_inside_Field_X =i_X;
                list_Fields_X_Direction.get(i_X).get(k_Y).i_inside_Field_Y =k_Y;
                list_Fields_X_Direction.get(i_X).get(k_Y).bcorrect_unique_ID=true;
            }

        }
        for (int i = 0; i < i_amount_of_people; i++) {
            list_y_coordinates_visible_rows.add(i);
        }



        int i_y_counter = 0;
        try {
            ResultSet set_personal_information;
            for (String s_loop_object : objlist_main.keySet()
                    ) {
                for (String s_loop_object_inner : objlist_main.get(s_loop_object)
                        ) {
                    set_personal_information = objDatabaseManager_Input.read_one_entry_one_attribute("persons", "s_sur_Name", s_loop_object_inner);
                    list_Fields_X_Direction.get(0).get(i_y_counter).setText(set_personal_information.getString(1));
                    set_personal_information = objDatabaseManager_Input.read_one_entry_one_attribute("persons", "s_pre_Name", s_loop_object_inner);
                    list_Fields_X_Direction.get(1).get(i_y_counter).setText(set_personal_information.getString(1));
                    set_personal_information = objDatabaseManager_Input.read_one_entry_one_attribute("persons", "s_grade", s_loop_object_inner);
                    list_Fields_X_Direction.get(2).get(i_y_counter).setText(set_personal_information.getString(1));
                    set_personal_information = objDatabaseManager_Input.read_one_entry_one_attribute("projects", "s_unique_ID", s_loop_object);
                    list_Fields_X_Direction.get(3).get(i_y_counter).setText(set_personal_information.getString(1));
                    i_y_counter++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

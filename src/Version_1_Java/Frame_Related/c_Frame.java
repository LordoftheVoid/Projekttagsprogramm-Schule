package Version_1_Java.Frame_Related;

import Version_1_Java.File_Interactions.Database.cDatabaseConnectionManager;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 14.08.2017.
 */



/*

Gemeinsame Klasse aller Fenster, realisiert Aufbau, Anlegung der Verknüpfung zur Datenbank etc

 */
public class c_Frame extends JFrame {


    private JTextField [] arr_Search_Input;
    private JTextField[] arrSearchMenue;
    private JTextField[] arrColumHeadRow;
    private JButton[] arr_sort_Buttons;
    private boolean[] arr_b_Sort_direction;



    public JTextField[] arrCreateEntryFields;


    public cDatabaseConnectionManager objDatabaseManager_Input;

    private CopyOnWriteArrayList<String> list_Column_Names;

    final int i_width_gobal = 120;
    final int yCoordinateListEntrys = 260;




    /*

    Schon neu
     */

    CopyOnWriteArrayList<cRowEntrys> listRows = new CopyOnWriteArrayList<>();



    public String s_Main_Table;


    public c_Frame(String s_Table_tm, cDatabaseConnectionManager obj_Database_Manager_tm, CopyOnWriteArrayList<String> list_Columns) {

        this.list_Column_Names =list_Columns;

        this.objDatabaseManager_Input = obj_Database_Manager_tm;
        this.s_Main_Table = s_Table_tm;
        this.getContentPane().setLayout(null);


    }

    public void v_show_Frame(int i_X, int k_Y) {

        this.setVisible(true);
        this.setBounds(i_X, k_Y, 500, 500);
    }


    public void v_setupEntryfields(String text, int amount){

        arrCreateEntryFields = new JTextField[amount];

        for (int i = 0; i < arrCreateEntryFields.length; i++) {
            arrCreateEntryFields[i]= new JTextField();
            this.getContentPane().add(arrCreateEntryFields[i]);
            arrCreateEntryFields[i].setBounds(i*i_width_gobal,yCoordinateListEntrys -40,i_width_gobal,20);
            arrCreateEntryFields[i].setVisible(true);


            arrCreateEntryFields[i].addKeyListener(new cmodKeyListener_ID(this));

        }
        JTextField createEntryField;

        createEntryField= new JTextField(text);
        this.getContentPane().add(createEntryField);
        createEntryField.setBounds(0,yCoordinateListEntrys -60,i_width_gobal*3,20);
        createEntryField.setVisible(true);
    }

    public void v_set_custom_Head(CopyOnWriteArrayList<String> list_s_values) {

        arrColumHeadRow = new JTextField[list_s_values.size()];

        for (int i_x = 0; i_x < arrColumHeadRow.length; i_x++) {
            arrColumHeadRow[i_x] = new JTextField();
            arrColumHeadRow[i_x].setText(list_s_values.get(i_x));
            arrColumHeadRow[i_x].setVisible(true);
            this.getContentPane().add(arrColumHeadRow[i_x]);
            arrColumHeadRow[i_x].setBounds(120 * i_x, 0, i_width_gobal, 20);

        }
    }

    public void v_set_custom_Search(CopyOnWriteArrayList<String> list_s_values) {

        arrSearchMenue = new JTextField[list_s_values.size()];
        arr_Search_Input = new JTextField[list_s_values.size()];

        for (int i = 0; i < arrSearchMenue.length; i++) {

            arrSearchMenue[i] = new JTextField();
            arrSearchMenue[i].setText("Suche nach:");
            arrSearchMenue[i].setBorder(new LineBorder(Color.RED,1));

            this.getContentPane().add(arrSearchMenue[i]);
            arrSearchMenue[i].setBounds(120 * i, 40, i_width_gobal, 20);
            arr_Search_Input[i] = new JTextField();
            arr_Search_Input[i].setText("");

            this.getContentPane().add(arr_Search_Input[i]);
            arr_Search_Input[i].setBounds(120 * i, 70, 120, 20);
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




    public void v_Setup_Listener() {


        for (cRowEntrys objList:listRows
             ) {
            for (int i = 0; i < objList.fields.length; i++) {
                objList.fields[i].addKeyListener(new cmodKeyListener_NON_ID(objDatabaseManager_Input,s_Main_Table,objList,list_Column_Names.get(i)));
            }
        }

    }


    public void v_generate_rows_from_Database() {

        ResultSet set_entrys;
        CopyOnWriteArrayList<String> list_IDs = new CopyOnWriteArrayList<>();

        try {
            set_entrys = objDatabaseManager_Input.read_entrys_one_attribute(this.s_Main_Table, "s_unique_ID");
            while (set_entrys.next()) {
                if (!set_entrys.getString(1).equals("-1")) {
                    list_IDs.add(set_entrys.getString(1));
                }
            }

            for (String objList:list_IDs
                 ) {
                listRows.add(new cRowEntrys(this,objList));
            }

            for (int j = 0; j < listRows.size(); j++) {
                listRows.get(j).v_setup(list_Column_Names.size(),this.getContentPane(),yCoordinateListEntrys+j*20);
                for (int k = 0; k < listRows.get(j).fields.length; k++) {
                    set_entrys = objDatabaseManager_Input.read_one_entry_one_attribute(this.s_Main_Table, list_Column_Names.get(k), listRows.get(j).suniqueRowID);
                    listRows.get(j).v_setCellContent(k,set_entrys.getString(1));
                }
            }
        } catch (SQLException e) {
            cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
        }
    }



    public void v_search() {


        /*





         */

        HashMap<Integer, String> mapValues = new HashMap<>();

        ArrayList<Integer> listResults = new ArrayList<>();

        for (int i = 0; i < arr_Search_Input.length; i++) {
            if(arr_Search_Input[i].getText().length()>0){
                mapValues.put(i,arr_Search_Input[i].getText());
            }
        }

        if(mapValues.size()>0){
            for (int i = 0; i < listRows.size(); i++) {
                if(listRows.get(i).b_searchRow(mapValues)){
                    listResults.add(i);
                }
                listRows.get(i).v_disable();
            }

            int i=0;
            for (Integer objList:listResults
                 ) {
                listRows.get(objList).v_enable();
                listRows.get(objList).v_setYCoordinate(yCoordinateListEntrys+i*20);
                i++;
            }


        }else{
            for (int i = 0; i < listRows.size(); i++) {
                listRows.get(i).v_setYCoordinate(yCoordinateListEntrys+20*i);
                listRows.get(i).v_enable();
            }
        }

    }


    public void v_sort_setup() {
        arr_sort_Buttons = new JButton[arr_Search_Input.length];
        arr_b_Sort_direction = new boolean[arr_sort_Buttons.length];
        for (int i = 0; i < arr_Search_Input.length; i++) {
            arr_sort_Buttons[i] = new JButton();
            this.getContentPane().add(arr_sort_Buttons[i]);
            arr_sort_Buttons[i].setBounds(arr_Search_Input[i].getX(), arr_Search_Input[i].getY() + 50, 120, 50);
            arr_sort_Buttons[i].setBorder(new LineBorder(Color.RED, 1));
            arr_sort_Buttons[i].setVisible(true);
            arr_sort_Buttons[i].setText(" A ... Z");
            arr_b_Sort_direction[i] = true;
            arr_sort_Buttons[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    for (int j = 0; j < arr_sort_Buttons.length; j++) {

                        if (arr_sort_Buttons[j].equals(e.getSource())) {
                            v_sort(arr_b_Sort_direction[j], j);
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


    public void v_sort(boolean b_direction_tm, int i_X_colum) {


        TreeMap<String, Integer> mapStringInteger = new TreeMap<>();


        for (int i = 0; i < listRows.size(); i++) {
            if(listRows.get(i).bEnabled){
                mapStringInteger.put(listRows.get(i).getCellContent(i_X_colum),i);
            }
        }



        if(b_direction_tm){
            int i=0;
            for (String objList:mapStringInteger.keySet()
                    ) {
                listRows.get(mapStringInteger.get(objList)).v_setYCoordinate(yCoordinateListEntrys+20*i);
                listRows.get(mapStringInteger.get(objList)).v_enable();
                i++;
            }
        }else{
            int i = mapStringInteger.keySet().size();
            for (String objList:mapStringInteger.keySet()
                    ) {
                listRows.get(mapStringInteger.get(objList)).v_setYCoordinate(yCoordinateListEntrys+20*i);
                listRows.get(mapStringInteger.get(objList)).v_enable();
                i--;
            }


        }

        arr_b_Sort_direction[i_X_colum] = !arr_b_Sort_direction[i_X_colum];
        if (arr_b_Sort_direction[i_X_colum]) {
            arr_sort_Buttons[i_X_colum].setText(" A ... Z");
        } else {
            arr_sort_Buttons[i_X_colum].setText("Z ... A");
        }
    }


    public void v_update_from_List_and_Database(cHash_Map_ID_projects_to_List_ID_pupils objlist_main) {
        int i_amount_of_people = 0;
        for (String s_loop_object : objlist_main.keySet()
                ) {
            i_amount_of_people = i_amount_of_people + objlist_main.get(s_loop_object).size();
        }


        int i = 0;
        while(i<i_amount_of_people){
            listRows.add(new cRowEntrys(objDatabaseManager_Input,i_width_gobal));
            listRows.get(i).v_ShortSetup(5,this.getContentPane(),yCoordinateListEntrys + 20*i);
            i++;
        }


        /*

        Achtung Hardcode! 5 ist wo anders definiert!




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
                    set_personal_information = objDatabaseManager_Input.read_one_entry_one_attribute("projects", "s_teacher_ID", s_loop_object);
                    list_Fields_X_Direction.get(3).get(i_y_counter).setText(set_personal_information.getString(1));

                    boolean projektFound = false;


                    /*
                    Achtung Hardcode!


                    for (int i = 0; i < 4; i++) {
                        String Colum = "i_pref" + String.valueOf(i);
                        set_personal_information = objDatabaseManager_Input.read_one_entry_one_attribute("persons", Colum, s_loop_object_inner);
                        if (s_loop_object.equals(set_personal_information.getString(1))) {
                            list_Fields_X_Direction.get(4).get(i_y_counter).setText(s_loop_object);
                            projektFound = true;
                            break;
                        }
                    }
                    if (!projektFound) {
                        list_Fields_X_Direction.get(4).get(i_y_counter).setText("Kein Projekt!");
                    }


                    i_y_counter++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    */
    }


}

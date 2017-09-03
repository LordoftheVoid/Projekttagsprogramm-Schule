package Version_1_Java.Frame_Related;

import Version_1_Java.File_Interactions.Database.c_Database_Manager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 30.07.2017.
 */
public class cmodKeyListener_ID implements KeyListener {

    c_Database_Manager objDatabaseManager_keyListener;


    CopyOnWriteArrayList <CopyOnWriteArrayList<c_mod_Text_Field>> list_entrys_rows_Listener;


    String s_table_reference;

    Container con_target;

    cmodKeyListener_ID(c_Database_Manager objDatabasemanager_main, CopyOnWriteArrayList<CopyOnWriteArrayList<c_mod_Text_Field>> list_Inputs_tm, String table_source, Container con_source){
        this.con_target=con_source;
        this.list_entrys_rows_Listener=list_Inputs_tm;
        objDatabaseManager_keyListener =objDatabasemanager_main;
        s_table_reference =table_source;

    }




    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {


        c_mod_Text_Field objsource = ((c_mod_Text_Field) e.getSource());


        if(this.s_table_reference.equals("pupils")) {
            if (list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_Y).getText().length() >= 3 & list_entrys_rows_Listener.get(1).get(objsource.i_inside_Field_Y).getText().length() >= 3) {
                String s_unique_id = "";
                for (int i = 0; i < 2; i++) {
                    for (int k = 0; k < 2; k++) {
                        s_unique_id = s_unique_id + list_entrys_rows_Listener.get(i).get(objsource.i_inside_Field_Y).getText().charAt(k);
                    }
                }
                try {
                    if (objDatabaseManager_keyListener.entry_check(this.s_table_reference, s_unique_id)) {
                        objDatabaseManager_keyListener.create_entry(this.s_table_reference, s_unique_id);
                        for (int i_X = 0; i_X < list_entrys_rows_Listener.get(0).size(); i_X++) {
                            list_entrys_rows_Listener.get(i_X).get(objsource.i_inside_Field_Y).bcorrect_unique_ID = true;
                            list_entrys_rows_Listener.get(i_X).get(objsource.i_inside_Field_Y).s_unique_ID_Textfieldrow = s_unique_id;
                        }
                        objDatabaseManager_keyListener.update_entry(this.s_table_reference, objsource.s_unique_ID_Textfieldrow, "s_pre_Name", list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).getText());

                        CopyOnWriteArrayList <c_mod_Text_Field> list_new_Fields = new CopyOnWriteArrayList<>();
                        for (int i = 0; i < list_entrys_rows_Listener.get(0).size(); i++) {
                            list_new_Fields.add(new c_mod_Text_Field());
                            list_new_Fields.get(i).v_initiation(90*i,list_entrys_rows_Listener.get(list_entrys_rows_Listener.size()-2).get(0).getY()+40,90,20,this.con_target);
                            list_new_Fields.get(i).setText("Text");
                            list_new_Fields.get(i).setText("");
                            if(i<=2){
                                list_new_Fields.get(i).addKeyListener(new cmodKeyListener_ID(this.objDatabaseManager_keyListener, this.list_entrys_rows_Listener, this.s_table_reference, this.con_target));
                            }
                            list_new_Fields.get(i).addKeyListener(new cmodKeyListener_NON_ID(objDatabaseManager_keyListener,this.s_table_reference));

                        }

                        list_entrys_rows_Listener.add(list_new_Fields);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if(list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).getText().equals("")){
                try {
                    objDatabaseManager_keyListener.delete_entry(s_table_reference,objsource.s_unique_ID_Textfieldrow);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                    list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).bcorrect_unique_ID = false;
                    list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).s_unique_ID_Textfieldrow = "";
                }
            }
        }

        if(this.s_table_reference.equals("projekte")){
            if(objsource.getText().equals("")){

                try {
                    objDatabaseManager_keyListener.delete_entry(s_table_reference,objsource.s_unique_ID_Textfieldrow);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                    list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).bcorrect_unique_ID = false;
                    list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).s_unique_ID_Textfieldrow = "";
                }

            }else {
                try {
                    if (objDatabaseManager_keyListener.entry_check(this.s_table_reference, objsource.getText())) {
                        objDatabaseManager_keyListener.create_entry(this.s_table_reference, objsource.getText());
                        for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                            list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).bcorrect_unique_ID = true;
                            list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).s_unique_ID_Textfieldrow = objsource.getText();
                        }

                        CopyOnWriteArrayList <c_mod_Text_Field> list_new_Fields = new CopyOnWriteArrayList<>();
                        for (int i = 0; i < list_entrys_rows_Listener.get(0).size(); i++) {
                            list_new_Fields.add(new c_mod_Text_Field());
                            list_new_Fields.get(i).v_initiation(90*i,list_entrys_rows_Listener.get(list_entrys_rows_Listener.size()-2).get(0).getY()+40,90,20,this.con_target);
                            list_new_Fields.get(i).setText("Text");
                            list_new_Fields.get(i).setText("");

                        }


                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }






    }
}

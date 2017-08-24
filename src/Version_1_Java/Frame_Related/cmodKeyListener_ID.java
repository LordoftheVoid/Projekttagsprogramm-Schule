package Version_1_Java.Interfaces;

import Version_1_Java.DatenBankenSchnittstellen.c_Database_Manager;

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


    CopyOnWriteArrayList <CopyOnWriteArrayList<cmodTextField>> list_entrys_rows_Listener;


    String table;

    Container con_target;

    cmodKeyListener_ID(c_Database_Manager objDatabasemanager_main,  CopyOnWriteArrayList <CopyOnWriteArrayList<cmodTextField>> list_Inputs_tm, String table_source, Container con_target_tm){
        this.con_target=con_target_tm;
        this.list_entrys_rows_Listener=list_Inputs_tm;
        objDatabaseManager_keyListener =objDatabasemanager_main;
        table=table_source;

    }




    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {


        cmodTextField objsource = ((cmodTextField) e.getSource());


        if(this.table.equals("pupils")) {
            if (list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).getText().length() >= 3 & list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(1).getText().length() >= 3) {
                String s_unique_id = "";

                for (int i = 0; i < 2; i++) {
                    for (int k = 0; k < 2; k++) {
                        s_unique_id = s_unique_id + list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).getText().charAt(k);
                    }
                }

                System.out.println(objsource.getText()+"  ID"+ s_unique_id);

                try {
                    if (objDatabaseManager_keyListener.entry_check(this.table, s_unique_id)) {
                        objDatabaseManager_keyListener.create_entry(this.table, s_unique_id);
                        for (int i_x = 0; i_x < list_entrys_rows_Listener.get(0).size(); i_x++) {
                            list_entrys_rows_Listener.get(i_x).get(objsource.i_inside_Field_X).bcorrect_unique_ID = true;
                            list_entrys_rows_Listener.get(i_x).get(objsource.i_inside_Field_X).sunique_ID_Textfieldrow = s_unique_id;
                        }
                        objDatabaseManager_keyListener.update_entry(this.table, objsource.sunique_ID_Textfieldrow, "s_pre_Name", list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).getText());

                        CopyOnWriteArrayList <cmodTextField> list_new_Fields = new CopyOnWriteArrayList<>();
                        for (int i = 0; i < list_entrys_rows_Listener.get(0).size(); i++) {
                            list_new_Fields.add(new cmodTextField());
                            list_new_Fields.get(i).v_initiation(90*i,list_entrys_rows_Listener.get(list_entrys_rows_Listener.size()-2).get(0).getY()+40,90,20,this.con_target);
                            list_new_Fields.get(i).setText("Text");
                            list_new_Fields.get(i).setText("");

                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }

            if(list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).getText().equals("")){

                try {
                    objDatabaseManager_keyListener.delete_entry(table,objsource.sunique_ID_Textfieldrow);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                    list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).bcorrect_unique_ID = false;
                    list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).sunique_ID_Textfieldrow = "";
                }

            }

        }

        if(this.table.equals("projekte")){
            if(objsource.getText().equals("")){

                try {
                    objDatabaseManager_keyListener.delete_entry(table,objsource.sunique_ID_Textfieldrow);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                    list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).bcorrect_unique_ID = false;
                    list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).sunique_ID_Textfieldrow = "";
                }

            }else {
                try {
                    if (objDatabaseManager_keyListener.entry_check(this.table, objsource.getText())) {
                        objDatabaseManager_keyListener.create_entry(this.table, objsource.getText());
                        for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                            list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).bcorrect_unique_ID = true;
                            list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).sunique_ID_Textfieldrow = objsource.getText();
                        }

                        CopyOnWriteArrayList <cmodTextField> list_new_Fields = new CopyOnWriteArrayList<>();
                        for (int i = 0; i < list_entrys_rows_Listener.get(0).size(); i++) {
                            list_new_Fields.add(new cmodTextField());
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

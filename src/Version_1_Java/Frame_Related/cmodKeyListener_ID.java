package Version_1_Java.Frame_Related;

import Version_1_Java.File_Interactions.Database.cDatabaseConnectionManager;
import Version_1_Java.cMain;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 30.07.2017.
 */


/*

Implementiert das Löschen bzw Erzeugen neuer Einträge über die Möglichkeit, den Primärschlüssel vorhandener
Einträge zu manipulieren


 */
public class cmodKeyListener_ID implements KeyListener {

    cDatabaseConnectionManager objDatabaseManager_keyListener;


    CopyOnWriteArrayList<CopyOnWriteArrayList<c_mod_Text_Field>> list_entrys_rows_Listener;


    String s_table_reference;

    Container con_target;

    cmodKeyListener_ID(cDatabaseConnectionManager objDatabasemanager_main, CopyOnWriteArrayList<CopyOnWriteArrayList<c_mod_Text_Field>> list_Inputs_tm, String table_source, Container con_source) {
        this.con_target = con_source;
        this.list_entrys_rows_Listener = list_Inputs_tm;
        objDatabaseManager_keyListener = objDatabasemanager_main;
        s_table_reference = table_source;

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

        if (this.s_table_reference.equals("persons")) {

            System.out.println(list_entrys_rows_Listener.size());
            System.out.println(list_entrys_rows_Listener.get(0).size());


            if (list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_Y).getText().length() >= 3 & list_entrys_rows_Listener.get(1).get(objsource.i_inside_Field_Y).getText().length() >= 3) {
                String s_unique_id = "";
                for (int i = 0; i < 2; i++) {
                    for (int k = 0; k < 2; k++) {
                        s_unique_id = s_unique_id + list_entrys_rows_Listener.get(i).get(objsource.i_inside_Field_Y).getText().charAt(k);
                    }
                }
                try {
                    if (objDatabaseManager_keyListener.entry_check(this.s_table_reference, s_unique_id)) {
                        if (objDatabaseManager_keyListener.create_entry(this.s_table_reference, s_unique_id)) {
                            cMain.v_update_Textarea_Status("Ein Datenbankeintrag wurde angelegt");
                        }

                        for (int i_X = 0; i_X < list_entrys_rows_Listener.size(); i_X++) {
                            list_entrys_rows_Listener.get(i_X).get(objsource.i_inside_Field_Y).bcorrect_unique_ID = true;
                            list_entrys_rows_Listener.get(i_X).get(objsource.i_inside_Field_Y).s_unique_ID_Textfieldrow = s_unique_id;
                        }

                        objDatabaseManager_keyListener.update_entry(this.s_table_reference, objsource.s_unique_ID_Textfieldrow, "s_sur_Name", list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_Y).getText());


                        for (int i_X = 0; i_X < list_entrys_rows_Listener.size(); i_X++) {
                            c_mod_Text_Field obj_loop = new c_mod_Text_Field();
                            obj_loop.v_initiation(90 * i_X, list_entrys_rows_Listener.get(i_X).size() * 20 + 50, 90, 20, this.con_target);
                            obj_loop.setText("Test");
                            obj_loop.setText("");
                            obj_loop.i_inside_Field_X = i_X;
                            obj_loop.i_inside_Field_Y = list_entrys_rows_Listener.get(i_X).size();
                            obj_loop.bcorrect_unique_ID = false;
                            obj_loop.s_colum_identifier = list_entrys_rows_Listener.get(i_X).get(0).s_colum_identifier;
                            list_entrys_rows_Listener.get(i_X).add(obj_loop);

                            if (i_X <= 2) {
                                obj_loop.addKeyListener(new cmodKeyListener_ID(this.objDatabaseManager_keyListener, this.list_entrys_rows_Listener, this.s_table_reference, this.con_target));
                            }
                            obj_loop.addKeyListener(new cmodKeyListener_NON_ID(this.objDatabaseManager_keyListener, this.s_table_reference));

                        }

                    }
                } catch (SQLException e1) {
                    cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
                }
            }
            if (list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_Y).getText().equals("")) {

                objDatabaseManager_keyListener.delete_entry(s_table_reference, objsource.s_unique_ID_Textfieldrow);


                for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                    list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).bcorrect_unique_ID = false;
                    list_entrys_rows_Listener.get(objsource.i_inside_Field_X).get(0).s_unique_ID_Textfieldrow = "";
                    list_entrys_rows_Listener.remove(objsource.i_inside_Field_X);
                }

            }
        }

        if (this.s_table_reference.equals("projects")) {
            if (objsource.getText().equals("")) {
                objDatabaseManager_keyListener.delete_entry(s_table_reference, objsource.s_unique_ID_Textfieldrow);


                for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                    list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).bcorrect_unique_ID = false;
                    list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).s_unique_ID_Textfieldrow = "";
                }

            } else {
                try {
                    if (objDatabaseManager_keyListener.entry_check(this.s_table_reference, objsource.getText())) {
                        if (objDatabaseManager_keyListener.create_entry(this.s_table_reference, objsource.getText())) {
                            cMain.v_update_Textarea_Status("Ein Datenbankeintrag wurde angelegt");
                        }

                        for (int i_x = 0; i_x < list_entrys_rows_Listener.size(); i_x++) {
                            list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).bcorrect_unique_ID = true;
                            list_entrys_rows_Listener.get(0).get(objsource.i_inside_Field_X).s_unique_ID_Textfieldrow = objsource.getText();
                        }
                        for (int i_X = 0; i_X < list_entrys_rows_Listener.size(); i_X++) {
                            c_mod_Text_Field obj_loop = new c_mod_Text_Field();
                            obj_loop.v_initiation(90 * i_X, list_entrys_rows_Listener.get(i_X).size() * 20 + 50, 90, 20, this.con_target);
                            obj_loop.setText("Test");
                            obj_loop.setText("");
                            obj_loop.i_inside_Field_X = i_X;
                            obj_loop.i_inside_Field_Y = list_entrys_rows_Listener.get(i_X).size();
                            obj_loop.bcorrect_unique_ID = false;
                            obj_loop.s_colum_identifier = list_entrys_rows_Listener.get(i_X).get(0).s_colum_identifier;
                            list_entrys_rows_Listener.get(i_X).add(obj_loop);

                            if (i_X == 0) {
                                obj_loop.addKeyListener(new cmodKeyListener_ID(this.objDatabaseManager_keyListener, this.list_entrys_rows_Listener, this.s_table_reference, this.con_target));
                            }
                            obj_loop.addKeyListener(new cmodKeyListener_NON_ID(this.objDatabaseManager_keyListener, s_table_reference));
                        }


                    }
                } catch (SQLException e1) {
                    cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
                }
            }
        }


    }
}

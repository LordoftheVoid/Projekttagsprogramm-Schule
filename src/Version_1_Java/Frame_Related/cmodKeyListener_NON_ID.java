package Version_1_Java.Frame_Related;

import Version_1_Java.File_Interactions.Database.c_Database_Manager;
import Version_1_Java.cMain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Aaron on 30.07.2017.
 */
class cmodKeyListener_NON_ID implements KeyListener {

    private c_Database_Manager objDatabaseManager_keyListener;


    private String s_table_Listener;

    cmodKeyListener_NON_ID(c_Database_Manager objDatabasemanager_main, String table_source_tm) {
        s_table_Listener = table_source_tm;
        objDatabaseManager_keyListener = objDatabasemanager_main;

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
        if (objsource.bcorrect_unique_ID) {
            if (objDatabaseManager_keyListener.update_entry(s_table_Listener, objsource.s_unique_ID_Textfieldrow, objsource.s_colum_identifier, objsource.getText())) {
                cMain.v_update_Textarea_Status("Ein Wert in der Datenbank wurde erfolgreich geändert");
            } else {
                objsource.setText("");
                cMain.v_update_Textarea_Status("Dies war kein erlaubter Wert, bitte erst ein Projekt mit dieser Nummer initieren");
            }

        }

    }
}
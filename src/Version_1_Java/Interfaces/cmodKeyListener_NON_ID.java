package Version_1_Java.Interfaces;

import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

/**
 * Created by Aaron on 30.07.2017.
 */
class cmodKeyListener_NON_ID implements KeyListener {

    private cDatabaseManager objDatabaseManager_keyListener;

    // --Commented out by Inspection (11.08.2017 15:26):private cmodTextField[][] arrInout_Keylistener;

    private String table;

    cmodKeyListener_NON_ID(cDatabaseManager objDatabasemanager_main, cmodTextField[][] arrInput_Interface, String table_source ){
        table=table_source;
        objDatabaseManager_keyListener =objDatabasemanager_main;
        arrInout_Keylistener =arrInput_Interface;

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
        if(objsource.bcorrect_unique_ID) {
            try {
                objDatabaseManager_keyListener.update_entry(table,objsource.sunique_ID_Textfieldrow, objsource.colum, objsource.getText());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
}
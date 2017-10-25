package Version_1_Java.Frame_Related;

import Version_1_Java.File_Interactions.Database.cDatabaseConnectionManager;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Aaron on 30.07.2017.
 */



/*
Implementiert das Ändern trivaler Werte, beispielsweise Klassenstufe

 */
class cmodKeyListener_NON_ID implements KeyListener {

    private cDatabaseConnectionManager objDatabaseManager_keyListener;
    private String s_table_Listener;
    cRowEntries objRow;
    String sTableColumRef;

    cmodKeyListener_NON_ID(cDatabaseConnectionManager objDatabasemanager_main, String table_source_tm, cRowEntries objSourceRow, String ColumRef) {
        s_table_Listener = table_source_tm;
        objDatabaseManager_keyListener = objDatabasemanager_main;
        objRow = objSourceRow;
       sTableColumRef = ColumRef;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        JTextField objSource = (JTextField) e.getSource();
            objDatabaseManager_keyListener.updateEntry(s_table_Listener, objRow.suniqueRowID, sTableColumRef, objSource.getText());


    }
}
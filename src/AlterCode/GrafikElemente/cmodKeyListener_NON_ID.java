package AlterCode.GrafikElemente;

import NeuSortierung.DataBaseInteractions.CDatabaseInterface;

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

    cRowEntries objRow;
    String sTableColumRef;
    private CDatabaseInterface objDatabaseManager_keyListener;
    private String s_table_Listener;

    cmodKeyListener_NON_ID(String table_source_tm, cRowEntries objSourceRow, String ColumRef) {
        s_table_Listener = table_source_tm;
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
        //  cImports.objDatabaseManagerGlobal.updateEntry(s_table_Listener, objRow.suniqueRowID, sTableColumRef, objSource.getText());
    }
}
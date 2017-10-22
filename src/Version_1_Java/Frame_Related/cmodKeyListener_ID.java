package Version_1_Java.Frame_Related;

import Version_1_Java.File_Interactions.Database.cDatabaseConnectionManager;
import Version_1_Java.cMain;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

/**
 * Created by Aaron on 30.07.2017.
 */


/*

Implementiert das Löschen bzw Erzeugen neuer Einträge über die Möglichkeit, den Primärschlüssel vorhandener
Einträge zu manipulieren


 */
public class cmodKeyListener_ID implements KeyListener {

    cDatabaseConnectionManager objDatabaseManager_keyListener;





    String s_table_reference;

    Container con_target;

    c_Frame objFrameTarget;


    public cmodKeyListener_ID(c_Frame objFrameTarget) {
        this.objFrameTarget = objFrameTarget;
        s_table_reference = objFrameTarget.s_Main_Table;
        con_target = objFrameTarget.getContentPane();
        objDatabaseManager_keyListener = objFrameTarget.objDatabaseManager_Input;

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {


        if (this.s_table_reference.equals("persons")) {
            if (objFrameTarget.arrCreateEntryFields[0].getText().length() >= 3 & objFrameTarget.arrCreateEntryFields[1].getText().length() >= 3) {
                String s_unique_id = "";
                for (int i = 0; i < 2; i++) {
                    for (int k = 0; k < 2; k++) {
                        s_unique_id = s_unique_id + objFrameTarget.arrCreateEntryFields[i].getText().charAt(k);
                    }
                }

                try {
                    if (objDatabaseManager_keyListener.entry_check(this.s_table_reference, s_unique_id)) {
                        if (objDatabaseManager_keyListener.create_entry(this.s_table_reference, s_unique_id)) {
                            cMain.v_update_Textarea_Status("Ein Datenbankeintrag wurde angelegt");
                            objFrameTarget.v_generate_rows_from_Database();
                        }
                    }
                } catch (SQLException e1) {
                    cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
                }

            }
        }

        if(this.s_table_reference.equals("projects")) {
            try {
                if (objDatabaseManager_keyListener.entry_check(this.s_table_reference, objFrameTarget.arrCreateEntryFields[0].getText())) {
                    if (objDatabaseManager_keyListener.create_entry(this.s_table_reference, objFrameTarget.arrCreateEntryFields[0].getText())) {
                        cMain.v_update_Textarea_Status("Ein Datenbankeintrag wurde angelegt");
                        objFrameTarget.v_generate_rows_from_Database();
                    }
                }
            } catch (SQLException e1) {
                cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
            }



        }



    }


}


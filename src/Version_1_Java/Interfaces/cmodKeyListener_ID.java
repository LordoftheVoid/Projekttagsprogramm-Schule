package Version_1_Java.Interfaces;

import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

/**
 * Created by Aaron on 30.07.2017.
 */
public class cmodKeyListener_ID implements KeyListener {

    cDatabaseManager objDatabaseManager_keyListener;

    cmodTextField[][] arrInout_Keylistener;

    String table;


    cmodKeyListener_ID(cDatabaseManager objDatabasemanager_main, cmodTextField[][] arrInput_Interface, String table_source){
        objDatabaseManager_keyListener =objDatabasemanager_main;
        arrInout_Keylistener =arrInput_Interface;
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
            if (arrInout_Keylistener[0][objsource.iRow_position].getText().length() >= 3 & arrInout_Keylistener[1][objsource.iRow_position].getText().length() >= 3) {
                String unique_id = "";

                for (int i = 0; i < 2; i++) {
                    for (int k = 0; k < 2; k++) {
                        unique_id = unique_id + arrInout_Keylistener[i][objsource.iRow_position].getText().charAt(k);
                    }
                }

                try {
                    if (!objDatabaseManager_keyListener.entry_check(this.table, unique_id)) {
                        objDatabaseManager_keyListener.create_entry(this.table, unique_id);
                        for (int i_x = 0; i_x < arrInout_Keylistener.length; i_x++) {
                            arrInout_Keylistener[i_x][objsource.iRow_position].bcorrect_unique_ID = true;
                            arrInout_Keylistener[i_x][objsource.iRow_position].sunique_ID_Textfieldrow = unique_id;
                        }
                        objDatabaseManager_keyListener.update_entry(this.table, objsource.sunique_ID_Textfieldrow, "preName", arrInout_Keylistener[0][objsource.iRow_position].getText());
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }

            if(arrInout_Keylistener[0][objsource.iRow_position].getText().equals("")){

                try {
                    objDatabaseManager_keyListener.delete_entry(table,objsource.sunique_ID_Textfieldrow);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                for (int i_x = 0; i_x < arrInout_Keylistener.length; i_x++) {
                    arrInout_Keylistener[i_x][objsource.iRow_position].bcorrect_unique_ID = false;
                    arrInout_Keylistener[i_x][objsource.iRow_position].sunique_ID_Textfieldrow = "";
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

                for (int i_x = 0; i_x < arrInout_Keylistener.length; i_x++) {
                    arrInout_Keylistener[i_x][objsource.iRow_position].bcorrect_unique_ID = false;
                    arrInout_Keylistener[i_x][objsource.iRow_position].sunique_ID_Textfieldrow = "";
                }

            }else {
                try {
                    if (!objDatabaseManager_keyListener.entry_check(this.table, objsource.getText())) {
                        objDatabaseManager_keyListener.create_entry(this.table, objsource.getText());
                        for (int i_x = 0; i_x < arrInout_Keylistener.length; i_x++) {
                            arrInout_Keylistener[i_x][objsource.iRow_position].bcorrect_unique_ID = true;
                            arrInout_Keylistener[i_x][objsource.iRow_position].sunique_ID_Textfieldrow = objsource.getText();
                        }
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

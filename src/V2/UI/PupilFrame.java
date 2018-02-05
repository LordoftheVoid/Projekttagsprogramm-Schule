package V2.UI;

import V2.Settings.Imports;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */


public class PupilFrame extends BaseFrame {


/*
    arr_list_value_Strings[0].
    arr_list_value_Strings[0].
    arr_list_value_Strings[0]
    arr_list_value_Strings[0]
    arr_list_value_Strings[0]
    arr_list_value_Strings[0]
    arr_list_value_Strings[0]

        //    arr_list_Database_References[0].add("s_unique_ID");
        arr_list_Database_References[0].add("s_sur_Name");
        arr_list_Database_References[0].add("s_pre_Name");
        arr_list_Database_References[0].add("s_grade");
        arr_list_Database_References[0].add("i_pref0");
        arr_list_Database_References[0].add("i_pref1");
        arr_list_Database_References[0].add("i_pref2");
        arr_list_Database_References[0].add("i_pref3");

*/


    public PupilFrame(int spaltenAnzahl, String fensterName, int amountParameter) {
        super(spaltenAnzahl, fensterName);
        amountParametersnewEntry = amountParameter;

    }

    @Override
    public ArrayList<String[]> requestDataBaseContent()  {
        ArrayList<String []> returnValue = new ArrayList<>();
        try {
            returnValue = Imports.objDatabaseManagerGlobal.getValues("persons");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  returnValue;
    }

    @Override
    public void showfixedText() {
        this.columNames[0].setText("Nachname");
        this.columNames[1].setText("Vorname");
        this.columNames[2].setText("Klasse");
        this.columNames[3].setText("Erstwahl");
        this.columNames[4].setText("Zweitwahl");
        this.columNames[5].setText("Drittwahl");
        this.columNames[6].setText("Viertwahl");
    }

}

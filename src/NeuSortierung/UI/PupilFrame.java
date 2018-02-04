package NeuSortierung.UI;

import NeuSortierung.DataBaseInteractions.DataBaseObjekts.Pupil;
import NeuSortierung.Settings.Imports;

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
        this.spaltenNamen[0] = "Nachname";
        this.spaltenNamen[1] = "Vorname";
        this.spaltenNamen[2] = "Klasse";
        this.spaltenNamen[3] = "Erstwahl";
        this.spaltenNamen[4] = "Zweitwahl";
        this.spaltenNamen[5] = "Drittwahl";
        this.spaltenNamen[6] = "Viertwahl";


        this.showEntrys();

    }


    public void showEntrys() {

        ArrayList<Pupil> listEinträge = new ArrayList<>();

        listEinträge = Pupil.getFullListPupils();


        for (Pupil listenElement : listEinträge
                ) {

        }


    }



    @Override
    public ArrayList<String[]> requestDataBaseContent() {
        try {
          return  Imports.objDatabaseManagerGlobal.getValues("persons");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    void showEntrys(ArrayList<String[]> textArgs) {

    }

    @Override
    public void suche() {

    }

    @Override
    public void sortRows() {

    }
}

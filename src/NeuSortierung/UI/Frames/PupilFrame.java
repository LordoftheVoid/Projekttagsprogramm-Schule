package NeuSortierung.UI.Frames;

import NeuSortierung.DataBaseInteractions.DataBaseObjekts.Pupil;
import NeuSortierung.Settings.DataBaseObjectTypes;

import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */
public class PupilFrame extends AbstraktFrame {


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



    public PupilFrame(int spaltenAnzahl, String fensterName){
        super(spaltenAnzahl,fensterName, DataBaseObjectTypes.PUPIL);

        this.spaltenNamen[0]="Nachname";
        this.spaltenNamen[1]="Vorname";
        this.spaltenNamen[2]="Klasse";
        this.spaltenNamen[3]="Erstwahl";
        this.spaltenNamen[4]="Zweitwahl";
        this.spaltenNamen[5]="Drittwahl";
        this.spaltenNamen[6]="Viertwahl";

        this.stelleTextdar();

        this.erstelleEinträge();

    }

    @Override
    public void erstelleEinträge(){

        ArrayList<Pupil> listEinträge = new ArrayList<>();

        listEinträge = Pupil.getFullListPupils();


        for (Pupil listenElement:listEinträge
             ) {

        }



    }





}

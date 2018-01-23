package Version_1_Java.GrafikElemente.Frame_Implementationen;

/**
 * Created by Aaron on 22.01.2018.
 */
public class cSchuelerFrame extends cAbstraktesFrame {


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



    public cSchuelerFrame (int spaltenAnzahl, String fensterName){
        super(spaltenAnzahl,fensterName);

        this.spaltenNamen[0]="Nachname";
        this.spaltenNamen[1]="Vorname";
        this.spaltenNamen[2]="Klasse";
        this.spaltenNamen[3]="Erstwahl";
        this.spaltenNamen[4]="Zweitwahl";
        this.spaltenNamen[5]="Drittwahl";
        this.spaltenNamen[6]="Viertwahl";

        this.stelleTextdar();
    }



}

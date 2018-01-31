package NeuSortierung.FileInteractions;

/**
 * Created by Aaron on 11.09.2017.
 */


import NeuSortierung.cMain;

import java.io.File;


public class DirectoryCreator {

    public void v_creation(String s_url_tm, String s_name_tm) {
        File dir = new File(s_url_tm + "/" + s_name_tm);
        if (!dir.exists()) {
            dir.mkdir();
            cMain.v_update_Textarea_Status("Die Ordner wurden angelegt, bitte mit Datein füllen");
        } else {
            cMain.v_update_Textarea_Status("Die Ordner existierten bereits, Daten werden gelesen");
        }
    }
}

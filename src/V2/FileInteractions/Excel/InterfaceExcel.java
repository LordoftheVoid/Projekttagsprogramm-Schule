package V2.FileInteractions.Excel;

/**
 * Created by Aaron on 10.08.2017.
 */


import java.io.File;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterfaceExcel extends Thread {

    public CopyOnWriteArrayList<String> dateiListe;

    String urlOrdner = "";


    CopyOnWriteArrayList<String> suchDateiURLs(String s_tm_starting_directory) {

        File file_initial_file = new File(String.valueOf(s_tm_starting_directory));

        File[] arr_files_in_subdirectory = file_initial_file.listFiles();

        CopyOnWriteArrayList<String> files_found_subdirectory = new CopyOnWriteArrayList<>();

        if (arr_files_in_subdirectory != null) {
            for (File fil : arr_files_in_subdirectory) {
                if (fil.isDirectory()) {
                    files_found_subdirectory.addAll(suchDateiURLs(fil.getAbsolutePath()));
                } else {
                    files_found_subdirectory.add(fil.getAbsolutePath());
                }
            }
        }

        for (String loop_object : files_found_subdirectory
                ) {
            if (!loop_object.contains(".xls")) {
                files_found_subdirectory.remove(loop_object);
            }
        }

        return files_found_subdirectory;
    }


    public void updateDataBase(String urlOrdner) throws NullPointerException {
        this.urlOrdner = urlOrdner;
        this.start();
    }


    public void run() {
        this.dateiListe = suchDateiURLs(urlOrdner);
        ArrayList<fileReader> threadListe = new ArrayList<>();
        for (String eintrag : this.dateiListe
                ) {
            threadListe.add(new fileReader(eintrag));
        }
        for (fileReader eintrag : threadListe
                ) {
            try {
                eintrag.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /**TODO Broadcast the succesfull Import
         *
         */
    }


}


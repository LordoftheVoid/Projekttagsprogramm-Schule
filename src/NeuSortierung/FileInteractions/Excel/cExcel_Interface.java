package NeuSortierung.FileInteractions.Excel;

/**
 * Created by Aaron on 10.08.2017.
 */


import NeuSortierung.DataBaseInteractions.DataBaseObjekts.cPupil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class cExcel_Interface extends Thread {

    public CopyOnWriteArrayList<String> dateiListe;
    CopyOnWriteArrayList<cPupil> schuelerListe = new CopyOnWriteArrayList();

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


    public void updateDatenbank(String urlOrdner) throws NullPointerException {
        this.urlOrdner = urlOrdner;
        this.start();
    }


    public void run() {
        this.dateiListe = suchDateiURLs(urlOrdner);
        ArrayList<cDateiLeser> threadListe = new ArrayList<>();
        for (String eintrag : this.dateiListe
                ) {
            threadListe.add(new cDateiLeser(eintrag));
        }
        for (cDateiLeser eintrag : threadListe
                ) {
            try {
                eintrag.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        cPupil.updateSchueler(schuelerListe);
    }


    class cDateiLeser extends Thread {

        String dateiUrl = "";

        cDateiLeser(String url) {
            this.dateiUrl = url;
            this.start();
        }


        public void run() {
            String gelesenerNachname;
            String gelesenerVorname;
            String stufe = "";

            int i = dateiUrl.length() - 1;

            while (dateiUrl.charAt(i) != '\\') {
                i--;
            }
            i++;

            for (; i < dateiUrl.length(); i++) {
                stufe = stufe + dateiUrl.charAt(i);
                if (dateiUrl.charAt(i) == '.') {
                    break;
                }
            }

            HSSFWorkbook datei = null;

            try {
                datei = new HSSFWorkbook(new FileInputStream(this.dateiUrl));
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i_x = 0; i_x < datei.getSheetAt(0).getLastRowNum(); i_x++) {
                gelesenerNachname = datei.getSheetAt(0).getRow(i_x).getCell(0).getStringCellValue();
                gelesenerVorname = datei.getSheetAt(0).getRow(i_x).getCell(1).getStringCellValue();

                if(!gelesenerNachname.toLowerCase().equals("nachname")) {
                    schuelerListe.add(new cPupil(gelesenerNachname, gelesenerVorname, stufe));
                }
            }
        }

    }
}


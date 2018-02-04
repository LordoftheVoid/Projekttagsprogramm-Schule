package NeuSortierung.DataBaseInteractions.DataBaseObjekts;


import NeuSortierung.Settings.Imports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Aaron on 22.01.2018.
 */


public class Pupil extends DataBaseElementObject{

    int[] projektPräferenzen = new int[4];
    public String nachName = "";
    String vorName = "";
    String klassenStufe = "";

    String pseudoHash ="";

    public Pupil(String id) {
        super(id);
    }


    /*
    public Pupil(String nachN, String vorN, String klasse) {
        this.nachName = nachN;
        this.vorName= vorN;
        this.klassenStufe= klasse;

        //this.setDatabaseColums(DataBaseLinks.pupilValues);

        for (int i = 0; i < this.projektPräferenzen.length; i++) {
            projektPräferenzen[i] = -1;
        }

    }

*/

    @Override
    public void changeValue(String value, int colum) {

    }

    @Override
    public void savetoDataBase() throws SQLException {

    }





    public static ArrayList<Pupil> getFullListPupils() {
        ArrayList<Pupil> rueckgabeListe = new ArrayList<>();

        ArrayList<String> schuelerHashs = new ArrayList<>();

        ResultSet schuelerInDatenbank;
        try {
            schuelerInDatenbank=  Imports.objDatabaseManagerGlobal.readEntrysOneAttribut("persons","s_unique_ID");
            while (schuelerInDatenbank.next()){
                schuelerHashs.add(schuelerInDatenbank.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String schuelerWerte [][] = new String[3][schuelerHashs.size()];

        for (int i = 0; i < schuelerHashs.size(); i++) {
            ResultSet schuelerWert;
            try {
                schuelerWert=  Imports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons","s_sur_Name",schuelerHashs.get(i));
                while (schuelerWert.next()){
                    schuelerWerte[0][i]=schuelerWert.getString(1);
                }
                schuelerWert=  Imports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons","s_pre_Name",schuelerHashs.get(i));
                while (schuelerWert.next()){
                    schuelerWerte[1][i]=schuelerWert.getString(1);
                }
                schuelerWert=  Imports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons","s_grade",schuelerHashs.get(i));
                while (schuelerWert.next()){
                    schuelerWerte[2][i]=schuelerWert.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < schuelerHashs.size(); i++) {
         //   rueckgabeListe.add(new Pupil(schuelerWerte[0][i],schuelerWerte[1][i],schuelerWerte[2][i]));
        }

        return rueckgabeListe;
    }


    public static void updateSchueler(CopyOnWriteArrayList<Pupil> schuelerListe){

        int i=0;
        try{
            for (Pupil listenElement:schuelerListe
                 ) {
                listenElement.setHash();
                i++;
            }
        }catch (NullPointerException e1){
            schuelerListe.remove(schuelerListe.get(i));
        }

        ArrayList<String> schuelerHashListe = new ArrayList<>();

        ResultSet schuelerInDatenbank;
        try {
           schuelerInDatenbank=  Imports.objDatabaseManagerGlobal.readEntrysOneAttribut("persons","s_unique_ID");
            while (schuelerInDatenbank.next()){
            schuelerHashListe.add(schuelerInDatenbank.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Pupil listenEintrag:schuelerListe
             ) {
            if(schuelerHashListe.contains(listenEintrag.pseudoHash)){
                schuelerListe.remove(listenEintrag);
            }
        }


        for (Pupil listenEintrag:schuelerListe
             ) {
            try {
                Imports.objDatabaseManagerGlobal.createEntry("persons",listenEintrag.pseudoHash);
                Imports.objDatabaseManagerGlobal.updateEntry("persons",listenEintrag.pseudoHash,"s_sur_Name",listenEintrag.nachName);
                Imports.objDatabaseManagerGlobal.updateEntry("persons",listenEintrag.pseudoHash,"s_pre_Name",listenEintrag.vorName);
                Imports.objDatabaseManagerGlobal.updateEntry("persons",listenEintrag.pseudoHash,"s_grade",listenEintrag.klassenStufe);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }



    void setHash(){
        this.pseudoHash= ""+ this.nachName.charAt(0)+ this.nachName.charAt(1)+ this.nachName.charAt(3) +this.vorName.charAt(0) +this.vorName.charAt(1);
    }



    public int[] getProjekte() {
        return this.projektPräferenzen;
    }

    public void setProjekt(int neuerWert, int index) throws NullPointerException{

        if (index < 0 || index > this.projektPräferenzen.length) {
            throw new NullPointerException("Projekt existiert nicht");
        }

        if (!Imports.projektNummernGlobal.contains(neuerWert)) {
            throw new NullPointerException("Projekt existiert nicht");
        }

        this.projektPräferenzen[index] = neuerWert;
    }



}

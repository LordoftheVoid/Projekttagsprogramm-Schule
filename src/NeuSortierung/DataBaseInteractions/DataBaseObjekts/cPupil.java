package NeuSortierung.DataBaseInteractions.DataBaseObjekts;

import NeuSortierung.Settings.cImports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Aaron on 22.01.2018.
 */


public class cPupil {


    int[] projektPräferenzen = new int[4];
    public String nachName = "";
    String vorName = "";
    String klassenStufe = "";


    String pseudoHash ="";





    public cPupil(String nachN, String vorN, String klasse) {
        this.nachName = nachN;
        this.vorName= vorN;
        this.klassenStufe= klasse;

        //this.setDatabaseColums(cDataBaseLinks.pupilValues);

        for (int i = 0; i < this.projektPräferenzen.length; i++) {
            projektPräferenzen[i] = -1;
        }

    }

    public static ArrayList<cPupil> getFullListPupils() {
        ArrayList<cPupil> rueckgabeListe = new ArrayList<>();

        ArrayList<String> schuelerHashs = new ArrayList<>();

        ResultSet schuelerInDatenbank;
        try {
            schuelerInDatenbank=  cImports.objDatabaseManagerGlobal.readEntrysOneAttribut("persons","s_unique_ID");
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
                schuelerWert=  cImports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons","s_sur_Name",schuelerHashs.get(i));
                while (schuelerWert.next()){
                    schuelerWerte[0][i]=schuelerWert.getString(1);
                }
                schuelerWert=  cImports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons","s_pre_Name",schuelerHashs.get(i));
                while (schuelerWert.next()){
                    schuelerWerte[1][i]=schuelerWert.getString(1);
                }
                schuelerWert=  cImports.objDatabaseManagerGlobal.readOneEntryOneAtribute("persons","s_grade",schuelerHashs.get(i));
                while (schuelerWert.next()){
                    schuelerWerte[2][i]=schuelerWert.getString(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < schuelerHashs.size(); i++) {
            rueckgabeListe.add(new cPupil(schuelerWerte[0][i],schuelerWerte[1][i],schuelerWerte[2][i]));
        }

        return rueckgabeListe;
    }


    public static void updateSchueler(CopyOnWriteArrayList<cPupil> schuelerListe){

        int i=0;
        try{
            for (cPupil listenElement:schuelerListe
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
           schuelerInDatenbank=  cImports.objDatabaseManagerGlobal.readEntrysOneAttribut("persons","s_unique_ID");
            while (schuelerInDatenbank.next()){
            schuelerHashListe.add(schuelerInDatenbank.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (cPupil listenEintrag:schuelerListe
             ) {
            if(schuelerHashListe.contains(listenEintrag.pseudoHash)){
                schuelerListe.remove(listenEintrag);
            }
        }


        for (cPupil listenEintrag:schuelerListe
             ) {
            try {
                cImports.objDatabaseManagerGlobal.createEntry("persons",listenEintrag.pseudoHash);
                cImports.objDatabaseManagerGlobal.updateEntry("persons",listenEintrag.pseudoHash,"s_sur_Name",listenEintrag.nachName);
                cImports.objDatabaseManagerGlobal.updateEntry("persons",listenEintrag.pseudoHash,"s_pre_Name",listenEintrag.vorName);
                cImports.objDatabaseManagerGlobal.updateEntry("persons",listenEintrag.pseudoHash,"s_grade",listenEintrag.klassenStufe);
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

        if (!cImports.projektNummernGlobal.contains(neuerWert)) {
            throw new NullPointerException("Projekt existiert nicht");
        }

        this.projektPräferenzen[index] = neuerWert;
    }



}

package Version_1_Java.Objekte.ModifizierteSpeicherKlassen;

import Version_1_Java.Objekte.cProjekt;
import Version_1_Java.Objekte.cSchueler;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 14.04.2017.
 */
public class cErweiterteHashMapProjekteSchuelerListe extends HashMap<cProjekt, cArrayListErweitertSchueler>  {




    cArrayListErweitertSchueler listSchuelerohneProjekte= new cArrayListErweitertSchueler();



    public cErweiterteHashMapProjekteSchuelerListe(cArrayListErweitertProjekte listProjekte) {

        for (cProjekt Schleifenobjekt:listProjekte
             ) {
            this.put(Schleifenobjekt, new cArrayListErweitertSchueler());
        }
    }

    public cArrayListErweitertSchueler getListSchuelerohneProjekte() {
        return listSchuelerohneProjekte;
    }

    public  int iSummeAllerProjektwerte(){
        int sum=0;
        for (cProjekt Schleifenobjekt:this.keySet()
                ) {
            sum=sum+ this.get(Schleifenobjekt).SummeProjektwerteueberListe();

        }
        return sum;
    }

    public int iLaengeallerListen(){
        int sum=0;
        for (cProjekt Schleifenobjekt :this.keySet()
                ) {
            sum=sum+ this.get(Schleifenobjekt).size();

        }
        return sum;
    }




    public void Erstellung ( cArrayListErweitertSchueler objListSchueler_Main){



       CopyOnWriteArrayList <cSchueler> objListSchueler_Methode= new CopyOnWriteArrayList<>();


        for (cSchueler Schleifenobjekt:objListSchueler_Main
             ) {
            objListSchueler_Methode.add(new cSchueler(Schleifenobjekt));
        }

        boolean bSchueleruebrig= false;


        cErweiterteHashMapIntProjekte    MapIntegerProjekte = new cErweiterteHashMapIntProjekte();


        for (cProjekt Schleifenobjekt:this.keySet()
             ) {
            MapIntegerProjekte.put(Schleifenobjekt.iProjektnummer,Schleifenobjekt);

        }



        for (int i = 0; i < cSchueler.iMaximalanzahl_Projekte; i++) {
            while (objListSchueler_Methode.size() > 0) {
                cSchueler obj_Zu_Testender_Schueler;
                if (objListSchueler_Methode.size() > 1) {
                    obj_Zu_Testender_Schueler = objListSchueler_Methode.get((int) (Math.random() * objListSchueler_Methode.size()));
                } else {
                    obj_Zu_Testender_Schueler = objListSchueler_Methode.get(0);
                }

                if (!MapIntegerProjekte.get(obj_Zu_Testender_Schueler.arrPraeferenzen[i]).bvoll) {
                    this.get(MapIntegerProjekte.get(obj_Zu_Testender_Schueler.arrPraeferenzen[i])).add(obj_Zu_Testender_Schueler);
                    obj_Zu_Testender_Schueler.iPraeferenznummer_zugewiesenesProjekt=i;
                    obj_Zu_Testender_Schueler.Zugewiesenes_Projekt=MapIntegerProjekte.get(obj_Zu_Testender_Schueler.arrPraeferenzen[i]);
                    MapIntegerProjekte.get(obj_Zu_Testender_Schueler.arrPraeferenzen[i]).iteilnehmendeSchueler++;
                    if (MapIntegerProjekte.get(obj_Zu_Testender_Schueler.arrPraeferenzen[i]).iteilnehmendeSchueler == MapIntegerProjekte.get(obj_Zu_Testender_Schueler.arrPraeferenzen[i]).iMaximaleSchueleranzahl) {
                        MapIntegerProjekte.get(obj_Zu_Testender_Schueler.arrPraeferenzen[i]).bvoll = true;
                    }
                } else {
                    this.listSchuelerohneProjekte.add(obj_Zu_Testender_Schueler);
                    bSchueleruebrig=true;
                }
                objListSchueler_Methode.remove(obj_Zu_Testender_Schueler);
            }

            if(bSchueleruebrig) {
                for (cSchueler Schleifenobjekt : this.listSchuelerohneProjekte
                        ) {
                    objListSchueler_Methode.add(new cSchueler(Schleifenobjekt));
                }


                for (cProjekt Schleifenobjekt : this.keySet()
                        ) {
                    Schleifenobjekt.reset();
                }


                for (cSchueler Schleifenobjekt : objListSchueler_Methode
                        ) {
                    objListSchueler_Methode.add(Schleifenobjekt);
                }
                for (cProjekt Schleifenobjekt : this.keySet()
                        ) {
                    Schleifenobjekt.reset();
                }
            }else{
                break;
            }
        }
    }


}
















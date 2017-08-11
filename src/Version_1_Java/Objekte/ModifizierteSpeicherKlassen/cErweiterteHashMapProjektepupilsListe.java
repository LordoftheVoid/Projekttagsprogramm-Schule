package Version_1_Java.Objekte.ModifizierteSpeicherKlassen;

import Version_1_Java.Objekte.cProjekt;

import java.util.HashMap;

/**
 * Created by Aaron on 14.04.2017.
 */
public class cErweiterteHashMapProjektepupilsListe extends HashMap<cProjekt, cArrayListErweitertpupils>  {

/*


    cArrayListErweitertpupils listpupilsohneProjekte= new cArrayListErweitertpupils();



    public cErweiterteHashMapProjektepupilsListe(cArrayListErweitertProjekte listProjekte) {

        for (cProjekt loop_objekt:listProjekte
             ) {
            this.put(loop_objekt, new cArrayListErweitertpupils());
        }
    }

    public cArrayListErweitertpupils getListpupilsohneProjekte() {
        return listpupilsohneProjekte;
    }

    public  int iSummeAllerProjektwerte(){
        int sum=0;
        for (cProjekt loop_objekt:this.keySet()
                ) {
            sum=sum+ this.get(loop_objekt).SummeProjektwerteueberListe();

        }
        return sum;
    }

    public int iLaengeallerListen(){
        int sum=0;
        for (cProjekt loop_objekt :this.keySet()
                ) {
            sum=sum+ this.get(loop_objekt).size();

        }
        return sum;
    }



    public void Erstellung ( cArrayListErweitertpupils objListpupils_Main){


       CopyOnWriteArrayList <cpupils> objListpupils_Methode= new CopyOnWriteArrayList<>();


        for (cpupils loop_objekt:objListpupils_Main
             ) {
            objListpupils_Methode.add(new cpupils(loop_objekt));
        }

        boolean bpupilsuebrig= false;


        cErweiterteHashMapIntProjekte    MapIntegerProjekte = new cErweiterteHashMapIntProjekte();


        for (cProjekt loop_objekt:this.keySet()
             ) {
            MapIntegerProjekte.put(loop_objekt.iProjektnummer,loop_objekt);

        }



        for (int i = 0; i < cpupils.iMaximalanzahl_Projekte; i++) {
            while (objListpupils_Methode.size() > 0) {
                cpupils obj_Zu_Testender_pupils;
                if (objListpupils_Methode.size() > 1) {
                    obj_Zu_Testender_pupils = objListpupils_Methode.get((int) (Math.random() * objListpupils_Methode.size()));
                } else {
                    obj_Zu_Testender_pupils = objListpupils_Methode.get(0);
                }

                if (!MapIntegerProjekte.get(obj_Zu_Testender_pupils.arrPraeferenzen[i]).bvoll) {
                    this.get(MapIntegerProjekte.get(obj_Zu_Testender_pupils.arrPraeferenzen[i])).add(obj_Zu_Testender_pupils);
                    obj_Zu_Testender_pupils.iPraeferenznummer_zugewiesenesProjekt=i;
                    obj_Zu_Testender_pupils.Zugewiesenes_Projekt=MapIntegerProjekte.get(obj_Zu_Testender_pupils.arrPraeferenzen[i]);
                    MapIntegerProjekte.get(obj_Zu_Testender_pupils.arrPraeferenzen[i]).iteilnehmendepupils++;
                    if (MapIntegerProjekte.get(obj_Zu_Testender_pupils.arrPraeferenzen[i]).iteilnehmendepupils == MapIntegerProjekte.get(obj_Zu_Testender_pupils.arrPraeferenzen[i]).iMaximalepupilsanzahl) {
                        MapIntegerProjekte.get(obj_Zu_Testender_pupils.arrPraeferenzen[i]).bvoll = true;
                    }
                } else {
                    this.listpupilsohneProjekte.add(obj_Zu_Testender_pupils);
                    bpupilsuebrig=true;
                }
                objListpupils_Methode.remove(obj_Zu_Testender_pupils);
            }

            if(bpupilsuebrig) {
                for (cpupils loop_objekt : this.listpupilsohneProjekte
                        ) {
                    objListpupils_Methode.add(new cpupils(loop_objekt));
                }


                for (cProjekt loop_objekt : this.keySet()
                        ) {
                    loop_objekt.reset();
                }


                for (cpupils loop_objekt : objListpupils_Methode
                        ) {
                    objListpupils_Methode.add(loop_objekt);
                }
                for (cProjekt loop_objekt : this.keySet()
                        ) {
                    loop_objekt.reset();
                }
            }else{
                break;
            }
        }
    }

*/
}
















package Version_1_Java.Objekte.ModifizierteSpeicherKlassen;

import Version_1_Java.Objekte.cProjekt;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 15.04.2017.
 */
public class cArrayListErweitertProjekte extends CopyOnWriteArrayList<cProjekt> {




    public String toString(){

        String sReturnwert="";

        for (cProjekt Schleifenobjekt:this
                ) {
            sReturnwert=sReturnwert+Schleifenobjekt.iProjektnummer;
            sReturnwert=sReturnwert+" "+Schleifenobjekt.sLehrerkuerzel;
            sReturnwert=sReturnwert+" "+Schleifenobjekt.iMaximaleSchueleranzahl+" ";



            /*

            Output inklusive Stufenbegrenzung
            sReturnwert=sReturnwert+" "+String.valueOf(Schleifenobjekt.arrAufStufenVonBisBegrenzt[0]);
            sReturnwert=sReturnwert+" "+String.valueOf(+Schleifenobjekt.arrAufStufenVonBisBegrenzt[Schleifenobjekt.arrAufStufenVonBisBegrenzt.length-1])+" ";
                */
        }
        return sReturnwert;
    }





















}

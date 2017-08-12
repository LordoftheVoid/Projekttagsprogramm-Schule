package Version_1_Java.Objekte.ModifizierteSpeicherKlassen;

import Version_1_Java.Objekte.cpupils;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 09.04.2017.
 */

public class c_Array_List_extended_pupils extends CopyOnWriteArrayList<cpupils> {


        public int SummeProjektwerteueberListe(){
        int sum=0;
            for (cpupils loop_objekt:this
                 ) {
                sum=sum+loop_objekt.iPraeferenznummer_zugewiesenesProjekt;
            }
        return sum;
        }







    public String toString(){

        String sReturnwert="";


        /*
        for (cpupils loop_objekt:this
                ) {
            sReturnwert=sReturnwert+loop_objekt.
            sReturnwert=sReturnwert+" "+loop_objekt.sNachname;
            sReturnwert=sReturnwert+" "+loop_objekt.sKlassenstufe_mit_Buchstaben;

            for(int i = 0; i< cpupils.iMaximalanzahl_Projekte; i++){
                sReturnwert=sReturnwert+" "+loop_objekt.arrPraeferenzen[i]+" ";
            }
        }
        */
        return sReturnwert;
    }






}

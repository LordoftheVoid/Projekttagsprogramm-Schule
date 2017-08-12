package Version_1_Java.Objekte.ModifizierteSpeicherKlassen;

import Version_1_Java.Objekte.cpupils;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 09.04.2017.
 */

public class c_Array_List_extended_pupils extends CopyOnWriteArrayList<cpupils> {






    /*
src/Version_1_Java/Objekte/ModifizierteSpeicherKlassen/cArrayListErweitertpupils.java


        public int SummeProjektwerteueberListe(){
        int sum=0;
            for (cpupils loop_objekt:this
                 ) {
                sum=sum+loop_objekt.iPraeferenznummer_zugewiesenesProjekt;
            }
        return sum;
        }


        public int AnzahlpupilsOhneProjekt(){
            return this.size();
        }



        public enum parameterListe{
            Nachname, Vorname, Klasse
        }


        public void quicksort(parameterListe Suchkriterium){


            boolean bunsortiert=true;
            int iMenge_der_zu_sortierenden_Felder=this.size();
            while(bunsortiert){
                int iMenge_an_Schritten=iMenge_der_zu_sortierenden_Felder-1;
                int iAbtastposition_1=0;
                int iAbtastposition_2=1;
                boolean bsortiert=true;
                while(iMenge_an_Schritten>0){


                    switch (Suchkriterium) {


                        case Nachname:


                            break;



                        case Vorname:


                            break;




                        case Klasse:
                            if(this.get(iAbtastposition_1).iStufe>this.get(iAbtastposition_2).iStufe){
                                cpupils iZwischenspeicher= this.get(iAbtastposition_2);
                                this.set(iAbtastposition_2,this.get(iAbtastposition_1));
                                this.set(iAbtastposition_1,iZwischenspeicher);
                                bsortiert = false;
                            }

                            break;







                    }




                    iAbtastposition_1++;
                    iAbtastposition_2++;
                    iMenge_an_Schritten--;
                }
                if(bsortiert){
                    bunsortiert=false;
                }
                iMenge_der_zu_sortierenden_Felder--;
            }










        }


          public String toString(){

        String sReturnwert="";

        for (cpupils loop_objekt:this
                ) {
            sReturnwert=sReturnwert+loop_objekt.sVorname;
            sReturnwert=sReturnwert+" "+loop_objekt.sNachname;
            sReturnwert=sReturnwert+" "+loop_objekt.sKlassenstufe_mit_Buchstaben;

            for(int i = 0; i< cpupils.iMaximalanzahl_Projekte; i++){
                sReturnwert=sReturnwert+" "+loop_objekt.arrPraeferenzen[i]+" ";
            }
        }

        return sReturnwert;
    }




    */

}

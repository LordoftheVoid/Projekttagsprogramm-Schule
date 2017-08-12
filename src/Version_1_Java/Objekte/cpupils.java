package Version_1_Java.Objekte;

import Version_1_Java.cMain;

/**
 * Created by Aaron on 29.03.2017.
 */
public class cpupils {


    public int [] arrPraeferenzen= new int[cMain.iMaximalanzahl_Projekte];

    public cProjekt Zugewiesenes_Projekt;

    public int iPraeferenznummer_zugewiesenesProjekt=0;

    public String unique_ID="";

    public cpupils(String unique_ID_transfered) {
        this.unique_ID=unique_ID_transfered;
    }


}

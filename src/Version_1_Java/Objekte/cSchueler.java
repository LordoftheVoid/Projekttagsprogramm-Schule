package Version_1_Java.Objekte;

/**
 * Created by Aaron on 29.03.2017.
 */
public class cSchueler {



    public static final int iMaximalanzahl_Projekte=4;



   public  int iStufe;


    public int [] arrPraeferenzen= new int[iMaximalanzahl_Projekte];



    public cProjekt Zugewiesenes_Projekt;



    public String sVorname;
    public String sNachname;

    public String sKlassenstufe_mit_Buchstaben;


    public String sIdentifikation="";


    public int iPraeferenznummer_zugewiesenesProjekt=0;


    public cSchueler(String sVorname, String sNachname, int iStufe_uebergeben, int[] arrPraeferenzargumente, String sKlasse_mit_Buchstaben ){

        this.sKlassenstufe_mit_Buchstaben=sKlasse_mit_Buchstaben;
        this.sVorname=sVorname;
        this.sNachname=sNachname;

        /*

        Hier gab es noch einen Error

         */

        try {
            if (iStufe_uebergeben != -1) {
                this.iStufe = iStufe_uebergeben;
            } else {

                if (Character.isDigit(sKlasse_mit_Buchstaben.charAt(1))) {
                    this.iStufe = sKlasse_mit_Buchstaben.charAt(0) * 10 + sKlasse_mit_Buchstaben.charAt(1);

                } else {
                    this.iStufe = sKlasse_mit_Buchstaben.charAt(0);
                }
            }
        }catch (StringIndexOutOfBoundsException e_1){

        }



        this.sIdentifikation=this.sIdentifikation+this.sVorname.charAt(0);
        this.sIdentifikation=this.sIdentifikation+this.sVorname.charAt(1);

        this.sIdentifikation=this.sIdentifikation+this.sNachname.charAt(0);
        this.sIdentifikation=this.sIdentifikation+this.sNachname.charAt(1);




     for(int i=0;i<arrPraeferenzen.length;i++){
         arrPraeferenzen[i]=arrPraeferenzargumente[i];
     }
    }




    public cSchueler(cSchueler arg){

        this.sKlassenstufe_mit_Buchstaben=arg.sKlassenstufe_mit_Buchstaben;
        this.sVorname=arg.sVorname;
        this.sNachname=arg.sNachname;

        /*

        Hier gab es noch einen Error

         */

        this.iStufe=arg.iStufe;


        this.sIdentifikation=this.sIdentifikation+this.sVorname.charAt(0);
        this.sIdentifikation=this.sIdentifikation+this.sVorname.charAt(1);

        this.sIdentifikation=this.sIdentifikation+this.sNachname.charAt(0);
        this.sIdentifikation=this.sIdentifikation+this.sNachname.charAt(1);




        for(int i=0;i<arrPraeferenzen.length;i++){
            this.arrPraeferenzen[i]=arg.arrPraeferenzen[i];
        }
    }
}

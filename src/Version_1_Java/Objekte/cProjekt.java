package Version_1_Java.Objekte;

/**
 * Created by Aaron on 29.03.2017.
 */
public class cProjekt {

    /*
    Statischer Speicher sowie Konstruktor
     */

    public  final int iMaximaleSchueleranzahl;


    public String sLehrerkuerzel;

    public int iProjektnummer;


    public boolean bvoll=false;

    public String sIdentifikation;


    public cProjekt(String sLehrerkuerzel_uebergeben, int iProjektnummer_uebergeben, int iMaximaleSchueleranzahl_uebergeben){

        this.sLehrerkuerzel=sLehrerkuerzel_uebergeben;

        this.iProjektnummer=iProjektnummer_uebergeben;

        this.iMaximaleSchueleranzahl=iMaximaleSchueleranzahl_uebergeben;


        sIdentifikation= sLehrerkuerzel_uebergeben+String.valueOf(iProjektnummer_uebergeben);
    }


    public int iteilnehmendeSchueler=0;



    public void reset(){
      this.iteilnehmendeSchueler=0;
      this.bvoll=false;
    }


}
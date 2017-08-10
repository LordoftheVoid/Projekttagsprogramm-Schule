package Version_1_Java.Objekte;

/**
 * Created by Aaron on 29.03.2017.
 */
public class cProjekt {

    /*
    Statischer Speicher sowie Konstruktor
     */

    public  final int iMaximalepupilsanzahl;


    public String sLehrerkuerzel;

    public int iProjektnummer;


    public boolean bvoll=false;

    public String sIdentifikation;


    public cProjekt(String sLehrerkuerzel_uebergeben, int iProjektnummer_uebergeben, int iMaximalepupilsanzahl_uebergeben){

        this.sLehrerkuerzel=sLehrerkuerzel_uebergeben;

        this.iProjektnummer=iProjektnummer_uebergeben;

        this.iMaximalepupilsanzahl=iMaximalepupilsanzahl_uebergeben;


        sIdentifikation= sLehrerkuerzel_uebergeben+String.valueOf(iProjektnummer_uebergeben);
    }


    public int iteilnehmendepupils=0;



    public void reset(){
      this.iteilnehmendepupils=0;
      this.bvoll=false;
    }


}
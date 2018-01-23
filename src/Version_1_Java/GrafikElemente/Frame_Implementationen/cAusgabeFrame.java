package Version_1_Java.GrafikElemente.Frame_Implementationen;

/**
 * Created by Aaron on 22.01.2018.
 */



public class cAusgabeFrame extends cAbstraktesFrame {


    public cAusgabeFrame(int spaltenAnzahl, String strFenstername) {
        super(spaltenAnzahl, strFenstername);


        this.spaltenNamen[0]= "Nachname";
        this.spaltenNamen[1]= "Vorname";
        this.spaltenNamen[2]= "Klasse";
        this.spaltenNamen[3]= "Lehrkraftskürzel";
        this.spaltenNamen[4]= "Wahlnummer";

        this.stelleTextdar();
    }
}

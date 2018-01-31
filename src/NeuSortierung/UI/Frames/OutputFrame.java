package NeuSortierung.UI.Frames;

import NeuSortierung.Settings.DataBaseObjectTypes;

/**
 * Created by Aaron on 22.01.2018.
 */



public class OutputFrame extends AbstraktFrame {


    public OutputFrame(int spaltenAnzahl, String strFenstername) {
        super(spaltenAnzahl, strFenstername, DataBaseObjectTypes.LINK);

        this.spaltenNamen[0]= "Nachname";
        this.spaltenNamen[1]= "Vorname";
        this.spaltenNamen[2]= "Klasse";
        this.spaltenNamen[3]= "Lehrkraftskürzel";
        this.spaltenNamen[4]= "Wahlnummer";

        this.stelleTextdar();
    }
}

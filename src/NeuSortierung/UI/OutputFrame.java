package NeuSortierung.UI;

import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */



public class OutputFrame extends BaseFrame {


    public OutputFrame(int spaltenAnzahl, String strFenstername) {
        super(spaltenAnzahl, strFenstername);

        this.spaltenNamen[0]= "Nachname";
        this.spaltenNamen[1]= "Vorname";
        this.spaltenNamen[2]= "Klasse";
        this.spaltenNamen[3]= "Lehrkraftskürzel";
        this.spaltenNamen[4]= "Wahlnummer";


    }

    @Override
    public ArrayList<String[]> requestDataBaseContent() {

        return null;
    }

    @Override
    void showEntrys(ArrayList<String[]> textArgs) {

    }

    @Override
    void showEntrys() {

    }

    @Override
    void setupEntryRow() {

    }
}

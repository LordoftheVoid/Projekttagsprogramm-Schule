package Version_1_Java.Interfaces;

import Version_1_Java.cImports;
import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;


/**
 * Created by Aaron on 22.01.2018.
 */


public class cSchueler {


    int[] projektPräferenzen = new int[4];
    String nachName = "";
    String vorName = "";
    String klasse = "";


    public cSchueler(String Nachname, String Vorname, String Klasse) {
        for (int i = 0; i < this.projektPräferenzen.length; i++) {
            projektPräferenzen[i] = -1;
        }

    }

    public static ArrayList<cSchueler> erfrageSchueler() {
        ArrayList<cSchueler> rueckgabeListe = new ArrayList<>();


        return rueckgabeListe;
    }

    public int[] getProjekte() {
        return this.projektPräferenzen;
    }

    public void setProjekt(int neuerWert, int index) throws InvalidArgumentException {

        if (index < 0 || index > this.projektPräferenzen.length) {
            throw new InvalidArgumentException(null);
        }

        if (!cImports.projektNummernGlobal.contains(neuerWert)) {
            throw new InvalidArgumentException(null);
        }

        this.projektPräferenzen[index] = neuerWert;
    }

}

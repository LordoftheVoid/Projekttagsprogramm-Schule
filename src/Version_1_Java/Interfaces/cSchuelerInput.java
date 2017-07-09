package Version_1_Java.Interfaces;

import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertSchueler;
import Version_1_Java.Objekte.cSchueler;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 30.03.2017.
 */
public class cSchuelerInput extends JFrame {




   static JTextField[][] arrTextfields_Input= new JTextField[cSchueler.iMaximalanzahl_Projekte + 3][200];

   static JTextField [] arrTextFields_Menue= new JTextField[cSchueler.iMaximalanzahl_Projekte+3];

    cDatabaseManager Database;

    /*

    Die X-Koordinate kann gleich bleiben, die Y-Koordinate muss durch iterieren geaendert werden


     */
    ArrayList < ArrayList<JTextField> > listFeldersichtbar = new ArrayList<>();
    ArrayList < ArrayList<JTextField> > listFelderUnsichtbar = new ArrayList<>();


    boolean bAktualisieren=true;




    public cSchuelerInput( cDatabaseManager objDatabaseManager_Input) {

        this.Database=objDatabaseManager_Input;



        JTextField []  arrSuchmasken = new JTextField[arrTextfields_Input.length];
        JTextField [] arrSuchmaskenmenue= new JTextField[arrTextfields_Input.length];

        this.setBounds(500, 500, 1000, 500);
        this.setTitle("Fenster zur Eingabe der Schueler ");

        this.getContentPane().setLayout(null);

        {
            int iPraeferenzenzaehler=1;
            for (int i_x = 0; i_x < arrTextFields_Menue.length; i_x++) {

                    arrTextFields_Menue[i_x] = new JTextField();
                    arrTextFields_Menue[i_x].setVisible(true);
                    this.getContentPane().add(arrTextFields_Menue[i_x]);
                    arrTextFields_Menue[i_x].setBounds(120 * i_x, 0, 120, 20);
                    if (i_x < 3) {

                        if ( i_x == 0) {
                            arrTextFields_Menue[i_x].setText("Vorname");
                        }
                        if (i_x== 1) {
                            arrTextFields_Menue[i_x].setText("Nachname");
                        }
                        if (i_x == 2) {
                            arrTextFields_Menue[i_x].setText("Klassenstufe");
                        }

                    } else {
                        arrTextFields_Menue[i_x].setText("Praeferenz " + String.valueOf(iPraeferenzenzaehler));
                        iPraeferenzenzaehler++;
                    }
            }
        }

            for (int i_x = 0; i_x < arrTextfields_Input.length; i_x++) {
                for (int k_y = 0; k_y < arrTextfields_Input[i_x].length; k_y++) {
                    if(i_x==0){
                        listFeldersichtbar.add(new ArrayList<>());
                        listFeldersichtbar.get(k_y).add(arrTextfields_Input[i_x][k_y]);
                    }else{
                        listFeldersichtbar.get(k_y).add(arrTextfields_Input[i_x][k_y]);
                    }
                    arrTextfields_Input[i_x][k_y] = new JTextField();
                    this.getContentPane().add(arrTextfields_Input[i_x][k_y]);
                    arrTextfields_Input[i_x][k_y].setBounds(120 * i_x, 20 * k_y+i_x+arrTextFields_Menue[0].getHeight()+10 , 120, 20);
                }
            }






        for (int i_x = 3; i_x < arrTextfields_Input.length; i_x++) {
            for (int k_y = 0; k_y < arrTextfields_Input[i_x].length; k_y++) {
                arrTextfields_Input[i_x][k_y].setText(""+-1);
            }
        }

            for(int i=0;i<arrSuchmasken.length;i++){
                    arrSuchmasken[i]= new JTextField();
                    arrSuchmasken[i].setVisible(true);
                   this.getContentPane().add( arrSuchmasken[i]);
                    arrSuchmasken[i].setBounds(arrTextfields_Input[arrTextfields_Input.length-1][0].getX()+arrTextfields_Input[arrTextfields_Input.length-1][0].getWidth()+(110*i)+100,30,110,20);
                    arrSuchmasken[i].addKeyListener(new KeyListener() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                        }

                        @Override
                        public void keyPressed(KeyEvent e) {

                        }

                        @Override
                        public void keyReleased(KeyEvent e) {
                            String [] arrArgumente= new String[arrSuchmasken.length];
                            for (int j = 0; j < arrArgumente.length; j++) {
                                if(!Objects.equals(arrSuchmasken[j].getText(), "")){
                                    arrArgumente[j]=arrSuchmasken[j].getText();
                                }else{
                                    arrArgumente[j]=null;
                                }
                            }
                            Suche(arrArgumente);
                        }
                    });


            }




        for(int i=0;i<arrSuchmaskenmenue.length;i++){
                arrSuchmaskenmenue[i]= new JTextField();
                arrSuchmaskenmenue[i].setVisible(true);
                this.getContentPane().add( arrSuchmaskenmenue[i]);
                arrSuchmaskenmenue[i].setBounds(arrTextfields_Input[arrTextfields_Input.length-1][0].getX()+arrTextfields_Input[arrTextfields_Input.length-1][0].getWidth()+(110*i)+100,0,110,20);
                switch(i){
                    case 0:
                        arrSuchmaskenmenue[i].setText("Vornamensuche");
                        break;

                    case 1:
                        arrSuchmaskenmenue[i].setText("Nachnamensuche");

                        break;
                    case 2:
                        arrSuchmaskenmenue[i].setText("Klassensuche");
                        break;
                    case 3:
                        arrSuchmaskenmenue[i].setText("Projektsuche");
                        break;
                    default:
                        arrSuchmaskenmenue[i].setText(arrTextFields_Menue[i].getText()+"-Suche");
                        break;
                }
        }

        /*

        Datenbankenerzeugung
         */



















    }

    public  static void Suche (String arrStrings []){

        ArrayList< JTextField []> listArrYX_Anordnung= new ArrayList<>();
        class classarrBoolean{
            boolean [] arrBoolean;
            classarrBoolean(int iGröße){
                arrBoolean= new boolean[iGröße];
            }
            int iMengeanWertendesTypsTrue(){
                int iMengeanBooleans=0;
                for (boolean Schleifenelement:this.arrBoolean
                        ) {
                    if(Schleifenelement){
                        iMengeanBooleans++;
                    }
                }
                return iMengeanBooleans;
            }
        }

        HashMap<Integer, classarrBoolean> mapIntegerBooleanArray= new HashMap<>();
        int iLängeListe=0;
        for (int i = 0; i <arrTextfields_Input[0].length ; i++) {
            listArrYX_Anordnung.add(new JTextField[arrTextfields_Input.length]);
            for (int küberX = 0; küberX <arrTextfields_Input.length ; küberX++) {
                listArrYX_Anordnung.get(iLängeListe)[küberX]=arrTextfields_Input[küberX][i];
            }
            iLängeListe++;
        }

        for (int i = 0; i < listArrYX_Anordnung.size() ; i++) {
            mapIntegerBooleanArray.put(i,new classarrBoolean(arrTextfields_Input.length));
        }

        for (int iüberY = 0; iüberY < listArrYX_Anordnung.size(); iüberY++) {
            for (int küberX = 0; küberX <listArrYX_Anordnung.get(iüberY).length ; küberX++) {
                try {
                    if (listArrYX_Anordnung.get(iüberY)[küberX].getText().contains(arrStrings[küberX])) {
                        mapIntegerBooleanArray.get(iüberY).arrBoolean[küberX]=true;
                    }
                }catch(NullPointerException e_1){
                    mapIntegerBooleanArray.get(iüberY).arrBoolean[küberX]=true;
                }
            }
        }
        ArrayList<Integer> PositionenSpeicher= new ArrayList<>();

        for (Integer Schleifenobjekt :mapIntegerBooleanArray.keySet()
                ) {
            if(mapIntegerBooleanArray.get(Schleifenobjekt).iMengeanWertendesTypsTrue()==arrTextfields_Input.length){
                PositionenSpeicher.add(Schleifenobjekt);
            }
        }


        for (int i = 0; i <arrTextfields_Input.length ; i++) {
            for (int j = 0; j <arrTextfields_Input[i].length ; j++) {
                arrTextfields_Input[i][j].setVisible(false);
            }
        }

        for (int küberY = 0; küberY <PositionenSpeicher.size() ; küberY++) {
            for (int iüberX = 0; iüberX <arrTextfields_Input.length ; iüberX++) {
                listArrYX_Anordnung.get(PositionenSpeicher.get(küberY))[iüberX].setBounds(arrTextFields_Menue[iüberX].getX(),50+20*küberY,arrTextFields_Menue[iüberX].getWidth(),arrTextFields_Menue[iüberX].getHeight());
                listArrYX_Anordnung.get(PositionenSpeicher.get(küberY))[iüberX].setVisible(true);
            }
        }
    }

    public ArrayList<Integer> WertePruefung() {
        ArrayList<Integer> lRueckgabeWerte = new ArrayList<>();

        int i = 0;
        boolean bLegitimerWert = true;

        while (i < arrTextfields_Input[0].length) {
            String sInput = arrTextfields_Input[0][i].getText().trim();

            if (!sInput.equals("")) {
                for (int k = 0; k < sInput.length(); k++) {
                    if (Character.isWhitespace(sInput.charAt(k))) {
                        bLegitimerWert = false;
                        break;
                    }
                }
            } else {
                bLegitimerWert = false;
            }

            if (bLegitimerWert) {
                lRueckgabeWerte.add(i);
            }
            i++;
        }

        i = 0;

        while (i < lRueckgabeWerte.size()) {
            String sInput = arrTextfields_Input[1][i].getText().trim();
            if (!sInput.equals("")) {
                for (int k = 0; k < sInput.length(); k++) {
                    if (Character.isWhitespace(sInput.charAt(k))) {
                        lRueckgabeWerte.remove(lRueckgabeWerte.get(i));
                        break;
                    }
                }
            } else {
                lRueckgabeWerte.remove(lRueckgabeWerte.get(i));
                break;
            }
            i++;
        }

        i=0;

        while (i < lRueckgabeWerte.size()) {
            String sInput = arrTextfields_Input[2][i].getText().trim();
            if (!sInput.equals("")) {
                for (int k = 0; k < sInput.length(); k++) {
                    if (Character.isWhitespace(sInput.charAt(k))) {
                        lRueckgabeWerte.remove(lRueckgabeWerte.get(i));
                        break;
                    }
                }
            } else {
                lRueckgabeWerte.remove(lRueckgabeWerte.get(i));
                break;
            }
            i++;
        }




        /*
        Pruefung auf Integer, und, ob der Schueler in das Projekt rein darf!!!

         */


        return  lRueckgabeWerte;
    }

    public void update_der_Daten(  cArrayListErweitertSchueler list_Schueler_Main) {


        list_Schueler_Main.clear();






        ArrayList <Integer> ZwischenspeicherWertePruefung= this.WertePruefung();

        int [] [] arrSchuelerpraeferenzen= new int [ZwischenspeicherWertePruefung.size()][cSchueler.iMaximalanzahl_Projekte];

        CopyOnWriteArrayList<ArrayList<Integer> > listSchuelerwerte= new CopyOnWriteArrayList<>();


        for (int i = 0; i < ZwischenspeicherWertePruefung.size(); i++) {
            listSchuelerwerte.add(new ArrayList<>());
            for (int k = 3; k < arrTextfields_Input.length; k++) {
                try {
                    listSchuelerwerte.get(i).add(Integer.valueOf(arrTextfields_Input[k][ZwischenspeicherWertePruefung.get(i)].getText()));
                } catch (NumberFormatException e) {

                    /*
                    Fehlerbehandlung ?
                     */
                }
            }

        }

        for(int i=0;i<listSchuelerwerte.size();i++){
            for(int k=0;k<cSchueler.iMaximalanzahl_Projekte;k++){
                arrSchuelerpraeferenzen[i][k]= listSchuelerwerte.get(i).get(k);
            }
        }


        for(int i=0;i<listSchuelerwerte.size();i++) {

            cSchueler obj_zu_Erzeugend = new cSchueler(arrTextfields_Input[0][ZwischenspeicherWertePruefung.get(i)].getText(),arrTextfields_Input[1][ZwischenspeicherWertePruefung.get(i)].getText(), -1, arrSchuelerpraeferenzen[i], arrTextfields_Input[2][ZwischenspeicherWertePruefung.get(i)].getText());


            for ( cSchueler Schleifenobjekt:list_Schueler_Main
                 ) {
                if(Schleifenobjekt.sIdentifikation.equals(obj_zu_Erzeugend.sIdentifikation)){
                   list_Schueler_Main.remove(Schleifenobjekt);
                    break;
                }
            }

            list_Schueler_Main.add(obj_zu_Erzeugend);
        }

    }




    public void update_des_Interface( Connection Datenbankverbindung) throws SQLException {

            PreparedStatement extract_entrys= Datenbankverbindung.prepareStatement("SELECT preName, surName, grade FROM schüler");
            ResultSet set_entrys= extract_entrys.executeQuery();

            int iRowCounter=0;
            while (set_entrys.next()){
                arrTextfields_Input[0][iRowCounter].setText(set_entrys.getString(1));
                arrTextfields_Input[1][iRowCounter].setText(set_entrys.getString(2));
                arrTextfields_Input[2][iRowCounter].setText(set_entrys.getString(3));
                iRowCounter++;
            }

        /*
        Bekommt ein ResultSet
         */
    }
}














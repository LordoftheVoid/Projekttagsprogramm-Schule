package Version_1_Java.Interfaces;

import Version_1_Java.DatenBankenSchnittstellen.cDatabaseManager;


import Version_1_Java.cMain;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Aaron on 30.03.2017.
 */
public class c_Pupils_Frame_Input extends JFrame {


    private static cmodTextField[][] arrTextfields_Input = new cmodTextField[cMain.iMaximalanzahl_Projekte + 3][200];

    private static JTextField[] arrTextFields_Menue = new JTextField[cMain.iMaximalanzahl_Projekte + 3];


    private cDatabaseManager objDatabaseManager_Frame;


    /*

    Die X-Koordinate kann gleich bleiben, die Y-Koordinate muss durch iterieren geaendert werden


     */
    private ArrayList<ArrayList<JTextField>> listFeldersichtbar = new ArrayList<>();
    // --Commented out by Inspection (11.08.2017 15:26):ArrayList < ArrayList<JTextField> > listFelderUnsichtbar = new ArrayList<>();


    // --Commented out by Inspection (11.08.2017 15:26):boolean bAktualisieren=true;


    public c_Pupils_Frame_Input(cDatabaseManager obj_tm_DatabaseManager_Main) {


        this.objDatabaseManager_Frame = obj_tm_DatabaseManager_Main;


        JTextField[] arrSuchmasken = new JTextField[arrTextfields_Input.length];
        JTextField[] arrSuchmaskenmenue = new JTextField[arrTextfields_Input.length];

        this.setBounds(500, 500, 1000, 500);
        this.setTitle("Fenster zur Eingabe der pupils ");

        this.getContentPane().setLayout(null);

        {
            int iPraeferenzenzaehler = 1;
            for (int i_x = 0; i_x < arrTextFields_Menue.length; i_x++) {

                arrTextFields_Menue[i_x] = new JTextField();
                arrTextFields_Menue[i_x].setVisible(true);
                this.getContentPane().add(arrTextFields_Menue[i_x]);
                arrTextFields_Menue[i_x].setBounds(120 * i_x, 0, 120, 20);
                if (i_x < 3) {

                    if (i_x == 0) {
                        arrTextFields_Menue[i_x].setText("Nachname");
                    }
                    if (i_x == 1) {
                        arrTextFields_Menue[i_x].setText("Vorname");
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
                if (i_x == 0) {
                    listFeldersichtbar.add(new ArrayList<>());
                    listFeldersichtbar.get(k_y).add(arrTextfields_Input[i_x][k_y]);
                } else {
                    listFeldersichtbar.get(k_y).add(arrTextfields_Input[i_x][k_y]);
                }
                arrTextfields_Input[i_x][k_y] = new cmodTextField();

                if (k_y < cMain.i_amount_of_pupils_in_database) {
                    arrTextfields_Input[i_x][k_y].bcorrect_unique_ID = true;
                }

                this.getContentPane().add(arrTextfields_Input[i_x][k_y]);
                arrTextfields_Input[i_x][k_y].setBounds(120 * i_x, 20 * k_y + i_x + arrTextFields_Menue[0].getHeight() + 10, 120, 20);
                arrTextfields_Input[i_x][k_y].iRow_position = k_y;
                switch (i_x) {
                    case 0:
                        arrTextfields_Input[i_x][k_y].colum = "surName";

                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_ID(obj_tm_DatabaseManager_Main, arrTextfields_Input, "pupils"));
                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_NON_ID(obj_tm_DatabaseManager_Main, "pupils"));
                        break;
                    case 1:
                        arrTextfields_Input[i_x][k_y].colum = "preName";
                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_ID(obj_tm_DatabaseManager_Main, arrTextfields_Input, "pupils"));
                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_NON_ID(obj_tm_DatabaseManager_Main, "pupils"));
                        break;
                    case 2:
                        arrTextfields_Input[i_x][k_y].colum = "grade";
                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_NON_ID(obj_tm_DatabaseManager_Main, "pupils"));
                        break;
                    case 3:
                        arrTextfields_Input[i_x][k_y].colum = "pref0";
                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_NON_ID(obj_tm_DatabaseManager_Main, "pupils"));
                        break;
                    case 4:
                        arrTextfields_Input[i_x][k_y].colum = "pref1";
                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_NON_ID(obj_tm_DatabaseManager_Main,  "pupils"));
                        break;

                    case 5:
                        arrTextfields_Input[i_x][k_y].colum = "pref2";

                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_NON_ID(obj_tm_DatabaseManager_Main,  "pupils"));

                        break;

                    case 6:
                        arrTextfields_Input[i_x][k_y].colum = "pref3";

                        arrTextfields_Input[i_x][k_y].addKeyListener(new cmodKeyListener_NON_ID(obj_tm_DatabaseManager_Main,  "pupils"));

                        break;


                        /*

                        Einfuegen der Listener
                         */
                }
            }
        }


        for (int i_x = 3; i_x < arrTextfields_Input.length; i_x++) {
            for (int k_y = 0; k_y < arrTextfields_Input[i_x].length; k_y++) {
                arrTextfields_Input[i_x][k_y].setText("" + -1);
            }
        }

        for (int i = 0; i < arrSuchmasken.length; i++) {
            arrSuchmasken[i] = new JTextField();
            arrSuchmasken[i].setVisible(true);
            this.getContentPane().add(arrSuchmasken[i]);
            arrSuchmasken[i].setBounds(arrTextfields_Input[arrTextfields_Input.length - 1][0].getX() + arrTextfields_Input[arrTextfields_Input.length - 1][0].getWidth() + (110 * i) + 100, 30, 110, 20);
            arrSuchmasken[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    String[] arrArgumente = new String[arrSuchmasken.length];
                    for (int j = 0; j < arrArgumente.length; j++) {
                        if (!Objects.equals(arrSuchmasken[j].getText(), "")) {
                            arrArgumente[j] = arrSuchmasken[j].getText();
                        } else {
                            arrArgumente[j] = null;
                        }
                    }
                    Suche(arrArgumente);
                }
            });


        }


        for (int i = 0; i < arrSuchmaskenmenue.length; i++) {
            arrSuchmaskenmenue[i] = new JTextField();
            arrSuchmaskenmenue[i].setVisible(true);
            this.getContentPane().add(arrSuchmaskenmenue[i]);
            arrSuchmaskenmenue[i].setBounds(arrTextfields_Input[arrTextfields_Input.length - 1][0].getX() + arrTextfields_Input[arrTextfields_Input.length - 1][0].getWidth() + (110 * i) + 100, 0, 110, 20);
            switch (i) {
                case 0:

                    arrSuchmaskenmenue[i].setText("Nachnamensuche");
                    break;

                case 1:
                    arrSuchmaskenmenue[i].setText("Vornamensuche");

                    break;
                case 2:
                    arrSuchmaskenmenue[i].setText("Klassensuche");
                    break;
                case 3:
                    arrSuchmaskenmenue[i].setText("Projektsuche");
                    break;
                default:
                    arrSuchmaskenmenue[i].setText(arrTextFields_Menue[i].getText() + "-Suche");
                    break;
            }
        }

        /*

        Datenbankenerzeugung
         */


    }

    private static void Suche(String arrStrings[]) {

        ArrayList<JTextField[]> listArrYX_Anordnung = new ArrayList<>();
        class classarrBoolean {
            boolean[] arrBoolean;

            classarrBoolean(int iGröße) {
                arrBoolean = new boolean[iGröße];
            }

            int iMengeanWertendesTypsTrue() {
                int iMengeanBooleans = 0;
                for (boolean Schleifenelement : this.arrBoolean
                        ) {
                    if (Schleifenelement) {
                        iMengeanBooleans++;
                    }
                }
                return iMengeanBooleans;
            }
        }

        HashMap<Integer, classarrBoolean> mapIntegerBooleanArray = new HashMap<>();
        int iLängeListe = 0;
        for (int i = 0; i < arrTextfields_Input[0].length; i++) {
            listArrYX_Anordnung.add(new JTextField[arrTextfields_Input.length]);
            for (int kueberX = 0; kueberX < arrTextfields_Input.length; kueberX++) {
                listArrYX_Anordnung.get(iLängeListe)[kueberX] = arrTextfields_Input[kueberX][i];
            }
            iLängeListe++;
        }

        for (int i = 0; i < listArrYX_Anordnung.size(); i++) {
            mapIntegerBooleanArray.put(i, new classarrBoolean(arrTextfields_Input.length));
        }

        for (int iueberY = 0; iueberY < listArrYX_Anordnung.size(); iueberY++) {
            for (int kueberX = 0; kueberX < listArrYX_Anordnung.get(iueberY).length; kueberX++) {
                try {
                    if (listArrYX_Anordnung.get(iueberY)[kueberX].getText().contains(arrStrings[kueberX])) {
                        mapIntegerBooleanArray.get(iueberY).arrBoolean[kueberX] = true;
                    }
                } catch (NullPointerException e_1) {
                    mapIntegerBooleanArray.get(iueberY).arrBoolean[kueberX] = true;
                }
            }
        }
        ArrayList<Integer> PositionenSpeicher = new ArrayList<>();

        for (Integer loop_objekt : mapIntegerBooleanArray.keySet()
                ) {
            if (mapIntegerBooleanArray.get(loop_objekt).iMengeanWertendesTypsTrue() == arrTextfields_Input.length) {
                PositionenSpeicher.add(loop_objekt);
            }
        }


        for (int i = 0; i < arrTextfields_Input.length; i++) {
            for (int j = 0; j < arrTextfields_Input[i].length; j++) {
                arrTextfields_Input[i][j].setVisible(false);
            }
        }

        for (int kueberY = 0; kueberY < PositionenSpeicher.size(); kueberY++) {
            for (int iueberX = 0; iueberX < arrTextfields_Input.length; iueberX++) {
                listArrYX_Anordnung.get(PositionenSpeicher.get(kueberY))[iueberX].setBounds(arrTextFields_Menue[iueberX].getX(), 50 + 20 * kueberY, arrTextFields_Menue[iueberX].getWidth(), arrTextFields_Menue[iueberX].getHeight());
                listArrYX_Anordnung.get(PositionenSpeicher.get(kueberY))[iueberX].setVisible(true);
            }
        }
    }









        /*
        Pruefung auf Integer, und, ob der pupils in das Projekt rein darf!!!

         */










// --Commented out by Inspection START (11.08.2017 15:26):
//    public ArrayList<Integer> WertePruefung() {
//        ArrayList<Integer> lRueckgabeWerte = new ArrayList<>();
//
//        int i = 0;
//        boolean bLegitimerWert = true;
//
//        while (i < arrTextfields_Input[0].length) {
//            String sInput = arrTextfields_Input[0][i].getText().trim();
//
//            if (!sInput.equals("")) {
//                for (int k = 0; k < sInput.length(); k++) {
//                    if (Character.isWhitespace(sInput.charAt(k))) {
//                        bLegitimerWert = false;
//                        break;
//                    }
//                }
//            } else {
//                bLegitimerWert = false;
//            }
//
//            if (bLegitimerWert) {
//                lRueckgabeWerte.add(i);
//            }
//            i++;
//        }
//
//        i = 0;
//
//        while (i < lRueckgabeWerte.size()) {
//            String sInput = arrTextfields_Input[1][i].getText().trim();
//            if (!sInput.equals("")) {
//                for (int k = 0; k < sInput.length(); k++) {
//                    if (Character.isWhitespace(sInput.charAt(k))) {
//                        lRueckgabeWerte.remove(lRueckgabeWerte.get(i));
//                        break;
//                    }
//                }
//            } else {
//                lRueckgabeWerte.remove(lRueckgabeWerte.get(i));
//                break;
//            }
//            i++;
//        }
//
//        i=0;
//
//        while (i < lRueckgabeWerte.size()) {
//            String sInput = arrTextfields_Input[2][i].getText().trim();
//            if (!sInput.equals("")) {
//                for (int k = 0; k < sInput.length(); k++) {
//                    if (Character.isWhitespace(sInput.charAt(k))) {
//                        lRueckgabeWerte.remove(lRueckgabeWerte.get(i));
//                        break;
//                    }
//                }
//            } else {
//                lRueckgabeWerte.remove(lRueckgabeWerte.get(i));
//                break;
//            }
//            i++;
//        }
//
//
//
//
//        /*
//        Pruefung auf Integer, und, ob der pupils in das Projekt rein darf!!!
//
//         */
//
//
//        return  lRueckgabeWerte;
//    }




    public void v_update_Frame_from_Database() throws SQLException {

        ResultSet set_entrys= objDatabaseManager_Frame.read_entrys_all_attributes("pupils");

        int iRowCounter=0;
        while (set_entrys.next()){
            arrTextfields_Input[0][iRowCounter].setText(set_entrys.getString(3));
            arrTextfields_Input[1][iRowCounter].setText(set_entrys.getString(2));
            arrTextfields_Input[2][iRowCounter].setText(set_entrys.getString(4));
            arrTextfields_Input[3][iRowCounter].setText(set_entrys.getString(5));
            arrTextfields_Input[4][iRowCounter].setText(set_entrys.getString(6));
            arrTextfields_Input[5][iRowCounter].setText(set_entrys.getString(7));
            arrTextfields_Input[6][iRowCounter].setText(set_entrys.getString(8));

            for (int i_x = 0; i_x < arrTextfields_Input.length; i_x++) {
                arrTextfields_Input[i_x][iRowCounter].sunique_ID_Textfieldrow= set_entrys.getString(1);
                arrTextfields_Input[i_x][iRowCounter].bcorrect_unique_ID=true;
            }
            iRowCounter++;
        }
    }


}














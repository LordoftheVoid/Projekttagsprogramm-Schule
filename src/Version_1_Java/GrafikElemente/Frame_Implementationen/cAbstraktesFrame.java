package Version_1_Java.GrafikElemente.Frame_Implementationen;

import Version_1_Java.GrafikElemente.cRowEntries;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 14.08.2017.
 */



/*
Gemeinsame Klasse aller Fenster, realisiert Aufbau, Anlegung der Verknüpfung zur Datenbank etc
 */




public abstract class cAbstraktesFrame extends JFrame {


    public final int spaltenBreiteglobal = 120;
    final int yCoordinateListEntrys = 260;
    public String s_Main_Table;
    JTextField[] arrCreateEntryFields;
    CopyOnWriteArrayList<cRowEntries> listRows = new CopyOnWriteArrayList<>();
    String[] spaltenNamen;
    private JTextField[] suchFenster;
    private JTextField[] suchLabel;
    private JTextField[] anzeigeReihenname;
    private JButton[] sortierButtons;
    private boolean[] arr_b_Sort_direction;
    private JButton btnEintragserzeugung;


    public cAbstraktesFrame(int spaltenAnzahl, String strFenstername) {
        super(strFenstername);
        this.getContentPane().setLayout(null);

        anzeigeReihenname = new JTextField[spaltenAnzahl];
        arrCreateEntryFields = new JTextField[spaltenAnzahl];
        suchLabel = new JTextField[spaltenAnzahl];
        suchFenster = new JTextField[spaltenAnzahl];
        sortierButtons = new JButton[spaltenAnzahl];
        arr_b_Sort_direction = new boolean[spaltenAnzahl];
        spaltenNamen = new String[spaltenAnzahl];


        for (int i_x = 0; i_x < spaltenAnzahl; i_x++) {
            anzeigeReihenname[i_x] = new JTextField();
            anzeigeReihenname[i_x].setVisible(true);
            this.getContentPane().add(anzeigeReihenname[i_x]);
            anzeigeReihenname[i_x].setBounds(spaltenBreiteglobal * i_x, 0, spaltenBreiteglobal, 20);


            arrCreateEntryFields[i_x] = new JTextField();
            this.getContentPane().add(arrCreateEntryFields[i_x]);
            arrCreateEntryFields[i_x].setBounds(i_x * spaltenBreiteglobal, yCoordinateListEntrys - 40, spaltenBreiteglobal, 20);
            arrCreateEntryFields[i_x].setVisible(true);


            suchLabel[i_x] = new JTextField();
            suchLabel[i_x].setText("Suche nach:");
            suchLabel[i_x].setBorder(new LineBorder(Color.RED, 1));

            this.getContentPane().add(suchLabel[i_x]);
            suchLabel[i_x].setBounds(spaltenBreiteglobal * i_x, 40, spaltenBreiteglobal, 20);
            suchFenster[i_x] = new JTextField();
            suchFenster[i_x].setText("");


            System.out.println("Buttonerzeugung");

            sortierButtons[i_x] = new JButton();
            this.getContentPane().add(sortierButtons[i_x]);
            sortierButtons[i_x].setBounds(spaltenBreiteglobal* i_x, suchFenster[i_x].getY() + 100, 120, 50);
            sortierButtons[i_x].setBorder(new LineBorder(Color.RED, 1));
            sortierButtons[i_x].setVisible(true);
            sortierButtons[i_x].setText(" A ... Z");
            arr_b_Sort_direction[i_x] = true;


            this.getContentPane().add(suchFenster[i_x]);
            suchFenster[i_x].setBounds(spaltenBreiteglobal * i_x, 70, 120, 20);
            suchFenster[i_x].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    //     suche();
                }
            });


            sortierButtons[i_x].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    for (int j = 0; j < sortierButtons.length; j++) {

                        if (sortierButtons[j].equals(e.getSource())) {
                            ordnen(arr_b_Sort_direction[j], j);
                        }
                    }

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

        }


        JTextField createEntryField;

        createEntryField = new JTextField();
        this.getContentPane().add(createEntryField);
        createEntryField.setBounds(0, yCoordinateListEntrys - 60, spaltenBreiteglobal * 3, 20);
        createEntryField.setVisible(true);


    }

    public void stelleTextdar() {
        for (int i = 0; i < this.anzeigeReihenname.length; i++) {
            this.anzeigeReihenname[i].setText(this.spaltenNamen[i]);
        }
    }


    void suche() {

        HashMap<Integer, String> mapValues = new HashMap<>();

        ArrayList<Integer> listResults = new ArrayList<>();

        for (int i = 0; i < suchFenster.length; i++) {
            if (suchFenster[i].getText().length() > 0) {
                mapValues.put(i, suchFenster[i].getText());
            }
        }

        if (mapValues.size() > 0) {
            for (int i = 0; i < listRows.size(); i++) {
                if (listRows.get(i).b_searchRow(mapValues)) {
                    listResults.add(i);
                }
                listRows.get(i).v_disable();
            }

            int i = 0;
            for (Integer objList : listResults
                    ) {
                listRows.get(objList).v_enable();
                listRows.get(objList).v_setYCoordinate(yCoordinateListEntrys + i * 20);
                i++;
            }


        } else {
            for (int i = 0; i < listRows.size(); i++) {
                listRows.get(i).v_setYCoordinate(yCoordinateListEntrys + 20 * i);
                listRows.get(i).v_enable();
            }
        }

    }

    public void ordnen(boolean b_direction_tm, int i_X_colum) {


        TreeMap<String, Integer> mapStringInteger = new TreeMap<>();


        for (int i = 0; i < listRows.size(); i++) {
            if (listRows.get(i).bEnabled) {
                mapStringInteger.put(listRows.get(i).getCellContent(i_X_colum), i);
            }
        }


        if (b_direction_tm) {
            int i = 0;
            for (String objList : mapStringInteger.keySet()
                    ) {
                listRows.get(mapStringInteger.get(objList)).v_setYCoordinate(yCoordinateListEntrys + 20 * i);
                listRows.get(mapStringInteger.get(objList)).v_enable();
                i++;
            }
        } else {
            int i = mapStringInteger.keySet().size();
            for (String objList : mapStringInteger.keySet()
                    ) {
                listRows.get(mapStringInteger.get(objList)).v_setYCoordinate(yCoordinateListEntrys + 20 * i);
                listRows.get(mapStringInteger.get(objList)).v_enable();
                i--;
            }


        }

        arr_b_Sort_direction[i_X_colum] = !arr_b_Sort_direction[i_X_colum];
        if (arr_b_Sort_direction[i_X_colum]) {
            sortierButtons[i_X_colum].setText(" A ... Z");
        } else {
            sortierButtons[i_X_colum].setText("Z ... A");
        }
    }


    public void v_show_Frame(int i_X, int k_Y, int dx, int dy) {
        this.setVisible(true);
        this.setBounds(i_X, k_Y, dx, dy);
    }

}


/*




















    public void v_Setup_Listener() {

        for (cRowEntries objList : listRows
                ) {
            for (int i = 0; i < objList.fields.length; i++) {
                objList.fields[i].addKeyListener(new cmodKeyListener_NON_ID( s_Main_Table, objList, list_Column_Names.get(i)));
            }
        }

    }


    public void v_generate_rows_from_Database() {
        for (cRowEntries objLoop : listRows) {
            objLoop.v_disable();
            this.getContentPane().remove(objLoop.btnDelete);
            for (int i = 0; i < objLoop.fields.length; i++) {
                this.getContentPane().remove(objLoop.fields[i]);
                this.rootPane.remove(objLoop.fields[i]);
                this.remove(objLoop.fields[i]);
            }
        }

        listRows.clear();
        this.getContentPane().repaint();
        this.getContentPane().revalidate();
        this.repaint();
        this.revalidate();

        this.rootPane.repaint();
        this.rootPane.revalidate();

        ResultSet setEntries;
        CopyOnWriteArrayList<String> list_IDs = new CopyOnWriteArrayList<>();

        try {
            setEntries = objDatabaseManager_Input.readEoAttr(this.s_Main_Table, "s_unique_ID");
            while (setEntries.next()) {
                    if (!setEntries.getString(1).equals("-1")) {

                        list_IDs.add(setEntries.getString(1));
                    }
            }

            int i = 0;
            for (String objList : list_IDs
                    ) {
                cRowEntries objLoop = new cRowEntries(this, objList);
                objLoop.v_setup(list_Column_Names.size(), this.getContentPane(), yCoordinateListEntrys + i * 20);
                ResultSet setData = objDatabaseManager_Input.readEaAttr(this.s_Main_Table, objList);
                setData.next();
                if(this.s_Main_Table.equals("persons")){
                    for (int ini = 0; ini < objLoop.fields.length - 1; ini++) {
                        objLoop.v_setCellContent(ini, setData.getString(ini + 2));
                    }
                }else{
                    for (int ini = 0; ini < objLoop.fields.length - 1; ini++) {
                        objLoop.v_setCellContent(ini, setData.getString(ini + 1));
                    }
                }
                i++;
                listRows.add(objLoop);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
        }
        this.getContentPane().repaint();
        this.getContentPane().revalidate();

        this.repaint();
        this.revalidate();


    }





    public void v_sort_setup() {

    }


    public void v_initializeBtn() {
        this.btnEintragserzeugung = new JButton();

        btnEintragserzeugung.setVisible(true);
        this.getContentPane().add(btnEintragserzeugung);
        btnEintragserzeugung.setText("Erzeugen");

        this.btnEintragserzeugung.setBounds(this.arrCreateEntryFields.length * spaltenBreiteglobal + 20, arrCreateEntryFields[0].getY(), spaltenBreiteglobal, 20);

        btnEintragserzeugung.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (arrCreateEntryFields.length == 1) {
                    try {
                        int number = Integer.parseInt( arrCreateEntryFields[0].getText());
                        objDatabaseManager_Input.createEntry("projects",arrCreateEntryFields[0].getText());

                    } catch (SQLException e1) {
                       e1.printStackTrace();
                    }
                    catch (NumberFormatException e1){
                        cMain.v_update_Textarea_Status("Buchstaben sind nicht erlaubt, Zahlen eintragen bitte");
                    }
                    arrCreateEntryFields[0].setText("");
                } else {
                    String uniqueID = "";
                    uniqueID = uniqueID + arrCreateEntryFields[0].getText().charAt(0);
                    uniqueID = uniqueID + arrCreateEntryFields[0].getText().charAt(1);
                    uniqueID = uniqueID + arrCreateEntryFields[1].getText().charAt(0);
                    uniqueID = uniqueID + arrCreateEntryFields[1].getText().charAt(1);
                    try {
                        objDatabaseManager_Input.createEntry("persons", uniqueID);
                        objDatabaseManager_Input.updateEntry("persons", uniqueID, "s_pre_Name", arrCreateEntryFields[1].getText());
                        objDatabaseManager_Input.updateEntry("persons", uniqueID, "s_sur_Name", arrCreateEntryFields[0].getText());

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    arrCreateEntryFields[0].setText("");
                    arrCreateEntryFields[1].setText("");
                }
                v_generate_rows_from_Database();
                arrCreateEntryFields[0].setText("");
            }


            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }




    public void v_update_from_List_and_Database(cHash_Map_ID_projects_to_List_ID_pupils objlist_main) {
        int i = 0;
        ResultSet set_personal_information= null;

        for (String  loopobj:objlist_main.keySet()
             ) {
            for (String innerLoopObj:objlist_main.get(loopobj)
                 ) {
                cRowEntries objRow = new cRowEntries(this, spaltenBreiteglobal);
                objRow.v_ShortSetup(5, this.getContentPane(), yCoordinateListEntrys + 20 * i);
                try {
                    set_personal_information=objDatabaseManager_Input.readEaAttr("persons",innerLoopObj);
                    set_personal_information.next();
                    objRow.v_setCellContent(0,set_personal_information.getString(2));
                    objRow.v_setCellContent(1,set_personal_information.getString(3));
                    objRow.v_setCellContent(2,set_personal_information.getString(4));
                    for (int j = 0; j < 4; j++) {
                        if(set_personal_information.getString(4+j).equals(loopobj)){
                            objRow.v_setCellContent(4,String.valueOf(j));
                        }
                    }
                    set_personal_information=objDatabaseManager_Input.readEaAttr("projects",loopobj);
                    set_personal_information.next();
                    objRow.v_setCellContent(3,set_personal_information.getString(2));
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                listRows.add(objRow);
                i++;
            }
        }
    }
















 */



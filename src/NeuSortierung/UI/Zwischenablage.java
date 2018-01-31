package NeuSortierung.UI;

/**
 * Created by Aaron on 22.01.2018.
 */
public class Zwischenablage {
}
/*
package Version_1_Java.GrafikElemente;

import NeuSortierung.DataBaseInteractions.CDatabaseInterface;
import Version_1_Java.Lists.cHash_Map_ID_projects_to_List_ID_pupils;
import NeuSortierung.cMain;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 14.08.2017.
 */



/*

Gemeinsame Klasse aller Fenster, realisiert Aufbau, Anlegung der Verknüpfung zur Datenbank etc


public class cAbstraktesFrame extends JFrame {

    private JTextField[] arr_Search_Input;
    private JTextField[] arrSearchMenue;
    private JTextField[] arrColumHeadRow;
    private JButton[] arr_sort_Buttons;
    private boolean[] arr_b_Sort_direction;
    private JButton btnEntryCreation;


    public JTextField[] arrCreateEntryFields;

    public CDatabaseInterface objDatabaseManager_Input;

    private CopyOnWriteArrayList<String> list_Column_Names;

    final int spaltenBreiteglobal = 120;
    final int yCoordinateListEntrys = 260;






    CopyOnWriteArrayList<cRowEntries> listRows = new CopyOnWriteArrayList<>();


    public String s_Main_Table;


    public cAbstraktesFrame(String s_Table_tm, CopyOnWriteArrayList<String> list_Columns, String strFenstername) {
        super(strFenstername);
        this.list_Column_Names = list_Columns;
        this.s_Main_Table = s_Table_tm;
        this.getContentPane().setLayout(null);
    }

    public void v_show_Frame(int i_X, int k_Y, int dx, int dy) {

        this.setVisible(true);
        this.setBounds(i_X, k_Y, dx, dy);
    }


    public void v_setupEntryfields(String text, int amount) {

        arrCreateEntryFields = new JTextField[amount];

        for (int i = 0; i < arrCreateEntryFields.length; i++) {
            arrCreateEntryFields[i] = new JTextField();
            this.getContentPane().add(arrCreateEntryFields[i]);
            arrCreateEntryFields[i].setBounds(i * spaltenBreiteglobal, yCoordinateListEntrys - 40, spaltenBreiteglobal, 20);
            arrCreateEntryFields[i].setVisible(true);
        }
        JTextField createEntryField;

        createEntryField = new JTextField(text);
        this.getContentPane().add(createEntryField);
        createEntryField.setBounds(0, yCoordinateListEntrys - 60, spaltenBreiteglobal * 3, 20);
        createEntryField.setVisible(true);
    }

    public void v_set_custom_Head(CopyOnWriteArrayList<String> list_s_values) {

        arrColumHeadRow = new JTextField[list_s_values.size()];

        for (int i_x = 0; i_x < arrColumHeadRow.length; i_x++) {
            arrColumHeadRow[i_x] = new JTextField();
            arrColumHeadRow[i_x].setText(list_s_values.get(i_x));
            arrColumHeadRow[i_x].setVisible(true);
            this.getContentPane().add(arrColumHeadRow[i_x]);
            arrColumHeadRow[i_x].setBounds(120 * i_x, 0, spaltenBreiteglobal, 20);

        }
    }

    public void v_set_custom_Search(CopyOnWriteArrayList<String> list_s_values) {

        arrSearchMenue = new JTextField[list_s_values.size()];
        arr_Search_Input = new JTextField[list_s_values.size()];

        for (int i = 0; i < arrSearchMenue.length; i++) {

            arrSearchMenue[i] = new JTextField();
            arrSearchMenue[i].setText("Suche nach:");
            arrSearchMenue[i].setBorder(new LineBorder(Color.RED, 1));

            this.getContentPane().add(arrSearchMenue[i]);
            arrSearchMenue[i].setBounds(120 * i, 40, spaltenBreiteglobal, 20);
            arr_Search_Input[i] = new JTextField();
            arr_Search_Input[i].setText("");

            this.getContentPane().add(arr_Search_Input[i]);
            arr_Search_Input[i].setBounds(120 * i, 70, 120, 20);
            arr_Search_Input[i].addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    suche();
                }
            });


        }
    }


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


    public void suche() {


        HashMap<Integer, String> mapValues = new HashMap<>();

        ArrayList<Integer> listResults = new ArrayList<>();

        for (int i = 0; i < arr_Search_Input.length; i++) {
            if (arr_Search_Input[i].getText().length() > 0) {
                mapValues.put(i, arr_Search_Input[i].getText());
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


    public void v_sort_setup() {
        arr_sort_Buttons = new JButton[arr_Search_Input.length];
        arr_b_Sort_direction = new boolean[arr_sort_Buttons.length];
        for (int i = 0; i < arr_Search_Input.length; i++) {
            arr_sort_Buttons[i] = new JButton();
            this.getContentPane().add(arr_sort_Buttons[i]);
            arr_sort_Buttons[i].setBounds(arr_Search_Input[i].getX(), arr_Search_Input[i].getY() + 50, 120, 50);
            arr_sort_Buttons[i].setBorder(new LineBorder(Color.RED, 1));
            arr_sort_Buttons[i].setVisible(true);
            arr_sort_Buttons[i].setText(" A ... Z");
            arr_b_Sort_direction[i] = true;
            arr_sort_Buttons[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    for (int j = 0; j < arr_sort_Buttons.length; j++) {

                        if (arr_sort_Buttons[j].equals(e.getSource())) {
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
    }


    public void v_initializeBtn() {
        this.btnEntryCreation = new JButton();

        btnEntryCreation.setVisible(true);
        this.getContentPane().add(btnEntryCreation);
        btnEntryCreation.setText("Erzeugen");

        this.btnEntryCreation.setBounds(this.arrCreateEntryFields.length * spaltenBreiteglobal + 20, arrCreateEntryFields[0].getY(), spaltenBreiteglobal, 20);

        btnEntryCreation.addMouseListener(new MouseListener() {
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
            arr_sort_Buttons[i_X_colum].setText(" A ... Z");
        } else {
            arr_sort_Buttons[i_X_colum].setText("Z ... A");
        }
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
}

*/




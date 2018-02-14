package V2.UI.Frame;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.UI.NonFrameElements.Row;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 14.08.2017.
 */


public abstract class BaseFrame extends JFrame {


    private final static int WIDTHGLOBAL = 120;
    final int yCoordinateListEntrys = 260;
    public JTextField[] columNames;
    public JButton btnCreateEntry;
    JTextField[] arrCreateEntryFields;
    int columns;
    private CopyOnWriteArrayList<Row> listTextRows = new CopyOnWriteArrayList<>();
    private JTextField[] suchLabel;


    private JTextField[] arrTextFieldSortInput;
    private JButton[] arrButtonsSort;
    private boolean[] sortdirections;




    void setupGUIElementsSort(){
        for (int arrayIndex = 0; arrayIndex < this.columns; arrayIndex++) {
            arrButtonsSort[arrayIndex] = new JButton();
            this.getContentPane().add(arrButtonsSort[arrayIndex]);
            arrButtonsSort[arrayIndex].setBounds(WIDTHGLOBAL * arrayIndex, 120, 120, 50);
            arrButtonsSort[arrayIndex].setBorder(new LineBorder(Color.RED, 1));
            arrButtonsSort[arrayIndex].setVisible(true);
            arrButtonsSort[arrayIndex].setText(" A ... Z");
            sortdirections[arrayIndex] = true;

            arrButtonsSort[arrayIndex].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    for (int arrayIndex = 0; arrayIndex < arrButtonsSort.length; arrayIndex++) {

                        if (arrButtonsSort[arrayIndex].equals(e.getSource())) {
                            ordnen(sortdirections[arrayIndex], arrayIndex);
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

    void setupGUIElementsText(){
        for (int arrayIndex = 0; arrayIndex < this.columns; arrayIndex++) {
            columNames[arrayIndex] = new JTextField();
            columNames[arrayIndex].setVisible(true);
            this.getContentPane().add(columNames[arrayIndex]);
            columNames[arrayIndex].setBounds(WIDTHGLOBAL * arrayIndex, 0, WIDTHGLOBAL, 20);
        }
    }

    public BaseFrame(int colums, String strFenstername) {

        super(strFenstername);
        this.getContentPane().setLayout(null);


        columNames = new JTextField[colums];
        suchLabel = new JTextField[colums];
        arrTextFieldSortInput = new JTextField[colums];
        arrButtonsSort = new JButton[colums];
        sortdirections = new boolean[colums];

        this.columns = colums;

        this.setupGUITextFieldRowForCreation();
        this.setupGUIBtnForCreation(WIDTHGLOBAL);
        this.setupGUIElementsSearch();
        this.setupGUIElementsSort();
        this.setupGUIElementsText();

        this.showfixedText();

        try {
            this.generateRows(this.requestDataBaseContent());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.updateRowYKoordinate(300);


    }

    static public int getWIDTH() {
        return WIDTHGLOBAL;
    }

    public abstract ArrayList<DataBaseElementObject> requestDataBaseContent() throws SQLException;

    public abstract void generateDataBaseEntry();

    public abstract void setupGUIBtnForCreation(int width);

    public void setupGUITextFieldRowForCreation() {
        arrCreateEntryFields = new JTextField[2];

        for (int arrayIndex = 0; arrayIndex < 2; arrayIndex++) {
            arrCreateEntryFields[arrayIndex] = new JTextField();
            this.getContentPane().add(arrCreateEntryFields[arrayIndex]);
            arrCreateEntryFields[arrayIndex].setBounds(BaseFrame.getWIDTH() * arrayIndex, yCoordinateListEntrys - 60, BaseFrame.getWIDTH(), 20);
            arrCreateEntryFields[arrayIndex].setVisible(true);
        }

    }


    void clearRows() {

    }


    void resetInterface() {
        this.clearRows();
        try {
            this.generateRows(this.requestDataBaseContent());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void generateRows(ArrayList<DataBaseElementObject> dataBaseEntrys) {
        for (int listIndex = 0; listIndex < dataBaseEntrys.size(); listIndex++) {
            this.listTextRows.add(new Row(this.columns, dataBaseEntrys.get(listIndex), this.getContentPane()));
        }
    }


    void updateRowYKoordinate(int yFirstElement) {
        for (int listIndex = 0; listIndex < this.listTextRows.size(); listIndex++) {
            this.listTextRows.get(listIndex).setYCoordinates(listIndex * 20 + yFirstElement);
        }
    }


    void search(String[] args) {

    }


    void displayText(ArrayList<String[]> rowValues) {

    }


    public void generateNewDataBaseEntry(String[] values) {

    }


    public void ordnen(boolean b_direction_tm, int i_X_colum) {

        sortdirections[i_X_colum] = !sortdirections[i_X_colum];
        if (sortdirections[i_X_colum]) {
            arrButtonsSort[i_X_colum].setText(" A ... Z");
        } else {
            arrButtonsSort[i_X_colum].setText("Z ... A");
        }
    }


    public void displayFrame(int xKoordinate, int yKoordinate, int dx, int dy) {
        this.setVisible(true);
        this.setBounds(xKoordinate, yKoordinate, dx, dy);
    }


    public abstract void showfixedText();


    void setupGUIElementsSearch(){

        for (int arrayIndex = 0; arrayIndex < this.columns; arrayIndex++) {
            suchLabel[arrayIndex] = new JTextField();
            suchLabel[arrayIndex].setText("Suche nach:");
            suchLabel[arrayIndex].setBorder(new LineBorder(Color.RED, 1));

            this.getContentPane().add(suchLabel[arrayIndex]);
            suchLabel[arrayIndex].setBounds(WIDTHGLOBAL * arrayIndex, 40, WIDTHGLOBAL, 20);
            arrTextFieldSortInput[arrayIndex] = new JTextField();
            arrTextFieldSortInput[arrayIndex].setText("");

            this.getContentPane().add(arrTextFieldSortInput[arrayIndex]);

            arrTextFieldSortInput[arrayIndex].setBounds(WIDTHGLOBAL * arrayIndex, 70, 120, 20);

            arrTextFieldSortInput[arrayIndex].addKeyListener(new KeyListener() {
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
        }





    }


}

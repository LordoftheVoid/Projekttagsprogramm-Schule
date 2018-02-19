package V2.UI.Frame;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;
import V2.UI.NonFrameElements.DisplayedRows.AbstractRow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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
    CopyOnWriteArrayList<AbstractRow> listTextRows = new CopyOnWriteArrayList<>();
    private JTextField[] suchLabel;


    private JTextField[] arrTextFieldSortInput;
    private boolean[] sortdirections;
    private JButton[] arrButtonsSort;

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


    public void displayFrame(int xKoordinate, int yKoordinate, int dx, int dy) {
        this.setVisible(true);
        this.setBounds(xKoordinate, yKoordinate, dx, dy);
    }

    private void setupGUIElementsSort() {
        for (int arrayIndex = 0; arrayIndex < this.columns; arrayIndex++) {
            arrButtonsSort[arrayIndex] = new JButton();
            this.getContentPane().add(arrButtonsSort[arrayIndex]);
            arrButtonsSort[arrayIndex].setBounds(WIDTHGLOBAL * arrayIndex, 120, 120, 50);
            arrButtonsSort[arrayIndex].setBorder(new LineBorder(Color.RED, 1));
            arrButtonsSort[arrayIndex].setVisible(true);
            arrButtonsSort[arrayIndex].setText(" A ... Z");
            sortdirections[arrayIndex] = false;

            arrButtonsSort[arrayIndex].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    for (int arrayIndex = 0; arrayIndex < arrButtonsSort.length; arrayIndex++) {
                        if (arrButtonsSort[arrayIndex].equals(e.getSource())) {
                            sortListVisibleElements(arrayIndex);
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


    private void setupGUIElementsText() {
        for (int arrayIndex = 0; arrayIndex < this.columns; arrayIndex++) {
            columNames[arrayIndex] = new JTextField();
            columNames[arrayIndex].setVisible(true);
            this.getContentPane().add(columNames[arrayIndex]);
            columNames[arrayIndex].setBounds(WIDTHGLOBAL * arrayIndex, 0, WIDTHGLOBAL, 20);
        }
    }

    public void setupGUITextFieldRowForCreation() {
        arrCreateEntryFields = new JTextField[2];

        for (int arrayIndex = 0; arrayIndex < 2; arrayIndex++) {
            arrCreateEntryFields[arrayIndex] = new JTextField();
            this.getContentPane().add(arrCreateEntryFields[arrayIndex]);
            arrCreateEntryFields[arrayIndex].setBounds(BaseFrame.WIDTHGLOBAL * arrayIndex, yCoordinateListEntrys - 60, BaseFrame.WIDTHGLOBAL, 20);
            arrCreateEntryFields[arrayIndex].setVisible(true);
        }

    }


    private void clearRows() {
        for (int i = 0; i < this.listTextRows.size(); i++) {
            this.listTextRows.get(i).removeFromFrame(this.getContentPane());
        }
    }


    void resetInterface() {
        this.clearRows();
        this.listTextRows = new CopyOnWriteArrayList<>();
        try {
            this.generateRows(this.requestDataBaseContent());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void updateRowYKoordinate(int yFirstElement) {
        for (int listIndex = 0; listIndex < this.listTextRows.size(); listIndex++) {
            this.listTextRows.get(listIndex).setYCoordinates(listIndex * 20 + yFirstElement);
        }
    }


    private void setupGUIElementsSearch() {

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
                    searchListofRows();
                }
            });
        }


    }


    private void sortListVisibleElements(int indexColumtoCompare) {

        AbstractRow.indexCompareElement = indexColumtoCompare;

        Collections.sort(this.listTextRows);
        if (sortdirections[indexColumtoCompare]) {
            Collections.reverse(this.listTextRows);
        }

        sortdirections[indexColumtoCompare] = !sortdirections[indexColumtoCompare];
        if (sortdirections[indexColumtoCompare]) {
            arrButtonsSort[indexColumtoCompare].setText(" A ... Z");
        } else {
            arrButtonsSort[indexColumtoCompare].setText("Z ... A");
        }
        this.updateRowYKoordinate(250);
    }


    private void searchListofRows() {

    }


    public abstract void showfixedText();

    public abstract ArrayList<AbstractDataBaseRepresentation> requestDataBaseContent() throws SQLException;

    public abstract void setupGUIBtnForCreation(int width);

    abstract void generateRows(ArrayList<AbstractDataBaseRepresentation> dataBaseEntrys);


}

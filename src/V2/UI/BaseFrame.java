package V2.UI;

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
    int columns;
    JTextField[] arrCreateEntryFields = new JTextField[2];
    private CopyOnWriteArrayList<Row> listTextRows = new CopyOnWriteArrayList<>();
    private JTextField[] suchLabel;


    private JTextField[] suchFenster;
    private JButton[] sortierButtons;
    private boolean[] sortdirections;


    public BaseFrame(int colums, String strFenstername) {

        super(strFenstername);
        this.getContentPane().setLayout(null);

        columNames = new JTextField[colums];
        suchLabel = new JTextField[colums];
        suchFenster = new JTextField[colums];
        sortierButtons = new JButton[colums];
        sortdirections = new boolean[colums];

        this.columns = colums;


        for (int i_x = 0; i_x < colums; i_x++) {
            columNames[i_x] = new JTextField();
            columNames[i_x].setVisible(true);
            this.getContentPane().add(columNames[i_x]);
            columNames[i_x].setBounds(WIDTHGLOBAL * i_x, 0, WIDTHGLOBAL, 20);


            suchLabel[i_x] = new JTextField();
            suchLabel[i_x].setText("Suche nach:");
            suchLabel[i_x].setBorder(new LineBorder(Color.RED, 1));

            this.getContentPane().add(suchLabel[i_x]);
            suchLabel[i_x].setBounds(WIDTHGLOBAL * i_x, 40, WIDTHGLOBAL, 20);
            suchFenster[i_x] = new JTextField();
            suchFenster[i_x].setText("");


            sortierButtons[i_x] = new JButton();
            this.getContentPane().add(sortierButtons[i_x]);
            sortierButtons[i_x].setBounds(WIDTHGLOBAL * i_x, suchFenster[i_x].getY() + 100, 120, 50);
            sortierButtons[i_x].setBorder(new LineBorder(Color.RED, 1));
            sortierButtons[i_x].setVisible(true);
            sortierButtons[i_x].setText(" A ... Z");
            sortdirections[i_x] = true;


            this.setGUIElementsForCreation();


            this.getContentPane().add(suchFenster[i_x]);
            suchFenster[i_x].setBounds(WIDTHGLOBAL * i_x, 70, 120, 20);
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

                    for (int arrayIndex = 0; arrayIndex < sortierButtons.length; arrayIndex++) {

                        if (sortierButtons[arrayIndex].equals(e.getSource())) {
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


    public void setGUIElementsForCreation() {
        for (int arrayIndex = 0; arrayIndex < 2; arrayIndex++) {
            arrCreateEntryFields[arrayIndex] = new JTextField();
            this.getContentPane().add(arrCreateEntryFields[arrayIndex]);
            arrCreateEntryFields[arrayIndex].setBounds(BaseFrame.getWIDTH() * arrayIndex, yCoordinateListEntrys - 60, BaseFrame.getWIDTH(), 20);
            arrCreateEntryFields[arrayIndex].setVisible(true);
        }


        /**TODO: Eine Ebene tiefer legen
         *
         */
        btnCreateEntry = new JButton("Eintrag erzeugen");
        super.getContentPane().add(btnCreateEntry);
        btnCreateEntry.setBounds(arrCreateEntryFields[1].getX()+WIDTHGLOBAL,arrCreateEntryFields[1].getY(),WIDTHGLOBAL,60);
        btnCreateEntry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               JButton source = (JButton)  e.getSource();
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
            sortierButtons[i_X_colum].setText(" A ... Z");
        } else {
            sortierButtons[i_X_colum].setText("Z ... A");
        }
    }


    public void displayFrame(int i_X, int k_Y, int dx, int dy) {
        this.setVisible(true);
        this.setBounds(i_X, k_Y, dx, dy);
    }


    public abstract void showfixedText();


}

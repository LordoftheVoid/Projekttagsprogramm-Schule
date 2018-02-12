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



/*
Gemeinsame Klasse aller Fenster, realisiert Aufbau, Anlegung der Verknüpfung zur Datenbank etc
 */


public abstract class BaseFrame extends JFrame {


    private final static  int WIDTHGLOBAL = 120;
    final int yCoordinateListEntrys = 260;
    private CopyOnWriteArrayList<Row> listTextRows = new CopyOnWriteArrayList<>();
    private  JTextField[] suchLabel;
    public JTextField[] columNames;
    public JTextField[] arrCreateEntryFields;
    int columns;
    public JButton btnCreateEntry;



    /*
    Other Variables every Frame uses
     */
    /*
    GUI-Elements that every Frame shares
     */
    private JTextField[] suchFenster;
    private JButton[] sortierButtons;
    private boolean[] sortdirections;


   static  public int  getWIDTH(){
        return WIDTHGLOBAL;
    }


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


            this.setUpCreationGUIElements();


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

                    for (int j = 0; j < sortierButtons.length; j++) {

                        if (sortierButtons[j].equals(e.getSource())) {
                            ordnen(sortdirections[j], j);
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


    public abstract ArrayList<DataBaseElementObject> requestDataBaseContent() throws SQLException;


    public abstract void setUpCreationGUIElements();




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
        for (int i = 0; i < dataBaseEntrys.size(); i++) {
            this.listTextRows.add(new Row(this.columns, dataBaseEntrys.get(i), this.getContentPane()));
        }
    }


    void updateRowYKoordinate(int yFirstElement) {
        for (int i = 0; i < this.listTextRows.size(); i++) {
            this.listTextRows.get(i).setYCoordinates(i * 20 + yFirstElement);
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

package NeuSortierung.UI.FrameRows;

import NeuSortierung.DataBaseInteractions.DataBaseObjekts.DataBaseElementInterFace;

import javax.swing.*;

/**
 * Created by Aaron on 31.01.2018.
 */
public class BaseClassRow {

    JTextField arrFields[];

    public BaseClassRow(DataBaseElementInterFace dataBaseEntry, JFrame frame, int yKoordinate, int spaltenbreite) {
        this.arrFields = new JTextField[dataBaseEntry.amountofColums];
        System.out.println(dataBaseEntry.amountofColums);
        for (int i = 0; i < dataBaseEntry.amountofColums; i++) {
            this.arrFields[i] = new JTextField();
            this.arrFields[i].setText(dataBaseEntry.values[i]);
        }
        for (int i = 0; i < this.arrFields.length; i++) {
            frame.getContentPane().add(this.arrFields[i]);
            this.arrFields[i].setVisible(true);
            this.arrFields[i].setBounds(spaltenbreite * i, yKoordinate, 120, 20);
        }


    }

    public void setYKoordinates(int newYValue) {
        for (int i = 0; i < arrFields.length; i++) {
            arrFields[i].setLocation(arrFields[i].getX(), newYValue);
        }

    }

}

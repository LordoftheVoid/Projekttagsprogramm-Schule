package NeuSortierung.UI.FrameRows;

import NeuSortierung.DataBaseInteractions.DataBaseObjekts.cDataBaseElement;

import javax.swing.*;

/**
 * Created by Aaron on 31.01.2018.
 */
public class cBaseClassRow {

    JTextField arrFields [];



    public cBaseClassRow(cDataBaseElement baseElement){

    }


    public void setYKoordinates( int newYValue){
        for (int i = 0; i < arrFields.length; i++) {
            arrFields[i].setLocation(arrFields[i].getX(),newYValue);
        }

    }

}

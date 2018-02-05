package V2.UI.NonFrameElements;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aaron on 05.02.2018.
 */
public class Row {


    JTextField [] displayElements;

    Row(int columns, String dataBaseElementId, Container targetFrame){

        this.displayElements = new JTextField[columns];
        for (int i = 0; i < columns; i++) {
            displayElements[i] = new JTextField();
            displayElements[i].setVisible(true);
            targetFrame.add(displayElements[i]);
        }

    }

    void showText(String [] newText){
        for (int i = 0; i < displayElements.length; i++) {
            displayElements[i].setText(newText[i]);
        }
    }


    void setYCoordinates(int yKoordinate){
        for (int i = 0; i < this.displayElements.length; i++) {
            displayElements[i].setLocation(displayElements[i].getX(),yKoordinate);
        }
    }



    void updateDataBaseEntry(){

        /**TODO Aufruf vom Listener, Update des Eintrags
        */
    }

    void deleteDataBaseEntry(){

    }


}

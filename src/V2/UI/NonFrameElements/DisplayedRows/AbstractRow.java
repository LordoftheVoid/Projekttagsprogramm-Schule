package V2.UI.NonFrameElements.DisplayedRows;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aaron on 05.02.2018.
 */
public abstract class AbstractRow implements  Comparable {


    JTextField[] displayElements;
    JButton btnDeleteEntry;
    AbstractDataBaseRepresentation dataBaseEntry;

    public static int indexCompareElement=0;

    public AbstractRow(int columns, AbstractDataBaseRepresentation dataBaseEntry, Container targetFrame) {

        this.dataBaseEntry = dataBaseEntry;
        this.displayElements = new JTextField[columns];

        this.generateTextFields();
        this.setupFocusListener();
        this.showText();

        for (int arrayIndex = 0; arrayIndex < columns; arrayIndex++) {
            displayElements[arrayIndex].setVisible(true);
            targetFrame.add(displayElements[arrayIndex]);
            displayElements[arrayIndex].setBounds(arrayIndex * 120, 600, 120, 20);
        }
        btnDeleteEntry = new JButton("Eintrag löschen");
        targetFrame.add(btnDeleteEntry);
        btnDeleteEntry.setVisible(true);
        btnDeleteEntry.setBounds(this.displayElements.length * 120, 600, 120, 20);


    }


    @Override
    public int compareTo(Object o) {
        AbstractRow comparedObj = (AbstractRow) o;
        return this.displayElements[indexCompareElement].getText().compareTo(comparedObj.displayElements[indexCompareElement].getText());
    }



    private void showText() {
        String[] valuesEntry = this.dataBaseEntry.getNonHashdataBaseValues();
        for (int arrayIndex = 0; arrayIndex < displayElements.length; arrayIndex++) {
            displayElements[arrayIndex].setText(valuesEntry[arrayIndex]);
        }
    }

   public  void removeFromFrame(Container targetFrame) {
        for (int arrayIndex = 0; arrayIndex < this.displayElements.length; arrayIndex++) {
            targetFrame.remove(displayElements[arrayIndex]);
        }
        targetFrame.remove(this.btnDeleteEntry);
    }

    public void setYCoordinates(int yKoordinate) {
        for (int arrayIndex = 0; arrayIndex < this.displayElements.length; arrayIndex++) {
            displayElements[arrayIndex].setLocation(displayElements[arrayIndex].getX(), yKoordinate);
        }
        btnDeleteEntry.setLocation(btnDeleteEntry.getX(), yKoordinate);
    }


    void deleteDataBaseEntry() {
        this.dataBaseEntry.deleteEntry();
    }

    abstract void generateTextFields();

    abstract void setupFocusListener();
}

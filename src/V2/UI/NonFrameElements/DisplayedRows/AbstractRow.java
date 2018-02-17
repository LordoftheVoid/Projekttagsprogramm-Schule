package V2.UI.NonFrameElements.DisplayedRows;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.UI.NonFrameElements.TextFields.CustomTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Aaron on 05.02.2018.
 */
public abstract class AbstractRow implements  Comparable {


    JTextField[] displayElements;
    JButton btnDeleteEntry;
    DataBaseElementObject dataBaseEntry;

    public AbstractRow(int columns, DataBaseElementObject dataBaseEntry, Container targetFrame) {

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

    abstract void generateTextFields();

    public static int indexCompareElement=0;

    @Override
    public int compareTo(Object o) {
        AbstractRow comparedObj = (AbstractRow) o;
        return this.displayElements[indexCompareElement].getText().compareTo(comparedObj.displayElements[indexCompareElement].getText());
    }


    void setupFocusListener() {

        for (int arrayIndex = 0; arrayIndex < displayElements.length; arrayIndex++) {
            displayElements[arrayIndex].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    CustomTextField objSource = (CustomTextField) e.getSource();
                    objSource.oldValue = objSource.getText();

                }

                @Override
                public void focusLost(FocusEvent e) {
                    CustomTextField objSource = (CustomTextField) e.getSource();
                    if (objSource.getText().equals(objSource.oldValue)) {
                        if (objSource.isValidInput()) {
                            objSource.dataBaseEntry.genericSetter(objSource.getText(), objSource.index);
                            System.out.println("Es ging");
                        } else {
                            objSource.setText(objSource.oldValue);
                            System.out.println("Es ging nicht!");
                            //TODO: MAulen!
                        }
                    }
                }
            });
        }


    }


    void showText() {
        String[] valuesEntry = new String[this.displayElements.length];

        int amountIdentityValues = this.dataBaseEntry.getVisibleIdentityValues().length;
        int amountInteraktionValues = this.dataBaseEntry.getInterAktionValues().length;


        //TODO: Aus der Datenbank erfragen!!
        for (int arrayIndex = 0; arrayIndex < amountIdentityValues + amountInteraktionValues; arrayIndex++) {
            if (arrayIndex < amountIdentityValues) {
                valuesEntry[arrayIndex] = this.dataBaseEntry.getVisibleIdentityValues()[arrayIndex];
            } else {
                valuesEntry[arrayIndex] = this.dataBaseEntry.getInterAktionValues()[arrayIndex - amountIdentityValues];
            }
        }

        for (int arrayIndex = 0; arrayIndex < displayElements.length; arrayIndex++) {
            System.out.println(valuesEntry[arrayIndex] + "+");
            displayElements[arrayIndex].setText(valuesEntry[arrayIndex]);
        }
    }

   public  void removeFromFrame(Container targetFrame) {
        for (int arrayIndex = 0; arrayIndex < this.displayElements.length; arrayIndex++) {
            targetFrame.remove(displayElements[arrayIndex]);
        }
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


}

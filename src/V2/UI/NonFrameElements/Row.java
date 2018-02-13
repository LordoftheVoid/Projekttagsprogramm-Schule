package V2.UI.NonFrameElements;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by Aaron on 05.02.2018.
 */
public class Row {


    JTextField[] displayElements;
    JButton btnDeleteEntry;
    DataBaseElementObject dataBaseEntry;

    public Row(int columns, DataBaseElementObject dataBaseEntry, Container targetFrame) {

        this.dataBaseEntry = dataBaseEntry;
        this.displayElements = new JTextField[columns];
        for (int arrayIndex = 0; arrayIndex < columns; arrayIndex++) {
            displayElements[arrayIndex] = new JTextField();
            displayElements[arrayIndex].setVisible(true);
            targetFrame.add(displayElements[arrayIndex]);
            displayElements[arrayIndex].setBounds(arrayIndex * 120, 600, 120, 20);
        }
        btnDeleteEntry = new JButton("Eintrag löschen");
        targetFrame.add(btnDeleteEntry);
        btnDeleteEntry.setVisible(true);
        btnDeleteEntry.setBounds(this.displayElements.length * 120, 600, 120, 20);

        this.showText();
    }

    void showText() {
        String[] valuesEntry = new String[this.displayElements.length];

        int amountIdentityValues = this.dataBaseEntry.getPublicIdentityValues().length;
        int amountInteraktionValues = this.dataBaseEntry.getInterAktionValues().length;


        for (int arrayIndex = 0; arrayIndex < amountIdentityValues + amountInteraktionValues; arrayIndex++) {
            if (arrayIndex < amountIdentityValues) {
                valuesEntry[arrayIndex] = this.dataBaseEntry.getPublicIdentityValues()[arrayIndex];
            } else {
                valuesEntry[arrayIndex] = this.dataBaseEntry.getInterAktionValues()[arrayIndex - amountIdentityValues];
            }
        }

        for (int arrayIndex = 0; arrayIndex < displayElements.length; arrayIndex++) {
            displayElements[arrayIndex].setText(valuesEntry[arrayIndex]);
        }
    }

    void removeFromFrame(Container targetFrame) {
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


    void updateDataBaseEntry() {
        boolean valid = this.dataBaseEntry.isValid();

        if (valid) {
            try {
                this.dataBaseEntry.savetoDataBase();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            /**TODO Nutzerausgabe ?
             *
             */
        }

    }

    void deleteDataBaseEntry() {
        this.dataBaseEntry.deleteEntry();
    }


}

package V2.UI.NonFrameElements.DisplayedRows;

import V2.DataBaseInternalClasses.AbstractDataBaseRepresentation;
import V2.UI.Frame.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Aaron on 05.02.2018.
 */
public abstract class AbstractRow implements  Comparable {


    JTextField[] displayElements;
    private JButton btnDeleteEntry;
    AbstractDataBaseRepresentation dataBaseEntry;

    public static int indexCompareElement=0;

    AbstractRow(int columns, AbstractDataBaseRepresentation dataBaseEntry, Container targetFrame) {

        this.dataBaseEntry = dataBaseEntry;
        this.displayElements = new JTextField[columns];

        this.generateTextFields();
        this.setupFocusListener();
        this.showText();

        for (int arrayIndex = 0; arrayIndex < columns; arrayIndex++) {
            displayElements[arrayIndex].setVisible(true);
            targetFrame.add(displayElements[arrayIndex]);
            displayElements[arrayIndex].setBounds(arrayIndex * BaseFrame.WIDTHGLOBAL, 600, BaseFrame.WIDTHGLOBAL, 20);
        }
        btnDeleteEntry = new JButton("Eintrag löschen");
        targetFrame.add(btnDeleteEntry);
        btnDeleteEntry.setVisible(true);
        btnDeleteEntry.setBounds(this.displayElements.length * BaseFrame.WIDTHGLOBAL, 600, BaseFrame.WIDTHGLOBAL, 20);
        btnDeleteEntry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteDataBaseEntry();
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


    @Override
    public int compareTo(Object o) {
        AbstractRow comparedObj = (AbstractRow) o;
        return this.displayElements[indexCompareElement].getText().compareTo(comparedObj.displayElements[indexCompareElement].getText());
    }

    public String getText(int index){
        return this.displayElements [index].getText();
    }


    private void showText() {
        String[] valuesEntry = this.dataBaseEntry.getNonHashdataBaseValues();
        for (int arrayIndex = 0; arrayIndex < this.displayElements.length; arrayIndex++) {
            displayElements[arrayIndex].setText(valuesEntry[arrayIndex]);
        }
    }

   public  void removeFromFrame(Container targetFrame) {
       for (JTextField displayElement : this.displayElements) {
           targetFrame.remove(displayElement);
       }
        targetFrame.remove(this.btnDeleteEntry);
    }

    public void setYCoordinates(int yKoordinate) {
        for (JTextField displayElement : this.displayElements) {
            displayElement.setLocation(displayElement.getX(), yKoordinate);
        }
        btnDeleteEntry.setLocation(btnDeleteEntry.getX(), yKoordinate);
    }


    private void deleteDataBaseEntry() {
        this.dataBaseEntry.deleteEntry();
    }

    abstract void generateTextFields();

    abstract void setupFocusListener();
}

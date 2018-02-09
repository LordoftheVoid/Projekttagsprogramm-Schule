package V2.UI.NonFrameElements;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by Aaron on 05.02.2018.
 */
public class Row {


    JTextField [] displayElements;
    JButton btnDeleteEntry;
    DataBaseElementObject dataBaseEntry;

    public Row(int columns, DataBaseElementObject dataBaseEntry, Container targetFrame){

        this.dataBaseEntry =dataBaseEntry;
        this.displayElements = new JTextField[columns];
        for (int i = 0; i < columns; i++) {
            displayElements[i] = new JTextField();
            displayElements[i].setVisible(true);
            targetFrame.add(displayElements[i]);
            displayElements[i].setBounds(i*120, 600, 120, 20);
        }
        btnDeleteEntry = new JButton("Eintrag löschen");
        targetFrame.add(btnDeleteEntry);
        btnDeleteEntry.setVisible(true);
        btnDeleteEntry.setBounds(this.displayElements.length*120,600,120,20);
    }

    void showText(){
        String[] valuesEntry = this.dataBaseEntry.getIdentityValues();
        for (int i = 0; i < displayElements.length; i++) {
            displayElements[i].setText(valuesEntry[i]);
        }
    }

    void removeFromFrame(Container targetFrame){
        for (int i = 0; i < this.displayElements.length; i++) {
            targetFrame.remove(displayElements[i]);
        }
    }



   public  void setYCoordinates(int yKoordinate){
        for (int i = 0; i < this.displayElements.length; i++) {
            displayElements[i].setLocation(displayElements[i].getX(),yKoordinate);
        }
        btnDeleteEntry.setLocation(btnDeleteEntry.getX(),yKoordinate);
    }



    void updateDataBaseEntry(){
       boolean valid= this.dataBaseEntry.isValid();

       if(valid){
           try {
               this.dataBaseEntry.savetoDataBase();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }else{
           /**TODO Nutzerausgabe ?
            *
            */
       }

    }

    void deleteDataBaseEntry(){
        this.dataBaseEntry.deleteEntry();
    }


}

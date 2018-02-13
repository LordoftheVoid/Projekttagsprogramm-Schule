package V2.UI;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.Settings.Imports;
import V2.UI.NonFrameElements.ButtonCreation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */


public class PupilFrame extends BaseFrame {


    public PupilFrame(int spaltenAnzahl, String fensterName) {
        super(spaltenAnzahl, fensterName);
    }


    @Override
    public ArrayList<DataBaseElementObject> requestDataBaseContent() throws SQLException {
        ArrayList<DataBaseElementObject> entrys = new ArrayList<>();

        ArrayList<String> listIDs = Imports.objDatabaseManagerGlobal.getEntryIDs("Pupil");


        for (String entry : listIDs
                ) {
            entrys.add(new Pupil(entry));
        }
        return entrys;
    }

    @Override
    public void generateDataBaseEntry() {

    }

    @Override
    public void setGUIBtnForCreation(int btnWidth) {
        btnCreateEntry = new ButtonCreation("Eintrag erzeugen", super.arrCreateEntryFields);
        super.getContentPane().add(btnCreateEntry);
        btnCreateEntry.setBounds(arrCreateEntryFields[1].getX() + btnWidth, arrCreateEntryFields[1].getY(), btnWidth * 2, 20);
        btnCreateEntry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ButtonCreation btnSource = (ButtonCreation) e.getSource();
                String surName = btnSource.getTextfromInputFields()[0];
                String preName = btnSource.getTextfromInputFields()[1];
                Pupil newPupil;
                try {
                    newPupil = new Pupil(surName, preName);
                    if (newPupil.isValid()) {
                        newPupil.generateDataBaseEntry();
                    } else {
                        throw new IllegalArgumentException();
                    }
               }catch (IllegalArgumentException exceptionMissingValues){
                    btnSource.resetTextInputfields();
                   //TODO: MAULEN das es nicht geht
                    System.out.println("GEHT NICHT!");
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


    @Override
    public void showfixedText() {
        this.columNames[0].setText("Nachname");
        this.columNames[1].setText("Vorname");
        this.columNames[2].setText("Klasse");
        this.columNames[3].setText("Erstwahl");
        this.columNames[4].setText("Zweitwahl");
        this.columNames[5].setText("Drittwahl");
        this.columNames[6].setText("Viertwahl");
    }


}

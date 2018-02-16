package V2.UI.Frame;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.Settings.Imports;

import javax.swing.*;
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
    public void setupGUIBtnForCreation(int btnWidth) {
        btnCreateEntry = new JButton("Eintrag erzeugen");
        super.getContentPane().add(btnCreateEntry);
        btnCreateEntry.setBounds(arrCreateEntryFields[1].getX() + btnWidth, arrCreateEntryFields[1].getY(), btnWidth * 2, 20);

        btnCreateEntry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Pupil newPupil = new Pupil(arrCreateEntryFields[0].getText(), arrCreateEntryFields[1].getText());
                    if (!Imports.objDatabaseManagerGlobal.entryExists("Pupil", newPupil.getHash())) {
                        //TODO: Maulen 2.0!
                    } else {
                        newPupil.generateDataBaseEntry();
                        newPupil.setIdentityValue(arrCreateEntryFields[0].getText(), 0);
                        newPupil.setIdentityValue(arrCreateEntryFields[1].getText(), 1);
                        //Todo: Melden das es ging
                    }
                    arrCreateEntryFields[0].setText("");
                    arrCreateEntryFields[1].setText("");
                }catch (IllegalArgumentException e1){
                    System.out.println(e1.getMessage());
                  e1.printStackTrace();
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

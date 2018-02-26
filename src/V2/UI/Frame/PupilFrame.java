package V2.UI.Frame;

import V2.DataBaseInternalClasses.AbstractDataBaseRepresentation;
import V2.DataBaseInternalClasses.Pupil;
import V2.UI.NonFrameElements.DisplayedRows.PupilRow;
import V2.cMain;

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
    public ArrayList<AbstractDataBaseRepresentation> requestDataBaseContent() throws SQLException {
        ArrayList<AbstractDataBaseRepresentation> entrys = new ArrayList<>();

        ArrayList<String> listIDs = cMain.objDatabaseManagerGlobal.getEntryIDs("Pupil");

        for (String idNewPupil : listIDs
                ) {
            Pupil newEntry = new Pupil(idNewPupil);

            cMain.updateStatus("Ein neuer Schüler wird angezeigt");
            entrys.add(newEntry);
        }
        return entrys;
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
                    String newPupilHash = Pupil.generateHash(arrCreateEntryFields[0].getText(), arrCreateEntryFields[1].getText());
                    if (cMain.objDatabaseManagerGlobal.entryExists("Pupil", newPupilHash)) {
                        cMain.updateStatus("Ein Schüler mit diesem Namen existierte bereits, bitte Namen ändern");
                    } else {
                        try {
                            cMain.objDatabaseManagerGlobal.createEntry("Pupil", newPupilHash);
                            cMain.objDatabaseManagerGlobal.updateNonIDValues("Pupil", newPupilHash, 0, arrCreateEntryFields[0].getText());
                            cMain.objDatabaseManagerGlobal.updateNonIDValues("Pupil", newPupilHash, 1, arrCreateEntryFields[1].getText());
                        } catch (SQLException e1) {
                            //TODO: Fehlerursachen ?
                        }
                    }
                    arrCreateEntryFields[0].setText("");
                    arrCreateEntryFields[1].setText("");
                    resetInterface();
                } catch (IllegalArgumentException e1) {
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
    void generateRows(ArrayList<AbstractDataBaseRepresentation> dataBaseEntrys) {
        for (int listIndex = 0; listIndex < dataBaseEntrys.size(); listIndex++) {
            this.listvisibleTextRows.add(new PupilRow(this.columns, dataBaseEntrys.get(listIndex), this.getContentPane()));
        }
        int brokenDataBaseEntrys = 0;
        for (int listIndex = 0; listIndex < dataBaseEntrys.size(); listIndex++) {
            if (!dataBaseEntrys.get(listIndex).isValidDataBaseEntry()) {
                brokenDataBaseEntrys++;
            }
        }
        if (brokenDataBaseEntrys == 0) {
            cMain.updateStatus("Die Schüler- Datenbank ist in Ordnung soweit, man könnte eine Zuordnung anlegen");
        } else {
            cMain.updateStatus("" + brokenDataBaseEntrys + " Schüler haben nicht korrekte Werte, beispielsweise keine  vier Projektpräferenzen.");
        }
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

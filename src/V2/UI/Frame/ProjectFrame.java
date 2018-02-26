package V2.UI.Frame;

import V2.DataBaseInternalClasses.AbstractDataBaseRepresentation;
import V2.DataBaseInternalClasses.Project;
import V2.UI.NonFrameElements.DisplayedRows.ProjectRow;
import V2.cMain;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */
public class ProjectFrame extends BaseFrame {


    public ProjectFrame(int spaltenanzahl, String name) {
        super(spaltenanzahl, name);
    }


    @Override
    public ArrayList<AbstractDataBaseRepresentation> requestDataBaseContent() throws SQLException {

        ArrayList<AbstractDataBaseRepresentation> entrys = new ArrayList<>();

        ArrayList<String> listIDs = cMain.objDatabaseManagerGlobal.getEntryIDs("Project");

        for (String entry : listIDs
                ) {
            entrys.add(new Project(entry));
        }

        return entrys;
    }


    @Override
    public void setupGUIBtnForCreation(int width) {
        btnCreateEntry = new JButton("Eintrag erzeugen");
        super.getContentPane().add(btnCreateEntry);
        btnCreateEntry.setBounds(arrCreateEntryFields[1].getX() + width, arrCreateEntryFields[1].getY(), width * 2, 20);


        btnCreateEntry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean isNumber = true;
                for (int charIndex = 0; charIndex < arrCreateEntryFields[0].getText().length(); charIndex++) {
                    isNumber = isNumber && Character.isDigit(arrCreateEntryFields[0].getText().charAt(charIndex));
                }

                if (isNumber) {
                    if (cMain.objDatabaseManagerGlobal.entryExists("Project", arrCreateEntryFields[0].getText())) {
                        cMain.updateStatus("Ein Projekt mit dieser Nummer existiert bereits, bitte erst das alte löschen");
                    } else {
                        try {
                            cMain.objDatabaseManagerGlobal.createEntry("Project", arrCreateEntryFields[0].getText());
                            cMain.objDatabaseManagerGlobal.updateNonIDValues("Project", arrCreateEntryFields[0].getText(), 0, arrCreateEntryFields[0].getText());
                            cMain.objDatabaseManagerGlobal.updateNonIDValues("Project", arrCreateEntryFields[0].getText(), 1, arrCreateEntryFields[1].getText());
                        } catch (SQLException e1) {
                            //TODO :Fehlerursachen ?
                        }
                    }
                    arrCreateEntryFields[0].setText("");
                    arrCreateEntryFields[1].setText("");
                    resetInterface();

                } else {
                    cMain.updateStatus("Diese Eingabe war keine Nummer, erlaubt sind nur die Ziffern von 0-9");
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
            this.listvisibleTextRows.add(new ProjectRow(this.columns, dataBaseEntrys.get(listIndex), this.getContentPane()));
        }
        int brokenDataBaseEntrys = 0;
        for (int listIndex = 0; listIndex < dataBaseEntrys.size(); listIndex++) {
            if (!dataBaseEntrys.get(listIndex).isValidDataBaseEntry()) {
                brokenDataBaseEntrys++;
            }
        }
        if (brokenDataBaseEntrys == 0) {
            cMain.updateStatus("Die Projekt-Datenbank ist in Ordnung soweit, man könnte eine Zuordnung anlegen");
        } else {
            cMain.updateStatus("" + brokenDataBaseEntrys + " Projekte haben nicht korrekte Werte, beispielsweise keine  leere Werte.");
        }


    }


    @Override
    public void showfixedText() {
        this.columNames[0].setText("Projektnummer");
        this.columNames[1].setText("Lehrerkürzel");
        this.columNames[2].setText("Maximale Schülerzahl");
    }


}

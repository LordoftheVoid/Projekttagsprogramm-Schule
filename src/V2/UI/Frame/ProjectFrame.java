package V2.UI.Frame;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;
import V2.DataBaseInteractions.DataBaseObjekts.Project;
import V2.Settings.Imports;
import V2.UI.NonFrameElements.DisplayedRows.ProjectRow;

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

        ArrayList<String> listIDs = Imports.objDatabaseManagerGlobal.getEntryIDs("Project");


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

                try {
                    int a = Integer.parseInt(arrCreateEntryFields[0].getText());

                    Project newProject = new Project(arrCreateEntryFields[0].getText());
                    newProject.generateDataBaseEntry();
                    newProject.setIdentityValue(arrCreateEntryFields[1].getText(), 1);
                    resetInterface();
                    arrCreateEntryFields[0].setText("");
                    arrCreateEntryFields[1].setText("");

                } catch (NumberFormatException e1) {
                    //TODO MAULEN
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
        System.out.println("Aufruf");
        for (int listIndex = 0; listIndex < dataBaseEntrys.size(); listIndex++) {
            this.listTextRows.add(new ProjectRow(this.columns, dataBaseEntrys.get(listIndex), this.getContentPane()));
        }
    }


    @Override
    public void showfixedText() {
        this.columNames[0].setText("Projektnummer");
        this.columNames[1].setText("Lehrerkürzel");
        this.columNames[2].setText("Maximale Schülerzahl");
    }


}

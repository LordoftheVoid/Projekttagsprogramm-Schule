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
            System.out.println(entry);
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
                    if(!isNumber){
                    //TODO: Maulen
                    }else{
                        Project newProject = new Project(arrCreateEntryFields[0].getText(),arrCreateEntryFields[1].getText() );
                        resetInterface();
                        arrCreateEntryFields[0].setText("");
                        arrCreateEntryFields[1].setText("");
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

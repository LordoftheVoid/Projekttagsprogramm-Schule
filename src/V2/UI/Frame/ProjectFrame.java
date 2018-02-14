package V2.UI.Frame;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.DataBaseInteractions.DataBaseObjekts.Project;
import V2.Settings.Imports;
import V2.UI.NonFrameElements.Buttons.CreationButton;


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
    public ArrayList<DataBaseElementObject> requestDataBaseContent() throws SQLException {

        ArrayList<DataBaseElementObject> entrys = new ArrayList<>();

        ArrayList<String> listIDs = Imports.objDatabaseManagerGlobal.getEntryIDs("Project");


        for (String entry : listIDs
                ) {
            entrys.add(new Project(entry, 2, 1));
        }

        return entrys;
    }

    @Override
    public void generateDataBaseEntry() {

    }

    @Override
    public void setGUIBtnForCreation(int width) {
        btnCreateEntry = new CreationButton("Eintrag erzeugen",arrCreateEntryFields);
        super.getContentPane().add(btnCreateEntry);
        btnCreateEntry.setBounds(arrCreateEntryFields[1].getX()+width,arrCreateEntryFields[1].getY(),width*2,20);


        btnCreateEntry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
        this.columNames[0].setText("Projektnummer");
        this.columNames[1].setText("Lehrerkürzel");
        this.columNames[2].setText("Maximale Schülerzahl");
    }


}

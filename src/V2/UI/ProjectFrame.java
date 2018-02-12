package V2.UI;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.DataBaseInteractions.DataBaseObjekts.Project;
import V2.Settings.Imports;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */
public class ProjectFrame extends BaseFrame {

    /*


        arr_list_Database_References[1].add("s_unique_ID");
        arr_list_Database_References[1].add("s_teacher_ID");
        arr_list_Database_References[1].add("i_max_pupils");


*/
    public ProjectFrame(int spaltenanzahl, String name, int creationParameters) {
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
    public void setUpCreationGUIElements() {
        for (int i = 0; i < 2; i++) {
            arrCreateEntryFields[i] = new JTextField();
            this.getContentPane().add(arrCreateEntryFields[i]);
            arrCreateEntryFields[i].setBounds(BaseFrame.getWIDTH() * i, yCoordinateListEntrys - 60, BaseFrame.getWIDTH(), 20);
            arrCreateEntryFields[i].setVisible(true);
        }

    }

    @Override
    public void showfixedText() {
        this.columNames[0].setText("Projektnummer");
        this.columNames[1].setText("Lehrerkürzel");
        this.columNames[2].setText("Maximale Schülerzahl");
    }


}

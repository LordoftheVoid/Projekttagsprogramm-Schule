package V2.UI;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.Settings.Imports;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */


public class PupilFrame extends BaseFrame {


    public PupilFrame(int spaltenAnzahl, String fensterName, int amountParameter) {
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
    public void setUpCreationGUIElements() {
        for (int i = 0; i < 2; i++) {
            this.arrCreateEntryFields[i] = new JTextField();
            this.getContentPane().add(arrCreateEntryFields[i]);
            this.arrCreateEntryFields[i].setBounds(BaseFrame.getWIDTH() * i, yCoordinateListEntrys - 60, BaseFrame.getWIDTH(), 20);
            this.arrCreateEntryFields[i].setVisible(true);
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

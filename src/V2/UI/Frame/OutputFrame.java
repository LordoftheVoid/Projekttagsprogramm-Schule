package V2.UI.Frame;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;
import V2.DataBaseInteractions.DataBaseObjekts.Link;
import V2.Settings.Imports;
import V2.UI.NonFrameElements.DisplayedRows.LinkRow;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */



public class OutputFrame extends BaseFrame {


    public OutputFrame(int spaltenAnzahl, String strFenstername)  {
        super(spaltenAnzahl, strFenstername);
    }

    @Override
    public ArrayList<AbstractDataBaseRepresentation> requestDataBaseContent() throws SQLException {
        ArrayList<AbstractDataBaseRepresentation> entrys = new ArrayList<>();

        ArrayList<String> listIDs = Imports.objDatabaseManagerGlobal.getEntryIDs("Link");

        for (String idNewPupil : listIDs
                ) {
            Link newEntry = new Link(idNewPupil);
            entrys.add(newEntry);
        }
        return entrys;
    }



    @Override
    public void setupGUIBtnForCreation(int width) {

    }

    @Override
    public void setupGUITextFieldRowForCreation() {

    }

    @Override
    void generateRows(ArrayList<AbstractDataBaseRepresentation> dataBaseEntrys) {
        for (int listIndex = 0; listIndex < dataBaseEntrys.size(); listIndex++) {
            this.listTextRows.add(new LinkRow(this.columns, dataBaseEntrys.get(listIndex), this.getContentPane()));
        }
    }


    @Override
    public void showfixedText() {
        this.columNames[0].setText("Nachname");
        this.columNames[1].setText("Vorname");
        this.columNames[2].setText("Klasse");
        this.columNames[3].setText("Projektnummer");
        this.columNames[4].setText("Wahlnummer");
    }
}

package V2.UI.Frame;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;

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
        return null;
    }



    @Override
    public void setupGUIBtnForCreation(int width) {

    }

    @Override
    public void setupGUITextFieldRowForCreation() {

    }

    @Override
    void generateRows(ArrayList<AbstractDataBaseRepresentation> dataBaseEntrys) {

    }


    @Override
    public void showfixedText() {

    }
}

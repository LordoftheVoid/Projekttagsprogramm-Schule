package V2.UI;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;

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
    public ArrayList<DataBaseElementObject> requestDataBaseContent() throws SQLException {
        return null;
    }

    @Override
    public void setUpCreationGUIElements() {

    }


    @Override
    public void showfixedText() {

    }
}

package V2.UI;

import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */



public class OutputFrame extends BaseFrame {


    public OutputFrame(int spaltenAnzahl, String strFenstername)  {
        super(spaltenAnzahl, strFenstername);
    }

    @Override
    public ArrayList<String[]> requestDataBaseContent() {
        return null;
    }

    @Override
    public void showfixedText() {

    }
}

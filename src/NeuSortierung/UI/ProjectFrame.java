package NeuSortierung.UI;

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

        amountParametersnewEntry = creationParameters;
        this.spaltenNamen[0] = "Projektnummer";
        this.spaltenNamen[1] = "Lehrkraftskürzel";
        this.spaltenNamen[2] = "Maximale Schüleranzahl";

    }

    @Override
    public ArrayList<String[]> requestDataBaseContent() {
        return null;
    }

    @Override
    void showEntrys(ArrayList<String[]> textArgs) {

    }


    void showEntrys() {

    }


    void setupEntryRow() {

    }

    @Override
    public void suche() {

    }
}

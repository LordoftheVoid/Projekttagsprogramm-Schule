package NeuSortierung.UI.Frames;

import NeuSortierung.Settings.DataBaseObjectTypes;

/**
 * Created by Aaron on 22.01.2018.
 */
public class ProjectFrame extends AbstraktFrame {

    /*


        arr_list_Database_References[1].add("s_unique_ID");
        arr_list_Database_References[1].add("s_teacher_ID");
        arr_list_Database_References[1].add("i_max_pupils");


*/

    public ProjectFrame(int spaltenanzahl, String name) {
        super(spaltenanzahl, name, DataBaseObjectTypes.PROJECT);

        this.spaltenNamen[0] = "Projektnummer";
        this.spaltenNamen[1] = "Lehrkraftskürzel";
        this.spaltenNamen[2] = "Maximale Schüleranzahl";

        this.stelleTextdar();
    }
}

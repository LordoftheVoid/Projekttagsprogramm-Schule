package NeuSortierung.UI.Frames;

/**
 * Created by Aaron on 22.01.2018.
 */
public class cProjektFrame extends cAbstraktesFrame {

    /*


        arr_list_Database_References[1].add("s_unique_ID");
        arr_list_Database_References[1].add("s_teacher_ID");
        arr_list_Database_References[1].add("i_max_pupils");


*/

    public cProjektFrame(int spaltenanzahl, String name) {
        super(spaltenanzahl, name);

        this.spaltenNamen[0] = "Projektnummer";
        this.spaltenNamen[1] = "Lehrkraftskürzel";
        this.spaltenNamen[2] = "Maximale Schüleranzahl";

        this.stelleTextdar();
    }
}

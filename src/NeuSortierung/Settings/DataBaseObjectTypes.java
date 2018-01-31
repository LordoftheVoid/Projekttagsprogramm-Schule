package NeuSortierung.Settings;

import java.util.ArrayList;

/**
 * Created by Aaron on 31.01.2018.
 */
public enum DataBaseObjectTypes {

    PUPIL(DataBaseLinks.pupilValues.size(), DataBaseLinks.pupilTable, DataBaseLinks.pupilValues),
    PROJECT(DataBaseLinks.projectValues.size(), DataBaseLinks.projectTable, DataBaseLinks.projectValues),
    LINK(DataBaseLinks.linkValues.size(), "", DataBaseLinks.linkValues);

    public int amountColumns;
    public String tableReference;
    public ArrayList<String> columReference;


    DataBaseObjectTypes(int columns, String tableName, ArrayList<String> columNamnes) {
        this.amountColumns = columns;
        this.tableReference = tableName;
        this.columReference = columNamnes;
    }
}

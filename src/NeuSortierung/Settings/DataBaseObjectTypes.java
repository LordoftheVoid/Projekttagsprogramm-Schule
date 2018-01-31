package NeuSortierung.Settings;

import java.util.ArrayList;

/**
 * Created by Aaron on 31.01.2018.
 */
public enum DataBaseObjectTypes {

    PUPIL(cDataBaseLinks.pupilValues.size(), cDataBaseLinks.pupilTable, cDataBaseLinks.pupilValues),
    PROJECT(cDataBaseLinks.projectValues.size(), cDataBaseLinks.projectTable, cDataBaseLinks.projectValues),
    LINK(cDataBaseLinks.linkValues.size(), "", cDataBaseLinks.linkValues);

    public int amountColumns;
    public String tableReference;
    public ArrayList<String> columReference;


    DataBaseObjectTypes(int columns, String tableName, ArrayList<String> columNamnes) {
        this.amountColumns = columns;
        this.tableReference = tableName;
        this.columReference = columNamnes;
    }
}

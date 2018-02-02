package NeuSortierung.Settings;

import NeuSortierung.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import NeuSortierung.DataBaseInteractions.DataBaseObjekts.Pupil;

import java.util.ArrayList;

/**
 * Created by Aaron on 31.01.2018.
 */
public enum DataBaseObjectTypes {

    PUPIL(DataBaseLinks.pupilValues.size(),2, DataBaseLinks.pupilTable, DataBaseLinks.pupilValues),
    PROJECT(DataBaseLinks.projectValues.size(),1, DataBaseLinks.projectTable, DataBaseLinks.projectValues),
    LINK(DataBaseLinks.linkValues.size(),0, "", DataBaseLinks.linkValues);

    public int amountColumns;
    public String tableReference;
    public ArrayList<String> columReference;
    public int amountofCreationFields;


    DataBaseObjectTypes(int columns, int amountofCreationFields, String tableName, ArrayList<String> columNamnes, DataBaseElementObject ) {
        this.amountColumns = columns;
        this.tableReference = tableName;
        this.columReference = columNamnes;
        this.amountofCreationFields = amountofCreationFields;
    }
}

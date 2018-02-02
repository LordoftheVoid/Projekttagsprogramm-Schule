package NeuSortierung.DataBaseInteractions.DataBaseObjekts;

import NeuSortierung.Settings.DataBaseObjectTypes;
import NeuSortierung.Settings.Imports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static NeuSortierung.Settings.Imports.objDatabaseManagerGlobal;

/**
 * Created by Aaron on 02.02.2018.
 */
public class DataBaseElementObject implements DataBaseElementInterFace {


    public int amountofColums;

    public HashMap<Integer, String> databaseColumMap;

    public String[] values;

    DataBaseObjectTypes elementType;

    String id;


    public DataBaseElementObject(DataBaseObjectTypes type, String id) {
        this.amountofColums = type.amountColumns;
        this.elementType = type;
        this.id = id;
        this.values = new String[type.amountColumns];
        for (int i = 0; i < this.values.length; i++) {
            this.values[i] = "Nicht definiert";
        }

        ResultSet newElement;
        try {
            for (int i = 1; i < this.amountofColums; i++) {
                newElement = objDatabaseManagerGlobal.readOneEntryOneAtribute(type.tableReference, type.columReference.get(i), id);
                while (newElement.next()) {
                    this.values[i - 1] = newElement.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void generateValidChild(DataBaseObjectTypes type) {

    }


    public static ArrayList<DataBaseElementInterFace> getallElements(DataBaseObjectTypes type) {
        ArrayList<DataBaseElementInterFace> rueckgabe = new ArrayList<>();
        ArrayList<String> idList = new ArrayList<>();

        ResultSet dataBaseEntryId;
        try {
            dataBaseEntryId = Imports.objDatabaseManagerGlobal.readEntrysOneAttribut(type.tableReference, type.columReference.get(0));
            while (dataBaseEntryId.next()) {
                idList.add(dataBaseEntryId.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (String listEntry : idList) {
            rueckgabe.add(new DataBaseElementObject(type, listEntry));
        }

        return rueckgabe;
    }


    public void changeValue(String value,int colum){
        this.values[colum]= value;
        try {
            Imports.objDatabaseManagerGlobal.updateEntry(this.elementType.tableReference,this.id,this.elementType.columReference.get(colum+1),value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void savetoDataBase() throws SQLException {

        Imports.objDatabaseManagerGlobal.createEntry(this.elementType.tableReference,this.id);

        for (int i = 1; i <this.values.length-1; i++) {
            Imports.objDatabaseManagerGlobal.updateEntry(this.elementType.tableReference,this.id,this.elementType.columReference.get(i),this.values[i]);
        }
    }

}

package NeuSortierung.DataBaseInteractions.DataBaseObjekts;

import NeuSortierung.Settings.DataBaseObjectTypes;
import NeuSortierung.Settings.Imports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import static NeuSortierung.Settings.Imports.objDatabaseManagerGlobal;

/**
 * Created by Aaron on 31.01.2018.
 */
public  class DataBaseElement {

    public int amountofColums;

    public HashMap<Integer, String> databaseColumMap;

    public String[] values;

    public DataBaseElement(DataBaseObjectTypes type, String id) {
        this.amountofColums = type.amountColumns;
        this.values = new String[type.amountColumns];
        for (int i = 0; i < this.values.length; i++) {
            this.values[i]= "";
        }

        ResultSet newElement;
        try {
            for (int i = 0; i < this.amountofColums; i++) {
                newElement = objDatabaseManagerGlobal.readOneEntryOneAtribute(type.tableReference,type.columReference.get(i),id);
                while (newElement.next()) {
                    this.values[i] = newElement.getString(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<DataBaseElement> getallElements(DataBaseObjectTypes type){
        ArrayList<DataBaseElement> rueckgabe = new ArrayList<>();
        ArrayList<String> idList = new ArrayList<>();

        ResultSet dataBaseEntryId;
        try {
            dataBaseEntryId=  Imports.objDatabaseManagerGlobal.readEntrysOneAttribut(type.tableReference,type.columReference.get(0));
            while (dataBaseEntryId.next()){
                idList.add(dataBaseEntryId.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (String listEntry : idList) {
            rueckgabe.add(new DataBaseElement(type,listEntry));
        }

        return rueckgabe;
    }
}


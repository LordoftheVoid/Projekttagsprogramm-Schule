package NeuSortierung.DataBaseInteractions.DataBaseObjekts;


import com.sun.javaws.exceptions.InvalidArgumentException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aaron on 02.02.2018.
 */
public class DataBaseElementObject implements DataBaseElementInterFace {


    public int amountofColums;

    public HashMap<Integer, String> databaseColumMap;

    public String[] values;



    String id;


    public DataBaseElementObject( String id) {

     /*
        this.id = id;
        this.values = new String[.amountColumns];
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
        */
    }









    public static ArrayList<DataBaseElementInterFace> getallElements() {

        ArrayList<DataBaseElementInterFace> rueckgabe = new ArrayList<>();
        /*
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
        */
        return rueckgabe;

    }


    public void changeValue(String value,int colum){

        /**TODO
         * Je nach Index den Hash neu berechnen !!
         */
        /*
        this.values[colum]= value;
        try {
            Imports.objDatabaseManagerGlobal.updateEntry(this.elementType.tableReference,this.id,this.elementType.columReference.get(colum+1),value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }


    @Override
    public void setValue(int index, String value) throws InvalidArgumentException {

    }

    public void savetoDataBase() throws SQLException {
/*
        Imports.objDatabaseManagerGlobal.createEntry(this.elementType.tableReference,this.id);

        for (int i = 1; i <this.values.length-1; i++) {
            Imports.objDatabaseManagerGlobal.updateEntry(this.elementType.tableReference,this.id,this.elementType.columReference.get(i),this.values[i]);
        }
        */
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String[] getValues() throws IndexOutOfBoundsException {
        return new String[0];
    }

    @Override
    public String getHash() throws NullPointerException {
        return null;
    }

    @Override
    public String updateHash() throws InvalidArgumentException {
        return null;
    }

    @Override
    public void deleteEntry() {

    }


}

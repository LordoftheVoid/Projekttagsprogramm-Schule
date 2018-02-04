package NeuSortierung.DataBaseInteractions.DataBaseObjekts;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.sql.SQLException;

/**
 * Created by Aaron on 31.01.2018.
 */
public interface DataBaseElementInterFace {


    void setValue(int index, String value) throws InvalidArgumentException;


    void savetoDataBase() throws SQLException;

    boolean isValid();

    String[] getValues() throws IndexOutOfBoundsException;

    String getHash() throws NullPointerException;

    String updateHash() throws InvalidArgumentException;

    void deleteEntry();
}


/*

     HashMap<Integer,String> columMap= new HashMap<>();

     String tableReference= null;

     static ArrayList<DataBaseElementInterFace> getallElements(){
         ArrayList<DataBaseElementInterFace> rueckGabe = new ArrayList<>();

         ArrayList<String> idList = new ArrayList<>();

         ResultSet dataBaseEntryId;
         try {
             dataBaseEntryId = Imports.objDatabaseManagerGlobal.readEntrysOneAttribut(tableReference, columMap.get(0));
             while (dataBaseEntryId.next()) {
                 idList.add(dataBaseEntryId.getString(1));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }


         for (String listEntry : idList) {
             rueckGabe.add(new DataBaseElementInterFace() {
         }

         return  rueckGabe;
     }

*/

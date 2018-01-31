package NeuSortierung.DataBaseInteractions.DataBaseObjekts;

import NeuSortierung.Settings.DataBaseObjects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aaron on 31.01.2018.
 */
public abstract class cDataBaseElement {



    public int amountofColums;

    public HashMap<Integer,String> databaseColumMap;

    public String [] values;

    public cDataBaseElement(int amountofColums, DataBaseObjects type){
        this.amountofColums =amountofColums;
    }


    void setDatabaseColums(ArrayList<String> args){
        for (int i = 0; i < args.size(); i++) {
            this.databaseColumMap.put(i,args.get(i));
        }
    }





}


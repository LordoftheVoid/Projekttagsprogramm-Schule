package V2.DataBaseInteractions.DataBaseObjekts;


import V2.Settings.Imports;
import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by Aaron on 22.01.2018.
 */


public class Pupil extends DataBaseElementObject{



        static int  amountIdentityValues = 3;

    static int  amountInteraktionValues = 4;


    String pseudoHash ="";

    public Pupil(String idsString) {
        super(idsString);

        String [] valuesDataBase = Imports.objDatabaseManagerGlobal.getEntryValuesfromDataBase("persons",idsString);

        for (int i = 0; i < amountIdentityValues; i++) {
            try {
                this.setIdentityValue(valuesDataBase[i],i);
            }catch (InvalidArgumentException e1){

            }
        }

        for (int i = amountIdentityValues; i < amountIdentityValues+amountInteraktionValues; i++) {
            try {
                this.setInteraktionValue(valuesDataBase[i],i);
            }catch (InvalidArgumentException e1){

            }
        }


        try {
            this.updateHash();
        } catch (InvalidArgumentException e) {
            /**TODO Ausgabe muss sich beschweren
             *
             */
        }
    }





    @Override
    public void updateHash() throws InvalidArgumentException{


        if(this.getIdentityValues()[0].length() <3 || this.getIdentityValues()[1].length()<3){
            throw new InvalidArgumentException(new String[]{"Missing Information"});
        }
        this.pseudoHash = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                this.pseudoHash =this.pseudoHash+ this.getIdentityValues()[i].charAt(j);
            }
        }


    }


    public boolean isValid(){
        boolean returnValue = true;

        for (int i = 0; i < this.getIdentityValues().length; i++) {
            if(this.getIdentityValues()[i]!=null&& !this.getIdentityValues()[i].equals("")){
                returnValue =false;
            }
        }

        return  returnValue;
    }


}

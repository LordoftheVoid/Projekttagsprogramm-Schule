package V2.DataBaseInteractions.DataBaseObjekts;

/**
 * Created by Aaron on 31.01.2018.
 */



public class Link extends AbstractDataBaseRepresentation {

        //Vorname, Nachname, Klasse, Projektnummer, Welche Wahl

    static int amountIdentityValues = 3;

    static int amountInteraktionValues = 0;


    /*

    Der Link bekommt einen Schüler und ein Projekt als Argument

     */

    Link(String id){
        super(id,amountIdentityValues,amountInteraktionValues);
    }


    @Override
    public void generateDataBaseEntry() {

    }

    @Override
    public void genericSetter(String newValue, int index) {

    }

    @Override
    protected void savetoDataBase(String newValue, int index) {

    }
}

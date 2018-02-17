package V2.DataBaseInteractions.DataBaseObjekts;

/**
 * Created by Aaron on 31.01.2018.
 */



public class Link extends DataBaseElementObject {



    static int amountIdentityValues = 0;

    static int amountInteraktionValues = 0;



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

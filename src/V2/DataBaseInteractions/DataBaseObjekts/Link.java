package V2.DataBaseInteractions.DataBaseObjekts;

import java.sql.SQLException;

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
    public void setValue(String value, int colum) {



    }

    @Override
    public void savetoDataBase() throws SQLException {

    }

    @Override
    public void generateDataBaseEntry() {

    }

    @Override
    public void updateFromDataBase() {

    }


}

package V2.DataBaseInteractions.DataBaseObjekts;



import com.sun.javaws.exceptions.InvalidArgumentException;

import java.sql.SQLException;

/**
 * Created by Aaron on 22.01.2018.
 */
public class Project implements DataBaseElementInterFace {


    public Project( String id) {

    }





    public void changeValue(String value, int colum) {

    }




    @Override
    public void setValue(int index, String value) throws InvalidArgumentException {

    }

    @Override
    public void savetoDataBase() throws SQLException {

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

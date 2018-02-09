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

    public String[] getIdentityValues() throws IndexOutOfBoundsException {
        return new String[0];
    }

    @Override
    public String[] getInterAktionValues() throws IndexOutOfBoundsException {
        return new String[0];
    }

    @Override
    public void setInteraktionValue(String arg, int index) throws InvalidArgumentException {

    }

    @Override
    public void setIdentityValue(String arg, int index) throws InvalidArgumentException {

    }

    @Override
    public String getHash() throws NullPointerException {
        return null;
    }

    @Override
    public void updateHash() throws InvalidArgumentException {

    }

    @Override
    public void deleteEntry() {

    }


}

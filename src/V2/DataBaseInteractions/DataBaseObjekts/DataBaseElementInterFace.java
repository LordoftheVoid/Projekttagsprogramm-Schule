package V2.DataBaseInteractions.DataBaseObjekts;

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

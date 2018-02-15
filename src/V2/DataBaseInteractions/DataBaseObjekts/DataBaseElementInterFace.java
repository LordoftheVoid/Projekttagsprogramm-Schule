package V2.DataBaseInteractions.DataBaseObjekts;

/**
 * Created by Aaron on 31.01.2018.
 */
public interface DataBaseElementInterFace {

    void setValue(int index, String value) throws IllegalArgumentException;


    void generateDataBaseEntry();

    boolean isValid();

    String[] getPublicIdentityValues() throws IndexOutOfBoundsException;

    String [] getInterAktionValues() throws IndexOutOfBoundsException;

    void setInteraktionValue(String arg, int index) throws IllegalArgumentException;

    void setIdentityValue(String arg, int index) throws IllegalArgumentException;


    String getHash() throws NullPointerException;

    void updateHash() throws IllegalArgumentException;

    void deleteEntry();

    void updateFromDataBase();


}

package V2.DataBaseInteractions.DataBaseObjekts;

/**
 * Created by Aaron on 31.01.2018.
 */
public interface InterfaceDataBaseRepresentation {


    void generateDataBaseEntry();


    boolean isValidDataBaseEntry();

    String[] getVisibleIdentityValues() throws IndexOutOfBoundsException;

    String [] getInterAktionValues() throws IndexOutOfBoundsException;

    void setInteraktionValuetoDataBase(String arg, int index) throws IllegalArgumentException;

    void setIdentityValue(String arg, int index) throws IllegalArgumentException;

    String getHash() throws NullPointerException;

    void deleteEntry();



}

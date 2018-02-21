package V2.DataBaseInteractions.DataBaseObjekts;

/**
 * Created by Aaron on 31.01.2018.
 */
public interface InterfaceDataBaseRepresentation {


    boolean isValidDataBaseEntry();

    String getDisplayableValue(int index);

    void setDisplayayableValue(int index, String newValue);

    String getHash() throws NullPointerException;

    void deleteEntry();

    int getamountofDisplayableValues();

    String getTableReference();

}

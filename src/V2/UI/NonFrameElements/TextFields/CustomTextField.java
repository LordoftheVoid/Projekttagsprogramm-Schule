package V2.UI.NonFrameElements.TextFields;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;

import javax.swing.*;

/**
 * Created by Aaron on 16.02.2018.
 */
public  abstract  class CustomTextField extends JTextField {

    public int index;


    public String oldValue;

   public AbstractDataBaseRepresentation dataBaseEntry;



    CustomTextField(int index, AbstractDataBaseRepresentation dataBaseEntry){
        super();
        this.index =index;
      this.dataBaseEntry = dataBaseEntry;
    }


    public  abstract  boolean isValidInput();

}

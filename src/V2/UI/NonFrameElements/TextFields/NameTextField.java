package V2.UI.NonFrameElements.TextFields;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;

/**
 * Created by Aaron on 16.02.2018.
 */


public class NameTextField extends  CustomTextField {


    public NameTextField(int index, AbstractDataBaseRepresentation dataBaseEnty) {
        super(index, dataBaseEnty);
    }


    @Override
    public boolean isValidInput() {
        return  this.getText().length()>2;
    }


}

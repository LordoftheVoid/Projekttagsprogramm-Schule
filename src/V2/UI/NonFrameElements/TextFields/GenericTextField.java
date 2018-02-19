package V2.UI.NonFrameElements.TextFields;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;

/**
 * Created by Aaron on 16.02.2018.
 */
public class GenericTextField extends CustomTextField {

    public GenericTextField(int index, AbstractDataBaseRepresentation dataBaseEnty) {
        super(index, dataBaseEnty);
    }

    @Override
    public boolean isValidInput() {
        return true;
    }
}

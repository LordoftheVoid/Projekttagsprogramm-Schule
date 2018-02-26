package V2.UI.NonFrameElements.TextFields;

import V2.DataBaseInternalClasses.AbstractDataBaseRepresentation;

/**
 * Created by Aaron on 25.02.2018.
 */
public class GenericNonInteractiveTextField extends  CustomTextField {


    public GenericNonInteractiveTextField(int index, AbstractDataBaseRepresentation dataBaseEntry) {
        super(index, dataBaseEntry);
    }

    @Override
    public boolean isValidInput() {
        return false;
    }
}

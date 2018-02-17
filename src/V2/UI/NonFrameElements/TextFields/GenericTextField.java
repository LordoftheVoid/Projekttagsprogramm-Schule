package V2.UI.NonFrameElements.TextFields;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;

/**
 * Created by Aaron on 16.02.2018.
 */
public class GenericTextField extends CustomTextField {

    public GenericTextField(int index, DataBaseElementObject dataBaseEnty) {
        super(index, dataBaseEnty);
    }

    @Override
    public boolean isValidInput() {
        return true;
    }
}

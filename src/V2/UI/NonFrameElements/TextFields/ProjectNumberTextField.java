package V2.UI.NonFrameElements.TextFields;

import V2.DataBaseInternalClasses.AbstractDataBaseRepresentation;
import V2.cMain;


/**
 * Created by Aaron on 16.02.2018.
 */
public class ProjectNumberTextField extends CustomTextField {

    public ProjectNumberTextField(int index, AbstractDataBaseRepresentation dataBaseEnty) {
        super(index, dataBaseEnty);
    }

    @Override
    public boolean isValidInput() {
        return !this.getText().equals("") && cMain.objDatabaseManagerGlobal.entryExists("Project", this.getText());
    }
}

package V2.UI.NonFrameElements.TextFields;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;
import V2.Settings.Imports;

/**
 * Created by Aaron on 16.02.2018.
 */
public class ProjectNumberTextField extends CustomTextField {

    public ProjectNumberTextField(int index, AbstractDataBaseRepresentation dataBaseEnty) {
        super(index, dataBaseEnty);
    }

    @Override
    public boolean isValidInput() {
        return !this.getText().equals("") && Imports.objDatabaseManagerGlobal.entryExists("Project", this.getText());
    }
}

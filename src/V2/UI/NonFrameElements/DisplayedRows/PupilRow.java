package V2.UI.NonFrameElements.DisplayedRows;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.UI.NonFrameElements.TextFields.GenericTextField;
import V2.UI.NonFrameElements.TextFields.NameTextField;
import V2.UI.NonFrameElements.TextFields.ProjectNumberTextField;

import java.awt.*;

/**
 * Created by Aaron on 16.02.2018.
 */

public class PupilRow extends AbstractRow {

    public PupilRow(int columns, DataBaseElementObject dataBaseEntry, Container targetFrame) {
        super(columns, dataBaseEntry, targetFrame);
    }

    @Override
    void generateTextFields() {

        this.displayElements[0] = new NameTextField(0, this.dataBaseEntry);
        this.displayElements[1] = new NameTextField(1, this.dataBaseEntry);
        this.displayElements[2] = new GenericTextField(2, this.dataBaseEntry);
        this.displayElements[3] = new ProjectNumberTextField(3, this.dataBaseEntry);
        this.displayElements[4] = new ProjectNumberTextField(3, this.dataBaseEntry);
        this.displayElements[5] = new ProjectNumberTextField(3, this.dataBaseEntry);
        this.displayElements[6] = new ProjectNumberTextField(3, this.dataBaseEntry);
    }


}

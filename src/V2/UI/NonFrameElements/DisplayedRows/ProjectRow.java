package V2.UI.NonFrameElements.DisplayedRows;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;
import V2.UI.NonFrameElements.TextFields.GenericTextField;

import java.awt.*;

/**
 * Created by Aaron on 16.02.2018.
 */

public class ProjectRow extends AbstractRow {
    public ProjectRow(int columns, AbstractDataBaseRepresentation dataBaseEntry, Container targetFrame) {
        super(columns, dataBaseEntry, targetFrame);
    }

    @Override
    void generateTextFields() {
        this.displayElements[0] = new GenericTextField(0,this.dataBaseEntry);
        this.displayElements[1] = new GenericTextField(1,this.dataBaseEntry);
        this.displayElements[2] = new GenericTextField(2,this.dataBaseEntry);

    }
}

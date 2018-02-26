package V2.UI.NonFrameElements.DisplayedRows;

import V2.DataBaseInternalClasses.AbstractDataBaseRepresentation;
import V2.UI.NonFrameElements.TextFields.GenericNonInteractiveTextField;

import java.awt.*;

/**
 * Created by Aaron on 25.02.2018.
 */


public class LinkRow extends AbstractRow {


    public LinkRow(int columns, AbstractDataBaseRepresentation dataBaseEntry, Container targetFrame) {
        super(columns, dataBaseEntry, targetFrame);
    }

    @Override
    void generateTextFields() {
        this.displayElements[0] = new GenericNonInteractiveTextField(0, this.dataBaseEntry);
        this.displayElements[1] = new GenericNonInteractiveTextField(1, this.dataBaseEntry);
        this.displayElements[2] = new GenericNonInteractiveTextField(2, this.dataBaseEntry);
        this.displayElements[3] = new GenericNonInteractiveTextField(3, this.dataBaseEntry);
        this.displayElements[4] = new GenericNonInteractiveTextField(4, this.dataBaseEntry);

    }

    @Override
    void setupFocusListener() {

    }
}

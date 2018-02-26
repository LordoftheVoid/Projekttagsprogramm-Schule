package V2.UI.NonFrameElements.DisplayedRows;

import V2.DataBaseInteractions.DataBaseObjekts.AbstractDataBaseRepresentation;
import V2.UI.NonFrameElements.TextFields.CustomTextField;
import V2.UI.NonFrameElements.TextFields.GenericTextField;
import V2.cMain;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Aaron on 16.02.2018.
 */

public class ProjectRow extends AbstractRow {
    public ProjectRow(int columns, AbstractDataBaseRepresentation dataBaseEntry, Container targetFrame) {
        super(columns, dataBaseEntry, targetFrame);
    }

    @Override
    void generateTextFields() {
        this.displayElements[0] = new GenericTextField(0, this.dataBaseEntry);
        this.displayElements[1] = new GenericTextField(1, this.dataBaseEntry);
        this.displayElements[2] = new GenericTextField(2, this.dataBaseEntry);

    }

    @Override
    void setupFocusListener() {
        for (int arrayIndex = 0; arrayIndex < displayElements.length; arrayIndex++) {
            displayElements[arrayIndex].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {

                    CustomTextField objSource = (CustomTextField) e.getSource();
                    objSource.oldValue = objSource.getText();

                }

                @Override
                public void focusLost(FocusEvent e) {
                    CustomTextField objSource = (CustomTextField) e.getSource();
                    if (objSource.getText().equals(objSource.oldValue)) {
                        if (objSource.isValidInput()) {
                            objSource.dataBaseEntry.setDisplayayableValue(objSource.index, objSource.getText());
                            cMain.updateStatus("Der Wert wurde erfolgreich verändert");
                        } else {
                            objSource.setText(objSource.oldValue);
                            cMain.updateStatus("Der Versuch, einen Wert zu ändern schlug fehl, vermutlich aufgrund falscher Eingaben");
                        }
                    }

                }

            });
        }
    }

}

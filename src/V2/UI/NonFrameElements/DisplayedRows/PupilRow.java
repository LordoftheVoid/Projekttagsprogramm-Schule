package V2.UI.NonFrameElements.DisplayedRows;

import V2.DataBaseInternalClasses.AbstractDataBaseRepresentation;
import V2.UI.NonFrameElements.TextFields.CustomTextField;
import V2.UI.NonFrameElements.TextFields.GenericTextField;
import V2.UI.NonFrameElements.TextFields.NameTextField;
import V2.UI.NonFrameElements.TextFields.ProjectNumberTextField;
import V2.cMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Aaron on 16.02.2018.
 */

public class PupilRow extends AbstractRow {

    public PupilRow(int columns, AbstractDataBaseRepresentation dataBaseEntry, Container targetFrame) {
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

    @Override
    void setupFocusListener() {
        for (JTextField displayElement : displayElements) {
            displayElement.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    CustomTextField objSource = (CustomTextField) e.getSource();
                    objSource.oldValue = objSource.getText();

                }

                @Override
                public void focusLost(FocusEvent e) {
                    CustomTextField objSource = (CustomTextField) e.getSource();
                    if (!objSource.getText().equals(objSource.oldValue)) {
                        if (objSource.isValidInput()) {
                            objSource.dataBaseEntry.setDisplayayableValue(objSource.index, objSource.getText());
                            cMain.updateStatus("Ein DatenBankWert konnte erfolgreich verändert werden");
                        } else {
                            objSource.setText(objSource.oldValue);
                            cMain.updateStatus("Die Werte konnten nicht geändert werden, vermutlich wegen nicht erlaubter Eingaben");
                            cMain.updateStatus("Es gilt: Die Projekte müssen zuerst angelegt werden");
                        }
                    }
                }
            });
        }

    }


}

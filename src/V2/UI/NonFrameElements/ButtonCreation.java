package V2.UI.NonFrameElements;

import javax.swing.*;

/**
 * Created by Aaron on 13.02.2018.
 */
public class ButtonCreation extends JButton {

    JTextField[] inputFields;

    public ButtonCreation(String displayText, JTextField[] fields) {
        super(displayText);
        this.inputFields = fields;
    }

    public String[] getTextfromInputFields() {
        String[] returnedStrings = new String[this.inputFields.length];

        for (int arrayIndex = 0; arrayIndex < this.inputFields.length; arrayIndex++) {
            returnedStrings[arrayIndex] = this.inputFields[arrayIndex].getText();
        }
        return returnedStrings;
    }

    public void resetTextInputfields() {
        for (int arrayIndex = 0; arrayIndex < this.inputFields.length; arrayIndex++) {
            this.inputFields[arrayIndex].setText("");
        }
    }

}


package V2.UI.NonFrameElements.Buttons;

import javax.swing.*;

/**
 * Created by Aaron on 14.02.2018.
 */
public  class CreationButton extends JButton {

    public JTextField[] valueFields = new JTextField[2];

    public CreationButton(String textShown, JTextField[] actualFields) {
        super(textShown);
        this.valueFields = actualFields;
    }

}
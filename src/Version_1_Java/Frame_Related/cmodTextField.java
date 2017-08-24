package Version_1_Java.Interfaces;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aaron on 09.07.2017.
 */
public class cmodTextField extends JTextField {

    int i_inside_Field_X;

    int i_inside_Field_Y;



    String colum;


    boolean bcorrect_unique_ID=false;


    String sunique_ID_Textfieldrow;


    public void v_initiation (int i_X, int k_Y, int width, int height, Container obj_target_tm){
        this.setBounds(i_X,k_Y,width,height);
        this.setVisible(true);
        obj_target_tm.add(this);

    }


}

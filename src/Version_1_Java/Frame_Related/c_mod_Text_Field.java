package Version_1_Java.Frame_Related;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Aaron on 09.07.2017.
 */
public class c_mod_Text_Field extends JTextField {

    int i_inside_Field_X;

    int i_inside_Field_Y;



    String s_colum_identifier;


    boolean bcorrect_unique_ID=false;





    String s_unique_ID_Textfieldrow;


    public void v_initiation (int i_X, int k_Y, int width, int height, Container obj_target_tm){
        this.setBounds(i_X,k_Y,width,height);
        this.setVisible(true);
        obj_target_tm.add(this);
    }


}

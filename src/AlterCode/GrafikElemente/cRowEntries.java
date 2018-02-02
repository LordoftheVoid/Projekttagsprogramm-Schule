package AlterCode.GrafikElemente;

import NeuSortierung.UI.Frames.AbstraktFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

/**
 * Created by Aaron on 22.10.2017.
 */


public class cRowEntries {

    JTextField[] fields;
    String suniqueRowID = "";
    int iglobalWidth;
    JButton btnDelete;
    String sReferenceTable = "";
   public  boolean bEnabled = true;
    private AbstraktFrame objTarget;


    public cRowEntries(AbstraktFrame objSource, String suniqueRowID) {
        this.sReferenceTable = objSource.s_Main_Table;
        this.suniqueRowID = suniqueRowID;
        this.iglobalWidth = objSource.widthGlobal;
        objTarget = objSource;
    }

    public cRowEntries(AbstraktFrame objSource, int iglobalWidth) {
        this.iglobalWidth = iglobalWidth;
        objTarget = objSource;
    }

    public void v_ShortSetup(int iamountElements, Container conDisplayTarget, int iYCoordinate) {
        this.fields = new JTextField[iamountElements];
        for (int i = 0; i < this.fields.length; i++) {
            this.fields[i] = new JTextField();
            conDisplayTarget.add(this.fields[i]);
            this.fields[i].setVisible(true);
            this.fields[i].setBounds(i * iglobalWidth, iYCoordinate, iglobalWidth, 20);
        }
    }


    public void v_setup(int iamountElements, Container conDisplayTarget, int iYCoordinate) {
        this.fields = new JTextField[iamountElements];

        btnDelete = new JButton("Eintrag löschen");

        this.v_ShortSetup(iamountElements, conDisplayTarget, iYCoordinate);

        conDisplayTarget.add(btnDelete);
        btnDelete.setVisible(true);
        btnDelete.setVisible(true);
        btnDelete.setBounds(fields.length * iglobalWidth + 20, iYCoordinate, iglobalWidth * 2, 20);
        btnDelete.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //         Imports.objDatabaseManagerGlobal.deleteEntry(sReferenceTable,suniqueRowID);
                //      objTarget.v_generate_rows_from_Database();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void v_setCellContent(int icellposition, String svalue) {
        this.fields[icellposition].setText(svalue);
    }

    /**
     * @param icellPosition
     * @return
     */
    public String getCellContent(int icellPosition) {
        return this.fields[icellPosition].getText();
    }


    /**
     *
     */
    public void v_setYCoordinate(int ivalue) {
        for (int i = 0; i < this.fields.length; i++) {
            fields[i].setLocation(iglobalWidth * i, ivalue);
        }
        this.btnDelete.setLocation(btnDelete.getX(), ivalue);
    }


    public boolean b_searchRow(HashMap<Integer, String> mapSearchValues) {
        boolean bcontains = true;

        for (Integer objList : mapSearchValues.keySet()
                ) {
            if (!this.fields[objList].getText().contains(mapSearchValues.get(objList))) {
                bcontains = false;
                break;
            }
        }
        return bcontains;
    }

    public void v_enable() {
        for (JTextField objField : this.fields
                ) {
            objField.setVisible(true);
            objField.setEnabled(true);
        }
        this.btnDelete.setVisible(true);
        this.btnDelete.setEnabled(true);

        this.bEnabled = true;
    }


    public void v_disable() {
        this.bEnabled = false;
        for (JTextField objField : this.fields
                ) {
            objField.setVisible(false);
            objField.setEnabled(false);
        }
        this.btnDelete.setVisible(false);
        this.btnDelete.setEnabled(false);
    }


}

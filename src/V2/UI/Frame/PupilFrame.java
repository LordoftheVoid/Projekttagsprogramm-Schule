package V2.UI.Frame;

import V2.DataBaseInteractions.DataBaseObjekts.DataBaseElementObject;
import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.Settings.Imports;
import V2.UI.NonFrameElements.Buttons.CreationButton;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */


public class PupilFrame extends BaseFrame {


    public PupilFrame(int spaltenAnzahl, String fensterName) {
        super(spaltenAnzahl, fensterName);
    }


    @Override
    public ArrayList<DataBaseElementObject> requestDataBaseContent() throws SQLException {
        ArrayList<DataBaseElementObject> entrys = new ArrayList<>();

        ArrayList<String> listIDs = Imports.objDatabaseManagerGlobal.getEntryIDs("Pupil");


        for (String entry : listIDs
                ) {
            entrys.add(new Pupil(entry));
        }
        return entrys;
    }

    @Override
    public void generateDataBaseEntry() {

    }

    @Override
    public void setupGUIBtnForCreation(int btnWidth) {
        btnCreateEntry = new CreationButton("Eintrag erzeugen", arrCreateEntryFields);
        super.getContentPane().add(btnCreateEntry);
        btnCreateEntry.setBounds(arrCreateEntryFields[1].getX() + btnWidth, arrCreateEntryFields[1].getY(), btnWidth * 2, 20);

        btnCreateEntry.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CreationButton btnSource = (CreationButton) e.getSource();

                arrCreateEntryFields[0].setBounds(700, 0, 200, 200);
                btnSource.valueFields[0].setBounds(1000, 0, 200, 200);
                btnSource.valueFields[1].setBounds(300, 0, 200, 200);
                if (btnSource.valueFields[0].getText().length() < 3 || btnSource.valueFields[1].getText().length() < 3) {
                    System.out.println("zu kurz!");
                    //TODO: Sinnvoll maulen 2.0!
                } else {
                    btnSource.valueFields[0].setText("");
                    btnSource.valueFields[1].setText("");

                    //TODO:Maulen!!

                    Pupil newPupil = new Pupil(btnSource.valueFields[0].getText(), btnSource.valueFields[1].getText());
                    try {
                        if (Imports.objDatabaseManagerGlobal.entryExists("Pupil", newPupil.getHash())) {
                            //TODO: Maulen 2.0!
                        } else {
                            newPupil.generateDataBaseEntry();
                            //Todo: Melden das es ging
                        }
                    } catch (SQLException e1) {

                    }
                }

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


    @Override
    public void showfixedText() {
        this.columNames[0].setText("Nachname");
        this.columNames[1].setText("Vorname");
        this.columNames[2].setText("Klasse");
        this.columNames[3].setText("Erstwahl");
        this.columNames[4].setText("Zweitwahl");
        this.columNames[5].setText("Drittwahl");
        this.columNames[6].setText("Viertwahl");
    }

}

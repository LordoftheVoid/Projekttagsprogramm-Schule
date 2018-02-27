package V2.UI.Frame;

import V2.DataBaseInternalClasses.AbstractDataBaseRepresentation;
import V2.DataBaseInternalClasses.Link;
import V2.FileInteractions.Generators.Excel.ExcelGridFile;
import V2.FileInteractions.Generators.Excel.ExcelGridFileGenerator;
import V2.UI.NonFrameElements.DisplayedRows.LinkRow;
import V2.cMain;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Aaron on 22.01.2018.
 */



public class OutputFrame extends BaseFrame {


    public OutputFrame(int spaltenAnzahl, String strFenstername)  {
        super(spaltenAnzahl, strFenstername);

        setUpExcelCreation();

    }


    private  void setUpExcelCreation(){

        JButton createExcelFiles = new JButton("Excel-Dateien  zu diesem Ergebnis erzeugen");
        this.getContentPane().add(createExcelFiles);
        createExcelFiles.setVisible(true);
        createExcelFiles.setBounds(0,170,300,40);
        createExcelFiles.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ExcelGridFileGenerator testOne = new ExcelGridFileGenerator(cMain.sourceURLS.getURLoutputDirectory());


                ArrayList<ExcelGridFile> fileTestsOne = testOne.generateFilesFromDataBase(2);

                for (ExcelGridFile element : fileTestsOne
                        ) {
                    element.saveFiletoDisk();
                }

                fileTestsOne = testOne.generateFilesFromDataBase(3);

                for (ExcelGridFile element : fileTestsOne
                        ) {
                    element.saveFiletoDisk();
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
    public ArrayList<AbstractDataBaseRepresentation> requestDataBaseContent() throws SQLException {
        ArrayList<AbstractDataBaseRepresentation> entrys = new ArrayList<>();

        ArrayList<String> listIDs = cMain.objDatabaseManagerGlobal.getEntryIDs("Link");

        for (String idNewPupil : listIDs
                ) {
            Link newEntry = new Link(idNewPupil);
            entrys.add(newEntry);
        }
        return entrys;
    }



    @Override
    public void setupGUIBtnForCreation(int width) {

    }

    @Override
    public void setupGUITextFieldRowForCreation() {

    }

    @Override
    void generateRows(ArrayList<AbstractDataBaseRepresentation> dataBaseEntrys) {
        for (AbstractDataBaseRepresentation dataBaseEntry : dataBaseEntrys) {
            this.listvisibleTextRows.add(new LinkRow(this.columns, dataBaseEntry, this.getContentPane()));
        }
    }


    @Override
    public void showfixedText() {
        this.columNames[0].setText("Nachname");
        this.columNames[1].setText("Vorname");
        this.columNames[2].setText("Klasse");
        this.columNames[3].setText("Projektnummer");
        this.columNames[4].setText("Wahlnummer");
    }
}

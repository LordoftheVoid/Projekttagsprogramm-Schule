package V2;

import V2.DataBaseInteractions.DataBaseObjekts.Link;
import V2.DataBaseInteractions.DataBaseObjekts.Project;
import V2.DataBaseInteractions.DataBaseObjekts.Pupil;
import V2.DataBaseInteractions.DatabaseInterface;
import V2.FileInteractions.DirectoryCreator;
import V2.FileInteractions.Excel.InterfaceExcel;
import V2.Settings.Imports;
import V2.UI.Frame.BaseFrame;
import V2.UI.Frame.ProjectFrame;
import V2.UI.Frame.PupilFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Aaron on 29.03.2017.
 */

public class cMain {


    //TODO GLOBAL: Das man die Projektnummer nicht manuell ändern kann, sonder ein neues Projekt anlegen muss wenn man das will

    //TODO: FeinSchliff: Das Suchen die Einträge zählt

    static JTextArea statusDisplay;
    static JFrame objFrameMain;


    /**
     * Updates the main display for interactions with a user,
     * examples might include "This is an invalid input", "Please change the following entry", etc.
     */
    public static void updateStatus(String newLine) {
        if (statusDisplay.getText().length() > 2000) {
            statusDisplay.setText("");
        }
        statusDisplay.setText(statusDisplay.getText() + "\n" + newLine);
    }

    public static void main(String args[]) {


        if (args[0].equals("Normiert")) {
            String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\Dateiumgebungen\\TestUmgebungen\\DataBaseNormValues.db";
            try {
                Imports.objDatabaseManagerGlobal = new DatabaseInterface(dataBaseUrl);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (args[0].equals("Real")) {
            String dataBaseUrl = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\DateiUmgebungen\\Real\\DatenBank-Ordner\\DefaultDataBase.db";

            try {
                Imports.objDatabaseManagerGlobal = new DatabaseInterface(dataBaseUrl);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String baseURLtoExdelDirectory = "C:\\Einziger Arbeitsordner Windows\\Code\\ProjektTagsProgramm\\DateiUmgebungen\\Real";
            InterfaceExcel interfaceExcel = new InterfaceExcel(baseURLtoExdelDirectory + "\\Excel-Datei-Ordner");
        }


        if (args.length == 0) {
            Imports.setupImport();
            InterfaceExcel interfaceExcel = new InterfaceExcel(Imports.fileJAR.getParent() + "/Excel-Datei-Ordner");

            DirectoryCreator objDirectoryManager = new DirectoryCreator();

            objDirectoryManager.v_creation(Imports.fileJAR.getParent(), "Datenbank-Ordner");
            objDirectoryManager.v_creation(Imports.fileJAR.getParent(), "Excel-Datei-Ordner");
            objDirectoryManager.v_creation(Imports.fileJAR.getParent(), "Output-Ordner (Excel-Dateien)");

        }


        objFrameMain = new JFrame("Projekttagsverwaltungsprogramm Version 1.0");
        objFrameMain.setVisible(true);
        objFrameMain.setBounds(0, 0, 600, 1200);
        objFrameMain.getContentPane().setLayout(null);
        objFrameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        statusDisplay = new JTextArea();
        objFrameMain.getContentPane().add(statusDisplay);
        statusDisplay.setText("");
        statusDisplay.setBounds(0, 450, 600, 1000);
        statusDisplay.setBorder(new LineBorder(Color.black));

        JButton btnCreateUI = new JButton("Herzlich willkommen,   \n hier klicken um Programm zu starten");
        objFrameMain.getContentPane().add(btnCreateUI);
        btnCreateUI.setVisible(true);
        btnCreateUI.setBounds(0, 0, 600, 450);

        updateStatus("Hier werden in Zukunft wichtige Nachrichten auftauchen");


        btnCreateUI.addMouseListener(new MouseListener() {
                                         @Override
                                         public void mouseClicked(MouseEvent e) {
                                             try {
                                                 btnCreateUI.setEnabled(false);
                                                 btnCreateUI.setVisible(false);
                                                 objFrameMain.getContentPane().remove(btnCreateUI);
                                                 setupMainInterface();

                                             } catch (NullPointerException e1) {
                                                 updateStatus("Hallo, Test drei ");
                                                 e1.printStackTrace();
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
                                     }
        );
    }


    public static void setupMainInterface() {


        BaseFrame frameSchueler = new PupilFrame(7, "Schueler-Anzeige-Fenster");

        BaseFrame frameProjekte = new ProjectFrame(3, "Projekte-Anzeige-Fenster");

        JButton btnEnablePupilUI = new JButton("Schüler-Eingabe-Feld");
        btnEnablePupilUI.setVisible(true);
        btnEnablePupilUI.setBounds(0, 0, 300, 150);
        objFrameMain.getContentPane().add(btnEnablePupilUI);


        JButton btnRereadFiles = new JButton("Hier klicken um neue Dateien einzulesen");
        btnRereadFiles.setBounds(300, 0, 300, 150);
        btnRereadFiles.setVisible(true);
        objFrameMain.getContentPane().add(btnRereadFiles);

        btnRereadFiles.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InterfaceExcel interfaceExcel = new InterfaceExcel(Imports.fileJAR.getParent() + "/Excel-Datei-Ordner");
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


        btnEnablePupilUI.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameSchueler.displayFrame(100, 100, 1200, 1000);

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


        JButton btnEnableProjectUI = new JButton("Projekt-Eingabe-Feld aufrufen");
        btnEnableProjectUI.setVisible(true);
        btnEnableProjectUI.setBounds(0, 150, 300, 150);
        objFrameMain.getContentPane().add(btnEnableProjectUI);
        btnEnableProjectUI.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameProjekte.displayFrame(200, 200, 600, 1000);

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


        JButton btnEnableOutputUI = new JButton("Kalkulation und Ausgabe der moeglichen Projektverteilungen");
        btnEnableOutputUI.setVisible(true);
        btnEnableOutputUI.setBounds(0, 300, 600, 150);
        objFrameMain.getContentPane().add(btnEnableOutputUI);
        btnEnableOutputUI.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {


                //TODO: Datenverifikation!!


                CopyOnWriteArrayList<Pupil> listPupilswithoutProject = new CopyOnWriteArrayList<>();
                CopyOnWriteArrayList<Project> listProjects = new CopyOnWriteArrayList<>();
                CopyOnWriteArrayList<Link> pupilProjectLinkList = new CopyOnWriteArrayList<>();

               ArrayList<String> listDataBaseIDs = new ArrayList<>();

                listDataBaseIDs = Imports.objDatabaseManagerGlobal.getEntryIDs("Pupil");

                for (String listElement : listDataBaseIDs
                        ) {
                    listPupilswithoutProject.add(new Pupil(listElement));
                }
                listDataBaseIDs.clear();

                listDataBaseIDs = Imports.objDatabaseManagerGlobal.getEntryIDs("Project");

                for (String listElement : listDataBaseIDs
                        ) {
                    listProjects.add(new Project(listElement));
                }


                while (listPupilswithoutProject.size() > 0) {
                    for (int projectPreferenceIndex = 0; projectPreferenceIndex < 4; projectPreferenceIndex++) {
                        Pupil pupilRandomlyChoosenfromList;
                        try {
                            pupilRandomlyChoosenfromList = listPupilswithoutProject.get((int) (Math.random() * listPupilswithoutProject.size()));
                        }catch (IndexOutOfBoundsException e1){
                            System.out.println("Fertig");
                            break;
                        }
                        String preferedProject = pupilRandomlyChoosenfromList.getDisplayableValue(3+projectPreferenceIndex);
                        for (Project projectInList : listProjects
                                ) {
                            if (projectInList.getHash().equals(preferedProject)) {
                                try {
                                    projectInList.assignNewPupil();
                                    Link result = new Link(pupilRandomlyChoosenfromList.getHash(), projectInList.getHash());
                                    result.setDisplayayableValue(0,pupilRandomlyChoosenfromList.getDisplayableValue(0));
                                    result.setDisplayayableValue(1,pupilRandomlyChoosenfromList.getDisplayableValue(1));
                                    pupilProjectLinkList.add(result);
                                    listPupilswithoutProject.remove(pupilRandomlyChoosenfromList);
                                } catch (IndexOutOfBoundsException e1) {
                                    listProjects.remove(projectInList);
                                }
                            }
                        }
                    }
                }

                for (Link entry:pupilProjectLinkList
                     ) {
                    System.out.println(entry.getPupilHash() +"     ---"+ entry.getProjectID());
                }



                //Main-Function of the Programm

                /*
                PseudoCode:

                Erzeuge die Schüler & Projekte aus der Datenbank, inklusive WertePrüfung

                Erst Projekte, dann Schüler


                Teste für alle Präferenzen die Verteilung auf Projekte durch, speicher das Ergebnis in einer Liste


                 Teste diese Liste, generiere sie ausreichend oft neu

                 Behalte die beste

                 Generiere für jeden Link auf der Liste ein DatenbankElement Link

                 Geb das Frame aus

                 */
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


}

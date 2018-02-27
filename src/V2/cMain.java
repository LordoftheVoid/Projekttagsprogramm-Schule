package V2;

import V2.DataBaseInternalClasses.Link;
import V2.DataBaseInternalClasses.Project;
import V2.DataBaseInternalClasses.Pupil;
import V2.FileInteractions.Generators.InstructionFileGenerator;
import V2.FileInteractions.Readers.DatabaseInterface;
import V2.FileInteractions.Readers.ExcelFileReader;
import V2.FileInteractions.URLManipulation.URLInterFace;
import V2.HelperClasses.LinkwithoutDataBaseBackUp;
import V2.UI.Frame.BaseFrame;
import V2.UI.Frame.OutputFrame;
import V2.UI.Frame.ProjectFrame;
import V2.UI.Frame.PupilFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Aaron on 29.03.2017.
 */

public class cMain {


    //TODO GLOBAL: Das man die Projektnummer nicht manuell ändern kann, sonder ein neues Projekt anlegen muss wenn man das will

    //TODO: FeinSchliff: Das Suchen die Einträge zählt

    //TODO: HARDCODES ENTFERNEN


    public static DatabaseInterface objDatabaseManagerGlobal;
    public static URLInterFace sourceURLS;
    private static JTextArea statusDisplay;
    private static JFrame objFrameMain;

    /**
     * Updates the main display for interactions with a user,
     * examples might include "This is an invalid input", "Please change the following entry", etc.
     */
    public static void updateStatus(String newLine) {
        if (statusDisplay.getText().length() > 500) {
            statusDisplay.setText("");
        }


        statusDisplay.setText(statusDisplay.getText() + "\n" + newLine);


    }

    public static void main(String args[]) {


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
        updateStatus("Hier werden in Zukunft wichtige (!) Nachrichten auftauchen");


        sourceURLS = new URLInterFace(args);


        String[] requiredDirectories = new String[3];

        requiredDirectories[0] = "\\Datenbank-Ordner";
        requiredDirectories[1] = "\\Zu lesende Excel-Dateien";
        requiredDirectories[2] = "\\Ergebnis-Ordner";

        sourceURLS.setupDirectories(requiredDirectories);

        try {
            cMain.objDatabaseManagerGlobal = new DatabaseInterface(sourceURLS.setupDataBaseURL());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        boolean readFromExcel = true;

        try {
            readFromExcel = Boolean.parseBoolean(args[1]);
        } catch (ArrayIndexOutOfBoundsException e1) {

        }
        if (readFromExcel) {
            ArrayList<String> listExCelFilesFound = sourceURLS.listExelURL();

            for (String fileURL : listExCelFilesFound
                    ) {
                ExcelFileReader newReader = new ExcelFileReader(fileURL);
            }
        }


        InstructionFileGenerator generator = new InstructionFileGenerator(URLInterFace.baseURL);

        generator.generateHelpFile();

        generator.generateAdviceFile();


        JButton btnCreateUI = new JButton("Herzlich willkommen,   \n hier klicken um Programm zu starten");
        objFrameMain.getContentPane().add(btnCreateUI);
        btnCreateUI.setVisible(true);
        btnCreateUI.setBounds(0, 0, 600, 450);


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


    private static void setupMainInterface() {


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

                ArrayList<String> filesFound = sourceURLS.listExelURL();

                if (filesFound.size() > 1) {
                    for (String aFilesFound : filesFound) {
                        cMain.updateStatus("Es wurde eine Excel-Datei unter " + aFilesFound + " gefunden, das Lesen beginnt");
                        ExcelFileReader newReader = new ExcelFileReader(aFilesFound);
                    }
                } else {
                    cMain.updateStatus("Es wurden keine Dateien gefunden, bitte stellen sie sicher, dass sie existieren");
                    cMain.updateStatus("Es wird nach Dateien des Typs .xls im Pfad");
                    cMain.updateStatus(URLInterFace.baseURL + "  gesucht");
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


                cMain.updateStatus("Die Berechnung hat begonnen");
                ArrayList<String> listDataBaseIDs;

                CopyOnWriteArrayList<Pupil> listPupilswithoutProjectOverall = new CopyOnWriteArrayList<>();
                CopyOnWriteArrayList<Project> listProjects = new CopyOnWriteArrayList<>();
                CopyOnWriteArrayList<LinkwithoutDataBaseBackUp> listResultLinks = new CopyOnWriteArrayList<>();

                listDataBaseIDs = cMain.objDatabaseManagerGlobal.getEntryIDs("Pupil");

                for (String listElement : listDataBaseIDs
                        ) {
                    listPupilswithoutProjectOverall.add(new Pupil(listElement));
                }
                listDataBaseIDs.clear();

                listDataBaseIDs = cMain.objDatabaseManagerGlobal.getEntryIDs("Project");
                HashMap<String, Project> idProjectHashMap = new HashMap<>();
                for (String listElement : listDataBaseIDs
                        ) {
                    Project newProjectFromID = new Project(listElement);
                    listProjects.add(newProjectFromID);
                    idProjectHashMap.put(newProjectFromID.getHash(), newProjectFromID);
                }


                boolean validData = true;

                for (Pupil pupilInDataBase : listPupilswithoutProjectOverall
                        ) {
                    validData = validData && pupilInDataBase.isValidDataBaseEntry();
                    if (!validData) {
                        break;
                    }
                }


                for (Project projectInDataBase : listProjects
                        ) {
                    validData = validData && projectInDataBase.isValidDataBaseEntry();
                    if (!validData) {
                        break;
                    }
                }

                if (validData) {
                    cMain.updateStatus("Die Berechnungen haben begonnen und können sich hinziehen, Updates werden erfolgen");

                    listDataBaseIDs = cMain.objDatabaseManagerGlobal.getEntryIDs("Link");
                    for (String listElement : listDataBaseIDs
                            ) {
                        try {
                            cMain.objDatabaseManagerGlobal.deleteEntry("Link", listElement);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    listDataBaseIDs.clear();


                    CopyOnWriteArrayList<Pupil> listofPupilsWithOutProjectBackUp = new CopyOnWriteArrayList<>();
                    listofPupilsWithOutProjectBackUp.addAll(listPupilswithoutProjectOverall);

                    int amountofTrys = 0;

                    CopyOnWriteArrayList<LinkwithoutDataBaseBackUp> listFinalResult = new CopyOnWriteArrayList<>();

                    boolean firstTimeInLoop = true;
                    int sumfinalResult = 0;
                    int invalidLinksFinalResult = 0;

                    while (amountofTrys < 10) {

                        listPupilswithoutProjectOverall.addAll(listofPupilsWithOutProjectBackUp);
                        listResultLinks.clear();

                        CopyOnWriteArrayList<Pupil> listPeoplewithoutProjectthisRound = new CopyOnWriteArrayList<>();

                        while (listPupilswithoutProjectOverall.size() > 0) {
                            for (int projectPreferenceIndex = 0; projectPreferenceIndex < 4; projectPreferenceIndex++) {
                                if (listPupilswithoutProjectOverall.size() == 0) break;

                                listPeoplewithoutProjectthisRound.addAll(listPupilswithoutProjectOverall);
                                while (listPeoplewithoutProjectthisRound.size() > 0) {

                                    Pupil pupilRandomlyChoosenfromList;

                                    int randomlyChoosenValue = (int) (Math.random() * listPeoplewithoutProjectthisRound.size());

                                    pupilRandomlyChoosenfromList = listPeoplewithoutProjectthisRound.get(randomlyChoosenValue);

                                    String preferedProject = pupilRandomlyChoosenfromList.getDisplayableValue(3 + projectPreferenceIndex);

                                    if (idProjectHashMap.get(preferedProject).hasSlotsavailable()) {
                                        idProjectHashMap.get(preferedProject).assignNewPupil();
                                        LinkwithoutDataBaseBackUp result = new LinkwithoutDataBaseBackUp(idProjectHashMap.get(preferedProject).getHash(), pupilRandomlyChoosenfromList.getHash());
                                        listResultLinks.add(result);
                                        result.setPupilPreferenceNumber(projectPreferenceIndex);
                                        listPupilswithoutProjectOverall.remove(pupilRandomlyChoosenfromList);
                                        listPeoplewithoutProjectthisRound.remove(pupilRandomlyChoosenfromList);
                                    } else {
                                        listPeoplewithoutProjectthisRound.remove(pupilRandomlyChoosenfromList);
                                    }
                                }

                            }

                            for (String key : idProjectHashMap.keySet()
                                    ) {
                                idProjectHashMap.get(key).resetPupilCount();
                            }


                            while (listPupilswithoutProjectOverall.size() > 0) {
                                LinkwithoutDataBaseBackUp result = new LinkwithoutDataBaseBackUp("-1", listPupilswithoutProjectOverall.get(0).getHash());
                                result.setPupilPreferenceNumber(5);
                                listResultLinks.add(result);
                                listPupilswithoutProjectOverall.remove(0);
                            }

                            cMain.updateStatus("Diese Nachricht markiert das Ende einer vollen Berechnung,  weitere sollten ähnlich lange dauern");
                        }


                        if (firstTimeInLoop) {
                            firstTimeInLoop = false;
                            listFinalResult.addAll(listResultLinks);
                            for (LinkwithoutDataBaseBackUp element : listResultLinks
                                    ) {
                                sumfinalResult = sumfinalResult + element.getPupilPreferenceNumber();
                                if (!element.isValidProject()) {
                                    invalidLinksFinalResult++;
                                }
                            }

                        } else {

                            int sumPreferencesThisResult = 0;
                            int invalidLinksthisResult = 0;
                            for (LinkwithoutDataBaseBackUp element : listResultLinks
                                    ) {
                                sumPreferencesThisResult = sumPreferencesThisResult + element.getPupilPreferenceNumber();
                                if (!element.isValidProject()) {
                                    invalidLinksthisResult++;
                                }
                            }

                            //   System.out.println(sumPreferencesThisResult + " ZwischenErgebnis" + invalidLinksthisResult);

                            if (invalidLinksthisResult <= invalidLinksFinalResult && sumPreferencesThisResult < sumfinalResult) {
                                sumfinalResult = sumPreferencesThisResult;
                                invalidLinksFinalResult = invalidLinksthisResult;
                                listFinalResult.clear();
                                listFinalResult.addAll(listResultLinks);
                                amountofTrys = 0;
                                cMain.updateStatus("Es kam zu einem neuen besten Ergebnis");
                            } else {
                                amountofTrys++;
                            }
                            listResultLinks.clear();


                        }
                    }


                    for (LinkwithoutDataBaseBackUp finalLink : listFinalResult
                            ) {
                        String newHash = Link.generateHash(finalLink.getProjectHash(), finalLink.getPupilHash());
                        Link newResultLink = new Link(newHash);
                        newResultLink.setDisplayayableValue(0, cMain.objDatabaseManagerGlobal.getValuefromDataBase("Pupil", finalLink.getPupilHash(), 0));
                        newResultLink.setDisplayayableValue(1, cMain.objDatabaseManagerGlobal.getValuefromDataBase("Pupil", finalLink.getPupilHash(), 1));
                        newResultLink.setDisplayayableValue(2, cMain.objDatabaseManagerGlobal.getValuefromDataBase("Pupil", finalLink.getPupilHash(), 2));

                        if (!Objects.equals(finalLink.getProjectHash(), "-1")) {

                            newResultLink.setDisplayayableValue(3, finalLink.getProjectHash());
                            newResultLink.setDisplayayableValue(4, String.valueOf(finalLink.getPupilPreferenceNumber() + 1));
                        } else {
                            newResultLink.setDisplayayableValue(3, "Schüler ohne Projekt!!");
                            newResultLink.setDisplayayableValue(4, "Schüler ohne Projekt!!");
                        }
                    }

                    // System.out.println(sumfinalResult + " Bestes Ergebnis" + invalidLinksFinalResult);

                    //   System.out.println("-----------------------------------");

                    BaseFrame frameOutput = new OutputFrame(5, "Das beste Ergebnis");
                    frameOutput.displayFrame(0, 0, 1000, 1000);
                    cMain.updateStatus("");
                    cMain.updateStatus("");
                    cMain.updateStatus("Hier etwas ändern wird keinerlei Einfluss haben, dies dient lediglich der Kontrolle");
                    cMain.updateStatus("Ich bitte darum, entsprechende Hinweise zur Kontrolle in der Datei Hinweise zu beachten! ");
                    cMain.updateStatus("");
                    cMain.updateStatus("");

                } else {
                    cMain.updateStatus("Die Datenbank war nicht vollständig und oder hatte falsche Werte, bitte eintragen/ändern");
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
                cMain.updateStatus("Das Berechnen wird seine Zeit dauern, im Zweifel kurz warten");
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }


}

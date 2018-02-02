package NeuSortierung;

import AlterCode.Lists.cHash_Map_ID_projects_to_List_ID_pupils;
import NeuSortierung.FileInteractions.DirectoryCreator;
import NeuSortierung.FileInteractions.Excel.ExcelInterface;
import NeuSortierung.FileInteractions.Excel.OutputFileGenerator;
import NeuSortierung.Settings.DataBaseLinks;
import NeuSortierung.Settings.Imports;
import NeuSortierung.UI.Frames.AbstraktFrame;
import NeuSortierung.UI.Frames.ProjectFrame;
import NeuSortierung.UI.Frames.PupilFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by Aaron on 29.03.2017.
 */
public class cMain {


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
        /**
         * Setup of the display shown to the user, mainly through JFrame.
         */


        //   System.out.println(System.getProperties());


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


        DataBaseLinks.init();


        updateStatus("Hier werden in Zukunft wichtige Nachrichten auftauchen");

        btnCreateUI.addMouseListener(new MouseListener() {
                                         @Override
                                         public void mouseClicked(MouseEvent e) {
                                             try {
                                                 Imports.setupImport();
                                                 btnCreateUI.setEnabled(false);
                                                 btnCreateUI.setVisible(false);
                                                 objFrameMain.getContentPane().remove(btnCreateUI);
                                                 setupInteraktiveInterface();

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


    public static void setupInteraktiveInterface() {

        DirectoryCreator objDirectoryManager = new DirectoryCreator();

        objDirectoryManager.v_creation(Imports.fileJAR.getParent(), "Datenbank-Ordner");
        objDirectoryManager.v_creation(Imports.fileJAR.getParent(), "Excel-Datei-Ordner");
        objDirectoryManager.v_creation(Imports.fileJAR.getParent(), "Output-Ordner (Excel-Dateien)");


        ExcelInterface excelInterface = new ExcelInterface();
        excelInterface.updateDataBase(Imports.fileJAR.getParent() + "/Excel-Datei-Ordner");

        AbstraktFrame frameSchueler = new PupilFrame(7, "Schueler-Anzeige-Fenster");

        AbstraktFrame frameProjekte = new ProjectFrame(3, "Projekte-Anzeige-Fenster");


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
                /*
                excelInterface.dateiListe.clear();
                excelInterface.dateiListe = excelInterface.list_search_for_xls_Files(Imports.fileJAR.getParent() + "/Excel-Datei-Ordner");

                cMain.updateStatus("\n Es wurden " + excelInterface.dateiListe.size() + " Excel-Dateien gefunden.");

                for (String loop_objekt_s : excelInterface.dateiListe
                        ) {
                    excelInterface.readFile(loop_objekt_s);
                }


                statusDisplay.setText(statusDisplay.getText() + "\n Es wurden " + excelInterface.personsFound + " neue Schüler mittels Excel eingelesen.");
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


        btnEnablePupilUI.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameSchueler.v_show_Frame(100, 100, 1200, 1000);

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
                frameProjekte.v_show_Frame(200, 200, 600, 1000);

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



                /*

                Test ob Werte null sind, wenn ja, Abbruch der Methode
                 */


                boolean b_all_values_valid = true;
                String NameInvalidPerson = "";
                String gradeInvalidPerson = "";
                try {
                    ResultSet entrys_persons = Imports.objDatabaseManagerGlobal.readEntrysAllAttributes("persons");
                    while (entrys_persons.next()) {
                        for (int i = 1; i < 8; i++) {
                            if (entrys_persons.getString(i) == null) {
                                NameInvalidPerson = entrys_persons.getString(2);
                                gradeInvalidPerson = entrys_persons.getString(4);

                                b_all_values_valid = false;
                                break;
                            }
                        }
                        if (!b_all_values_valid) {
                            break;
                        }
                    }
                } catch (SQLException e1) {
                    cMain.updateStatus("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
                }

                if (b_all_values_valid) {
                    //          obj_Frame_Output.v_show_Frame(700, 500, 1000, 1000);

                    cMain.updateStatus("Die Berechnung hat begonnen, das könnte seine Zeit dauern");

                    cHash_Map_ID_projects_to_List_ID_pupils objHashmap_projects_pupils = new cHash_Map_ID_projects_to_List_ID_pupils();
                    objHashmap_projects_pupils.v_setup_from_Database();
                    boolean b_constant_people = false;

                    objHashmap_projects_pupils.v_arrangement();

                    int i_counter = 0;


                    cHash_Map_ID_projects_to_List_ID_pupils obj_best_solution = new cHash_Map_ID_projects_to_List_ID_pupils();

                    obj_best_solution.putAll(objHashmap_projects_pupils);
                    obj_best_solution.i_sum_of_preferences = objHashmap_projects_pupils.i_sum_of_preferences;

                    while (!b_constant_people) {

                        objHashmap_projects_pupils.v_arrangement();

                        cMain.updateStatus("Es wurde ein Zuteilung vorgenomen, Überprüfung erfolgt. " + i_counter);
                        if (objHashmap_projects_pupils.get("-1").size() <= obj_best_solution.get("-1").size()) {
                            if (objHashmap_projects_pupils.i_sum_of_preferences < obj_best_solution.i_sum_of_preferences) {
                                obj_best_solution.clear();
                                obj_best_solution.putAll(objHashmap_projects_pupils);
                                obj_best_solution.i_sum_of_preferences = objHashmap_projects_pupils.i_sum_of_preferences;
                                cMain.updateStatus("Es wurde eine neue beste Lösung gefunden. ");

                                i_counter = 0;
                            } else {
                                i_counter++;
                            }
                        } else {
                            i_counter++;
                        }
                        if (i_counter == 15) {
                            b_constant_people = true;
                        }

                    }

                    cMain.updateStatus("Es wurde die beste Lösung gefunden, Output erfolgt.");

/*
                    obj_Frame_Output.v_set_custom_Head(arr_list_value_Strings[2]);

                    obj_Frame_Output.v_set_custom_Search(arr_list_value_Strings[2]);
                    obj_Frame_Output.v_sort_setup();

                    obj_Frame_Output.v_update_from_List_and_Database(obj_best_solution);

        */
                    OutputFileGenerator obj_File_Generator = new OutputFileGenerator(obj_best_solution);
                    obj_File_Generator.v_write_xls_Files(Imports.fileJAR.getParent() + "/Output-Ordner ( Excel-Dateien)");

                } else {
                    cMain.updateStatus("Ein Schüler namens " + NameInvalidPerson + " aus Klasse  " + gradeInvalidPerson + "   hatte einen Wert auf Null, bitte ergänzen");
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


}

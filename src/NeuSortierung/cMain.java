package NeuSortierung;

import AlterCode.Lists.cHash_Map_ID_projects_to_List_ID_pupils;
import NeuSortierung.DataBaseInteractions.DataBaseObjekts.cDataBaseElement;
import NeuSortierung.DataBaseInteractions.DataBaseObjekts.cPupil;
import NeuSortierung.FileInteractions.Excel.cExcel_Interface;
import NeuSortierung.FileInteractions.Excel.c_Output_File_Generator;
import NeuSortierung.FileInteractions.cDirectoryCreator;
import NeuSortierung.Settings.DataBaseObjectTypes;
import NeuSortierung.Settings.cDataBaseLinks;
import NeuSortierung.Settings.cImports;
import NeuSortierung.UI.Frames.cAbstraktesFrame;
import NeuSortierung.UI.Frames.cProjektFrame;
import NeuSortierung.UI.Frames.cSchuelerFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Aaron on 29.03.2017.
 * The Goal of this application ist to assign people to projects.
 * <p>
 * Both entities will be stored in a database, but unlinked.
 * <p>
 * Afterwards, a Hashmapfunction creates a unique Connection-Table.
 * This will be repeated until a given Hashmap is the "prefered one",
 * based on a range of parameters.
 * <p>
 * Afterwards, this solution is displayed and stored in Excel-Sheets for easy
 * export.
 * <p>
 * One way to store entities in the database is through a automatic function that reads Excel-files.
 */
public class cMain {


    static JTextArea objTextareaStatus;
    static JFrame objFrameMain;


    /**
     * Updates the main display for interactions with a user,
     * examples might include "This is an invalid input", "Please change the following entry", etc.
     */
    public static void v_update_Textarea_Status(String s_new_Line) {
        if (objTextareaStatus.getText().length() > 2000) {
            objTextareaStatus.setText("");
        }
        objTextareaStatus.setText(objTextareaStatus.getText() + "\n" + s_new_Line);
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


        objTextareaStatus = new JTextArea();
        objFrameMain.getContentPane().add(objTextareaStatus);
        objTextareaStatus.setText("");
        objTextareaStatus.setBounds(0, 450, 600, 1000);
        objTextareaStatus.setBorder(new LineBorder(Color.black));


        JButton erzeugedenRest = new JButton("Herzlich willkommen,   \n hier klicken um Programm zu starten");
        objFrameMain.getContentPane().add(erzeugedenRest);
        erzeugedenRest.setVisible(true);
        erzeugedenRest.setBounds(0, 0, 600, 450);


        /*
        Hardcoded Colum-Names of the Database
         */
        cDataBaseLinks.init();


        v_update_Textarea_Status("Hier werden in Zukunft wichtige Nachrichten auftauchen");

        erzeugedenRest.addMouseListener(new MouseListener() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {
                                                try {
                                                    cImports.setupImport();
                                                    erzeugedenRest.setEnabled(false);
                                                    erzeugedenRest.setVisible(false);
                                                    objFrameMain.getContentPane().remove(erzeugedenRest);
                                                    v_generate_Interface();

                                                } catch (NullPointerException e1) {
                                                    v_update_Textarea_Status("Hallo, Test drei ");
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


    public static void v_generate_Interface() {

        cDirectoryCreator objDirectoryManager = new cDirectoryCreator();

        objDirectoryManager.v_creation(cImports.fileJAR.getParent(), "Datenbank-Ordner");
        objDirectoryManager.v_creation(cImports.fileJAR.getParent(), "Excel-Datei-Ordner");
        objDirectoryManager.v_creation(cImports.fileJAR.getParent(), "Output-Ordner (Excel-Dateien)");


        cExcel_Interface obj_File_Reader_Excel = new cExcel_Interface();
        obj_File_Reader_Excel.updateDatenbank(cImports.fileJAR.getParent() + "/Excel-Datei-Ordner");

        cDataBaseElement Test1 = new cDataBaseElement(DataBaseObjectTypes.PUPIL, "DilLu");


        cAbstraktesFrame frameSchueler = new cSchuelerFrame(7, "Schueler-Anzeige-Fenster");

        cAbstraktesFrame frameProjekte = new cProjektFrame(3, "Projekte-Anzeige-Fenster");


        ArrayList<cPupil> aktiveSchueler = cPupil.getFullListPupils();


        JButton btn_pupils_Frame = new JButton("Schüler-Eingabe-Feld");
        btn_pupils_Frame.setVisible(true);
        btn_pupils_Frame.setBounds(0, 0, 300, 150);
        objFrameMain.getContentPane().add(btn_pupils_Frame);


        JButton btn_Read_Files = new JButton("Hier klicken um neue Dateien einzulesen");
        btn_Read_Files.setBounds(300, 0, 300, 150);
        btn_Read_Files.setVisible(true);
        objFrameMain.getContentPane().add(btn_Read_Files);

        btn_Read_Files.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                /*
                obj_File_Reader_Excel.dateiListe.clear();
                obj_File_Reader_Excel.dateiListe = obj_File_Reader_Excel.list_search_for_xls_Files(cImports.fileJAR.getParent() + "/Excel-Datei-Ordner");

                cMain.v_update_Textarea_Status("\n Es wurden " + obj_File_Reader_Excel.dateiListe.size() + " Excel-Dateien gefunden.");

                for (String loop_objekt_s : obj_File_Reader_Excel.dateiListe
                        ) {
                    obj_File_Reader_Excel.readFile(loop_objekt_s);
                }


                objTextareaStatus.setText(objTextareaStatus.getText() + "\n Es wurden " + obj_File_Reader_Excel.personsFound + " neue Schüler mittels Excel eingelesen.");
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


        btn_pupils_Frame.addMouseListener(new MouseListener() {
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


        JButton btn_projekt_Frame = new JButton("Projekt-Eingabe-Feld aufrufen");
        btn_projekt_Frame.setVisible(true);
        btn_projekt_Frame.setBounds(0, 150, 300, 150);
        objFrameMain.getContentPane().add(btn_projekt_Frame);
        btn_projekt_Frame.addMouseListener(new MouseListener() {
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


        JButton btn_Frame_Output = new JButton("Kalkulation und Ausgabe der moeglichen Projektverteilungen");
        btn_Frame_Output.setVisible(true);
        btn_Frame_Output.setBounds(0, 300, 600, 150);
        objFrameMain.getContentPane().add(btn_Frame_Output);
        btn_Frame_Output.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {



                /*

                Test ob Werte null sind, wenn ja, Abbruch der Methode
                 */


                boolean b_all_values_valid = true;
                String NameInvalidPerson = "";
                String gradeInvalidPerson = "";
                try {
                    ResultSet entrys_persons = cImports.objDatabaseManagerGlobal.readEntrysAllAttributes("persons");
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
                    cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
                }

                if (b_all_values_valid) {
                    //          obj_Frame_Output.v_show_Frame(700, 500, 1000, 1000);

                    cMain.v_update_Textarea_Status("Die Berechnung hat begonnen, das könnte seine Zeit dauern");

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

                        cMain.v_update_Textarea_Status("Es wurde ein Zuteilung vorgenomen, Überprüfung erfolgt. " + i_counter);
                        if (objHashmap_projects_pupils.get("-1").size() <= obj_best_solution.get("-1").size()) {
                            if (objHashmap_projects_pupils.i_sum_of_preferences < obj_best_solution.i_sum_of_preferences) {
                                obj_best_solution.clear();
                                obj_best_solution.putAll(objHashmap_projects_pupils);
                                obj_best_solution.i_sum_of_preferences = objHashmap_projects_pupils.i_sum_of_preferences;
                                cMain.v_update_Textarea_Status("Es wurde eine neue beste Lösung gefunden. ");

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

                    cMain.v_update_Textarea_Status("Es wurde die beste Lösung gefunden, Output erfolgt.");

/*
                    obj_Frame_Output.v_set_custom_Head(arr_list_value_Strings[2]);

                    obj_Frame_Output.v_set_custom_Search(arr_list_value_Strings[2]);
                    obj_Frame_Output.v_sort_setup();

                    obj_Frame_Output.v_update_from_List_and_Database(obj_best_solution);

        */
                    c_Output_File_Generator obj_File_Generator = new c_Output_File_Generator(obj_best_solution);
                    obj_File_Generator.v_write_xls_Files(cImports.fileJAR.getParent() + "/Output-Ordner ( Excel-Dateien)");

                } else {
                    cMain.v_update_Textarea_Status("Ein Schüler namens " + NameInvalidPerson + " aus Klasse  " + gradeInvalidPerson + "   hatte einen Wert auf Null, bitte ergänzen");
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

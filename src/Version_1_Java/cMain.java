package Version_1_Java;

import Version_1_Java.File_Interactions.Database.cDatabaseConnectionManager;
import Version_1_Java.File_Interactions.Directories.c_Directory_Creator;
import Version_1_Java.File_Interactions.Files.cExcel_File_Reader;
import Version_1_Java.File_Interactions.Files.c_Output_File_Generator;
import Version_1_Java.Frame_Related.c_Frame;
import Version_1_Java.Lists.cHash_Map_ID_projects_to_List_ID_pupils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by Aaron on 29.03.2017.
 */
public class cMain {


    public static final int iMaximalanzahl_Projekte = 4;
    static JTextArea obj_Textarea_Status;
    static cDatabaseConnectionManager obj_Database_manager_Main;
    static JFrame obj_Frame_Main;
    static  final File fileJAR = new File(cMain.class.getProtectionDomain().getCodeSource().getLocation().getPath());





    /*
        Ausgabe Fenster, um dem Nutzer Rückmeldung zu geben

     */
    public static void v_update_Textarea_Status(String s_new_Line) {
        if (obj_Textarea_Status.getText().length() > 2500) {
            obj_Textarea_Status.setText("");
        }
        obj_Textarea_Status.setText(obj_Textarea_Status.getText() + "\n" + s_new_Line);
    }


    static boolean bDatabaseFound = false;


    public static void main(String args[]) {
        /*
        Hauptfenster
         */
        obj_Frame_Main = new JFrame("Projekttagsverwaltungsprogramm Version 1.0");
        obj_Frame_Main.setVisible(true);
        obj_Frame_Main.setBounds(500, 0, 1000, 1000);
        obj_Frame_Main.getContentPane().setLayout(null);
        obj_Frame_Main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        /*

        Ausgabe-TextArea
         */
        obj_Textarea_Status = new JTextArea();
        obj_Frame_Main.getContentPane().add(obj_Textarea_Status);
        obj_Textarea_Status.setText("");
        obj_Textarea_Status.setBounds(0, 400, 700, 1000);
        obj_Textarea_Status.setBorder(new LineBorder(Color.black));





        c_Directory_Creator objDirectoryManager = new c_Directory_Creator();


        objDirectoryManager.v_creation(fileJAR.getParent(),"Datenbank-Ordner");
        objDirectoryManager.v_creation(fileJAR.getParent(), "Excel-Datei-Ordner");



        /*
        Erzeugung und Initialisierung der Datenbankverbindung

         */
        obj_Database_manager_Main = new cDatabaseConnectionManager();
        bDatabaseFound =obj_Database_manager_Main.v_initialization(s_generate_Database_URL(fileJAR.getParent()));


        v_generate_Interface(bDatabaseFound);

        JButton btn_Retry_Database_Connection = new JButton();
        obj_Frame_Main.getContentPane().add(btn_Retry_Database_Connection);
        btn_Retry_Database_Connection.setText("Datenbankverbindug starten bzw testen");
        btn_Retry_Database_Connection.setVisible(true);
        btn_Retry_Database_Connection.setBounds(700, 700, 300, 300);
        btn_Retry_Database_Connection.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                bDatabaseFound=  obj_Database_manager_Main.v_initialization(s_generate_Database_URL(fileJAR.getParent()));
                v_generate_Interface(bDatabaseFound);

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


    public static String s_generate_Database_URL(String sparentURL) {

        String sDynamikDatabaseURL = sparentURL + "/" + "Datenbank-Ordner";
        File fileDatabaseDirectory = new File(sDynamikDatabaseURL);
        boolean bFileFound = false;
        for (File loop_obj_File : fileDatabaseDirectory.listFiles()) {
            if (loop_obj_File.getName().contains(".db")) {
                sDynamikDatabaseURL = sDynamikDatabaseURL +"/"+ loop_obj_File.getName();
                bFileFound = true;
                break;
            }
        }

        if (bFileFound) {
            return sDynamikDatabaseURL;
        } else {
            return "NO_FILE_FOUND";
        }

    }



    public static void v_generate_Interface(boolean bInput) {
        if (bInput) {

            //Erzeugung der Datei-Leser und Lesen
            cExcel_File_Reader obj_File_Reader_Excel = new cExcel_File_Reader(obj_Database_manager_Main);

            obj_File_Reader_Excel.list_of_filenames_with_xls = obj_File_Reader_Excel.list_search_for_xls_Files(fileJAR.getParent() + "/Excel-Datei-Ordner");
            cMain.v_update_Textarea_Status("Es wurden " + obj_File_Reader_Excel.list_of_filenames_with_xls.size() + " Excel-Dateien gefunden.");

            for (String loop_objekt_s : obj_File_Reader_Excel.list_of_filenames_with_xls
                    ) {
                obj_File_Reader_Excel.read_file_extracting_pupils(loop_objekt_s);
            }

            obj_File_Reader_Excel.v_update_Database_from_list();
            cMain.v_update_Textarea_Status("Es wurden " + obj_File_Reader_Excel.i_amount_of_pupils() + " neue Schüler mittels Excel eingelesen.");


            int i_amount_pupils_Database = 0;
            int i_amount_projects_Database = 0;
            try {
                i_amount_pupils_Database = obj_Database_manager_Main.i_amout_of_entrys_in_Database("persons");
                i_amount_projects_Database = obj_Database_manager_Main.i_amout_of_entrys_in_Database("projects");
            } catch (SQLException e_1) {

            }
            obj_Textarea_Status.setText(obj_Textarea_Status.getText() + "\n Die momentane Datenbank erfasst " + i_amount_pupils_Database + " Schüler und " + i_amount_projects_Database + "  Projekte ");


            CopyOnWriteArrayList<String>[] arr_list_value_Strings = new CopyOnWriteArrayList[3];
            CopyOnWriteArrayList<String>[] arr_list_Database_References = new CopyOnWriteArrayList[2];


        /*


        Hardcode der Eigenschaften der Datenbank
        ? Würden sie sich die Mühe machen und die  von dem Programm bei Programmstart aus der Datenbank auslesen lassen
        oder ist das so legitim ?

         */

            arr_list_value_Strings[0] = new CopyOnWriteArrayList<>();
            arr_list_value_Strings[1] = new CopyOnWriteArrayList<>();
            arr_list_value_Strings[2] = new CopyOnWriteArrayList<>();


            arr_list_value_Strings[0].add("Nachname");
            arr_list_value_Strings[0].add("Vorname");
            arr_list_value_Strings[0].add("Klasse");
            arr_list_value_Strings[0].add("Erstwahl");
            arr_list_value_Strings[0].add("Zweitwahl");
            arr_list_value_Strings[0].add("Drittwahl");
            arr_list_value_Strings[0].add("Viertwahl");


            arr_list_value_Strings[1].add("Projektnummer");
            arr_list_value_Strings[1].add("Lehrkraftskürzel");
            arr_list_value_Strings[1].add("Maximale Schüleranzahl");

            arr_list_value_Strings[2].add("Nachname");
            arr_list_value_Strings[2].add("Vorname");
            arr_list_value_Strings[2].add("Klasse");
            arr_list_value_Strings[2].add("Lehrkraftskürzel");
            arr_list_value_Strings[2].add("Wahlnummer");


            arr_list_Database_References[0] = new CopyOnWriteArrayList<>();
            arr_list_Database_References[1] = new CopyOnWriteArrayList<>();


            //  arr_list_Database_References[1].add("s_unique_ID"); Was ist damit ?
            arr_list_Database_References[0].add("s_sur_Name");
            arr_list_Database_References[0].add("s_pre_Name");
            arr_list_Database_References[0].add("s_grade");
            arr_list_Database_References[0].add("i_pref0");
            arr_list_Database_References[0].add("i_pref1");
            arr_list_Database_References[0].add("i_pref2");
            arr_list_Database_References[0].add("i_pref3");


            arr_list_Database_References[1].add("s_unique_ID");
            arr_list_Database_References[1].add("s_teacher_ID");
            arr_list_Database_References[1].add("i_max_pupils");


        /*


        Erzeugung etc des Schüler-Fensters
         */

            c_Frame obj_Frame_pupils = new c_Frame("persons", obj_Database_manager_Main);

            obj_Frame_pupils.v_generate_rows_from_Database(arr_list_Database_References[0].size(), arr_list_Database_References[0]);

            obj_Frame_pupils.v_set_custom_Head(arr_list_value_Strings[0]);

            obj_Frame_pupils.v_set_custom_Search(arr_list_value_Strings[0]);

            obj_Frame_pupils.v_Setup_Listener(2);

            obj_Frame_pupils.v_sort_setup();


            //Frame Projekte

            c_Frame obj_Frame_projects = new c_Frame("projects", obj_Database_manager_Main);

            obj_Frame_projects.v_generate_rows_from_Database(arr_list_Database_References[1].size(), arr_list_Database_References[1]);

            obj_Frame_projects.v_set_custom_Head(arr_list_value_Strings[1]);

            obj_Frame_projects.v_set_custom_Search(arr_list_value_Strings[1]);

            obj_Frame_projects.v_Setup_Listener(1);

            obj_Frame_projects.v_sort_setup();


            //Frame Output

            c_Frame obj_Frame_Output = new c_Frame("projects", obj_Database_manager_Main);


        /*

        Ab hier im wesentlichen nur noch Erzeugung weiterer grafischer Elemente
        sowie deren Funktionen bei Mausklick.
         */


            JButton btn_pupils_Frame = new JButton("Schüler-Eingabe-Feld aufrufen");
            btn_pupils_Frame.setVisible(true);
            btn_pupils_Frame.setBounds(0, 0, 300, 300);
            obj_Frame_Main.getContentPane().add(btn_pupils_Frame);


            JButton btn_Read_Files = new JButton("Hier klicken um neue Dateien einzulesen");
            btn_Read_Files.setBounds(700, 400, 300, 300);
            btn_Read_Files.setVisible(true);
            obj_Frame_Main.getContentPane().add(btn_Read_Files);

            btn_Read_Files.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    obj_File_Reader_Excel.list_of_filenames_with_xls.clear();
                    obj_File_Reader_Excel.list_of_filenames_with_xls = obj_File_Reader_Excel.list_search_for_xls_Files(fileJAR.getParent() + "/Excel-Datei-Ordner");

                    cMain.v_update_Textarea_Status("\n Es wurden " + obj_File_Reader_Excel.list_of_filenames_with_xls.size() + " Excel-Dateien gefunden.");

                    for (String loop_objekt_s : obj_File_Reader_Excel.list_of_filenames_with_xls
                            ) {
                        obj_File_Reader_Excel.read_file_extracting_pupils(loop_objekt_s);
                    }

                    obj_File_Reader_Excel.v_update_Database_from_list();

                    obj_Textarea_Status.setText(obj_Textarea_Status.getText() + "\n Es wurden " + obj_File_Reader_Excel.i_amount_of_pupils() + " neue Schüler mittels Excel eingelesen.");

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
                    obj_Frame_pupils.v_show_Frame(100, 100);

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
            btn_projekt_Frame.setBounds(300, 0, 300, 300);
            obj_Frame_Main.getContentPane().add(btn_projekt_Frame);
            btn_projekt_Frame.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    obj_Frame_projects.v_show_Frame(200, 200);

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
            btn_Frame_Output.setBounds(600, 0, 300, 300);
            obj_Frame_Main.getContentPane().add(btn_Frame_Output);
            btn_Frame_Output.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {




                /*

                Test ob Werte null sind, wenn ja, Abbruch der Methode
                 */


                    boolean b_all_values_valid = true;
                    try {
                        ResultSet entrys_persons = obj_Database_manager_Main.read_entrys_all_attributes("persons");
                        while (entrys_persons.next()) {
                            for (int i = 1; i < 8; i++) {
                                if (entrys_persons.getString(i) == null) {
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
                        obj_Frame_Output.v_show_Frame(500, 500);

                        cMain.v_update_Textarea_Status("Die Berechnung hat begonnen, das könnte seine Zeit dauern");

                        cHash_Map_ID_projects_to_List_ID_pupils objHashmap_projects_pupils = new cHash_Map_ID_projects_to_List_ID_pupils(obj_Database_manager_Main);
                        objHashmap_projects_pupils.v_setup_from_Database();
                        objHashmap_projects_pupils.v_arrangement();
                        boolean b_constant_people = false;

                        int i_counter = 0;

                        cHash_Map_ID_projects_to_List_ID_pupils obj_best_solution = new cHash_Map_ID_projects_to_List_ID_pupils(obj_Database_manager_Main);

                        obj_best_solution.putAll(objHashmap_projects_pupils);
                        obj_best_solution.i_sum_of_preferences = objHashmap_projects_pupils.i_sum_of_preferences;

                        while (!b_constant_people) {

                            objHashmap_projects_pupils.v_arrangement();

                            cMain.v_update_Textarea_Status("Es wurde ein Zuteilung vorgenomen, Überprüfung erfolgt.");
                            if (objHashmap_projects_pupils.get("-1").size() <= obj_best_solution.get("-1").size()) {
                                if (objHashmap_projects_pupils.i_sum_of_preferences < obj_best_solution.i_sum_of_preferences) {
                                    obj_best_solution.clear();
                                    obj_best_solution.putAll(objHashmap_projects_pupils);
                                    obj_best_solution.i_sum_of_preferences = objHashmap_projects_pupils.i_sum_of_preferences;


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


                        obj_Frame_Output.v_set_custom_Head(arr_list_value_Strings[2]);

                        obj_Frame_Output.v_set_custom_Search(arr_list_value_Strings[2]);
                        obj_Frame_Output.v_sort_setup();

                        obj_Frame_Output.v_update_from_List_and_Database(obj_best_solution);

                        c_Output_File_Generator obj_File_Generator = new c_Output_File_Generator(obj_best_solution, obj_Database_manager_Main);
                        obj_File_Generator.v_write_xls_Files();

                    } else {
                        cMain.v_update_Textarea_Status("Es waren unerlaubte bzw nicht festgesetzte Werte in der Schüler-Datenbank, bitte ergänzen");
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
}

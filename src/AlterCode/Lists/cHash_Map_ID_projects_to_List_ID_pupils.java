package AlterCode.Lists;

import NeuSortierung.DataBaseInteractions.CDatabaseInterface;
import NeuSortierung.Settings.cImports;
import NeuSortierung.cMain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Aaron on 12.08.2017.
 */



/*
Implementation einer Hash-Map, um die Verlinkung von Schülern zu Projekten zu
realisieren.

Aufbau erfolg nach Klick auf den Button, Ausgabe über Out-Put-Generator


 */

public class cHash_Map_ID_projects_to_List_ID_pupils extends HashMap<String, ArrayList<String>> {

    CDatabaseInterface obj_Databasemanager_list;


    public int i_sum_of_preferences = 0;

    public int i_amount_of_pupils = 0;




    public void v_setup_from_Database() {
        try {
            ResultSet set_IDs_from_Database = obj_Databasemanager_list.readEoAttr("projects", "s_unique_ID");
            while (set_IDs_from_Database.next()) {
                this.put(set_IDs_from_Database.getString(1), new ArrayList<>());
            }
        } catch (SQLException e) {
            cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
        }
    }


    public void v_arrangement() {


        this.i_sum_of_preferences = 0;

        this.i_amount_of_pupils = 0;

        for (String s_loop_object : this.keySet()
                ) {
            this.get(s_loop_object).clear();
        }


        CopyOnWriteArrayList<String> list_ID_pupils = new CopyOnWriteArrayList<>();

        try {
            ResultSet set_pupils_ID = obj_Databasemanager_list.readEoAttr("persons", "s_unique_ID");
            while (set_pupils_ID.next()) {
                list_ID_pupils.add(set_pupils_ID.getString(1));
            }
        } catch (SQLException e1) {
            cMain.v_update_Textarea_Status("\n FEHLER \n Die Datenbank konnte nicht korrekt arbeiten, sollte dies wiederholt auftreten bitte Benuterhandbuch zu Rate ziehen \n");
        }


        HashMap<String, Integer> objHash_Map_ID_amount_people = new HashMap<>();

        for (String s_loop_object : this.keySet()
                ) {
            objHash_Map_ID_amount_people.put(s_loop_object, 0);
        }


        for (int i_preference_counter = 0; i_preference_counter < cImports.iMaximalanzahl_Projekte; i_preference_counter++) {

            ArrayList<String> arrlist_ID_without_projects = new ArrayList<>();

            String s_random_pupil_ID = "";

            int i_random_counter;

            while (list_ID_pupils.size() > 0) {
                if (list_ID_pupils.size() > 1) {
                    i_random_counter = (int) (Math.random() * list_ID_pupils.size());
                    s_random_pupil_ID = list_ID_pupils.get(i_random_counter);
                } else {
                    s_random_pupil_ID = list_ID_pupils.get(0);
                }


                String s_prefence_specific_pupil = "";
                try {
                    ResultSet set_prefence_specific_id = obj_Databasemanager_list.readOneEntryAllAtributes("persons", "i_pref" + String.valueOf(i_preference_counter), s_random_pupil_ID);
                    s_prefence_specific_pupil = set_prefence_specific_id.getString(1);
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }

                int i_max_amount_project = 0;
                try {
                    ResultSet set_amount_specific_id = obj_Databasemanager_list.readOneEntryAllAtributes("projects", "i_max_pupils", s_prefence_specific_pupil);
                    i_max_amount_project = Integer.parseInt(set_amount_specific_id.getString(1));
                } catch (SQLException e2) {
                    e2.printStackTrace();
                }


                if (objHash_Map_ID_amount_people.get(s_prefence_specific_pupil) < i_max_amount_project) {
                    objHash_Map_ID_amount_people.replace(s_prefence_specific_pupil, objHash_Map_ID_amount_people.get(s_prefence_specific_pupil), objHash_Map_ID_amount_people.get(s_prefence_specific_pupil) + 1);
                    this.get(s_prefence_specific_pupil).add(s_random_pupil_ID);
                    i_sum_of_preferences = i_sum_of_preferences + i_preference_counter;
                    this.i_amount_of_pupils++;

                } else {
                    arrlist_ID_without_projects.add(s_random_pupil_ID);
                }
                list_ID_pupils.remove(s_random_pupil_ID);
            }

            list_ID_pupils.addAll(arrlist_ID_without_projects);

            if (i_preference_counter < cImports.iMaximalanzahl_Projekte - 1) {
                arrlist_ID_without_projects.clear();
            } else {
                this.get("-1").addAll(arrlist_ID_without_projects);
                this.i_amount_of_pupils = this.i_amount_of_pupils + arrlist_ID_without_projects.size();
            }
        }

    }
}

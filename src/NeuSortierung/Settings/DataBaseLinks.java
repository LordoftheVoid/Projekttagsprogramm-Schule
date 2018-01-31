package NeuSortierung.Settings;

import java.util.ArrayList;

/**
 * Created by Aaron on 31.01.2018.
 */
public class DataBaseLinks {


    public static  ArrayList <String> pupilValues= new ArrayList<>();
    public static String pupilTable = "persons";

    public  static ArrayList <String> projectValues= new ArrayList<>();
    public  static String projectTable = "projects";

    public  static ArrayList <String> linkValues= new ArrayList<>();


    public static void init(){

        pupilValues.add("s_unique_ID");
        pupilValues.add("s_sur_Name");
        pupilValues.add("s_pre_Name");
        pupilValues.add("s_grade");


        projectValues.add("s_unique_ID");
        projectValues.add("s_teacher_ID");




    }


}

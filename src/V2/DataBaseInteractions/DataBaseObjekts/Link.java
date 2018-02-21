package V2.DataBaseInteractions.DataBaseObjekts;

/**
 * Created by Aaron on 31.01.2018.
 */


public class Link {

    //Vorname, Nachname, Klasse, Projektnummer, Welche Wahl


    private String pupilHash;
    private String projectID;
    private boolean isValidProject;


    public Link(String pupilHash, String projectID) {
        //   super(pupilHash+projectID);
        if (projectID.equals("-1")) {
            this.isValidProject = false;
        }
        this.pupilHash = pupilHash;
        this.projectID = projectID;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getPupilHash() {
        return pupilHash;
    }

    public boolean isValidProject() {
        return isValidProject;
    }


    /*
    Der Link bekommt einen Schüler und ein Projekt als Argument
     */

    private void setValidProject(boolean validProject) {
        isValidProject = validProject;
    }


}

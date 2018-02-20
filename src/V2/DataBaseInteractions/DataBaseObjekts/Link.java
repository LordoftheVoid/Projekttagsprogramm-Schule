package V2.DataBaseInteractions.DataBaseObjekts;

/**
 * Created by Aaron on 31.01.2018.
 */



public class Link extends AbstractDataBaseRepresentation {

        //Vorname, Nachname, Klasse, Projektnummer, Welche Wahl

    private static int amountIdentityValues = 3;

    private static int amountInteraktionValues = 0;


    private String pupilHash;
    private String projectID;


    public String getProjectID() {
        return projectID;
    }


    public String getPupilHash() {
        return pupilHash;
    }

    public boolean isValidProject() {
        return isValidProject;
    }


    private  boolean isValidProject;

    private void setValidProject(boolean validProject) {
        isValidProject = validProject;
    }


    /*
    Der Link bekommt einen Schüler und ein Projekt als Argument
     */

    public Link(String pupilHash, String projectID){
        super(pupilHash+projectID,amountIdentityValues,amountInteraktionValues);
        if(projectID.equals("-1")){
            this.isValidProject = false;
        }
        this.pupilHash = pupilHash;
        this.projectID = projectID;
    }


    @Override
    public void generateDataBaseEntry() {

    }

    @Override
    public void genericSetter(String newValue, int index) {

    }

    @Override
    protected void savetoDataBase(String newValue, int index) {

    }




}

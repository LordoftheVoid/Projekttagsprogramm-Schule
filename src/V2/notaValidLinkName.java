package V2;

/**
 * Created by Aaron on 25.02.2018.
 */
public class notaValidLinkName {

    private boolean validProject = true;
    private int pupilPreferenceNumber;
    private String projectHash;
    private String pupilHash;


    public notaValidLinkName(String projectHash, String pupilHash) {
        this.projectHash = projectHash;
        this.pupilHash = pupilHash;
        if (projectHash.equals("-1")) {
            this.validProject = false;
        }
    }

    public String getProjectHash() {
        return projectHash;
    }


    public String getPupilHash() {
        return pupilHash;
    }


    public boolean isValidProject() {
        return validProject;
    }


    public int getPupilPreferenceNumber() {
        return pupilPreferenceNumber;
    }

    public void setPupilPreferenceNumber(int pupilPreferenceNumber) {
        this.pupilPreferenceNumber = pupilPreferenceNumber;
    }
}

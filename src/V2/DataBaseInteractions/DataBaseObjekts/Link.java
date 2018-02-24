package V2.DataBaseInteractions.DataBaseObjekts;

/**
 * Created by Aaron on 31.01.2018.
 */


public class Link extends AbstractDataBaseRepresentation{

        //Vorname, Nachname, Klasse, Projektnummer, Welche Wahl


        private String pupilHash;
        private String projectID;
        private boolean isValidProject;


        public Link(String pupilHash, String projectID) {
            super(pupilHash + projectID);
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


        private void setValidProject(boolean validProject) {
            isValidProject = validProject;
        }

        @Override
        public int getamountofDisplayableValues() {
            return 0;
        }

        @Override
        public String getTableReference() {
            return "Link";
        }

        @Override
        protected void savetoDataBase(int index, String newValue) {

        }


}


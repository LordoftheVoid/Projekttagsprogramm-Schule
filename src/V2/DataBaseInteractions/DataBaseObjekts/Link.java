package V2.DataBaseInteractions.DataBaseObjekts;


import V2.cMain;

import java.sql.SQLException;

/**
 * Created by Aaron on 31.01.2018.
 */


public class Link extends AbstractDataBaseRepresentation{

        //Vorname, Nachname, Klasse, Projektnummer, Welche Wahl



        private boolean isValidProject;


        public Link(String pseudoHash) {
            super(pseudoHash);
        }


        public boolean isValidProject() {
            return isValidProject;
        }


        private void setValidProject(boolean validProject) {
            isValidProject = validProject;
        }

        @Override
        public int getamountofDisplayableValues() {
            return 5;
        }

        @Override
        public String getTableReference() {
            return "Link";
        }

        @Override
        protected void savetoDataBase(int index, String newValue) {
            try {
                cMain.objDatabaseManagerGlobal.updateNonIDValues("Link" +
                        "", this.getHash(), index, newValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

       public static String generateHash( String pupilID,String projectID){
            return  pupilID +","+projectID;
        }
}


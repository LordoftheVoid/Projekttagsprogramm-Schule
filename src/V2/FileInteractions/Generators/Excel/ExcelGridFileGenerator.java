package V2.FileInteractions.Generators.Excel;


import V2.cMain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aaron on 25.02.2018.
 */
public class ExcelGridFileGenerator {

    private String parentDirectoryURL;

    public ExcelGridFileGenerator(String parentDirectoryURL) {
        this.parentDirectoryURL = parentDirectoryURL;
    }


    public ArrayList<ExcelGridFile> generateFilesFromDataBase(int indexToSeperateIntoFiles) {
        ArrayList<ExcelGridFile> listOfProducedFiles = new ArrayList<>();

        ArrayList<String> listOfLinkIDs = cMain.objDatabaseManagerGlobal.getEntryIDs("Link");

        HashMap<String, ExcelGridFile> filetowritetoHashMap = new HashMap<>();

        String newFileName = "";

        String[] columNames = new String[5];
        columNames[0] = "Nachname";
        columNames[0] = "Vorname";
        columNames[0] = "Klasse";
        columNames[0] = "Projektnummer";
        columNames[0] = "PräferenzNummer";
        switch (indexToSeperateIntoFiles) {
            case 2:
                newFileName = "Klasse ";
                break;

            case 3:
                newFileName = "Projekt ";
                break;
        }


        for (String listOfLinkID : listOfLinkIDs) {
            String separatingValue = cMain.objDatabaseManagerGlobal.getValuefromDataBase("Link", listOfLinkID, indexToSeperateIntoFiles);
            if (filetowritetoHashMap.keySet().contains(separatingValue)) {
                filetowritetoHashMap.get(separatingValue).addContent(cMain.objDatabaseManagerGlobal.getVisibleValuesfromDataBase("Link", listOfLinkID));
            } else {
                filetowritetoHashMap.put(separatingValue, new ExcelGridFile(parentDirectoryURL));
                filetowritetoHashMap.get(separatingValue).addContent(columNames);
                filetowritetoHashMap.get(separatingValue).addContent(cMain.objDatabaseManagerGlobal.getVisibleValuesfromDataBase("Link", listOfLinkID));
            }
            filetowritetoHashMap.get(separatingValue).setFileName(newFileName + separatingValue);
        }

        for (ExcelGridFile elementHashmap : filetowritetoHashMap.values()
                ) {
            listOfProducedFiles.add(elementHashmap);
        }


        return listOfProducedFiles;
    }

}

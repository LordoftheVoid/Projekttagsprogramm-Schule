package V2.FileInteractions.Generators.Excel;

import V2.Settings.Imports;

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

        ArrayList<String> listOfLinkIDs = Imports.objDatabaseManagerGlobal.getEntryIDs("Link");

        HashMap<String, ExcelGridFile> filetowritetoHashMap = new HashMap<>();

        String newFileName = "";
        switch (indexToSeperateIntoFiles) {
            case 2:
                newFileName = "Klasse ";
                break;

            case 3:
                newFileName = "Projekt ";
                break;
        }


        for (int listIndex = 0; listIndex < listOfLinkIDs.size(); listIndex++) {
            String separatingValue = Imports.objDatabaseManagerGlobal.getValuefromDataBase("Link", listOfLinkIDs.get(listIndex), indexToSeperateIntoFiles);
            if (filetowritetoHashMap.keySet().contains(separatingValue)) {
                filetowritetoHashMap.get(separatingValue).addContent(Imports.objDatabaseManagerGlobal.getVisibleValuesfromDataBase("Link", listOfLinkIDs.get(listIndex)));
            } else {
                filetowritetoHashMap.put(separatingValue, new ExcelGridFile(parentDirectoryURL));
                filetowritetoHashMap.get(separatingValue).addContent(Imports.objDatabaseManagerGlobal.getVisibleValuesfromDataBase("Link", listOfLinkIDs.get(listIndex)));
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

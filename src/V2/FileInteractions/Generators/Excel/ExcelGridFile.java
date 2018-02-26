package V2.FileInteractions.Generators.Excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Aaron on 25.02.2018.
 */
public class ExcelGridFile {

    String fileURl;
    private ArrayList<String[]> content;
    private String fileName;

    public ExcelGridFile(String fileURL) {
        this.fileURl = fileURL;
        this.content = new ArrayList<>();
    }

    public void addContent(String[] newRow) {
        this.content.add(newRow);
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void saveFiletoDisk() {


        File file = new File(fileURl + "\\" + fileName + ".xls");

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet s = wb.createSheet();
        HSSFRow arrRow[] = new HSSFRow[content.size()];
        HSSFCell arrCell[] = new HSSFCell[content.get(0).length];
        for (int rowIndex = 0; rowIndex < content.size(); rowIndex++) {
            arrRow[rowIndex] = s.createRow(rowIndex);
            for (int cellIndex = 0; cellIndex < content.get(0).length; cellIndex++) {
                arrCell[cellIndex] = arrRow[rowIndex].createCell(cellIndex);
                arrCell[cellIndex].setCellValue(content.get(rowIndex)[cellIndex]);
            }
        }

        // end deleted sheet
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            wb.write(out);
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}



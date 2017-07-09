package Version_1_Java.DateiSchnittstellen;

import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertProjekte;
import Version_1_Java.Objekte.ModifizierteSpeicherKlassen.cArrayListErweitertSchueler;
import Version_1_Java.Objekte.cProjekt;
import Version_1_Java.Objekte.cSchueler;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Aaron on 06.04.2017.
 */
public class cDateiLeser {



    /*

    Achtung, funktionier basically garnicht!


     */

    File Ausgangspunkt;

    ArrayList<Scanner> listSchuelerDateienScanner = new ArrayList<>();
    ArrayList<Scanner> listProjektDateienScanner = new ArrayList<>();
    ArrayList<HSSFWorkbook> listExcelDateien = new ArrayList<>();

    public cDateiLeser(File Beginn) {
        this.Ausgangspunkt = Beginn;
    }


    public void Dateiscan() {

        File[] files = this.Ausgangspunkt.listFiles();


        for (File Schleifenobjekt : files
                ) {
            if (Schleifenobjekt.getName().contains(".txt")) {
                if (Schleifenobjekt.getName().contains("Schueler")) {
                    try {
                        this.listSchuelerDateienScanner.add(new Scanner(Schleifenobjekt));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                if (Schleifenobjekt.getName().contains("Projekte")) {
                    try {
                        this.listProjektDateienScanner.add(new Scanner(Schleifenobjekt));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
            if (Schleifenobjekt.getName().contains(".xls")) {
                try {
                    this.listExcelDateien.add(new HSSFWorkbook(new FileInputStream(Schleifenobjekt)));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }


    }



    public cArrayListErweitertSchueler SchuelerAusExel()  {

        cArrayListErweitertSchueler objZwischenspeicher= new cArrayListErweitertSchueler();

        ArrayList<String > listsVorname= new ArrayList<>();

        ArrayList<String > listsNachname= new ArrayList<>();

        for (HSSFWorkbook Schleifenobjekt:listExcelDateien
             ) {
            HSSFSheet sheet = Schleifenobjekt.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            for (int r = 0; r < rows; r++) {
                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                for (int c = 0; c < row.getLastCellNum(); c++) {
                    HSSFCell cell = row.getCell(c);
                    if(c==0){
                        listsNachname.add(cell.getStringCellValue());
                    }else{
                        listsVorname.add(cell.getStringCellValue());
                    }
                }
            }
        }



        for (int i = 0; i <listsVorname.size() ; i++) {
            objZwischenspeicher.add(new cSchueler(listsVorname.get(i),listsNachname.get(i),-1,new int [cSchueler.iMaximalanzahl_Projekte],"1a"));
        }
        return  objZwischenspeicher;

    }






    public cArrayListErweitertProjekte erstellenProjektListeausDatei() {



        cArrayListErweitertProjekte objZwischenspeicher= new cArrayListErweitertProjekte();

        ArrayList<String > listLehrkuerzel= new ArrayList<>();

        ArrayList<Integer  > listProjektnummer= new ArrayList<>();

        ArrayList<Integer > listMaximalSchueleranzahl= new ArrayList<>();

        for (Scanner Schleifenobjekt : listProjektDateienScanner
                ) {
            while (Schleifenobjekt.hasNext()) {
                listProjektnummer.add( Schleifenobjekt.nextInt());
               listLehrkuerzel.add(Schleifenobjekt.next());
                listMaximalSchueleranzahl.add(Schleifenobjekt.nextInt());
            }
        }
        for (int i = 0; i <listLehrkuerzel.size() ; i++) {
            objZwischenspeicher.add(new cProjekt(listLehrkuerzel.get(i),listProjektnummer.get(i),listMaximalSchueleranzahl.get(i)));
        }

        return  objZwischenspeicher;

    }



    public cArrayListErweitertSchueler erstellenSchuelerListeausDatei()  {



        cArrayListErweitertSchueler objZwischenspeicher= new cArrayListErweitertSchueler();

        ArrayList<String > listsVorname= new ArrayList<>();

        ArrayList<String  > listsNachname= new ArrayList<>();

        ArrayList<String > listStringStufe= new ArrayList<>();

        ArrayList <int [] > listArrays = new ArrayList<>();


        for (Scanner Schleifenobjekt : listSchuelerDateienScanner
                ) {
            while(Schleifenobjekt.hasNext()) {
                listsVorname.add(Schleifenobjekt.next());
                listsNachname.add( Schleifenobjekt.next());
                listStringStufe.add(Schleifenobjekt.next());

                listArrays.add(new int[cSchueler.iMaximalanzahl_Projekte]);

                for(int i=0;i<cSchueler.iMaximalanzahl_Projekte;i++){

                    listArrays.get(listArrays.size()-1)[i]= Schleifenobjekt.nextInt();
                }
            }
        }


        for (int i = 0; i <listsVorname.size() ; i++) {
            objZwischenspeicher.add(new cSchueler(listsVorname.get(i),listsNachname.get(i),-1,listArrays.get(i),listStringStufe.get(i)));
        }

        return  objZwischenspeicher;

    }


    /*
    Einrichtung noch notwendig

     */

    public void schließen(){


    }






}

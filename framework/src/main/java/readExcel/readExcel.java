package readExcel;

import operation.flow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import utils.TryAndCatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

public class readExcel {
    public Screen screen = new Screen();
    public flow test;


    @Before
    public void setUp() throws IOException {


        this.test = new flow(this.screen);


    }

    @Test
    public void framework() throws IOException, InterruptedException, FindFailed {
        //open a file using FileInputStream
        String filePath = "test.xlsx";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //read the file as an excel file
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //get the first sheet from workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        //get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();

        //read each row of excel file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            //For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();


                //check the cell type and format accordingly
                switch (cell.getStringCellValue().toUpperCase(Locale.ROOT)) {
                    case "CLICK":
                        //use a loop to get the value of  colum name
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();

                            System.out.println(cell1.getStringCellValue());
                            //Find text using sikuli and do click  in a region of the screen


                        }


                        break;
                    case "GETTEXT":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            System.out.println(cell1.getStringCellValue());
                            System.out.println("ok");
                        }

                        break;
                   /* case "CREATEREGIONAPP":
                        while (cellIterator.hasNext()) {
                            //create an array with all the values of the cell
                            Cell cell1 = cellIterator.next();
                            System.out.println(cell1.getStringCellValue());
                            String[] array = cell1.getStringCellValue().split(",");
                            //create a region of the screen
                            Region region = new Region(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]));





                            Cell cell1 = cellIterator.next();
                            System.out.println(cell1.getStringCellValue());
                            TryAndCatch.createRegionApp();
                        }
                        break;

                    */
                    case "TYPEKEYS":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            //if the cell is string, then get the value
                            if (cell1.getCellType() == CellType.STRING) {
                                System.out.println(cell1.getStringCellValue());
                                this.test.typeKeys(cell1.getStringCellValue());
                            } else if (cell1.getCellType() == CellType.NUMERIC) {
                                System.out.println(cell1.getNumericCellValue());
                                this.test.typeKeys(String.valueOf(cell1.getNumericCellValue()));
                            }

                        }
                        break;
                    case "WAIT":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            System.out.println(cell1.getNumericCellValue());
                            this.test.wait((int) cell1.getNumericCellValue());
                        }
                        break;
                    case "CLICKBYTEXT":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1] + " " + stringCellValues[2] + " " + stringCellValues[3] + " " + stringCellValues[4]);
                            this.test.clickByText(stringCellValues[0], Integer.parseInt(array[3]), Integer.parseInt(array[4]));
                        }
                        break;

                    case "CREATEREGION":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1] + " " + stringCellValues[2] + " " + stringCellValues[3] + " " + stringCellValues[4]);
                            this.test.createRegion(stringCellValues[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]));


                        }
                        break;
                    case "CREATESUBREGION":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1] + " " + stringCellValues[2] + " " + stringCellValues[3] + " " + stringCellValues[4] + " " + stringCellValues[5]);
                            this.test.createSubRegion(stringCellValues[0], stringCellValues[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]));
                        }
                        break;
                    case "CREATEREGIONAPPBYNAME":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1]);
                            this.test.createRegionAppByName(stringCellValues[0], stringCellValues[1]);
                        }
                        break;
                    case "CREATEREGIONAPP":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            System.out.println(cell1.getStringCellValue());
                            this.test.createRegionApp(cell1.getStringCellValue());
                        }
                        break;
                    case "CREATESUBREGIONAPP":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1] + " " + stringCellValues[2] + " " + stringCellValues[3] + " " + stringCellValues[4] + " " + stringCellValues[5]);
                            this.test.createSubRegionApp(stringCellValues[0], stringCellValues[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]));

                        }
                        break;
                    case "CLICKBYTEXTINREGION":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1] + " " + stringCellValues[2] + " " + stringCellValues[3]);
                            this.test.clickByTextInRegion(stringCellValues[0], stringCellValues[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]));

                        }
                        break;
                    case "REGIONHASTEXT":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1] + " " + stringCellValues[2] + " " + stringCellValues[3] );
                            this.test.regionHasText(stringCellValues[0], stringCellValues[1], Integer.parseInt(stringCellValues[2]), Integer.parseInt(stringCellValues[3]));
                        }
                        break;
                    case "IFREGIONHASTEXTTYPEKEYS":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            //Add to array stringCellValues the array but Exclude first element of array
                            String[] stringCellValuesActions = Arrays.copyOfRange(array, 2, array.length);
                            System.out.println(stringCellValues[0] + " " + stringCellValuesActions);
                            this.test.ifTypesKeys(stringCellValues[0], stringCellValues[1],stringCellValuesActions);
                        }
                        break;
                    case "FINDWORDANDCLICK":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1]);
                            this.test.findWordAndClick(stringCellValues[0], stringCellValues[1]);
                        }
                        break;

                    case "FINDWORDANDCREATEAREGION":
                        while (cellIterator.hasNext()) {
                            Cell cell1 = cellIterator.next();
                            String[] array = cell1.getStringCellValue().split(",");
                            String[] stringCellValues = array;
                            System.out.println(stringCellValues[0] + " " + stringCellValues[1]);
                            this.test.findWordAndCreateRegion(stringCellValues[0], stringCellValues[1]);
                        }
                        break;


                }
                System.out.println();
            }
        }


    }
}
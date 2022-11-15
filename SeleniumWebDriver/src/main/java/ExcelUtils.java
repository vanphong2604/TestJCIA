import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    public static Object[][] getTableArray(String FilePath,Integer col, String SheetName) throws Exception {

        String[][] tabArray = null;

        //Create an object of File class to open xlsx file

        File file =    new File(FilePath);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook guru99Workbook = null;

        //Check condition if the file is xlsx file
        guru99Workbook = new XSSFWorkbook(inputStream);

        //Read sheet inside the workbook by its name

        Sheet guru99Sheet = guru99Workbook.getSheet(SheetName);

        //Find number of rows in excel file

        int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum() + 1;
        System.out.println(rowCount);
        tabArray = new String[rowCount - 2][col-1];

        //Create a loop over all the rows of excel file to read it

        for (int i = 2; i < rowCount; i++) {

            org.apache.poi.ss.usermodel.Row row = guru99Sheet.getRow(i);

            //Create a loop to print cell values in a row

            for (int j = 1; j < col; j++) {

                //Print Excel data in console
                if (row.getCell(j) != null) {
                    tabArray[i-2][j-1] = row.getCell(j).getStringCellValue();
                } else {
                    tabArray[i-2][j-1] = "";
                }

            }
        }
        System.out.println(tabArray.length);

        return(tabArray);

    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {


        Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

        int dataType = Cell.getColumnIndex();

        if (dataType == 4) {

            return "";

        } else {

            String CellData = Cell.getStringCellValue();

            return CellData;

        }
    }

    }
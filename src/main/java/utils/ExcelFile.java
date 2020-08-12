package utils;

import com.google.errorprone.annotations.Var;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to read/write excel files
 */
public class ExcelFile {

    /**
     * Reads a sheet on excel file and return
     *
     * @param filename  Name of the file to read
     * @param sheetname Name of the sheet in excel file to read
     * @return returns sheet data in List of Maps format
     * @throws IOException Exception is thrown if problem reading file
     */
    public static List readSheet(String filename, String sheetname) throws IOException {
        List<Map<String, String>> sheetVals = new ArrayList<Map<String, String>>();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filename));
        XSSFSheet sheet = workbook.getSheet(sheetname);
        XSSFRow firstRow = sheet.getRow(0);
        for (int row = 1; row <= sheet.getLastRowNum(); row++) {
            Map<String, String> hashMap = new HashMap<String, String>();
            for (int col = 0; col < firstRow.getLastCellNum(); col++) {


                XSSFRow currRow = sheet.getRow(row);

                hashMap.put(
                        new DataFormatter().formatCellValue(firstRow.getCell(col)).toString(),
                        new DataFormatter().formatCellValue(currRow.getCell(col)).toString()
                );

            }
            System.out.println(hashMap);
            sheetVals.add(hashMap);
        }
        workbook.close();
        return sheetVals;
    }

    private static void writeToFile(XSSFWorkbook workbook, String outputFile) {
        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function takes data and writes it to specified sheet on excel file
     * @param data Data to write to sheet
     * @param outputFile Path of file to be modified
     * @param sheetName Name of sheet to be modified
     * @throws IOException exception is thrown if unable to read/write the file
     */
    public static void writeToSheet(List<Map<String, String>> data, String outputFile, String sheetName) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(outputFile));
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row titleRow = sheet.createRow(0);
        int rowCount = 0;
        int colCount;
        for (Map<String, String> item : data) {

            Row row = sheet.createRow(rowCount + 1);
            colCount = 0;
            for (String key : item.keySet()) {
                if (rowCount == 0) {
                    Cell titleCell = titleRow.createCell(colCount);
                    titleCell.setCellValue((String) key);
                }

                Cell cell = row.createCell(colCount);
                cell.setCellValue((String) item.get(key));
                colCount++;
            }
            rowCount++;
        }
        writeToFile(workbook,outputFile);
    }
}

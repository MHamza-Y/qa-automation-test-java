package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelFile {

    public List readSheet(String filename, String sheetname) throws IOException {
        List <Map<String,String>> sheetVals = new ArrayList<Map<String, String>>();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filename));
        XSSFSheet sheet = workbook.getSheet(sheetname);
        int rows = sheet.getLastRowNum();
        XSSFRow firstRow = sheet.getRow(0);
        for(int row = 1;row<=sheet.getLastRowNum();row++){
            Map<String,String> hashMap = new HashMap<String, String>();
            for(int col = 0;col<firstRow.getLastCellNum();col++) {


                XSSFRow currRow = sheet.getRow(row);

                hashMap.put(
                        new DataFormatter().formatCellValue(firstRow.getCell(col)).toString(),
                        new DataFormatter().formatCellValue(currRow.getCell(col)).toString()
                );

            }
            System.out.println(hashMap);
            sheetVals.add(hashMap);
        }
        return  sheetVals;
    }

}

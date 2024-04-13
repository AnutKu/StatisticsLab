package read;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelReader {
    public List<List<Double>> readFromExcel(String file, String name) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet myExcelSheet = myExcelBook.getSheet(name);
        if (myExcelSheet == null) {
            throw new IllegalArgumentException("Лист с таким названием не найден в книге Excel.");
        }
        int lastRowNum = myExcelSheet.getLastRowNum();
        List<List<Double>> columnValues = new ArrayList<>();
        if (lastRowNum >= 0) {
            int totalColumns = myExcelSheet.getRow(0).getLastCellNum();
            for (int columnIndex = 0; columnIndex < totalColumns; columnIndex++) {
                List<Double> columnData = new ArrayList<>();
                for (int rowIndex = 1; rowIndex <= lastRowNum; rowIndex++) {
                    Row row = myExcelSheet.getRow(rowIndex);
                    if (row != null && row.getCell(columnIndex) != null) {
                        double cellValue = row.getCell(columnIndex).getNumericCellValue();
                        columnData.add(cellValue);
                    }
                }

                columnValues.add(columnData);
            }
        }
        return columnValues;
    }
}
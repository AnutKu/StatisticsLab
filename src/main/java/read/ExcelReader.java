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
    public List<List<Double>> readFromExcel(String file, int sheetNum) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet myExcelSheet = myExcelBook.getSheet("Вариант " + sheetNum);
        if (myExcelSheet == null) {
            throw new IllegalArgumentException("Лист с вариантом " + sheetNum + " не найден в книге Excel.");
        }

        List<Double> listX = new ArrayList<>();
        List<Double> listY = new ArrayList<>();
        List<Double> listZ = new ArrayList<>();

        for (int rowIndex = 1; rowIndex <= myExcelSheet.getLastRowNum(); rowIndex++) {
            Row row = myExcelSheet.getRow(rowIndex);
            if (row != null && row.getCell(0) != null && row.getCell(1) != null && row.getCell(2) != null) {
                double x = row.getCell(0).getNumericCellValue();
                double y = row.getCell(1).getNumericCellValue();
                double z = row.getCell(2).getNumericCellValue();

                listX.add(x);
                listY.add(y);
                listZ.add(z);
            }
        }
        return Arrays.asList(listX, listY, listZ);
    }
}
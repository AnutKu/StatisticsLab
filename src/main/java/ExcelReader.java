import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private static List<Double> listX = new ArrayList<>();
    private static List<Double> listY = new ArrayList<>();
    private static List<Double> listZ = new ArrayList<>();

    public static void readFromExcel(String file, int sheetNum) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet myExcelSheet = myExcelBook.getSheet("Вариант " + sheetNum);
        if (myExcelSheet == null) {
            throw new IllegalArgumentException("Лист с вариантом " + sheetNum + " не найден в книге Excel.");
        }

        listX.clear();
        listY.clear();
        listZ.clear();

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
        myExcelBook.close();
    }

    public static List<Double> getColumnX() {
        return listX;
    }

    public static List<Double> getColumnY() {
        return listY;
    }
    public static List<Double> getColumnZ() {
        return listZ;
    }
}
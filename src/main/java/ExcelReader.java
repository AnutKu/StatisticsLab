import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static void readFromExcel(String file, Integer Sheet_num) throws IOException{
        XSSFWorkbook myExcelBook = new XSSFWorkbook((new FileInputStream(file)));

    }
}

package write;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import calculatestatistics.*;

public class ExcelWriter {
    public static void writeExcelData(String filename) {
        // Ваши списки значений
        List<Double> geometricMeans = GeometricMean.getGeomtericMean();
        List<Double> means = Mean.getMean();
        List<Double> anotherSD = StandardDeviation.getStandardDeviation();
        List<Double> range = Range.getRange();
        List<Integer> quantity = QuantityElem.getQuantity();
        List<Double> cv = CoefficientVariation.getCV();
        List<Double> variance = Variance.getVariance();
        List<Double> minn = Minimum.getMin();
        List<Double> maxx = Maximum.getMax();
        List<String> intervals = ConfidenceInterval.getInterval();

        // Создаем новый документ Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            // Записываем значения в документ
            writeListToSheet(sheet, 0, geometricMeans);
            writeListToSheet(sheet, 1, means);
            writeListToSheet(sheet, 2, anotherSD);
            writeListToSheet(sheet, 3, range);
            writeListToSheet(sheet, 4, quantity);
            writeListToSheet(sheet, 5, cv);
            writeListToSheet(sheet, 6, variance);
            writeListToSheet(sheet, 7, minn);
            writeListToSheet(sheet, 8, maxx);
            writeListToSheet(sheet, 9, intervals);

            // Сохраняем документ
            try (FileOutputStream fileOut = new FileOutputStream(filename + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для записи списка значений в строку документа Excel
    private static void writeListToSheet(Sheet sheet, int rowNum, List<?> values) {
        Row row = sheet.createRow(rowNum);
        int cellNum = 0;
        for (Object value : values) {
            Cell cell = row.createCell(cellNum++);
            if (value instanceof Double) {
                cell.setCellValue((Double) value);
            } else if (value instanceof String) {
                cell.setCellValue((String) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((Integer) value);
            }
        }
    }
}

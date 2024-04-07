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
        List<Double> SD = StandardDeviation.getStandardDeviation();
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
            writeListToSheet(sheet, "Среднее геометрическое", 0, geometricMeans);
            writeListToSheet(sheet, "Среднее арифметическое", 1, means);
            writeListToSheet(sheet, "Стандартное отклонение",2, SD);
            writeListToSheet(sheet, "Размах",3, range);
            writeListToSheet(sheet, "Количество элементов", 4, quantity);
            writeListToSheet(sheet, "Коэффициент вариации", 5, cv);
            writeListToSheet(sheet, "Доверительные интервалы для мат.ожидания",6, intervals);
            writeListToSheet(sheet, "Дисперсия", 7, variance);
            writeListToSheet(sheet, "Минимум",8, minn);
            writeListToSheet(sheet, "Максимум",9, maxx);
            for (int i = 0; i <= 9; i++) {
                sheet.autoSizeColumn(i);
            }


            // Сохраняем документ
            try (FileOutputStream fileOut = new FileOutputStream(filename + ".xlsx")) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для записи списка значений в строку документа Excel
    private static void writeListToSheet(Sheet sheet, String header, int rowNum, List<?> values) {
        Row row = sheet.createRow(rowNum);
        CellStyle style = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        style.setFont(font);
        row.createCell(0).setCellValue(header);
        row.getCell(0).setCellStyle(style);
        int cellNum = 1;
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
        // Устанавливаем стиль для остальных ячеек в строке
        CellStyle centerAlignStyle = sheet.getWorkbook().createCellStyle();
        centerAlignStyle.setAlignment(HorizontalAlignment.CENTER);
        for (int i = 1; i <= values.size(); i++) {
            row.getCell(i).setCellStyle(centerAlignStyle);
        }
    }
}

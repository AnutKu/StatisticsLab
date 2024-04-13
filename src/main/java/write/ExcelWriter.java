package write;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import calculatestatistics.*;

public class ExcelWriter {
    public static void write(Map<String, List<?>> allResults, String filename) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            int rowNum = 0;
            for (Map.Entry<String, List<?>> entry : allResults.entrySet()) {
                String header = entry.getKey();
                List<?> values = entry.getValue();
                writeListToSheet(sheet, header, rowNum++, values);
            }

            for (int i = 0; i < allResults.size(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Сохраняем документ
            try (FileOutputStream fileOut = new FileOutputStream(filename + ".xlsx")) {
                workbook.write(fileOut);
            }
            catch (IOException e){throw new IOException("Ошибка при сохранении", e);}
        } catch (IOException e) {
            throw new IOException("Ошибка при экспорте", e);
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
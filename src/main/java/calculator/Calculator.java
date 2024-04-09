package calculator;

import calculatestatistics.*;
import read.ExcelReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calculator {
    private Map<String, statystics> statObjects = new HashMap<>();
    private Map<String, List<?>> allResults = new HashMap<>();
    private List<List<Double>> columns;

    public Calculator() throws IOException {
        // Инициализация объектов статистических калькуляторов
        statObjects.put("Стандартное отклонение", new StandardDeviation());
        statObjects.put("Доверительный интервал для мат ожидания", new ConfidenceInterval());
        statObjects.put("Количество элементов", new QuantityElem());
        statObjects.put("Среднее значение", new Mean());
        statObjects.put("Максимальное значение", new Maximum());
        statObjects.put("Минимальное значение", new Minimum());
        statObjects.put("Среднее геометрическое", new GeometricMean());
        statObjects.put("Коэффициент вариации", new CoefficientVariation());
        statObjects.put("Дисперсия", new Variance());
        statObjects.put("Размах", new Range());
    }

    public void read(String file, int sheetNum) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        columns = excelReader.readFromExcel(file, sheetNum);
        }


    public void calculateAll() {
        for (Map.Entry<String, statystics> entry : statObjects.entrySet()) {
            String name = entry.getKey();
            statystics calc = entry.getValue();
            try {
                calc.calculate(columns);
                allResults.put(name, calc.getResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    public Map<String, List<?>> getAllResults() {
        System.out.println(allResults);
        return allResults;
    }

}

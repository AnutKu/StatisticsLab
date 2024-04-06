package calculatestatistics;

import org.apache.commons.math3.stat.StatUtils;
import read.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class QuantityElem {
    private static List<Integer> result = new ArrayList<>();
    public static void calculateRange() {
        result = new ArrayList<>();
        List<List<Double>> columns = ExcelReader.getColumns();
        for (List<Double> column : columns) {
            Integer quantity = column.size();
            result.add(quantity);
        }
    }
    public static List<Integer> getQuantity(){
        return result;
    }
}
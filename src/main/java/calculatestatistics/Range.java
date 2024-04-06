package calculatestatistics;

import org.apache.commons.math3.stat.StatUtils;
import read.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class Range {
    private static List<Double> result = new ArrayList<>();
    public static void calculateRange() {
        result = new ArrayList<>();
        List<List<Double>> columns = ExcelReader.getColumns();
        for (List<Double> column : columns) {
            double range = StatUtils.max(column.stream().mapToDouble(Double::doubleValue).toArray()) - StatUtils.min(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(range);
        }
    }

    public static List<Double> getRange(){
        return result;
    }
}


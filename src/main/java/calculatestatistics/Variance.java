package calculatestatistics;

import org.apache.commons.math3.stat.StatUtils;
import read.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class Variance {
    private static List<Double> result = new ArrayList<>();
    public static void calculateVariance() {
        List<List<Double>> columns = ExcelReader.getColumns();
        result = new ArrayList<>();
        for (List<Double> column : columns) {
            double variance = StatUtils.variance((column.stream().mapToDouble(Double::doubleValue).toArray()));
            result.add(variance);
        }
    }

    public static List<Double> getVariance(){
        return result;
    }
}

package calculatestatistics;

import org.apache.commons.math3.stat.StatUtils;
import read.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class StandardDeviation {
    private static List<Double> result = new ArrayList<>();
    public static void calculateStandardDeviation() {
        List<List<Double>> columns = ExcelReader.getColumns();
        result = new ArrayList<>();
        for (List<Double> column : columns) {
            double sd = StatUtils.variance((column.stream().mapToDouble(Double::doubleValue).toArray()));
            result.add(Math.sqrt(sd));
        }
    }

    public static List<Double> getStandardDeviation(){
        return result;
    }
}


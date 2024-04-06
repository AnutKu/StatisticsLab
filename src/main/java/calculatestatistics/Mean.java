package calculatestatistics;

import org.apache.commons.math3.stat.StatUtils;
import java.util.ArrayList;
import java.util.List;
import read.ExcelReader;
public class Mean {
    private static List<Double> result = new ArrayList<>();
    public static void calculateMean() {
        result = new ArrayList<>();
        List<List<Double>> columns = ExcelReader.getColumns();
        for (List<Double> column : columns) {
            double mean = StatUtils.mean(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(mean);
        }
    }

    public static List<Double> getMean(){
        return result;
    }
}
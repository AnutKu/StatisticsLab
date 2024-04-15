package calculatestatistics;

import org.apache.commons.math3.stat.StatUtils;
import read.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class Minimum implements statystics {
    private List<Double> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        for (List<Double> column : columns) {
            double minimum = StatUtils.min(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(minimum);
        }
    }

    @Override
    public List<Double> getResult() {
        return result;
    }
}

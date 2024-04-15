package calculatestatistics;

import org.apache.commons.math3.stat.StatUtils;
import read.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class Range implements statystics {

    private static List<Double> result = new ArrayList<>();

    @Override
    public void calculate(List<List<Double>> columns) {
        for (List<Double> column : columns) {
            double range = StatUtils.max(column.stream().mapToDouble(Double::doubleValue).toArray()) - StatUtils.min(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(range);
        }
    }
    @Override
    public List<Double> getResult() {
        {
            return result;
    }
}}


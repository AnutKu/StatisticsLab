package calculatestatistics;

import read.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class CoefficientVariation {
    private static List<Double> result = new ArrayList<>();
    public static void calculateRange() {
        result = new ArrayList<>();
        List<List<Double>> columns = ExcelReader.getColumns();
        Integer ind = 0;
        ArrayList<Double> SD = (ArrayList<Double>) StandardDeviation.getStandardDeviation();
        ArrayList<Double> mean = (ArrayList<Double>) Mean.getMean();

        for (List<Double> column : columns) {
            Double cv = SD.get(ind) / mean.get(ind);
            ind = ind + 1;
            result.add(cv);

        }
}
    public static List<Double> getCV(){
        return result;
    }
}

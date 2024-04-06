package calculatestatistics;

import org.apache.commons.math3.stat.StatUtils;
import java.util.ArrayList;
import java.util.List;
import read.ExcelReader;
public class GeometricMean {
    private static List<Double> result = new ArrayList<>();
    public static void calculateGeometricMean() {
        List<List<Double>> columns = ExcelReader.getColumns();
        for (List<Double> column : columns) {
            double geometricMean = StatUtils.geometricMean(column.stream().mapToDouble(Double::doubleValue).toArray());
            result.add(geometricMean);
        }
    }

    public static List<Double> getGeomtericMean(){
        return result;
    }
}
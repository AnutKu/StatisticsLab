package calculatestatistics;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;
import read.ExcelReader;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class ConfidenceInterval {
    private static List<String> result = new ArrayList<>();
    public static void calculateInterval() {
        result = new ArrayList<>();
        List<List<Double>> columns = ExcelReader.getColumns();
        Integer ind = 0;
        List<Double> mean = Mean.getMean();
        List<Double> sd = StandardDeviation.getStandardDeviation();
        List<Integer> size = QuantityElem.getQuantity();
        double confidenceLevel = 0.95;
        for (List<Double> column : columns) {
            NormalDistribution normalDistribution = new NormalDistribution();
            double quant = normalDistribution.inverseCumulativeProbability(1 - (1 - confidenceLevel) / 2);
            double marginOfError = quant * (sd.get(ind) / size.get(ind));
            double lowerBound = mean.get(ind) - marginOfError;
            double upperBound = mean.get(ind) + marginOfError;
            result.add("[" + lowerBound + "; " + upperBound + "]");
        }
    }

    public static List<String> getInterval(){
        return result;
    }
}


package org.codeWithGA.NeuralNetwork.titanic;
import org.codeWithGA.NeuralNetwork.data.DataSet;
import java.util.ArrayList;
import java.util.List;

public class TitanicPreprocessor {
    public static DataSet prepare(DataSet rawData) {
        List<double[]> processedFeatures = new ArrayList<>();

        for (double[] row : rawData.getFeatures()) {

            double[] x = new double[6];
            x[0] = row[1];
            x[1] = row[3];
            x[2] = (row[4] == 0.0) ? 29.0 : row[4];
            x[3] = row[5];
            x[4] = row[6];
            x[5] = row[8];

            processedFeatures.add(x);
        }
        return new DataSet(processedFeatures, rawData.getLabels());
    }
}
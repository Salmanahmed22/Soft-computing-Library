package org.codeWithGA.NeuralNetwork.data;

import java.util.*;

public class Normalizer {

    public static DataSet minMax(DataSet data) {
        int nFeatures = data.getFeatures().get(0).length;
        double[] min = new double[nFeatures];
        double[] max = new double[nFeatures];

        Arrays.fill(min, Double.POSITIVE_INFINITY);
        Arrays.fill(max, Double.NEGATIVE_INFINITY);

        for (double[] x : data.getFeatures()) {
            for (int i = 0; i < nFeatures; i++) {
                min[i] = Math.min(min[i], x[i]);
                max[i] = Math.max(max[i], x[i]);
            }
        }

        List<double[]> normX = new ArrayList<>();
        for (double[] x : data.getFeatures()) {
            double[] nx = new double[nFeatures];
            for (int i = 0; i < nFeatures; i++) {
                nx[i] = (x[i] - min[i]) / (max[i] - min[i] + 1e-8);
            }
            normX.add(nx);
        }

        return new DataSet(normX, data.getLabels());
    }
}

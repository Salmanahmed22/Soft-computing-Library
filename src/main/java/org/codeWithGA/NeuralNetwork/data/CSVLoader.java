package org.codeWithGA.NeuralNetwork.data;
import java.io.*;
import java.util.*;

public class CSVLoader {

    public static DataSet load(String path, boolean hasHeader, int labelIndex) throws IOException {
        List<double[]> features = new ArrayList<>();
        List<Double> labels = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            if (hasHeader) br.readLine();

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                double y = parseValue(tokens[labelIndex]);

                double[] x = new double[tokens.length - 1];
                int featureIdx = 0;
                for (int i = 0; i < tokens.length; i++) {
                    if (i == labelIndex) continue;
                    x[featureIdx++] = parseValue(tokens[i]);
                }

                features.add(x);
                labels.add(y);
            }
        }
        return new DataSet(features, labels);
    }

    private static double parseValue(String val) {
        if (val == null || val.isEmpty()) return 0.0;
        val = val.trim().toLowerCase();

        if (val.equals("male")) return 0.0;
        if (val.equals("female")) return 1.0;
        if (val.equals("c")) return 1.0;
        if (val.equals("q")) return 2.0;
        if (val.equals("s")) return 0.0;

        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

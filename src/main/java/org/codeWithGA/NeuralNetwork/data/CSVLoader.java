package org.codeWithGA.NeuralNetwork.data;
import java.io.*;
import java.util.*;

public class CSVLoader {

    public static DataSet load(String path, boolean hasHeader) throws IOException {
        List<double[]> features = new ArrayList<>();
        List<Double> labels = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            if (hasHeader)
                br.readLine();

            while ((line = br.readLine()) != null) {
                // Split by comma, but ignore commas inside quotes
                String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                double[] x = new double[tokens.length - 1];
                for (int i = 0; i < tokens.length - 1; i++) {
                    x[i] = parseSafe(tokens[i]);
                }

                double y = parseSafe(tokens[tokens.length - 1]);

                features.add(x);
                labels.add(y);
            }
        }

        return new DataSet(features, labels);
    }

    private static double parseSafe(String value) {
        if (value == null || value.isEmpty())
            return 0.0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}

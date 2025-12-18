package org.codeWithGA.NeuralNetwork.data;

import java.util.*;

public class TrainTestSplit {

    public static DataSet[] split(DataSet data, double trainRatio, long seed) {
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) idx.add(i);

        Collections.shuffle(idx, new Random(seed));

        int trainSize = (int) (trainRatio * data.size());

        List<double[]> xTrain = new ArrayList<>();
        List<Double> yTrain = new ArrayList<>();
        List<double[]> xTest = new ArrayList<>();
        List<Double> yTest = new ArrayList<>();

        for (int i = 0; i < idx.size(); i++) {
            int id = idx.get(i);
            if (i < trainSize) {
                xTrain.add(data.getFeatures().get(id));
                yTrain.add(data.getLabels().get(id));
            } else {
                xTest.add(data.getFeatures().get(id));
                yTest.add(data.getLabels().get(id));
            }
        }

        return new DataSet[]{
                new DataSet(xTrain, yTrain),
                new DataSet(xTest, yTest)
        };
    }
}

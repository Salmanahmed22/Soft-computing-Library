package org.codeWithGA.NeuralNetwork.data;

import java.util.List;

public class DataSet {

    private List<double[]> features;
    private List<Double> labels;

    public DataSet(List<double[]> features, List<Double> labels) {
        this.features = features;
        this.labels = labels;
    }

    public List<double[]> getFeatures() {
        return features;
    }

    public List<Double> getLabels() {
        return labels;
    }

    public int size() {
        return features.size();
    }
}

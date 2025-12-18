package org.codeWithGA.NeuralNetwork.core;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private List<Layer> layers = new ArrayList<>();

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public double[] forward(double[] input) {
        double[] current = input;
        for (Layer layer : layers) {
            current = layer.forward(current);
        }
        return current;
    }

    public void backward(double[] gradOutput) {
        double[] currentGrad = gradOutput;
        for (int i = layers.size() - 1; i >= 0; i--) {
            currentGrad = layers.get(i).backward(currentGrad);
        }
    }

    public List<Layer> getLayers() {
        return layers;
    }
}
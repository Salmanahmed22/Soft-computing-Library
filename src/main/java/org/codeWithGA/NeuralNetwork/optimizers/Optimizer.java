package org.codeWithGA.NeuralNetwork.optimizers;

public interface Optimizer {
    void update(double[][] weights, double[][] gradW);
    void update(double[] biases, double[] gradB);
}
package org.codeWithGA.NeuralNetwork.initializers;

public interface WeightInitializer {
    double[][] initialize(int rows, int cols);
}
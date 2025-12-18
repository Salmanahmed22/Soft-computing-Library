package org.codeWithGA.NeuralNetwork.losses;

public interface LossFunction {
    double forward(double[] yTrue, double[] yPred);
    double[] backward(double[] yTrue, double[] yPred);
}
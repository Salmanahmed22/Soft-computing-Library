package org.codeWithGA.NeuralNetwork.activations;

public interface ActivationFunction {
    double forward(double x);
    double backward(double x);  // derivative
}
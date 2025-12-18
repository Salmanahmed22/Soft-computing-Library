package org.codeWithGA.NeuralNetwork.activations;

public class Sigmoid implements ActivationFunction {

    @Override
    public double forward(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    @Override
    public double backward(double x) {
        double s = forward(x);
        return s * (1 - s);
    }
}
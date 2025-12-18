package org.codeWithGA.NeuralNetwork.activations;

public class ReLU implements ActivationFunction {

    @Override
    public double forward(double x) {
        return Math.max(0, x);
    }

    @Override
    public double backward(double x) {
        return x > 0 ? 1.0 : 0.0;
    }
}
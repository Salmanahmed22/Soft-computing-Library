package org.codeWithGA.NeuralNetwork.activations;

public class Linear implements ActivationFunction {

    @Override
    public double forward(double x) {
        return x;
    }

    @Override
    public double backward(double x) {
        return 1.0;
    }
}
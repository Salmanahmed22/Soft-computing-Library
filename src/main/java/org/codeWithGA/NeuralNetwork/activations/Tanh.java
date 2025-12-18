package org.codeWithGA.NeuralNetwork.activations;

public class Tanh implements ActivationFunction {

    @Override
    public double forward(double x) {
        return Math.tanh(x);
    }

    @Override
    public double backward(double x) {
        double t = Math.tanh(x);
        return 1 - t * t;
    }
}
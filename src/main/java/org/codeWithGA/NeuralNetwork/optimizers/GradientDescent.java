package org.codeWithGA.NeuralNetwork.optimizers;

public class GradientDescent implements Optimizer {
    final private double learningRate;
    final private double momentum;
    private double[][] pastGrandW;
    private double[] pastGradB;

    public GradientDescent(double learningRate) {
        this(learningRate, 0.0);
    }

    public GradientDescent(double learningRate, double momentum) {
        this.learningRate = learningRate;
        this.momentum = momentum;
    }

    @Override
    public void update(double[][] weights, double[][] gradW) {
        if (pastGrandW == null || pastGrandW.length != weights.length || pastGrandW[0].length != weights[0].length) {
            pastGrandW = new double[weights.length][weights[0].length];
        }
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                pastGrandW[i][j] = momentum * pastGrandW[i][j] - learningRate * gradW[i][j];
                weights[i][j] += pastGrandW[i][j];
            }
        }
    }

    @Override
    public void update(double[] biases, double[] gradB) {
        if (pastGradB == null || pastGradB.length != biases.length) {
            pastGradB = new double[biases.length];
        }
        for (int i = 0; i < biases.length; i++) {
            pastGradB[i] = momentum * pastGradB[i] - learningRate * gradB[i];
            biases[i] += pastGradB[i];
        }
    }
}
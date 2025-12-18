package org.codeWithGA.NeuralNetwork.optimizers;

public class GradientDescent implements Optimizer {
    private double learningRate;
    private double momentum;
    private double[][] velocityW;
    private double[] velocityB;

    public GradientDescent(double learningRate) {
        this(learningRate, 0.0);
    }

    public GradientDescent(double learningRate, double momentum) {
        this.learningRate = learningRate;
        this.momentum = momentum;
    }

    @Override
    public void update(double[][] weights, double[][] gradW) {
        if (velocityW == null || velocityW.length != weights.length || velocityW[0].length != weights[0].length) {
            velocityW = new double[weights.length][weights[0].length];
        }
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                velocityW[i][j] = momentum * velocityW[i][j] - learningRate * gradW[i][j];
                weights[i][j] += velocityW[i][j];
            }
        }
    }

    @Override
    public void update(double[] biases, double[] gradB) {
        if (velocityB == null || velocityB.length != biases.length) {
            velocityB = new double[biases.length];
        }
        for (int i = 0; i < biases.length; i++) {
            velocityB[i] = momentum * velocityB[i] - learningRate * gradB[i];
            biases[i] += velocityB[i];
        }
    }
}
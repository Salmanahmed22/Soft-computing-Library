package org.codeWithGA.NeuralNetwork.core;

public abstract class Layer {
    public abstract double[] forward(double[] input);
    public abstract double[] backward(double[] gradOutput);

    // For storing gradients
    protected double[][] gradW;
    protected double[] gradB;

    public double[][] getGradW() {
        return gradW;
    }

    public double[] getGradB() {
        return gradB;
    }

    public void zeroGrad() {
        if (gradW != null) {
            for (int i = 0; i < gradW.length; i++) {
                for (int j = 0; j < gradW[i].length; j++) {
                    gradW[i][j] = 0;
                }
            }
        }
        if (gradB != null) {
            for (int i = 0; i < gradB.length; i++) {
                gradB[i] = 0;
            }
        }
    }
}
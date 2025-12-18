package org.codeWithGA.NeuralNetwork.losses;

public class MeanSquaredError implements LossFunction {

    @Override
    public double forward(double[] yTrue, double[] yPred) {
        if (yTrue.length != yPred.length) {
            throw new IllegalArgumentException("yTrue and yPred must have the same length");
        }
        double sum = 0.0;
        for (int i = 0; i < yTrue.length; i++) {
            double diff = yTrue[i] - yPred[i];
            sum += diff * diff;
        }
        return sum / yTrue.length;
    }

    @Override
    public double[] backward(double[] yTrue, double[] yPred) {
        if (yTrue.length != yPred.length) {
            throw new IllegalArgumentException("yTrue and yPred must have the same length");
        }
        double[] grad = new double[yTrue.length];
        for (int i = 0; i < yTrue.length; i++) {
            grad[i] = 2.0 * (yPred[i] - yTrue[i]) / yTrue.length;
        }
        return grad;
    }
}
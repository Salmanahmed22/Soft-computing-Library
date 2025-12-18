package org.codeWithGA.NeuralNetwork.losses;

public class CrossEntropyLoss implements LossFunction {

    @Override
    public double forward(double[] yTrue, double[] yPred) {
        if (yTrue.length != yPred.length) {
            throw new IllegalArgumentException("yTrue and yPred must have the same length");
        }
        double loss = 0.0;
        for (int i = 0; i < yTrue.length; i++) {
            if (yTrue[i] > 0) {  // assuming yTrue is one-hot or probabilities
                loss -= yTrue[i] * Math.log(yPred[i] + 1e-15);  // add epsilon to avoid log(0)
            }
        }
        return loss;
    }

    @Override
    public double[] backward(double[] yTrue, double[] yPred) {
        if (yTrue.length != yPred.length) {
            throw new IllegalArgumentException("yTrue and yPred must have the same length");
        }
        double[] grad = new double[yTrue.length];
        for (int i = 0; i < yTrue.length; i++) {
            grad[i] = yPred[i] - yTrue[i];
        }
        return grad;
    }
}
package org.codeWithGA.NeuralNetwork.initializers;

import java.util.Random;

public class XavierInitializer implements WeightInitializer {
    private Random random;

    public XavierInitializer() {
        this.random = new Random();
    }

    @Override
    public double[][] initialize(int rows, int cols) {
        double limit = Math.sqrt(6.0 / (rows + cols));
        double[][] weights = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                weights[i][j] = (random.nextDouble() - 0.5) * 2 * limit;
            }
        }
        return weights;
    }
}
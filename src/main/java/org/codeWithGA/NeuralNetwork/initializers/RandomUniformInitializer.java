package org.codeWithGA.NeuralNetwork.initializers;

import java.util.Random;
//when to use random form initializer?
//for small networks
public class RandomUniformInitializer implements WeightInitializer {
    private double limit;
    private Random random;

    public RandomUniformInitializer() {
        this(0.05);
    }

    public RandomUniformInitializer(double limit) {
        this.limit = limit;
        this.random = new Random();
    }

    @Override
    public double[][] initialize(int rows, int cols) {
        double[][] weights = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                weights[i][j] = (random.nextDouble() - 0.5) * 2 * limit;
            }
        }
        return weights;
    }
}
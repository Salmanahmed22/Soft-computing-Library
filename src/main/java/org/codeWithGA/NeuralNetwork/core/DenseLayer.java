package org.codeWithGA.NeuralNetwork.core;

import org.codeWithGA.NeuralNetwork.activations.ActivationFunction;
import org.codeWithGA.NeuralNetwork.initializers.WeightInitializer;
import org.codeWithGA.NeuralNetwork.utils.MatrixUtils;

public class DenseLayer extends Layer {
    private double[][] weights;
    private double[] biases;
    private ActivationFunction activation;
    private double[] lastInput;
    private double[] lastZ;

    public DenseLayer(int inputSize, int outputSize, ActivationFunction activation, WeightInitializer initializer) {
        this.weights = initializer.initialize(outputSize, inputSize);
        this.biases = new double[outputSize];  // initialize to 0
        this.activation = activation;
    }

    public DenseLayer(int inputSize, int outputSize, ActivationFunction activation) {
        this(inputSize, outputSize, activation, new org.codeWithGA.NeuralNetwork.initializers.RandomUniformInitializer());
    }

    @Override
    public double[] forward(double[] input) {
        lastInput = input.clone();
        double[] z = MatrixUtils.matVecMul(weights, input);
        MatrixUtils.add(z, biases);
        lastZ = z.clone();
        double[] output = new double[z.length];
        for (int i = 0; i < z.length; i++) {
            output[i] = activation.forward(z[i]);
        }
        return output;
    }

    @Override
    public double[] backward(double[] gradOutput) {
        // gradOutput is dL/da
        double[] gradZ = new double[gradOutput.length];
        for (int i = 0; i < gradOutput.length; i++) {
            gradZ[i] = gradOutput[i] * activation.backward(lastZ[i]);
        }

        // Initialize gradW and gradB if null
        if (gradW == null) {
            gradW = new double[weights.length][weights[0].length];
        }
        if (gradB == null) {
            gradB = new double[biases.length];
        }

        // Accumulate gradW = outer(gradZ, lastInput)
        double[][] currentGradW = MatrixUtils.outerProduct(gradZ, lastInput);
        MatrixUtils.add(gradW, currentGradW);

        // Accumulate gradB = gradZ
        MatrixUtils.add(gradB, gradZ);

        // gradInput = W^T * gradZ
        double[][] wT = MatrixUtils.transpose(weights);
        return MatrixUtils.matVecMul(wT, gradZ);
    }

    public double[][] getWeights() {
        return weights;
    }

    public double[] getBiases() {
        return biases;
    }
}
package org.codeWithGA.NeuralNetwork.initializers;
//Why this layer is important?
//Poor initialization → vanishing/exploding gradients
//Good initialization → faster convergence
public interface WeightInitializer {
    //rows -> number of neurons in this layer
    //cols -> number of inputs from the previous layer
    double[][] initialize(int rows, int cols);
}
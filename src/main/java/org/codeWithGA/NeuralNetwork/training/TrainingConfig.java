package org.codeWithGA.NeuralNetwork.training;

public class TrainingConfig {
    private double learningRate;
    private int batchSize;
    private int epochs;
    private boolean shuffle;

    public TrainingConfig(double learningRate, int batchSize, int epochs, boolean shuffle) {
        this.learningRate = learningRate;
        this.batchSize = batchSize;
        this.epochs = epochs;
        this.shuffle = shuffle;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public int getEpochs() {
        return epochs;
    }

    public boolean isShuffle() {
        return shuffle;
    }
}
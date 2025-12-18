package org.codeWithGA.NeuralNetwork.training;

import org.codeWithGA.NeuralNetwork.core.Layer;
import org.codeWithGA.NeuralNetwork.core.NeuralNetwork;
import org.codeWithGA.NeuralNetwork.losses.LossFunction;
import org.codeWithGA.NeuralNetwork.optimizers.Optimizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trainer {
    private NeuralNetwork network;
    private LossFunction lossFunction;
    private Optimizer optimizer;
    private TrainingConfig config;
    private List<Double> epochLosses = new ArrayList<>();

    public Trainer(NeuralNetwork network, LossFunction lossFunction, Optimizer optimizer, TrainingConfig config) {
        this.network = network;
        this.lossFunction = lossFunction;
        this.optimizer = optimizer;
        this.config = config;
    }

    public void fit(double[][] trainX, double[][] trainY) {
        int numSamples = trainX.length;
        Random rand = new Random();

        for (int epoch = 0; epoch < config.getEpochs(); epoch++) {
            double epochLoss = 0.0;
            int numBatches = 0;

            // Shuffle
            if (config.isShuffle()) {
                shuffle(trainX, trainY, rand);
            }

            // Batches
            for (int start = 0; start < numSamples; start += config.getBatchSize()) {
                int end = Math.min(start + config.getBatchSize(), numSamples);
                double[][] batchX = new double[end - start][];
                double[][] batchY = new double[end - start][];
                for (int i = start; i < end; i++) {
                    batchX[i - start] = trainX[i];
                    batchY[i - start] = trainY[i];
                }

                // Zero grads
                for (Layer layer : network.getLayers()) {
                    layer.zeroGrad();
                }

                // Forward and accumulate loss
                double batchLoss = 0.0;
                for (int i = 0; i < batchX.length; i++) {
                    double[] pred = network.forward(batchX[i]);
                    batchLoss += lossFunction.forward(batchY[i], pred);
                    double[] lossGrad = lossFunction.backward(batchY[i], pred);
                    network.backward(lossGrad);
                }
                batchLoss /= batchX.length;
                epochLoss += batchLoss;
                numBatches++;


                // Update - divide grads by batch size
                for (Layer layer : network.getLayers()) {
                    if (layer instanceof org.codeWithGA.NeuralNetwork.core.DenseLayer) {
                        org.codeWithGA.NeuralNetwork.core.DenseLayer dl = (org.codeWithGA.NeuralNetwork.core.DenseLayer) layer;
                        if (dl.getGradW() != null) {
                            // Divide by batch size
                            for (int r = 0; r < dl.getGradW().length; r++) {
                                for (int c = 0; c < dl.getGradW()[r].length; c++) {
                                    dl.getGradW()[r][c] /= batchX.length;
                                }
                            }
                            optimizer.update(dl.getWeights(), dl.getGradW());
                        }
                        if (dl.getGradB() != null) {
                            for (int j = 0; j < dl.getGradB().length; j++) {
                                dl.getGradB()[j] /= batchX.length;
                            }
                            optimizer.update(dl.getBiases(), dl.getGradB());
                        }
                    }
                }
            }

            epochLoss /= numBatches;
            epochLosses.add(epochLoss);
            System.out.println("Epoch " + epoch + " Loss: " + epochLoss);
        }
    }

    private void shuffle(double[][] x, double[][] y, Random rand) {
        for (int i = x.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            double[] tempX = x[i];
            x[i] = x[j];
            x[j] = tempX;
            double[] tempY = y[i];
            y[i] = y[j];
            y[j] = tempY;
        }
    }

    public List<Double> getEpochLosses() {
        return epochLosses;
    }
}

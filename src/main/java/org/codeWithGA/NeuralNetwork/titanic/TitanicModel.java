package org.codeWithGA.NeuralNetwork.titanic;

import org.codeWithGA.NeuralNetwork.core.DenseLayer;
import org.codeWithGA.NeuralNetwork.core.NeuralNetwork;
import org.codeWithGA.NeuralNetwork.activations.ReLU;
import org.codeWithGA.NeuralNetwork.activations.Sigmoid;
import org.codeWithGA.NeuralNetwork.initializers.XavierInitializer;

public class TitanicModel {
    private NeuralNetwork network;

    public TitanicModel(int inputFeatures) {
        network = new NeuralNetwork();

        network.addLayer(new DenseLayer(inputFeatures, 8, new ReLU(), new XavierInitializer()));

        network.addLayer(new DenseLayer(8, 1, new Sigmoid(), new XavierInitializer()));
    }

    public NeuralNetwork getNetwork() {
        return network;
    }

    public double[] predict(double[] input) {
        return network.forward(input);
    }
}
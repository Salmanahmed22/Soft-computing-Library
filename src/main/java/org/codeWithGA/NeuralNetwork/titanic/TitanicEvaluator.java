package org.codeWithGA.NeuralNetwork.titanic;
import org.codeWithGA.NeuralNetwork.data.DataSet;

public class TitanicEvaluator {

    public static void evaluate(TitanicModel model, DataSet testData) {
        int correct = 0;
        for (int i = 0; i < testData.size(); i++) {
            double[] pred = model.predict(testData.getFeatures().get(i));
            int predictedLabel = pred[0] >= 0.5 ? 1 : 0;
            int actualLabel = testData.getLabels().get(i).intValue();

            if (predictedLabel == actualLabel) correct++;
        }

        double accuracy = (double) correct / testData.size();
        System.out.println("Titanic Model Accuracy: " + (accuracy * 100) + "%");
    }
}
package org.codeWithGA.NeuralNetwork.titanic;

import org.codeWithGA.NeuralNetwork.data.*;

import java.util.List;

public class TitanicCaseStudy {

    public static void main(String[] args) throws Exception {
        System.out.println("--- Titanic Survival Prediction System ---");

        DataSet rawData = CSVLoader.load("src/main/java/org/codeWithGA/NeuralNetwork/datasets/titanic.csv", true, 1);

        DataSet cleanData = TitanicPreprocessor.prepare(rawData);

        cleanData = Preprocessor.removeInvalid(cleanData);
        DataSet normalizedData = Normalizer.minMax(cleanData);

        // 4. Train / Test split
        DataSet[] split = TrainTestSplit.split(normalizedData, 0.8, 42);
        DataSet trainData = split[0];
        DataSet testData = split[1];

        int inputFeatures = trainData.getFeatures().get(0).length;
        TitanicModel titanicModel = new TitanicModel(inputFeatures);

        System.out.println("Starting training...");
        List<Double> lossHistory = TitanicTrainer.train(titanicModel, trainData);

        TitanicEvaluator.evaluate(titanicModel, testData);

        Plotter.generateLossCurve(lossHistory);

        System.out.println("--- Process Complete. Check 'reports/' folder for charts. ---");
    }
}

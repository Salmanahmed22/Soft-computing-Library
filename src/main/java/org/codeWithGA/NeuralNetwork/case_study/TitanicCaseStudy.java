package org.codeWithGA.NeuralNetwork.case_study;

import org.codeWithGA.NeuralNetwork.data.*;

public class TitanicCaseStudy {

    public static void main(String[] args) throws Exception {

        // 1. Load dataset
        DataSet data = CSVLoader.load(
                "src/main/java/org/codeWithGA/NeuralNetwork/datasets/titanic.csv",
                true);

        // 2. Clean data
        data = Preprocessor.removeInvalid(data);

        // 3. Normalize features
        data = Normalizer.minMax(data);

        // 4. Train / Test split
        DataSet[] split = TrainTestSplit.split(data, 0.8, 42);
        DataSet train = split[0];
        DataSet test = split[1];

        // 5. Pass to Neural Network (later)
        System.out.println("Train size: " + train.size());
        System.out.println("Test size: " + test.size());
    }
}

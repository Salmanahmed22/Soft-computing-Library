package org.codeWithGA.NeuralNetwork.titanic;

import org.codeWithGA.NeuralNetwork.data.DataSet;
import org.codeWithGA.NeuralNetwork.training.Trainer;
import org.codeWithGA.NeuralNetwork.training.TrainingConfig;
import org.codeWithGA.NeuralNetwork.losses.CrossEntropyLoss;
import org.codeWithGA.NeuralNetwork.optimizers.GradientDescent;
import java.util.List;

public class TitanicTrainer {

    public static List<Double> train(TitanicModel model, DataSet trainData) {
        double[][] x = trainData.getFeatures().toArray(new double[0][]);
        double[][] y = new double[trainData.getLabels().size()][1];

        for (int i = 0; i < trainData.getLabels().size(); i++) {
            y[i][0] = trainData.getLabels().get(i);
        }

        TrainingConfig config = new TrainingConfig(0.05, 32, 200, true);
        Trainer trainer = new Trainer(
                model.getNetwork(),
                new CrossEntropyLoss(),
                new GradientDescent(0.05),
                config
        );

        trainer.fit(x, y);

        List<Double> lossHistory = trainer.getEpochLosses();
        Plotter.generateLossCurve(lossHistory);

        return lossHistory;
    }
}
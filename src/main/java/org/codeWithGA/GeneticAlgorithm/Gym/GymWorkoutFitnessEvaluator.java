package org.codeWithGA.GeneticAlgorithm.Gym;

import org.codeWithGA.GeneticAlgorithm.core.Chromosome;
import org.codeWithGA.GeneticAlgorithm.fitness.FitnessEvaluator;

public class GymWorkoutFitnessEvaluator implements FitnessEvaluator{
    @Override
    public double calculate(Chromosome chromosome) {
        WorkoutPlan metrics = new WorkoutPlan(chromosome);

        double totalReward = metrics.getTotalProgress();

        if (metrics.getRestDays() >= 1 && metrics.getRestDays() <= 2) {
            totalReward += 10.0;
        }

        return totalReward;
    }
}

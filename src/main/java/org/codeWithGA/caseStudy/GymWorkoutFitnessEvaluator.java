package org.codeWithGA.caseStudy;

import org.codeWithGA.core.Chromosome;
import org.codeWithGA.fitness.FitnessEvaluator;

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

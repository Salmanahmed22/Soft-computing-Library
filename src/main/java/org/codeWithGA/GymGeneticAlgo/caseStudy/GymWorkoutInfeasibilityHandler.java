package org.codeWithGA.GymGeneticAlgo.caseStudy;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;
import org.codeWithGA.GymGeneticAlgo.fitness.InfeasibilityHandler;

/**
 * Implements the InfeasibilityHandler interface and calculates PENALTIES ONLY.
 **/

public class GymWorkoutInfeasibilityHandler implements InfeasibilityHandler {
    @Override
    public double calculatePenalty(Chromosome chromosome) {
        WorkoutPlan metrics = new WorkoutPlan(chromosome);

        double totalPenalty = 0.0;

        if (metrics.getTotalFatigue() > 10) {
            totalPenalty += 10.0;
        }
        if (metrics.getTotalHours() > 6.0) {
            totalPenalty += 20.0;
        }
        if (metrics.getStrengthDays() > 4) {
            totalPenalty += 10.0;
        }
        if (metrics.getRestDays() == 0) {
            totalPenalty += 20.0;
        }

        return totalPenalty;
    }
}

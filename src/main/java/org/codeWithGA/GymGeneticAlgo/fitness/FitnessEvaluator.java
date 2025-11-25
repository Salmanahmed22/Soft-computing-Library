//package org.codeWithGA.fitness;
//
//
//import org.codeWithGA.core.Chromosome;
//import org.codeWithGA.core.Gene;
//
//import java.util.List;
//
//public class FitnessEvaluator {
//
//    /**
//     * Calculates the fitness score for a given chromosome (weekly workout plan).
//     * The higher the score, the better the balance between progress, recovery, and safety.
//     */
//    public static double calculateFitness(Chromosome chromosome) {
//        List<Gene> genes = chromosome.getGenes();
//        //                  0   1    2    3
//        double[] hours = {0.0, 1.5, 1.0, 0.5};
//        double[] progress = {0.0, 10.0, 6.0, 4.0};
//        double[] fatigueLoad = {-5.0, 5.0, 3.0, 1.0};
//
//
//        double totalHours = 0;
//        double totalProgress = 0;
//        double totalFatigue = 0;
//        int restDays = 0;
//        int strengthDays = 0;
//
//        for (Gene g : genes) {
//            int type = g.getType();
//            totalHours += hours[type];
//            totalProgress += progress[type];
//            totalFatigue += fatigueLoad[type];
//            if (type == 0) restDays++;
//            if (type == 1) strengthDays++;
//        }
//
//
//        double recoveryScore = (restDays >= 1 && restDays <= 2) ? 10.0 : 0.0;
//
//        double fatiguePenalty = (totalFatigue > 10) ? 10.0 : 0.0;
//        double overtrainingPenalty = (totalHours > 6.0) ? 20.0 : 0.0;
//        double strengthOverloadPenalty = (strengthDays > 4) ? 10.0 : 0.0;
//        double noRestPenalty = (restDays == 0) ? 20.0 : 0.0;
//
//        double fitness = (totalProgress + recoveryScore)
//                - (fatiguePenalty + overtrainingPenalty + strengthOverloadPenalty + noRestPenalty);
//
//        if (fitness < 0) fitness = 0;
//
//        return fitness;
//    }
//}

package org.codeWithGA.GymGeneticAlgo.fitness;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;

/**
 * Interface for the problem-specific fitness evaluation (REWARDS).
 */
public interface FitnessEvaluator {
    double calculate(Chromosome chromosome);
}

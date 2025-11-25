package org.codeWithGA.GymGeneticAlgo.caseStudy;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;
import org.codeWithGA.GymGeneticAlgo.core.Gene;
import java.util.List;


/**
 * A helper class to calculate all workout plan metrics in a single pass.
 * This avoids looping over the chromosome multiple times.
 */

public class WorkoutPlan {
    private static final double[] hours = {0.0, 1.5, 1.0, 0.5};
    private static final double[] progress = {0.0, 10.0, 6.0, 4.0};
    private static final double[] fatigueLoad = {-5.0, 5.0, 3.0, 1.0};

    private final double totalHours;
    private final double totalProgress;
    private final double totalFatigue;
    private final int restDays;
    private final int strengthDays;

    public double getTotalHours() {
        return totalHours;
    }

    public double getTotalProgress() {
        return totalProgress;
    }

    public double getTotalFatigue() {
        return totalFatigue;
    }

    public int getRestDays() {
        return restDays;
    }

    public int getStrengthDays() {
        return strengthDays;
    }

    public WorkoutPlan(Chromosome chromosome) {
        double tempHours = 0;
        double tempProgress = 0;
        double tempFatigue = 0;
        int tempRestDays = 0;
        int tempStrengthDays = 0;

        List<Gene> genes = chromosome.getGenes();
        for (Gene g : genes) {
            int type = g.getType();

            tempHours += hours[type];
            tempProgress += progress[type];
            tempFatigue += fatigueLoad[type];

            if (type == 0) tempRestDays++;
            if (type == 1) tempStrengthDays++;
        }

        this.totalHours = tempHours;
        this.totalProgress = tempProgress;
        this.totalFatigue = tempFatigue;
        this.restDays = tempRestDays;
        this.strengthDays = tempStrengthDays;
    }
}

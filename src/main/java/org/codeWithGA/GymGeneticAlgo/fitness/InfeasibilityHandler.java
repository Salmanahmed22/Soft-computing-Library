package org.codeWithGA.GymGeneticAlgo.fitness;
import org.codeWithGA.GymGeneticAlgo.core.Chromosome;

public interface InfeasibilityHandler {
    double calculatePenalty(Chromosome chromosome);
}

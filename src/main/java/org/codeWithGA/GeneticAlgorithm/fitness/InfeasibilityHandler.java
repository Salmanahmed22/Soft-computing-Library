package org.codeWithGA.GeneticAlgorithm.fitness;
import org.codeWithGA.GeneticAlgorithm.core.Chromosome;

public interface InfeasibilityHandler {
    double calculatePenalty(Chromosome chromosome);
}

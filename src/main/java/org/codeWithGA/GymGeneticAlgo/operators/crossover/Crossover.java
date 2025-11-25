package org.codeWithGA.GymGeneticAlgo.operators.crossover;
import org.codeWithGA.GymGeneticAlgo.core.Chromosome;

import java.util.List;

public interface Crossover {
    List<Chromosome> apply(Chromosome parent1, Chromosome parent2);
}

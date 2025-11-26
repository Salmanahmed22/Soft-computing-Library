package org.codeWithGA.GeneticAlgorithm.operators.crossover;
import org.codeWithGA.GeneticAlgorithm.core.Chromosome;

import java.util.List;

public interface Crossover {
    List<Chromosome> apply(Chromosome parent1, Chromosome parent2);
}

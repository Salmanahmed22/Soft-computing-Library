package org.codeWithGA.GeneticAlgorithm.operators.replacement;

import org.codeWithGA.GeneticAlgorithm.core.Chromosome;
import java.util.List;

public interface Replacement {
    List<Chromosome> apply(List<Chromosome> oldPopulation, List<Chromosome> offspring, int populationSize);
}

package org.codeWithGA.GymGeneticAlgo.operators.replacement;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;
import java.util.List;

public class GenerationalReplacement implements Replacement {

    @Override
    public List<Chromosome> apply(List<Chromosome> oldPopulation, List<Chromosome> offspring, int populationSize) {
        if (offspring.size() > populationSize) {
            return offspring.subList(0, populationSize);
        } else {
            return offspring;
        }
    }
}

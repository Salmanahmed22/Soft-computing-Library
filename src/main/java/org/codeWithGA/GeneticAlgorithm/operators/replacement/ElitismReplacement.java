package org.codeWithGA.GeneticAlgorithm.operators.replacement;

import org.codeWithGA.GeneticAlgorithm.core.Chromosome;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ElitismReplacement implements Replacement {

    private final int eliteCount;

    public ElitismReplacement(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    @Override
    public List<Chromosome> apply(List<Chromosome> oldPopulation, List<Chromosome> offspring, int populationSize) {
        List<Chromosome> nextGen = new ArrayList<>();

        oldPopulation.sort(Comparator.comparingDouble(Chromosome::getFitness).reversed());
        List<Chromosome> elites = oldPopulation.subList(0, Math.min(eliteCount, oldPopulation.size()));
        nextGen.addAll(elites);

        int remaining = populationSize - nextGen.size();
        for (int i = 0; i < remaining && i < offspring.size(); i++) {
            nextGen.add(offspring.get(i));
        }

        return nextGen;
    }
}

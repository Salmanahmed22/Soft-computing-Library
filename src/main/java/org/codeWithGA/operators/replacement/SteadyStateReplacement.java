package org.codeWithGA.operators.replacement;

import org.codeWithGA.core.Chromosome;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SteadyStateReplacement implements Replacement {

    private final int numToReplace;

    public SteadyStateReplacement(int numToReplace) {
        this.numToReplace = numToReplace;
    }

    @Override
    public List<Chromosome> apply(List<Chromosome> oldPopulation, List<Chromosome> offspring, int populationSize) {
        List<Chromosome> nextGen = new ArrayList<>(oldPopulation);

        nextGen.sort(Comparator.comparingDouble(Chromosome::getFitness));
        offspring.sort(Comparator.comparingDouble(Chromosome::getFitness).reversed());

        // replace weakest individuals
        for (int i = 0; i < numToReplace && i < offspring.size() && i < nextGen.size(); i++) {
            nextGen.set(i, offspring.get(i)); 
        }

        nextGen.sort(Comparator.comparingDouble(Chromosome::getFitness).reversed());
        if (nextGen.size() > populationSize) {
            return nextGen.subList(0, populationSize);
        }
        return nextGen;
    }
}

package org.codeWithGA.GymGeneticAlgo.operators.crossover;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;
import org.codeWithGA.GymGeneticAlgo.core.Gene;
import java.util.*;

public class OnePointCrossover implements Crossover {
    private final double crossoverRate;
    private final Random random = new Random();

    public OnePointCrossover(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    @Override
    public List<Chromosome> apply(Chromosome parent1, Chromosome parent2) {
        if (random.nextDouble() > crossoverRate)
            return List.of(parent1, parent2);
        
        int length = parent1.getGenes().size();
        int point = random.nextInt(0, length-1);
        List<Gene> genes1 = parent1.getGenes();
        List<Gene> genes2 = parent2.getGenes();

        for (int i = point; i < genes1.size(); i++) {
            Gene temp = genes1.get(i);
            genes1.set(i, genes2.get(i));
            genes2.set(i, temp);
        }

        Chromosome child1 = parent1.newWithGenes(genes1);
        Chromosome child2 = parent2.newWithGenes(genes2);

        return List.of(child1, child2);
    }


}


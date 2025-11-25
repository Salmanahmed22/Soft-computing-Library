package org.codeWithGA.GymGeneticAlgo.operators.crossover;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;
import org.codeWithGA.GymGeneticAlgo.core.Gene;
import java.util.*;

public class TwoPointCrossover implements Crossover {
    private final double crossoverRate;
    private final Random random = new Random();

    public TwoPointCrossover(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    @Override
    public List<Chromosome> apply(Chromosome parent1, Chromosome parent2) {
        if (random.nextDouble() > crossoverRate)
            return List.of(parent1, parent2);

        int length = parent1.getGenes().size();
        int point1 = random.nextInt(length);
        int point2;
        do{
            point2 = random.nextInt(length);
        }while(point1 == point2);
        
        if (point1 > point2) {
            int temp = point1;
            point1 = point2;
            point2 = temp;
        }

        List<Gene> genes1 = new ArrayList<>(parent1.getGenes());
        List<Gene> genes2 = new ArrayList<>(parent2.getGenes());

        for (int i = point1; i < point2; i++) {
            Gene temp = genes1.get(i);
            genes1.set(i, genes2.get(i));
            genes2.set(i, temp);
        }

        Chromosome child1 = parent1.newWithGenes(genes1);
        Chromosome child2 = parent2.newWithGenes(genes2);

        return List.of(child1, child2);
    }
}

package org.codeWithGA.operators.mutation;
import org.codeWithGA.core.Chromosome;
import org.codeWithGA.core.IntegerChromosome;

import java.util.Random;

public class RandomChoiceMutation implements Mutation {
    private final double mutationRate;
    private final int minValue;
    private final int maxValue;
    private final Random random = new Random();

    public RandomChoiceMutation(double mutationRate,int minValue, int maxValue) {
        this.mutationRate = mutationRate;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    @Override
    public void mutate(Chromosome chromosome) {
        if (!(chromosome instanceof IntegerChromosome)) return;

        IntegerChromosome intChrom = (IntegerChromosome) chromosome;
        int[] genes = intChrom.getIntegerGenes();

        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                int oldValue = genes[i];
                int newValue;
                do {
                    newValue = random.nextInt(maxValue - minValue + 1) + minValue;
                } while (newValue == oldValue);
                genes[i] = newValue;
            }
        }

        intChrom.setGenes(genes);
    }
}

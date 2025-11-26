package org.codeWithGA.GeneticAlgorithm.operators.mutation;
import org.codeWithGA.GeneticAlgorithm.core.Chromosome;
import org.codeWithGA.GeneticAlgorithm.core.FloatChromosome;

import java.util.Random;

public class UniformMutation implements Mutation{
    private final Random random = new Random();
    private final double mutationRate;
    private final double[] lowerBounds;
    private final double[] upperBounds;

    public UniformMutation(double mutationRate, double[] lowerBounds, double[] upperBounds) {
        this.mutationRate = mutationRate;
        this.lowerBounds = lowerBounds;
        this.upperBounds = upperBounds;
    }

    @Override
    public void mutate(Chromosome chromosome) {
        if (!(chromosome instanceof FloatChromosome)) return;

        FloatChromosome floatChrom = (FloatChromosome) chromosome;
        double[] genes = floatChrom.getFloatGenes();

        for (int i = 0; i < genes.length; i++) {
            if (random.nextDouble() < mutationRate) {
                double xi = genes[i];
                double LBi = lowerBounds[i];
                double UBi = upperBounds[i];

                double deltaL = xi - LBi;
                double deltaU = UBi - xi;

                double ri1 = random.nextDouble();
                boolean goLeft = ri1 <= 0.5;

                if (goLeft) {
                    double ri2 = random.nextDouble() * deltaL;
                    genes[i] = Math.max(LBi, xi - ri2);
                } else {
                    double ri2 = random.nextDouble() * deltaU;
                    genes[i] = Math.min(UBi, xi + ri2);
                }
            }
        }

        floatChrom.setGenes(genes);
    }
}

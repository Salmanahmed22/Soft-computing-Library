package org.codeWithGA.GymGeneticAlgo.core;

import java.util.Random;

public class FloatChromosome extends Chromosome {
    private double[] genes;

    public FloatChromosome(double[] genes) {
        super(null);
        this.genes = genes.clone();
    }

    public static FloatChromosome randomChromosome(int length, double[] lowerBounds, double[] upperBounds) {
        Random random = new Random();
        double[] genes = new double[length];
        for (int i = 0; i < length; i++) {
            double LBi = lowerBounds[i];
            double UBi = upperBounds[i];
            genes[i] = LBi + (UBi - LBi) * random.nextDouble();
        }
        return new FloatChromosome(genes);
    }

    public double[] getFloatGenes() {
        return genes.clone();
    }

    public void setGenes(double[] genes) {
        this.genes = genes.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < genes.length; i++) {
            sb.append(String.format("%.3f", genes[i]));
            if (i < genes.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

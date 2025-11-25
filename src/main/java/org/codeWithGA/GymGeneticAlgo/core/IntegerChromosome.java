package org.codeWithGA.GymGeneticAlgo.core;

import java.util.Random;

public class IntegerChromosome extends Chromosome {
    private int[] genes;

    public IntegerChromosome(int[] genes) {
        super(null);
        this.genes = genes.clone();
    }

    public static IntegerChromosome randomChromosome(int length, int min, int max) {
        Random random = new Random();
        int[] genes = new int[length];
        for (int i = 0; i < length; i++) {
            genes[i] = random.nextInt(max - min + 1) + min;
        }
        return new IntegerChromosome(genes);
    }

    public int[] getIntegerGenes() {
        return genes.clone();
    }

    public void setGenes(int[] genes) {
        this.genes = genes.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < genes.length; i++) {
            sb.append(genes[i]);
            if (i < genes.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

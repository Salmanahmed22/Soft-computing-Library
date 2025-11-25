package org.codeWithGA.GymGeneticAlgo.core;

import java.util.Random;

public class BinaryChromosome extends Chromosome {
    private boolean[] genes;

    public BinaryChromosome(boolean[] genes) {
        super(null);
        this.genes = genes.clone();
    }

    public static BinaryChromosome randomChromosome(int length) {
        Random random = new Random();
        boolean[] genes = new boolean[length];
        for (int i = 0; i < length; i++) {
            genes[i] = random.nextBoolean();
        }
        return new BinaryChromosome(genes);
    }

    public boolean[] getBinaryGenes() {
        return genes.clone();
    }

    public void setGenes(boolean[] genes) {
        this.genes = genes.clone();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < genes.length; i++) {
            sb.append(genes[i] ? "1" : "0");
            if (i < genes.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

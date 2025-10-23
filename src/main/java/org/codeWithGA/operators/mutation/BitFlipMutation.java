package org.codeWithGA.operators.mutation;
import org.codeWithGA.core.Chromosome;
import org.codeWithGA.core.BinaryChromosome;

import java.util.Random;

public class BitFlipMutation implements Mutation{
    private final double mutationRate;
    public BitFlipMutation(double mutationRate){
        this.mutationRate = mutationRate;
    }
    Random rand = new Random();
    @Override
    public void mutate(Chromosome chromosome) {
        if(!(chromosome instanceof BinaryChromosome)) return;
        BinaryChromosome binaryChrom = (BinaryChromosome) chromosome;
        boolean[] genes = binaryChrom.getBinaryGenes();
        for (int i = 0; i < genes.length; i++) {
            if (rand.nextDouble() < mutationRate) {
                genes[i] = !genes[i];
            }
        }
        binaryChrom.setGenes(genes);
    }
}

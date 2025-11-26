package org.codeWithGA.GymGeneticAlgo.core;

import org.codeWithGA.GymGeneticAlgo.fitness.FitnessEvaluator;
import org.codeWithGA.GymGeneticAlgo.fitness.InfeasibilityHandler;

import java.util.ArrayList;
import java.util.List;

public class Chromosome {
    private List<Gene> genes;
    private double fitness;

    //Deep copy
    public Chromosome(List<Gene> genes) {
        this.genes = new ArrayList<>();
        for(Gene g : genes){
            this.genes.add(g.copy());
        }
    }

    public static Chromosome randomChromosome() {
        List<Gene> genes = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            genes.add(Gene.randomGene());
        }
        return new Chromosome(genes);
    }

    public List<Gene> getGenes() {
        List<Gene> copy = new ArrayList<>();
        for (Gene g : genes) copy.add(g.copy());
        return copy;
    }
    public void setGenes(List<Gene> newGenes) {
        this.genes = new ArrayList<>();
        for (Gene g : newGenes) {
            this.genes.add(g.copy());
        }
    }
    public Chromosome newWithGenes(List<Gene> newGenes) {
        return new Chromosome(newGenes);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void calculateFitness(FitnessEvaluator fitnessFunction, InfeasibilityHandler handler) {
        double baseFitness = fitnessFunction.calculate(this);

        double penalty = handler.calculatePenalty(this);

        this.fitness = baseFitness - penalty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < genes.size(); i++) {
            sb.append(genes.get(i));
            if (i < genes.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

}

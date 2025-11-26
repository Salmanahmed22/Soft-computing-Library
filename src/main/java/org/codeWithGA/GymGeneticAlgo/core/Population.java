package org.codeWithGA.GymGeneticAlgo.core;

import org.codeWithGA.GymGeneticAlgo.fitness.FitnessEvaluator;
import org.codeWithGA.GymGeneticAlgo.fitness.InfeasibilityHandler;

import java.util.ArrayList;
import java.util.List;


public class Population {
    private List<Chromosome> chromosomes;

    // Constructor for creating initial random population
    public Population(int size,
                      FitnessEvaluator fitnessEvaluator, InfeasibilityHandler infeasibilityHandler) {
        chromosomes = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Chromosome randomChromosome = Chromosome.randomChromosome();
            randomChromosome.calculateFitness(fitnessEvaluator, infeasibilityHandler);
            chromosomes.add(randomChromosome);
        }
    }

    // Constructor for creating population from existing chromosomes
    public Population(List<Chromosome> chromosomes) {
        this.chromosomes = new ArrayList<>(chromosomes);
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    /**
     * Finds and returns the best chromosome in the population
     * (the one with the highest fitness value)
     * @return the chromosome with the maximum fitness
     */
    public Chromosome best() {
        if (chromosomes == null || chromosomes.isEmpty()) {
            throw new IllegalStateException("Population is empty");
        }

        Chromosome bestChromosome = chromosomes.get(0);
        double maxFitness = bestChromosome.getFitness();

        for (int i = 1; i < chromosomes.size(); i++) {
            Chromosome current = chromosomes.get(i);
            double currentFitness = current.getFitness();
            
            if (currentFitness > maxFitness) {
                maxFitness = currentFitness;
                bestChromosome = current;
            }
        }

        return bestChromosome;
    }

    @Override
    public String toString() {
        return chromosomes.toString();
    }
}
package org.codeWithGA.GymGeneticAlgo.caseStudy;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;
import org.codeWithGA.GymGeneticAlgo.core.Population;
import org.codeWithGA.GymGeneticAlgo.fitness.FitnessEvaluator;
import org.codeWithGA.GymGeneticAlgo.fitness.InfeasibilityHandler;
import org.codeWithGA.GymGeneticAlgo.operators.crossover.Crossover;
import org.codeWithGA.GymGeneticAlgo.operators.crossover.OnePointCrossover;
import org.codeWithGA.GymGeneticAlgo.operators.crossover.TwoPointCrossover;
import org.codeWithGA.GymGeneticAlgo.operators.crossover.UniformCrossover;
import org.codeWithGA.GymGeneticAlgo.operators.mutation.Mutation;
import org.codeWithGA.GymGeneticAlgo.operators.mutation.RandomChoiceMutation;
import org.codeWithGA.GymGeneticAlgo.operators.replacement.ElitismReplacement;
import org.codeWithGA.GymGeneticAlgo.operators.replacement.GenerationalReplacement;
import org.codeWithGA.GymGeneticAlgo.operators.replacement.Replacement;
import org.codeWithGA.GymGeneticAlgo.operators.replacement.SteadyStateReplacement;
import org.codeWithGA.GymGeneticAlgo.operators.selection.RouletteWheel;
import org.codeWithGA.GymGeneticAlgo.operators.selection.Selection;
import org.codeWithGA.GymGeneticAlgo.operators.selection.TournamentSelection;

import java.util.*;

public class CaseStudyApplication {
    
    private static int getValidatedInput(Scanner scanner, int min, int max, String prompt) {
        int choice;
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 
                if (choice >= min && choice <= max) {
                    return choice;
                }
            } else {
                scanner.nextLine(); 
            }
            System.out.println("Invalid input! Please enter a number between " + min + " and " + max);
        }
    }
    
    private static double getRateInput(Scanner scanner, double defaultRate, String rateName) {
        System.out.println(rateName + " Rate set to " + defaultRate + ".");
        System.out.print("Press Enter to confirm OR enter another value (0.0 - 1.0): ");
        String input = scanner.nextLine().trim();
        
        if (input.isEmpty()) {
            return defaultRate;
        }
        
        try {
            double rate = Double.parseDouble(input);
            if (rate >= 0.0 && rate <= 1.0) {
                return rate;
            } else {
                System.out.println("Invalid rate! Using default value: " + defaultRate);
                return defaultRate;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Using default value: " + defaultRate);
            return defaultRate;
        }
    }
    
    private static void printPopulation(Population population, int generation) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("GENERATION " + generation);
        System.out.println("=".repeat(80));
        
        List<Chromosome> chromosomes = population.getChromosomes();
        System.out.printf("%-8s %-50s %-15s%n", "Index", "Chromosome", "Fitness");
        System.out.println("-".repeat(80));
        
        for (int i = 0; i < chromosomes.size(); i++) {
            Chromosome c = chromosomes.get(i);
            System.out.printf("%-8d %-50s %-15.4f%n", i, c.toString(), c.getFitness());
        }
        
        System.out.println("-".repeat(80));
        Chromosome best = population.best();
        System.out.printf("Best Chromosome: %s (Fitness: %.4f)%n", best.toString(), best.getFitness());
        System.out.println("=".repeat(80) + "\n");
    }
    
    public static Population evolve(Population population, Selection selection,
                                    Crossover crossover, Mutation mutation,
                                    Replacement replacement, FitnessEvaluator fitnessEvaluator,
                                    InfeasibilityHandler infeasibilityHandler) {
        List<Chromosome> currentPop = population.getChromosomes();
        int populationSize = currentPop.size();
        
        List<Chromosome> parents = selection.select(currentPop);
        
        List<Chromosome> offspring = new ArrayList<>();
        for (int i = 0; i < parents.size() - 1; i += 2) {
            Chromosome parent1 = parents.get(i);
            Chromosome parent2 = parents.get(i + 1);
            
            List<Chromosome> children = crossover.apply(parent1, parent2);
            offspring.addAll(children);
        }
        
        for (Chromosome child : offspring) {
            mutation.mutate(child);
            child.calculateFitness(fitnessEvaluator, infeasibilityHandler);
        }
        
        List<Chromosome> newPopulationChromosomes = replacement.apply(currentPop, offspring, populationSize);
        
        Population newPopulation = new Population(newPopulationChromosomes);
        
        return newPopulation;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please Enter the size of Population:");
        int populationSize = scanner.nextInt();
        scanner.nextLine();
        
        
        System.out.println("\nChoose Selection method:\n1) RouletteWheel\n2) TournamentSelection");

        int selectionChoice = getValidatedInput(scanner, 1, 2, "Enter choice (1-2):");
        
        System.out.println("Size of Each Generation set to " + populationSize/2  + ".");
        System.out.print("Press Enter to confirm OR enter another value (2 - " + populationSize +" ): ");
        int generationSize  = scanner.nextInt();
        scanner.nextLine();
        if( generationSize < 2 || generationSize > populationSize) {
            generationSize = populationSize / 2;
            System.out.println("Invalid input! Using default value: " + generationSize);
        }
        
        Selection selection;
        switch (selectionChoice) {
            case 1: 
                selection = new RouletteWheel(generationSize);
                System.out.println("Selected: RouletteWheel with size " + generationSize);
                break;
            case 2: 
                selection = new TournamentSelection(populationSize/4, generationSize);
                System.out.println("Selected: TournamentSelection with tournament size " + populationSize/4 + 
                                    " and selection size " + generationSize);
                break;
            default: 
                selection = new TournamentSelection(populationSize/4, generationSize);
                break;
        }

        
        
        System.out.println("\nChoose Crossover method:\n1) OnePointCrossover\n2) TwoPointCrossover\n3) UniformCrossover");

        int crossoverChoice = getValidatedInput(scanner, 1, 3, "Enter choice (1-3):");
        double crossoverRate = getRateInput(scanner, 0.7, "Crossover");
        
        Crossover crossover;
        switch (crossoverChoice) {
            case 1: 
                crossover = new OnePointCrossover(crossoverRate);
                System.out.println("Selected: OnePointCrossover with rate " + crossoverRate);
                break;
            case 2: 
                crossover = new TwoPointCrossover(crossoverRate);
                System.out.println("Selected: TwoPointCrossover with rate " + crossoverRate);
                break;
            case 3: 
                crossover = new UniformCrossover(crossoverRate);
                System.out.println("Selected: UniformCrossover with rate " + crossoverRate);
                break;
            default: 
                crossover = new UniformCrossover(crossoverRate);
                break;
        }
        
        System.out.println("\nMutation method: RandomChoiceMutation");
        double mutationRate = getRateInput(scanner, 0.05, "Mutation");
        Mutation mutation = new RandomChoiceMutation(mutationRate, 0, 3);
        System.out.println("Selected: RandomChoiceMutation with rate " + mutationRate);
        
        
        System.out.println("\nChoose Replacement method:\n1) ElitismReplacement\n2) GenerationalReplacement\n3) SteadyStateReplacement");
        int replacementChoice = getValidatedInput(scanner, 1, 3, "Enter choice (1-3):");
        
        Replacement replacement;
        switch (replacementChoice) {
            case 1: 
                replacement = new ElitismReplacement(populationSize / 4);
                System.out.println("Selected: ElitismReplacement");
                break;
            case 2: 
                replacement = new GenerationalReplacement();
                System.out.println("Selected: GenerationalReplacement");
                break;
            case 3: 
                replacement = new SteadyStateReplacement(populationSize / 4);
                System.out.println("Selected: SteadyStateReplacement");
                break;
            default: 
                replacement = new GenerationalReplacement();
                break;
        }
        
        FitnessEvaluator fitnessEvaluator = new GymWorkoutFitnessEvaluator();
        InfeasibilityHandler infeasibilityHandler = new GymWorkoutInfeasibilityHandler();
        Population population = new Population(populationSize, fitnessEvaluator, infeasibilityHandler);
        
        System.out.println("\nPlease Enter Number of Generations:");
        int generations = scanner.nextInt();
        scanner.nextLine(); 
        
        printPopulation(population, 0);
        
        for (int i = 0; i < generations; i++) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            population = evolve(population, selection, crossover, mutation, replacement,
                            fitnessEvaluator, infeasibilityHandler);
            printPopulation(population, i + 1);
            
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("FINAL RESULTS");
        System.out.println("=".repeat(80));
        Chromosome best = population.best();
        System.out.println("Best Solution Found:");
        System.out.println("  Chromosome: " + best.toString());
        System.out.println("  Fitness: " + best.getFitness());
        System.out.println("=".repeat(80));
        
        scanner.close();
    }
}
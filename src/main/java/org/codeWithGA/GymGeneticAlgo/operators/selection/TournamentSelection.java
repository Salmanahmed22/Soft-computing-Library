package org.codeWithGA.GymGeneticAlgo.operators.selection;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TournamentSelection implements Selection {
    private int size; // tournament size
    private final int numberOfNeededParents;
    private Random random = new Random();

    public TournamentSelection(int size, int numberOfNeededParents) {
        this.size = size;
        this.numberOfNeededParents = numberOfNeededParents;
    }

    @Override
    public List<Chromosome> select(List<Chromosome> population) {

        if (population.size() < size) {
            size = population.size();
        }

        List<Chromosome> selectedParents = new ArrayList<>();
        for (int i = 0; i < numberOfNeededParents; i++) {
            List<Chromosome> tournament = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                Chromosome randomChromosome = population.get(random.nextInt(population.size()));
                tournament.add(randomChromosome);
            }
            Chromosome winner = Collections.max(tournament,
                    (c1, c2) -> Double.compare(c1.getFitness(), c2.getFitness()));
            selectedParents.add(winner);
        }
        return selectedParents;
    }
}

package org.codeWithGA.GeneticAlgorithm.operators.selection;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.codeWithGA.GeneticAlgorithm.core.Chromosome;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheel implements Selection{
    private int numberOfNeededParents;

    public RouletteWheel(int numberOfNeededParents) {
        this.numberOfNeededParents = numberOfNeededParents;
    }
    public void setNumberOfNeededParents(int numberOfNeededParents) {
        this.numberOfNeededParents = numberOfNeededParents;
    }

    @Override
    public List<Chromosome> select(List<Chromosome> population){
        double sumOfFitness = 0;
        for (Chromosome chromosome : population){
            sumOfFitness += chromosome.getFitness();
        }
        List<Chromosome> selectedParents = new ArrayList<Chromosome>();
        List<Pair<Double, Double>> ranges = new ArrayList<>();
        double rangeStart = 0;
        for (Chromosome chromosome : population){
            // 1e-9 gives chromosomes with 0 fitness small value 
            Double probability =
                    (chromosome.getFitness() + 1e-9) / (sumOfFitness + 1e-9 * population.size());
            Pair<Double,Double> range;
            range = new ImmutablePair<>(rangeStart, rangeStart + probability);
            rangeStart += probability;
            ranges.add(range);
        }
        while (selectedParents.size() < numberOfNeededParents){
            Double randProb = Math.random();
            int i = 0;
            for (Pair<Double, Double> range : ranges){
                // only left boundary inclusive except for the last range
                if (randProb >= range.getLeft() && randProb < range.getRight()||
                        (i == ranges.size() - 1 && randProb <= range.getRight())){
                    selectedParents.add(population.get(i));
                    break;
                }
                i++;
            }
        }
        return selectedParents;
    }
}

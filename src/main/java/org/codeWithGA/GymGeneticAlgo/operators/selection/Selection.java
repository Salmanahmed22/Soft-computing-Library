package org.codeWithGA.GymGeneticAlgo.operators.selection;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;

import java.util.List;

public interface Selection {
    List<Chromosome> select(List<Chromosome> population);
}

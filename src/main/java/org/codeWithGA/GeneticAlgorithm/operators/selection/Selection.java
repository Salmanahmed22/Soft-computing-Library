package org.codeWithGA.GeneticAlgorithm.operators.selection;

import org.codeWithGA.GeneticAlgorithm.core.Chromosome;

import java.util.List;

public interface Selection {
    List<Chromosome> select(List<Chromosome> population);
}

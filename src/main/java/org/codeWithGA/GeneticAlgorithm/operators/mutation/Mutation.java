package org.codeWithGA.GeneticAlgorithm.operators.mutation;

import org.codeWithGA.GeneticAlgorithm.core.Chromosome;

public interface Mutation {
    void mutate(Chromosome chromosome);
}

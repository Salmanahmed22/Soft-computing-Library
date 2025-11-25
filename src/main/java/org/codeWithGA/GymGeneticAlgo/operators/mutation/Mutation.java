package org.codeWithGA.GymGeneticAlgo.operators.mutation;

import org.codeWithGA.GymGeneticAlgo.core.Chromosome;

public interface Mutation {
    void mutate(Chromosome chromosome);
}

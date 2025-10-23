package org.codeWithGA.operators.replacement;

import org.codeWithGA.core.Chromosome;
import java.util.List;

public interface Replacement {
    List<Chromosome> apply(List<Chromosome> oldPopulation, List<Chromosome> offspring, int populationSize);
}

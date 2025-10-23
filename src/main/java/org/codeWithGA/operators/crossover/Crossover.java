package org.codeWithGA.operators.crossover;
import org.codeWithGA.core.Chromosome;

import java.util.List;

public interface Crossover {
    List<Chromosome> apply(Chromosome parent1, Chromosome parent2);
}

package org.codeWithGA.operators.mutation;

import org.codeWithGA.core.Chromosome;

public interface Mutation {
    void mutate(Chromosome chromosome);
}

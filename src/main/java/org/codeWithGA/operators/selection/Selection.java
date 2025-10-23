package org.codeWithGA.operators.selection;

import org.codeWithGA.core.Chromosome;

import java.util.List;

public interface Selection {
    List<Chromosome> select(List<Chromosome> population);
}

package org.codeWithGA.FuzzyLogic.EmployeeStressLevel;

import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;

import java.util.Map;

public interface Defuzzifier {
    double defuzzify(LinguisticVariable outputVar, Map<Double, Double> aggregatedShape);
}

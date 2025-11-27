package org.codeWithGA.FuzzyLogic.inference;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;
import org.codeWithGA.FuzzyLogic.rule.FuzzyRule;

import org.codeWithGA.FuzzyLogic.rule.RuleBaseManager;
import org.codeWithGA.FuzzyLogic.operators.*;

import org.codeWithGA.FuzzyLogic.defuzzification.CentroidDefuzzifier;
import org.codeWithGA.FuzzyLogic.defuzzification.MeanOfMaxDefuzzifier;
import java.util.*;


public class MamdaniInferenceEngine implements  FuzzyInferenceEngine {
    private Operator andOp = new MinAnd();
    private Operator orOp = new MaxOr();
    private String defuzzMethod = "centroid"; // default

    public void setDefuzzMethod(String method) {
        this.defuzzMethod = method;
    }


    @Override
    public double evaluate(Map<String, LinguisticVariable> inputVariables, Map<String, Double> inputValues, LinguisticVariable outputVar, RuleBaseManager ruleBase) {

        List<FuzzyRule> rules = ruleBase.getEnabledRules();

        Map<String, Map<String, Double>> fuzzifiedInputs = new HashMap<>();

        for (Map.Entry<String, Double> entry : inputValues.entrySet()) {
            LinguisticVariable var = inputVariables.get(entry.getKey());
            if (var != null) {
                fuzzifiedInputs.put(var.getName(), var.fuzzify(entry.getValue()));
            }
        }

        Map<Double, Double> aggregatedOutputShape = new TreeMap<>();
        double min = outputVar.getMinValue();
        double max = outputVar.getMaxValue();
        int steps = 100;
        double stepSize = (max - min) / steps;
        for (int i = 0; i <= steps; i++) {
            aggregatedOutputShape.put(min + i * stepSize, 0.0);
        }

        for (FuzzyRule rule : rules) {
            if (!rule.getConsequent().isMamdani()) continue;

            double firingStrength = rule.getFiringStrength(fuzzifiedInputs, andOp);

            var fuzzySet = outputVar.getSet(rule.getConsequent().getFuzzySetName());

            for (Map.Entry<Double, Double> entry : aggregatedOutputShape.entrySet()) {
                double x = entry.getKey();
                double originalMembership = fuzzySet.evaluate(x);
                double impliedMembership = Math.min(originalMembership, firingStrength);

                double currentAggregated = aggregatedOutputShape.get(x);
                aggregatedOutputShape.put(x, orOp.operate(currentAggregated, impliedMembership));
            }
        }

        if (defuzzMethod.equals("centroid")) {
            return new CentroidDefuzzifier().defuzzify(outputVar, aggregatedOutputShape);
        } else {
            return new MeanOfMaxDefuzzifier().defuzzify(outputVar, aggregatedOutputShape);
        }

    }
}




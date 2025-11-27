package org.codeWithGA.FuzzyLogic.inference;

import java.util.*;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;
import org.codeWithGA.FuzzyLogic.operators.Operator;
import org.codeWithGA.FuzzyLogic.operators.MinAnd;
import org.codeWithGA.FuzzyLogic.rule.RuleBaseManager;
import org.codeWithGA.FuzzyLogic.rule.FuzzyRule;


//BTW I made the Zero-order

public class SugenoInferenceEngine implements FuzzyInferenceEngine {

    private Operator andOp = new MinAnd();

    @Override
    public double evaluate(Map<String, LinguisticVariable> inputVariables,
                           Map<String, Double> inputValues,
                           LinguisticVariable outputVar,
                           RuleBaseManager ruleBase) {

        List<FuzzyRule> rules = ruleBase.getEnabledRules();

        Map<String, Map<String, Double>> fuzzifiedInputs = new HashMap<>();
        for (Map.Entry<String, Double> entry : inputValues.entrySet()) {
            LinguisticVariable var = inputVariables.get(entry.getKey());
            if (var != null) {
                fuzzifiedInputs.put(var.getName(), var.fuzzify(entry.getValue()));
            }
        }

        double numerator = 0.0;
        double denominator = 0.0;

        for (FuzzyRule rule : rules) {

            if (rule.getConsequent().isMamdani()) continue;

            double w_i = rule.getFiringStrength(fuzzifiedInputs, andOp);
            double z_i = rule.getConsequent().getSingletonValue();

            numerator += w_i * z_i;
            denominator += w_i;
        }

        if (denominator == 0.0)
            return 0.0;

        return numerator / denominator;
    }
}



package org.codeWithGA.FuzzyLogic.inference;
import java.util.*;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;
import org.codeWithGA.FuzzyLogic.operators.AndOperator;
import org.codeWithGA.FuzzyLogic.operators.MinAnd;

import org.codeWithGA.FuzzyLogic.operators.*;
import org.codeWithGA.FuzzyLogic.rule.RuleBaseManager;
import org.codeWithGA.FuzzyLogic.rule.FuzzyRule;
import org.codeWithGA.FuzzyLogic.defuzzification.SugenoWeightedAverage;


//BTW I made the Zero-order
public class SugenoInferenceEngine implements  FuzzyInferenceEngine {
    private AndOperator andOp = new MinAnd();
    private SugenoWeightedAverage weightedAvg = new SugenoWeightedAverage();

    public void setAndOperator(AndOperator op) { this.andOp = op; }

    @Override
    public double evaluate(Map<String, LinguisticVariable> inputVariables,Map<String, Double> inputValues, LinguisticVariable outputVar, RuleBaseManager ruleBase) {
        List<FuzzyRule> rules = ruleBase.getEnabledRules();

        List<Double> firingStrengths = new ArrayList<>();
        List<Double> singletonOutputs = new ArrayList<>();

        for (FuzzyRule rule : rules) {
            if (rule.getConsequent().isMamdani()) continue;

            double w_i = rule.getFiringStrength(andOp);
            double z_i = rule.getConsequent().getSingletonValue();

            firingStrengths.add(w_i);
            singletonOutputs.add(z_i);
        }

        return weightedAvg.calculate(firingStrengths, singletonOutputs);
    }
}

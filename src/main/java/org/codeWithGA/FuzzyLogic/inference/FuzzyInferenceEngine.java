package org.codeWithGA.FuzzyLogic.inference;
import org.codeWithGA.FuzzyLogic.rule.RuleBaseManager;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;
import java.util.Map;

public interface FuzzyInferenceEngine {
    double evaluate(
            Map<String, LinguisticVariable> inputVariables,
            Map<String, Double> inputValues,
            LinguisticVariable outputVar,
            RuleBaseManager ruleBase
    );

}



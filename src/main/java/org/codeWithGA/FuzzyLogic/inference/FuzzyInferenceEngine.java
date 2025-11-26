package org.codeWithGA.FuzzyLogic.inference;
import org.codeWithGA.FuzzyLogic.rule.RuleBaseManager;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;
import org.codeWithGA.FuzzyLogic.operators.AndOperator;
import org.codeWithGA.FuzzyLogic.operators.OrOperator;
import java.util.Map;

public interface FuzzyInferenceEngine {
    double evaluate(
            Map<String, LinguisticVariable> inputVariables,
            Map<String, Double> inputValues,
            LinguisticVariable outputVar,
            RuleBaseManager ruleBase
    );

}



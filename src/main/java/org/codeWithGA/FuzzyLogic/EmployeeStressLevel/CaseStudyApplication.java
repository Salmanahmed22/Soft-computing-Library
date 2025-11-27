package org.codeWithGA.FuzzyLogic.EmployeeStressLevel;

import org.codeWithGA.FuzzyLogic.defuzzification.CentroidDefuzzifier;
import org.codeWithGA.FuzzyLogic.inference.EvaluationResult;
import org.codeWithGA.FuzzyLogic.inference.FuzzyInferenceEngine;
import org.codeWithGA.FuzzyLogic.inference.MamdaniInferenceEngine;
import org.codeWithGA.FuzzyLogic.membership.TrapezoidalMF;
import org.codeWithGA.FuzzyLogic.membership.TriangularMF;
import org.codeWithGA.FuzzyLogic.operators.MaxOr;
import org.codeWithGA.FuzzyLogic.operators.MinAnd;
import org.codeWithGA.FuzzyLogic.rule.Antecedent;
import org.codeWithGA.FuzzyLogic.rule.Consequent;
import org.codeWithGA.FuzzyLogic.rule.FuzzyRule;
import org.codeWithGA.FuzzyLogic.rule.RuleBaseManager;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;

public class CaseStudyApplication {




        public static void main(String[] args) {

            // 1. Define Input Linguistic Variables
            LinguisticVariable sleepingHours = new LinguisticVariable("SleepingHours", 0, 12);
            sleepingHours.addFuzzySet("Very_Low", new TrapezoidalMF(0, 0, 2, 4));
            sleepingHours.addFuzzySet("Low", new TriangularMF(3, 5, 7));
            sleepingHours.addFuzzySet("Normal", new TrapezoidalMF(6, 8, 12, 12));

            LinguisticVariable workingHours = new LinguisticVariable("WorkingHours", 0, 16);
            workingHours.addFuzzySet("Low", new TrapezoidalMF(0, 0, 4, 6));
            workingHours.addFuzzySet("Moderate", new TriangularMF(5, 8, 11));
            workingHours.addFuzzySet("High", new TrapezoidalMF(10, 12, 16, 16));

            LinguisticVariable healthLevel = new LinguisticVariable("HealthLevel", 0, 10);
            healthLevel.addFuzzySet("Poor", new TrapezoidalMF(0, 0, 2, 4));
            healthLevel.addFuzzySet("Average", new TriangularMF(3, 5, 7));
            healthLevel.addFuzzySet("Good", new TrapezoidalMF(6, 8, 10, 10));

            // 2. Define Output Linguistic Variable (Stress Level)
            LinguisticVariable stressLevel = new LinguisticVariable("StressLevel", 0, 10);
            stressLevel.addFuzzySet("Low", new TrapezoidalMF(0, 0, 2, 4));
            stressLevel.addFuzzySet("Moderate", new TriangularMF(3, 5, 7));
            stressLevel.addFuzzySet("High", new TrapezoidalMF(6, 8, 10, 10));

            // 3. Define Rules
            RuleBaseManager ruleBase = new RuleBaseManager();

            // Rule examples
            ruleBase.addRule(new FuzzyRule(
                    new Antecedent("SleepingHours", "Very_Low").and(new Antecedent("WorkingHours", "High")),
                    new Consequent("StressLevel", "High")
            ));
            ruleBase.addRule(new FuzzyRule(
                    new Antecedent("SleepingHours", "Normal").and(new Antecedent("WorkingHours", "Low")),
                    new Consequent("StressLevel", "Low")
            ));
            ruleBase.addRule(new FuzzyRule(
                    new Antecedent("SleepingHours", "Low").and(new Antecedent("WorkingHours", "Moderate")).and(new Antecedent(healthLevel, "Average")),
                    new Consequent("StressLevel", "Moderate")
            ));
            ruleBase.addRule(new FuzzyRule(
                    new Antecedent("HealthLevel", "Poor"),
                    new Consequent("StressLevel", "High")
            ));
            ruleBase.addRule(new FuzzyRule(
                    new Antecedent(healthLevel, "Good").and(new Antecedent("SleepingHours", "Normal")),
                    new Consequent("StressLevel", "Low")
            ));

            // 4. Initialize Fuzzy Inference Engine
            FuzzyInferenceEngine engine = new MamdaniInferenceEngine(ruleBase, new MinAnd(), new MaxOr());

            // 5. Set Crisp Input Values
            double inputSleepingHours = 6.0;
            double inputWorkingHours = 10.0;
            double inputHealthLevel = 4.0;

            engine.setInputValue("SleepingHours", inputSleepingHours);
            engine.setInputValue("WorkingHours", inputWorkingHours);
            engine.setInputValue(healthLevel, inputHealthLevel);

            // 6. Evaluate
            EvaluationResult result = engine.evaluate();

            // 7. Defuzzify Output
            CentroidDefuzzifier defuzzifier = new CentroidDefuzzifier();
            double crispStressLevel = defuzzifier.defuzzify("StressLevel", result.getOutputMemberships(stressLevel));

            // 8. Display Results
            System.out.println("Input Values:");
            System.out.println("Sleeping Hours: " + inputSleepingHours);
            System.out.println("Working Hours: " + inputWorkingHours);
            System.out.println("Health Level: " + inputHealthLevel);
            System.out.println("\nEvaluated Stress Level: " + crispStressLevel);
        }

}

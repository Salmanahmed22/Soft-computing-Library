package org.codeWithGA.FuzzyLogic.EmployeeStressLevel;

import org.codeWithGA.FuzzyLogic.inference.*;
import org.codeWithGA.FuzzyLogic.membership.*;
import org.codeWithGA.FuzzyLogic.rule.*;
import org.codeWithGA.FuzzyLogic.variables.*;

import java.util.*;

public class CaseStudyApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // -------------------------------------------------
        // 1. Build Linguistic Variables
        // -------------------------------------------------
        System.out.println("Step 1: Building Linguistic Variables...");

        LinguisticVariable sleepingHours = new LinguisticVariable("SleepingHours", 0, 12);
        sleepingHours.addFuzzySet("Very_Low", new TrapezoidalMF(0, 0, 2, 4));
        sleepingHours.addFuzzySet("Low", new TriangularMF(2, 5, 7));
        sleepingHours.addFuzzySet("Normal", new TriangularMF(6, 8, 10));
        sleepingHours.addFuzzySet("High", new TrapezoidalMF(8, 10, 12, 12));

        LinguisticVariable workingHours = new LinguisticVariable("WorkingHours", 0, 16);
        workingHours.addFuzzySet("Low", new TriangularMF(0, 2, 7));
        workingHours.addFuzzySet("Moderate", new TriangularMF(4, 8, 12));
        workingHours.addFuzzySet("High", new TrapezoidalMF(9, 12, 16, 16));

        LinguisticVariable healthLevel = new LinguisticVariable("HealthLevel", 0, 10);
        healthLevel.addFuzzySet("Poor", new TriangularMF(0, 0, 5));
        healthLevel.addFuzzySet("Average", new TriangularMF(3, 5, 7));
        healthLevel.addFuzzySet("Good", new TriangularMF(5, 8, 10));

        LinguisticVariable stress = new LinguisticVariable("Stress", 0, 10);
        stress.addFuzzySet("Low", new TriangularMF(0, 0, 5));
        stress.addFuzzySet("Medium", new TriangularMF(3, 5, 7));
        stress.addFuzzySet("High", new TriangularMF(5, 8, 10));

        System.out.println("Linguistic variables created successfully.\n");

        // -------------------------------------------------
        // 2. Build Rule Base
        // -------------------------------------------------
        System.out.println("Step 2: Building Rule Base...");

        RuleBaseManager ruleBase = new RuleBaseManager();
        ruleBase.addRule(new FuzzyRule()
                .when("SleepingHours", "Low")
                .and("WorkingHours", "High")
                .then("Stress", "High"));
        ruleBase.addRule(new FuzzyRule()
                .when("SleepingHours", "Normal")
                .and("WorkingHours", "Moderate")
                .then("Stress", "Medium"));
        ruleBase.addRule(new FuzzyRule()
                .when("HealthLevel", "Good")
                .then("Stress", "Low"));

        // Sugeno example rules
        ruleBase.addRule(new FuzzyRule()
                .when("SleepingHours", "Low")
                .then("Stress", 8));
        ruleBase.addRule(new FuzzyRule()
                .when("HealthLevel", "Good")
                .then("Stress", 2));

        System.out.println("Rule base created with " + ruleBase.getRules().size() + " rules.\n");

        // -------------------------------------------------
        // 3. Ask user for inference type
        // -------------------------------------------------
        System.out.println("Step 3: Select Inference Type:");
        System.out.println("1 → Mamdani");
        System.out.println("2 → Sugeno");
        int choice = sc.nextInt();

        FuzzyInferenceEngine engine;

        if (choice == 1) {
            MamdaniInferenceEngine m = new MamdaniInferenceEngine();
            System.out.println("Choose Defuzzification:");
            System.out.println("1 → Centroid");
            System.out.println("2 → Mean of Maximum");
            int dChoice = sc.nextInt();
            if (dChoice == 1) {
                m.setDefuzzMethod("centroid");
                System.out.println("Using Mamdani + Centroid\n");
            } else {
                m.setDefuzzMethod("mom");
                System.out.println("Using Mamdani + Mean of Maximum\n");
            }
            engine = m;
        } else {
            System.out.println("Using Sugeno Inference (Weighted Average)\n");
            engine = new SugenoInferenceEngine();
        }

        // -------------------------------------------------
        // 4. Get input values
        // -------------------------------------------------
        System.out.println("Step 4: Enter Employee Data for Evaluation");

        System.out.print("Sleeping Hours: ");
        double inputSleepingHours = sc.nextDouble();
        System.out.print("Working Hours: ");
        double inputWorkingHours = sc.nextDouble();
        System.out.print("Health Level: ");
        double inputHealthLevel = sc.nextDouble();

        Map<String, Double> inputs = new HashMap<>();
        inputs.put("SleepingHours", inputSleepingHours);
        inputs.put("WorkingHours", inputWorkingHours);
        inputs.put("HealthLevel", inputHealthLevel);

        Map<String, LinguisticVariable> inputVars = new HashMap<>();
        inputVars.put("SleepingHours", sleepingHours);
        inputVars.put("WorkingHours", workingHours);
        inputVars.put("HealthLevel", healthLevel);

        // -------------------------------------------------
        // 5. Visualize Membership Values
        // -------------------------------------------------
        System.out.println("\nStep 5: Membership Values for Inputs");
        for (String varName : inputVars.keySet()) {
            LinguisticVariable var = inputVars.get(varName);
            double val = inputs.get(varName);
            System.out.println("Input: " + varName + " = " + val);
            for (String setName : var.getFuzzySetNames()) {
                double degree = var.getMembershipValue(setName, val);
                System.out.printf("  %s: %.3f%n", setName, degree);
            }
        }

        // -------------------------------------------------
        // 6. Evaluate and visualize rules
        // -------------------------------------------------
        System.out.println("\nStep 6: Rule Evaluation");

        double result = engine.evaluate(inputVars, inputs, stress, ruleBase);

        System.out.println("\nStep 7: Final Output");
        System.out.println("=============================");
        System.out.println("Final Stress Level Output = " + result);
        System.out.println("=============================");
    }
}

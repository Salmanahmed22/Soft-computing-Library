//package org.codeWithGA.FuzzyLogic.EmployeeStressLevel;
//
//import org.codeWithGA.FuzzyLogic.membership.TrapezoidalMF;
//import org.codeWithGA.FuzzyLogic.membership.TriangularMF;
//import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;
//
//public class CaseStudyApplication {
//        public static void main(String[] args) {
//
//            // -------------------------------
//            // 1. Create Linguistic Variables
//            // -------------------------------
//
//            // Sleeping Hours (0–12)
//            LinguisticVariable sleepingHours = new LinguisticVariable("SleepingHours", 0, 12);
//            sleepingHours.addFuzzySet("Very_Low", new TrapezoidalMF(0, 0, 2, 4));
//            sleepingHours.addFuzzySet("Low", new TriangularMF(2, 5, 7));
//            sleepingHours.addFuzzySet("Normal", new TriangularMF(6, 8, 10));
//            sleepingHours.addFuzzySet("High", new TrapezoidalMF(8, 10, 12, 12));
//
//            // Working Hours (0–16)
//            LinguisticVariable workingHours = new LinguisticVariable("WorkingHours", 0, 16);
//            workingHours.addFuzzySet("Low", new TriangularMF(0, 2, 7));
//            workingHours.addFuzzySet("Moderate", new TriangularMF(4, 8, 12));
//            workingHours.addFuzzySet("High", new TrapezoidalMF(9, 12, 16, 16));
//
//            // Health Level (0–10)
//            LinguisticVariable healthLevel = new LinguisticVariable("HealthLevel", 0, 10);
//            healthLevel.addFuzzySet("Poor", new TriangularMF(0, 0, 5));
//            healthLevel.addFuzzySet("Average", new TriangularMF(3, 5, 7));
//            healthLevel.addFuzzySet("Good", new TriangularMF(5, 8, 10));
//
//            // -------------------------------
//            // 2. Example crisp inputs
//            // -------------------------------
//            double sleep = 6;   // 5 hours of sleep
//            double work  = 10;  // 10 hours of work
//            double health = 4;  // health rating = 4/10
//
//            // -------------------------------
//            // 3. Fuzzify inputs
//            // -------------------------------
//            System.out.println("\n--- Fuzzy Evaluation ---\n");
//
//            System.out.println("Sleeping Hours = " + sleep);
//            sleepingHours.fuzzify(sleep).forEach((label, value) ->
//                    System.out.println("  " + label + ": " + value));
//
//            System.out.println("\nWorking Hours = " + work);
//            workingHours.fuzzify(work).forEach((label, value) ->
//                    System.out.println("  " + label + ": " + value));
//
//            System.out.println("\nHealth Level = " + health);
//            healthLevel.fuzzify(health).forEach((label, value) ->
//                    System.out.println("  " + label + ": " + value));
//
//            // --------------------------------
//            // 4. Next step (if needed):
//            // - Apply fuzzy rules
//            // - Calculate stress level
//            // - Defuzzify
//            // --------------------------------
//        }
//}




package org.codeWithGA.FuzzyLogic.EmployeeStressLevel;

import org.codeWithGA.FuzzyLogic.inference.*;
import org.codeWithGA.FuzzyLogic.membership.*;
import org.codeWithGA.FuzzyLogic.rule.*;
import org.codeWithGA.FuzzyLogic.variables.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CaseStudyApplication {

        public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // -------------------------------------------------
        // 1. Build Linguistic Variables
        // -------------------------------------------------

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

        // Output variable (for Mamdani only)
        LinguisticVariable stress = new LinguisticVariable("Stress", 0, 10);
        stress.addFuzzySet("Low", new TriangularMF(0, 0, 5));
        stress.addFuzzySet("Medium", new TriangularMF(3, 5, 7));
        stress.addFuzzySet("High", new TriangularMF(5, 8, 10));

        // -------------------------------------------------
        // 2. Build Rule Base
        // -------------------------------------------------
        RuleBaseManager ruleBase = new RuleBaseManager();

        File file = new File("src/main/resources/rules.json");
        try{
                ruleBase.loadFromFile(file);
        }
        catch (IOException e) {
                e.printStackTrace();
        }

        // -------------------------------------------------
        // 3. Ask user for inference type
        // -------------------------------------------------

        System.out.println("Choose Inference Type:");
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
                System.out.println("Using Mamdani + Centroid");
            } else {
                m.setDefuzzMethod("mom");
                System.out.println("Using Mamdani + Mean of Max");
            }

            engine = m;

        } else {
            System.out.println("Using Sugeno Inference (Weighted Average)");
            engine = new SugenoInferenceEngine();
        }

        // -------------------------------------------------
        // 4. Example crisp inputs
        // -------------------------------------------------
        Map<String, Double> inputs = new HashMap<>();
        inputs.put("SleepingHours", 6.0);
        inputs.put("WorkingHours", 10.0);
        inputs.put("HealthLevel", 4.0);

        Map<String, LinguisticVariable> inputVars = new HashMap<>();
        inputVars.put("SleepingHours", sleepingHours);
        inputVars.put("WorkingHours", workingHours);
        inputVars.put("HealthLevel", healthLevel);

        // -------------------------------------------------
        // 5. Compute Output
        // -------------------------------------------------
        double result = engine.evaluate(inputVars, inputs, stress, ruleBase);

        System.out.println("\n=============================");
        System.out.println("Final Stress Level Output = " + result);
        System.out.println("=============================");
        sc.close();
}
}



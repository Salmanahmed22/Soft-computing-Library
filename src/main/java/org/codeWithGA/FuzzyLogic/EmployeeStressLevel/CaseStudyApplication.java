package org.codeWithGA.FuzzyLogic.EmployeeStressLevel;

import org.codeWithGA.FuzzyLogic.membership.TrapezoidalMF;
import org.codeWithGA.FuzzyLogic.membership.TriangularMF;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;

public class CaseStudyApplication {
        public static void main(String[] args) {

            // -------------------------------
            // 1. Create Linguistic Variables
            // -------------------------------

            // Sleeping Hours (0–12)
            LinguisticVariable sleepingHours = new LinguisticVariable("SleepingHours", 0, 12);
            sleepingHours.addFuzzySet("Very_Low", new TrapezoidalMF(0, 0, 2, 4));
            sleepingHours.addFuzzySet("Low", new TriangularMF(2, 5, 7));
            sleepingHours.addFuzzySet("Normal", new TriangularMF(6, 8, 10));
            sleepingHours.addFuzzySet("High", new TrapezoidalMF(8, 10, 12, 12));

            // Working Hours (0–16)
            LinguisticVariable workingHours = new LinguisticVariable("WorkingHours", 0, 16);
            workingHours.addFuzzySet("Low", new TriangularMF(0, 2, 7));
            workingHours.addFuzzySet("Moderate", new TriangularMF(4, 8, 12));
            workingHours.addFuzzySet("High", new TrapezoidalMF(9, 12, 16, 16));

            // Health Level (0–10)
            LinguisticVariable healthLevel = new LinguisticVariable("HealthLevel", 0, 10);
            healthLevel.addFuzzySet("Poor", new TriangularMF(0, 0, 5));
            healthLevel.addFuzzySet("Average", new TriangularMF(3, 5, 7));
            healthLevel.addFuzzySet("Good", new TriangularMF(5, 8, 10));

            // -------------------------------
            // 2. Example crisp inputs
            // -------------------------------
            double sleep = 6;   // 5 hours of sleep
            double work  = 10;  // 10 hours of work
            double health = 4;  // health rating = 4/10

            // -------------------------------
            // 3. Fuzzify inputs
            // -------------------------------
            System.out.println("\n--- Fuzzy Evaluation ---\n");

            System.out.println("Sleeping Hours = " + sleep);
            sleepingHours.fuzzify(sleep).forEach((label, value) ->
                    System.out.println("  " + label + ": " + value));

            System.out.println("\nWorking Hours = " + work);
            workingHours.fuzzify(work).forEach((label, value) ->
                    System.out.println("  " + label + ": " + value));

            System.out.println("\nHealth Level = " + health);
            healthLevel.fuzzify(health).forEach((label, value) ->
                    System.out.println("  " + label + ": " + value));

            // --------------------------------
            // 4. Next step (if needed):
            // - Apply fuzzy rules
            // - Calculate stress level
            // - Defuzzify
            // --------------------------------
        }
}

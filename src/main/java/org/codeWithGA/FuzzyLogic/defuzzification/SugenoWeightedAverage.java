package org.codeWithGA.FuzzyLogic.defuzzification;
import java.util.List;
public class SugenoWeightedAverage {
    public double calculate(List<Double> firingStrengths, List<Double> singletonValues) {
        if (firingStrengths.isEmpty() || singletonValues.isEmpty()) {
            throw new IllegalArgumentException("Sugeno calculation requires non-empty lists.");
        }

        if (firingStrengths.size() != singletonValues.size()) {
            throw new IllegalArgumentException("Mismatched sizes: firing strengths and singleton values.");
        }

        double numerator = 0.0;
        double denominator = 0.0;

        for (int i = 0; i < firingStrengths.size(); i++) {
            double w = firingStrengths.get(i);
            double z = singletonValues.get(i);

            numerator += w * z;
            denominator += w;
        }

        if (denominator == 0.0) {
            return 0.0;
        }

        return numerator / denominator;
    }
}

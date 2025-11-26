package org.codeWithGA.FuzzyLogic.defuzzification;
import org.codeWithGA.FuzzyLogic.EmployeeStressLevel.Defuzzifier;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;
import java.util.Map;

public class CentroidDefuzzifier implements Defuzzifier {

    @Override
    public double defuzzify(LinguisticVariable outputVar, Map<Double, Double> aggregatedShape) {
        if (aggregatedShape == null || aggregatedShape.isEmpty()) {
            // No information -> return middle of domain
            double mid = (outputVar.getMinValue() + outputVar.getMaxValue()) / 2.0;
            return mid;
        }

        double numerator = 0.0;
        double denominator = 0.0;

        for (Map.Entry<Double, Double> e : aggregatedShape.entrySet()) {
            double x = e.getKey();
            double mu = e.getValue();
            numerator += x * mu;
            denominator += mu;
        }

        if (denominator <= 0.0) {
            // avoid division by zero -> return mid-domain
            return (outputVar.getMinValue() + outputVar.getMaxValue()) / 2.0;
        }

        return numerator / denominator;
    }
}

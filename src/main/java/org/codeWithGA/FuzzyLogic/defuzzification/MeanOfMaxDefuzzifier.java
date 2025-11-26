package org.codeWithGA.FuzzyLogic.defuzzification;
import org.codeWithGA.FuzzyLogic.EmployeeStressLevel.Defuzzifier;
import org.codeWithGA.FuzzyLogic.variables.LinguisticVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MeanOfMaxDefuzzifier implements Defuzzifier {

    @Override
    public double defuzzify(LinguisticVariable outputVar, Map<Double, Double> aggregatedShape) {
        if (aggregatedShape == null || aggregatedShape.isEmpty()) {
            return (outputVar.getMinValue() + outputVar.getMaxValue()) / 2.0;
        }   

        double maxMu = Double.NEGATIVE_INFINITY;
        for (double mu : aggregatedShape.values()) {
            if (mu > maxMu) maxMu = mu;
        }
        if (maxMu <= 0.0) {
            return (outputVar.getMinValue() + outputVar.getMaxValue()) / 2.0;
        }

        List<Double> xs = new ArrayList<>();
        for (Map.Entry<Double, Double> e : aggregatedShape.entrySet()) {
            if (Double.compare(e.getValue(), maxMu) == 0) {
                xs.add(e.getKey());
            }
        }

        if (xs.isEmpty()) {
            return (outputVar.getMinValue() + outputVar.getMaxValue()) / 2.0;
        }

        double sum = 0.0;
        for (double x : xs) sum += x;
        return sum / xs.size();
    }
}

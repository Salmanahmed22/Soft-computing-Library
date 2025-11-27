package org.codeWithGA.FuzzyLogic.variables;

import org.codeWithGA.FuzzyLogic.membership.MembershipFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
this class is for each input variable in the fuzzy logic
 */
public class LinguisticVariable {
    private String name;
    private double minValue,maxValue;
    private Map<String, MembershipFunction> fuzzySets;

    public LinguisticVariable(String name, double minValue, double maxValue) {
        this.name = name;
        this.minValue = minValue;
        this.maxValue = maxValue;
        fuzzySets = new HashMap<>();
    }

    public void addFuzzySet(String label, MembershipFunction fuzzySet) {
        fuzzySets.put(label, fuzzySet);
    }

    public Map<String, Double> fuzzify(double value) {
        value = clamp(value);
        double finalValue = value;
        return fuzzySets.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().evaluate(finalValue)
                ));
    }
    public String getName() { return name; }
    public MembershipFunction getSet(String setName) {
        return fuzzySets.get(setName);
    }
    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    // IF THE GIVEN VALUE IS OUT OF RANGE IT CLAMPS TO THE RANGE BOUNDARIES
    private double clamp(double v) {
        return Math.max(minValue, Math.min(maxValue, v));
    }

    public String[] getFuzzySetNames() {
        String[] fuzzySetNames = new String[fuzzySets.size()];
        for (int i = 0; i < fuzzySetNames.length; i++) {
            fuzzySetNames[i] = fuzzySets.keySet().toArray()[i].toString();
        }
        return fuzzySetNames;
    }

    public double getMembershipValue(String setName, double val) {
        MembershipFunction fuzzySet = getSet(setName);
        return fuzzySet.evaluate(val);
    }
}

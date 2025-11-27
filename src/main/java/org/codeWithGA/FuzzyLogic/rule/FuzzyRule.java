package org.codeWithGA.FuzzyLogic.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.codeWithGA.FuzzyLogic.operators.Operator;


public class FuzzyRule {
    private String id; 
    private List<Antecedent> antecedents = new ArrayList<>();
    private Consequent consequent;
    private double weight = 1.0;
    private boolean enabled = true;
    private String name; 

    public FuzzyRule() {
        this.id = UUID.randomUUID().toString();
    }

    public FuzzyRule(String name) {
        this();
        this.name = name;
    }

    public FuzzyRule when(String variable, String fuzzySet) {
        antecedents.clear();
        antecedents.add(new Antecedent(variable, fuzzySet, null));
        return this;
    }

    public FuzzyRule and(String variable, String fuzzySet) {
        antecedents.add(new Antecedent(variable, fuzzySet, "AND"));
        return this;
    }

    public FuzzyRule or(String variable, String fuzzySet) {
        antecedents.add(new Antecedent(variable, fuzzySet, "OR"));
        return this;
    }

    public FuzzyRule then(String variable, String fuzzySet) {
        this.consequent = new Consequent(variable, fuzzySet);
        return this;
    }

    public FuzzyRule then(String variable, double singletonValue) {
        this.consequent = new Consequent(variable, singletonValue);
        return this;
    }

    public double getFiringStrength(Map<String, Map<String, Double>> fuzzifiedInputs, Operator andOperator) {
        if (antecedents.isEmpty()) {
            return weight;
        }

        // Import MaxOr for OR operations
        org.codeWithGA.FuzzyLogic.operators.MaxOr orOperator = new org.codeWithGA.FuzzyLogic.operators.MaxOr();
        
        double strength = -1.0; // Use -1 as initialization to distinguish from 0.0
        
        for (int i = 0; i < antecedents.size(); i++) {
            Antecedent ant = antecedents.get(i);
            String varName = ant.getVariableName();
            String setName = ant.getFuzzySetName();

            Map<String, Double> setMemberships = fuzzifiedInputs.get(varName);
            if (setMemberships == null) continue;

            double membershipValue = setMemberships.getOrDefault(setName, 0.0);
            
            if (i == 0) {
                // First antecedent - initialize strength
                strength = membershipValue;
            } else {
                // Check the operator before this antecedent
                String operatorBefore = ant.getOperatorBefore();
                if ("OR".equalsIgnoreCase(operatorBefore)) {
                    strength = orOperator.operate(strength, membershipValue);
                } else {
                    // Default to AND (including when operatorBefore is "AND" or null)
                    strength = andOperator.operate(strength, membershipValue);
                }
            }
        }
        
        // Handle case where no antecedents were processed
        if (strength < 0.0) {
            strength = 1.0;
        }
        
        return strength * weight; 
    }

    public FuzzyRule enable() {
        this.enabled = true;
        return this;
    }

    public FuzzyRule disable() {
        this.enabled = false;
        return this;
    }

    public String getId() {
        return id;
    }

    public List<Antecedent> getAntecedents() {
        return antecedents;
    }

    public void setAntecedents(List<Antecedent> antecedents) {
        this.antecedents = new ArrayList<>(antecedents);
    }

    public Consequent getConsequent() {
        return consequent;
    }

    public void setConsequent(Consequent consequent) {
        this.consequent = consequent;
    }

    public double getWeight() {
        return weight;
    }

    public FuzzyRule setWeight(double weight) {
        if (weight < 0.0) weight = 0.0;
        if (weight > 1.0) weight = 1.0;
        this.weight = weight;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public FuzzyRule setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (name != null) sb.append("[").append(name).append("] ");
        sb.append("IF ");
        for (int i = 0; i < antecedents.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(antecedents.get(i).toString());
        }
        sb.append(" THEN ").append(consequent != null ? consequent.toString() : "null");
        sb.append(" [weight=").append(weight).append(", enabled=").append(enabled).append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FuzzyRule fuzzyRule = (FuzzyRule) o;
        return Objects.equals(id, fuzzyRule.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

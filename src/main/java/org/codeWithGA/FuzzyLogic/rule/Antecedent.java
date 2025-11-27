package org.codeWithGA.FuzzyLogic.rule;

import java.util.Objects;

public class Antecedent {
    private String variableName;
    private String fuzzySetName;
    private String operatorBefore; 

    public Antecedent() {}

    public Antecedent(String variable, String fuzzySet) {
        this(variable, fuzzySet, null);
    }

    public Antecedent(String variable, String fuzzySet, String operatorBefore) {
        this.variableName = variable;
        this.fuzzySetName = fuzzySet;
        this.operatorBefore = operatorBefore;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variable) {
        this.variableName = variable;
    }

    public String getFuzzySetName() {
        return fuzzySetName;
    }

    public void setFuzzySetName(String fuzzySet) {
        this.fuzzySetName = fuzzySet;
    }

    public String getOperatorBefore() {
        return operatorBefore;
    }

    public void setOperatorBefore(String operatorBefore) {
        this.operatorBefore = operatorBefore;
    }

    @Override
    public String toString() {
        if (operatorBefore == null) {
            return variableName + " is " + fuzzySetName;
        }
        return operatorBefore + " " + variableName + " is " + fuzzySetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Antecedent that = (Antecedent) o;
        return Objects.equals(variableName, that.variableName)
                && Objects.equals(fuzzySetName, that.fuzzySetName)
                && Objects.equals(operatorBefore, that.operatorBefore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName, fuzzySetName, operatorBefore);
    }
}

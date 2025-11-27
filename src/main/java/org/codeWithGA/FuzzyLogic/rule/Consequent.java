package org.codeWithGA.FuzzyLogic.rule;

import java.util.Objects;


public class Consequent {
    private String variableName;
    private String fuzzySetName;
    private double singletonValue;
    private boolean isMamdani;

    public Consequent() {}

    // Constructor for Mamdani
    public Consequent(String variableName, String fuzzySetName) {
        this.variableName = variableName;
        this.fuzzySetName = fuzzySetName;
        this.isMamdani = true;
    }

    // Constructor for Sugeno
    public Consequent(String variableName, double singletonValue) {
        this.variableName = variableName;
        this.singletonValue = singletonValue;
        this.isMamdani = false;
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

    public boolean isMamdani() { 
        return isMamdani; 
    }

    public void setMamdani(boolean isMamdani) { 
        this.isMamdani = isMamdani; 
    }

    public double getSingletonValue() {
        return singletonValue;
    }

    public void setSingletonValue(double singletonValue) {
        this.singletonValue = singletonValue;
    }

    @Override
    public String toString() {
        return variableName + " is " + fuzzySetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Consequent that = (Consequent) o;
        return Objects.equals(variableName, that.variableName) && Objects.equals(fuzzySetName, that.fuzzySetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName, fuzzySetName);
    }
}

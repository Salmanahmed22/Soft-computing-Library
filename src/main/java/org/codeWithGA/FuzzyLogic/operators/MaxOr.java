package org.codeWithGA.FuzzyLogic.operators;

public class MaxOr implements Operator {
    @Override
    public double operate(double a, double b) {
        return Math.max(a, b);
    }
}

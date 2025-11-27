package org.codeWithGA.FuzzyLogic.operators;

public class MinAnd implements Operator{
    @Override
    public double operate(double a, double b) {
        return Math.min(a, b);
    }
}

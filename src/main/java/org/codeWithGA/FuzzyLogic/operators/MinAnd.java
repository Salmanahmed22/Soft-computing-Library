package org.codeWithGA.FuzzyLogic.operators;

public class MinAnd implements AndOperator{
    @Override
    public double operate(double a, double b) {
        return Math.min(a, b);
    }
}

package org.codeWithGA.FuzzyLogic.operators;

public class ProbSumOr implements Operator {
    @Override
    public double operate(double a, double b) {
        return a + b - a * b;
    }
}

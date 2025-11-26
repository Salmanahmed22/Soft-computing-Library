package org.codeWithGA.FuzzyLogic.operators;

public class ProbSumOr implements OrOperator {
    @Override
    public double operate(double a, double b) {
        return a + b - a * b;
    }
}

package org.codeWithGA.FuzzyLogic.operators;

public class ProductAnd implements AndOperator {
    @Override
    public double operate(double a, double b) {
        return a * b;
    }
}

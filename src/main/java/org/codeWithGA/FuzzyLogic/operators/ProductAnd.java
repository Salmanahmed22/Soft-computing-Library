package org.codeWithGA.FuzzyLogic.operators;

public class ProductAnd implements Operator {
    @Override
    public double operate(double a, double b) {
        return a * b;
    }
}

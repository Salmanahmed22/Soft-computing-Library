package org.codeWithGA.EmployeeStressLevelFuzzyLogic.membership;

public class TriangularMF implements MembershipFunction{
    private double a,b,c;
    public TriangularMF(double a, double b, double c) {
        if (a > b || b > c)
            throw new IllegalArgumentException("Parameters must satisfy a ≤ b ≤ c");
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double evaluate(double x) {
        if (x <= a || x >= c) return 0;
        else if (x == b) return 1;
        else if (x < b) return (x - a) / (b - a);
        else return (c - x) / (c - b);
    }
}

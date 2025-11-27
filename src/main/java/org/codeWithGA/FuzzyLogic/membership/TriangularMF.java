package org.codeWithGA.FuzzyLogic.membership;

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
        if (x <= a || x >= c) {
            return 0;
        }

        else if (x == b) {
            return 1;
        }

        else if (x < b) {
            double m = 1.0 / (this.b - this.a);

            double b_intercept = -m * this.a;

            return m * x + b_intercept;
        }

        else { // (x > b)
            double m = -1.0 / (this.c - this.b);

            double b_intercept = -m * this.c;

            return m * x + b_intercept;
        }
    }
}
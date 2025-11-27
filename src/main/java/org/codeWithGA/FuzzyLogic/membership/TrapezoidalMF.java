package org.codeWithGA.FuzzyLogic.membership;

public class TrapezoidalMF implements MembershipFunction{
    private double a,b,c,d;
    public TrapezoidalMF(double a, double b, double c, double d){
        if (a > b || b > c || c > d) {
            throw new IllegalArgumentException("Parameters must satisfy a ≤ b ≤ c ≤ d");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    @Override
    public double evaluate(double x){
        if (x <= a || x >= d)
            return 0;

        else if (x >= b && x <= c)
            return 1;

        else if (x < b) {
            double m = 1.0 / (this.b - this.a);

            double b_intercept = -m * this.a;

            return m * x + b_intercept;
        }

        else { // (x > c)
            double m = -1.0 / (this.d - this.c);

            double b_intercept = 1.0 - m * this.c;

            return m * x + b_intercept;
        }
    }
}
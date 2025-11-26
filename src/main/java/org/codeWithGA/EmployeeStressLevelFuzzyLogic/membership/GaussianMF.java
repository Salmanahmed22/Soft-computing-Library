package org.codeWithGA.EmployeeStressLevelFuzzyLogic.membership;

public class GaussianMF implements MembershipFunction {
    private double mean;
    private double sigma;


    public GaussianMF(double mean, double sigma) {
        if (sigma <= 0) {
            throw new IllegalArgumentException("Sigma must be positive.");
        }
        this.mean = mean;
        this.sigma = sigma;
    }

    @Override
    public double evaluate(double x) {
        double exponent = -Math.pow(x - mean, 2) / (2 * sigma * sigma);
        return Math.exp(exponent);
    }
}

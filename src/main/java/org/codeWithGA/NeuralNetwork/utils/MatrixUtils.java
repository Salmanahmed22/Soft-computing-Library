package org.codeWithGA.NeuralNetwork.utils;

public class MatrixUtils {

    public static double[] matVecMul(double[][] mat, double[] vec) {
        int rows = mat.length;
        int cols = mat[0].length;
        double[] result = new double[rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i] += mat[i][j] * vec[j];
            }
        }
        return result;
    }

    public static double[][] transpose(double[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        double[][] t = new double[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                t[j][i] = mat[i][j];
            }
        }
        return t;
    }

    public static double[][] outerProduct(double[] a, double[] b) {
        double[][] result = new double[a.length][b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i][j] = a[i] * b[j];
            }
        }
        return result;
    }

    public static void add(double[][] a, double[][] b) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] += b[i][j];
            }
        }
    }

    public static void add(double[] a, double[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] += b[i];
        }
    }
}
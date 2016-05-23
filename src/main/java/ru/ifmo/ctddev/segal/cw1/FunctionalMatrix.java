package ru.ifmo.ctddev.segal.cw1;

import java.util.List;
import java.util.function.Function;

/**
 * @author Daniyar Itegulov
 */
public class FunctionalMatrix {
    private Function<List<Double>, Double>[][] matrix;

    public FunctionalMatrix(int n) {
        matrix = new Function[n][n];
    }

    public void set(int i, int j, Function<List<Double>, Double> function) {
        matrix[i][j] = function;
    }

    public double[][] apply(List<Double> arguments) {
        int n = matrix.length;
        if (arguments.size() != n) {
            throw new IllegalArgumentException("Illegal argument size!");
        }
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[i][j].apply(arguments);
            }
        }
        return result;
    }
}

package ru.ifmo.ctddev.segal.cw1;

import java.util.List;
import java.util.function.Function;

/**
 * @author Daniyar Itegulov
 */
public class FunctionalVector {
    private Function<List<Double>, Double>[] vector;

    public FunctionalVector(int n) {
        vector = new Function[n];
    }

    public void set(int i, Function<List<Double>, Double> function) {
        vector[i] = function;
    }

    public double[] apply(List<Double> arguments) {
        int n = vector.length;
        if (arguments.size() != n) {
            throw new IllegalArgumentException("Illegal argument size!");
        }
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = vector[i].apply(arguments);
        }
        return result;
    }
}

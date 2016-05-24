package ru.ifmo.ctddev.segal.cw1.system_solvers;

import org.ejml.simple.SimpleMatrix;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static double[][] t(double[] v) {
        double[][] ans = new double[v.length][1];
        for (int i = 0; i < v.length; i++) {
            ans[i][0] = v[i];
        }
        return ans;
    }

    public static double[] extract(SimpleMatrix matrix) {
        if (matrix.numCols() != 1) {
            throw new AssertionError("extract not 1 cols");
        }
        double[] ans = new double[matrix.numRows()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = matrix.get(i, 0);
        }
        return ans;
    }

    public static List<Double> toList(double[] v) {
        List<Double> ans = new ArrayList<>();
        for (double d : v) {
            ans.add(d);
        }
        return ans;
    }

}
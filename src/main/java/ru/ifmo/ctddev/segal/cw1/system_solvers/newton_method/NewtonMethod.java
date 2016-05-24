package ru.ifmo.ctddev.segal.cw1.system_solvers.newton_method;

import org.ejml.simple.SimpleMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalVector;

import java.util.ArrayList;
import java.util.List;

public class NewtonMethod {

    private static double[][] t(double[] v) {
        double[][] ans = new double[v.length][1];
        for (int i = 0; i < v.length; i++) {
            ans[i][0] = v[i];
        }
        return ans;
    }

    private static double[] extract(SimpleMatrix matrix) {
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

    public static double[] solve(double[] start, FunctionalMatrix J, FunctionalVector F, double EPS, int maxIter) {
        SimpleMatrix x = new SimpleMatrix(t(start));
        boolean ok = false;
        for (int it = 0; it < maxIter; it++) {
            SimpleMatrix next = x.minus(
                    new SimpleMatrix(J.apply(toList(extract(x)))).invert()
                            .mult(new SimpleMatrix(t(F.apply(toList(extract(x))))))
            );
            if (next.minus(x).normF() < EPS) {
                x = next;
                ok = true;
                break;
            }
            x = next;
        }
        if (ok) {
            return extract(x);
        } else {
            throw new AssertionError("Newton not converges!!!");
        }
    }
}
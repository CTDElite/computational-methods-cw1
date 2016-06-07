package ru.ifmo.ctddev.segal.cw1.task2;

import ru.ifmo.ctddev.segal.cw1.Constants;
import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalVector;
import ru.ifmo.ctddev.segal.cw1.system_solvers.gradient_method.GradientMethod;
import ru.ifmo.ctddev.segal.cw1.system_solvers.newton_method.NewtonMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Aleksei Latyshev
 */

public class Solver {
    public static final int MAX_ITER = 10000;
    public static final double EPS = 5e-7;

    public static final double[] start = new double[] {13692, 371, 2.0, 14.8, 1559};

    public static Map<Constants.Substance, Double> solve(double T) {
        return solve(T, EPS, MAX_ITER, start);
    }

    public static Map<Constants.Substance, Double> solve(double T, double EPS, int MAX_ITER, double[] start) {
        List<Double> K = Arrays.asList(
                Constants.K(4, T),
                Constants.K(5, T),
                Constants.K(6, T)
        );

        List<Constants.Substance> substances = Arrays.asList(
                Constants.Substance.GA_CL,
                Constants.Substance.GA_CL2,
                Constants.Substance.GA_CL3,
                Constants.Substance.H_CL,
                Constants.Substance.H2
        );
        List<Double> D = substances.stream().map(substance -> substance.D(T)).collect(Collectors.toList());
        List<Double> P = Arrays.asList(
                0D,
                0D,
                0D,
                10_000D,
                0D,
                90_000D
        );
        FunctionalMatrix J = Jacobi.createTask2(K, D);
        FunctionalVector F = Task.task2(K, D, P);
        double[] ans = NewtonMethod.solve(start, J, F, EPS, MAX_ITER);
        Map<Constants.Substance, Double> ret = new HashMap<>();
        for (int i = 0; i < ans.length; i++) {
            ret.put(substances.get(i), P.get(i) - ans[i]);
        }
        return ret;
    }
}
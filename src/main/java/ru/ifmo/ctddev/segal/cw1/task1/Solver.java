package ru.ifmo.ctddev.segal.cw1.task1;

import ru.ifmo.ctddev.segal.cw1.Constants;
import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalVector;
import ru.ifmo.ctddev.segal.cw1.system_solvers.newton_method.NewtonMethod;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Aleksei Latyshev
 */

public class Solver {

    public static final int MAX_ITER = 1000;
    public static final double EPS = 1e-6;
    public static final double[] start = new double[] {0, 0, 0, 0, 0};

    public Map<Constants.Substance, Double> solve(double T) {
        return solve(T, EPS, MAX_ITER, start);
    }

    public Map<Constants.Substance, Double> solve(double T, double EPS, int MAX_ITER, double[] start) {
        List<Double> K = Arrays.asList(
                Constants.K(1, T),
                Constants.K(2, T),
                Constants.K(3, T)
        );

        List<Constants.Substance> substances = Arrays.asList(
                Constants.Substance.AL_CL,
                Constants.Substance.AL_CL2,
                Constants.Substance.AL_CL3,
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
        FunctionalMatrix J = Jacobi.createTask1(K, D);
        FunctionalVector F = Task.task1(K, D, P);
        double[] ans = NewtonMethod.solve(start, J, F, EPS, MAX_ITER);
        Map<Constants.Substance, Double> ret = new HashMap<>();
        for (int i = 0; i < ans.length; i++) {
            ret.put(substances.get(i), ans[i]);
        }
        return ret;
    }

}
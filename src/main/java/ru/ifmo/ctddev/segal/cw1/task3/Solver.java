package ru.ifmo.ctddev.segal.cw1.task3;

import ru.ifmo.ctddev.segal.cw1.Constants;
import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalVector;
import ru.ifmo.ctddev.segal.cw1.system_solvers.newton_method.NewtonMethod;

import java.util.Arrays;
import java.util.List;

public class Solver {
    public static final int MAX_ITER = 1000;
    public static final double EPS = 1e-6;
    public static final double[] start = new double[] {0, 0, 0, 0, 0};

    public double[] solve1(double T, double xg) {
        return solve(T, EPS, MAX_ITER, start, Arrays.asList(
                30 * xg,
                30 * (1 - xg),
                1500D,
                0D,
                0D,
                98470D
        ));
    }

    public double[] solve2(double T, double xg) {
        return solve(T, EPS, MAX_ITER, start, Arrays.asList(
                30 * xg,
                30 * (1 - xg),
                1500D,
                0D,
                9847D,
                98470D - 9847D
        ));
    }

    public double[] solve(double T, double EPS, int MAX_ITER, double[] start, List<Double> P) {
        List<Double> K = Arrays.asList(
                Constants.K(9, T),
                Constants.K(10, T)
        );
        List<Double> D = Arrays.asList(
                Constants.Substance.AL_CL3.D(T),
                Constants.Substance.GA_CL.D(T),
                Constants.Substance.N_H3.D(T),
                Constants.Substance.H_CL.D(T),
                Constants.Substance.H2.D(T)
        );
        FunctionalMatrix J = Jacobi.createTask3(K, D, P);
        FunctionalVector F = Task.task3(K, D, P);
        return NewtonMethod.solve(start, J, F, EPS, MAX_ITER);
    }
}
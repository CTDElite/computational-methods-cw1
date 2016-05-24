package ru.ifmo.ctddev.segal.cw1.task2;

import ru.ifmo.ctddev.segal.cw1.Constants;
import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalVector;
import ru.ifmo.ctddev.segal.cw1.system_solvers.newton_method.NewtonMethod;
import ru.ifmo.ctddev.segal.cw1.task1.Task;

import java.util.Arrays;
import java.util.List;

/**
 * @author Aleksei Latyshev
 */

public class Solver {
    public static final int MAX_ITER = 1000;
    public static final double EPS = 1e-6;
    public static final double[] start = new double[] {0, 0, 0, 0, 0};

    public double[] solve(double T) {
        return solve(T, EPS, MAX_ITER, start);
    }

    public double[] solve(double T, double EPS, int MAX_ITER, double[] start) {
        List<Double> K = Arrays.asList(
                Constants.K(1, T),
                Constants.K(2, T),
                Constants.K(3, T)
        );
        List<Double> D = Arrays.asList(
                Constants.Substance.GA_CL.D(T),
                Constants.Substance.GA_CL2.D(T),
                Constants.Substance.GA_CL3.D(T),
                Constants.Substance.H_CL.D(T),
                Constants.Substance.H2.D(T)
        );
        List<Double> P = Arrays.asList(
                0D,
                0D,
                0D,
                10_000D,
                0D,
                90_000D
        );
        FunctionalMatrix J = ru.ifmo.ctddev.segal.cw1.task1.Jacobi.createTask1(K, D);
        FunctionalVector F = Task.task1(K, D, P);
        return NewtonMethod.solve(start, J, F, EPS, MAX_ITER);
    }

}
package ru.ifmo.ctddev.segal.cw1.task3;

import javafx.util.Pair;
import org.ejml.simple.SimpleMatrix;
import ru.ifmo.ctddev.segal.cw1.Constants;
import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalVector;
import ru.ifmo.ctddev.segal.cw1.system_solvers.Utils;
import ru.ifmo.ctddev.segal.cw1.system_solvers.gradient_method.GradientMethod;
import ru.ifmo.ctddev.segal.cw1.system_solvers.newton_method.NewtonMethod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solver {

    public static final int MAX_ITER = 1000;
    public static final double EPS = 1e-3;
    public static final double[] start = new double[] {14000, 24, 0, 10, 1550, 0.5};

    public static Pair<Map<Constants.Substance, Double>, Double> solve1(double T, double xg) {
        return solve(T, EPS, MAX_ITER, start, Arrays.asList(
                30 * xg,
                30 * (1 - xg),
                1500D,
                0D,
                0D,
                98470D
        ));
    }

    public static Pair<Map<Constants.Substance, Double>, Double> solve2(double T, double xg) {
        return solve(T, EPS, MAX_ITER, start, Arrays.asList(
                30 * xg,
                30 * (1 - xg),
                1500D,
                0D,
                1000000000D,
                98470D - 9847D
        ));
    }

    public static Pair<Map<Constants.Substance, Double>, Double> solve(double T, double EPS, int MAX_ITER, double[] start, List<Double> P) {
        List<Double> K = Arrays.asList(
                Constants.K(9, T),
                Constants.K(10, T)
        );
        List<Constants.Substance> substances = Arrays.asList(
                Constants.Substance.AL_CL3,
                Constants.Substance.GA_CL,
                Constants.Substance.N_H3,
                Constants.Substance.H_CL,
                Constants.Substance.H2
        );
        List<Double> D = substances.stream().map(substance -> substance.D(T)).collect(Collectors.toList());
        FunctionalMatrix J = Jacobi.createTask3(K, D, P);
        FunctionalVector F = Task.task3(K, D, P);
        double[] ans = NewtonMethod.solve(start, J, F, EPS, MAX_ITER);
        System.err.println("zero = " + new SimpleMatrix(new double[][]{F.apply(Utils.toList(ans))}).normF());
        Map<Constants.Substance, Double> ret = new HashMap<>();
        for (int i = 0; i < substances.size(); i++) {
            ret.put(substances.get(i), ans[i]);
        }
        return new Pair<>(ret, ans[ans.length - 1]);
    }
}
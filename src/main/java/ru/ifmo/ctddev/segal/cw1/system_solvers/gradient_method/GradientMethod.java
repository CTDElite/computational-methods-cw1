package ru.ifmo.ctddev.segal.cw1.system_solvers.gradient_method;

import org.ejml.simple.SimpleMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalVector;

import java.util.Arrays;

import static ru.ifmo.ctddev.segal.cw1.system_solvers.Utils.*;

/**
 * @author Aleksei Latyshev
 */


public class GradientMethod {

    public static double[] solve(double[] start, FunctionalMatrix J, FunctionalVector F, double EPS, double MAX_ITER) {
        SimpleMatrix x = new SimpleMatrix(t(start));
        boolean ok = false;
        for (int i = 0; i < MAX_ITER; i++) {
            SimpleMatrix f = new SimpleMatrix(new double[][]{F.apply(toList(extract(x)))});
            SimpleMatrix W = new SimpleMatrix(J.apply(toList(extract(x))));
            SimpleMatrix H = W.mult(W.transpose()).mult(f.transpose());
            double u = f.mult(H).get(0, 0) / H.transpose().mult(H).get(0, 0);
            SimpleMatrix next = x.minus(W.transpose().mult(f.transpose()).scale(u));
            if (x.minus(next).normF() / x.normF() < EPS) {
                x = next;
                ok = true;
                break;
            }
            x = next;
        }
        if (ok) {
            System.err.println("roots = " + Arrays.toString(extract(x)));
            System.err.format("|f(roots)| = %s\n", new SimpleMatrix(t(F.apply(toList(extract(x))))).normF());
            return extract(x);
        } else {
            throw new AssertionError("gradient not converge");
        }
    }

}
package ru.ifmo.ctddev.segal.cw1.system_solvers.newton_method;

import org.ejml.simple.SimpleMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;
import ru.ifmo.ctddev.segal.cw1.FunctionalVector;

import java.util.Arrays;

import static ru.ifmo.ctddev.segal.cw1.system_solvers.Utils.*;

/**
 * @author Aleksei Latyshev
 */


public class NewtonMethod {

    public static double[] solve(double[] start, FunctionalMatrix J, FunctionalVector F, double EPS, int maxIter) {
        SimpleMatrix x = new SimpleMatrix(t(start));
        boolean ok = false;
        for (int it = 0; it < maxIter; it++) {
            SimpleMatrix next = x.minus(
                    new SimpleMatrix(J.apply(toList(extract(x)))).invert()
                            .mult(new SimpleMatrix(t(F.apply(toList(extract(x))))))
            );
            if (next.minus(x).normF() / x.normF() < EPS && next.minus(x).normF() < EPS) {
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
            throw new AssertionError("Newton not converges!!!");
        }
    }
}
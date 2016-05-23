package ru.ifmo.ctddev.segal.cw1.task1;

import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;

import java.util.List;
import java.util.function.Function;

/**
 * @author Daniyar Itegulov
 */
public class Jacobi {

    public static FunctionalMatrix createTask1(List<Double> K, List<Double> D) {
        FunctionalMatrix functionalMatrix = new FunctionalMatrix(5);
        Function<List<Double>, Double> zero = x -> 0D;
        functionalMatrix.set(0, 0, args -> -2 * K.get(0) * args.get(0) * args.get(4));
        functionalMatrix.set(0, 1, zero);
        functionalMatrix.set(0, 2, zero);
        functionalMatrix.set(0, 3, args -> 2 * args.get(3));
        functionalMatrix.set(0, 4, args -> -K.get(0) * args.get(0) * args.get(0));

        functionalMatrix.set(1, 0, zero);
        functionalMatrix.set(1, 1, args -> -K.get(1) * args.get(4));
        functionalMatrix.set(1, 2, zero);
        functionalMatrix.set(1, 3, args -> 2 * args.get(3));
        functionalMatrix.set(1, 4, args -> -K.get(1) * args.get(1));

        functionalMatrix.set(2, 0, zero);
        functionalMatrix.set(2, 1, zero);
        functionalMatrix.set(2, 2, args -> -2 * K.get(2) * args.get(2) * Math.pow(args.get(4), 3D));
        functionalMatrix.set(2, 3, args -> 6 * Math.pow(args.get(3), 5));
        functionalMatrix.set(2, 4, args -> -3 * K.get(2) * args.get(2) * args.get(2) * args.get(4) * args.get(4));

        functionalMatrix.set(3, 0, zero);
        functionalMatrix.set(3, 1, zero);
        functionalMatrix.set(3, 2, zero);
        functionalMatrix.set(3, 3, args -> -D.get(3));
        functionalMatrix.set(3, 4, args -> -2 * D.get(4));

        functionalMatrix.set(4, 0, args -> -D.get(0));
        functionalMatrix.set(4, 1, args -> -2 * D.get(1));
        functionalMatrix.set(4, 2, args -> -3 * D.get(2));
        functionalMatrix.set(4, 3, args -> -D.get(3));
        functionalMatrix.set(4, 4, args -> 0D);

        return functionalMatrix;
    }
}

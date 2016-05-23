package ru.ifmo.ctddev.segal.cw1.task1;

import ru.ifmo.ctddev.segal.cw1.FunctionalVector;

import java.util.List;

/**
 * @author Daniyar Itegulov
 */
public class Task {

    public static FunctionalVector task1(List<Double> K, List<Double> D, List<Double> P) {
        FunctionalVector functionalVector = new FunctionalVector(5);

        functionalVector.set(0, args -> args.get(3) * args.get(3) - K.get(0) * args.get(4));
        functionalVector.set(1, args -> args.get(3) * args.get(3) - K.get(1) * args.get(1) * args.get(4));
        functionalVector.set(2, args -> Math.pow(args.get(3), 6) - K.get(2) * args.get(2) * args.get(2) * Math.pow(args.get(4), 3));
        functionalVector.set(3, args -> D.get(3) * (P.get(3) - args.get(3)) + 2 * D.get(4) * (P.get(4) - args.get(4)));
        functionalVector.set(4, args -> D.get(0) * (P.get(0) - args.get(0)) + 2 * D.get(1) * (P.get(1) - args.get(1)) + 3 * D.get(2) * (P.get(2) - args.get(2)) + D.get(3) * (P.get(3) - args.get(3)));

        return functionalVector;
    }
}

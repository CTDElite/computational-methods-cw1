package task3;

import ru.ifmo.ctddev.segal.cw1.FunctionalVector;

import java.util.List;

/**
 * @author Daniyar Itegulov
 */
public class Task {

    public static FunctionalVector task3(List<Double> K, List<Double> D, List<Double> P) {
        FunctionalVector functionalVector = new FunctionalVector(6);

        functionalVector.set(0, args -> args.get(0) * args.get(2) - K.get(0) * args.get(5) * Math.pow(args.get(3), 3));
        functionalVector.set(1, args -> args.get(1) * args.get(2) - K.get(1) * (1 - args.get(5)) * args.get(3) * args.get(4));
        functionalVector.set(2, args -> D.get(3) * (P.get(3) - args.get(3)) + 2 * D.get(4) * (P.get(4) - args.get(4)) + 3 * D.get(2) * (P.get(2) - args.get(2)));
        functionalVector.set(3, args -> 3 * D.get(0) * (P.get(0) - args.get(0)) + D.get(1) * (P.get(1) - args.get(1)) + D.get(3) * (P.get(3) - args.get(3)));
        functionalVector.set(4, args -> D.get(0) * (P.get(0) - args.get(0)) + D.get(1) * (P.get(1) - args.get(1)) - D.get(2) * (P.get(2) - args.get(2)));
        functionalVector.set(5, args -> D.get(0) * (P.get(0) - args.get(0)) * (1 - args.get(5)) - D.get(1) * (P.get(1) - args.get(1)) * args.get(5));

        return functionalVector;
    }
}

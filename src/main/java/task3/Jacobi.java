package task3;

import ru.ifmo.ctddev.segal.cw1.FunctionalMatrix;

import java.util.List;
import java.util.function.Function;

/**
 * @author Daniyar Itegulov
 */
public class Jacobi {

    public static FunctionalMatrix createTask3(List<Double> K, List<Double> D, List<Double> P) {
        FunctionalMatrix functionalMatrix = new FunctionalMatrix(6);
        Function<List<Double>, Double> zero = x -> 0D;
        functionalMatrix.set(0, 0, args -> args.get(2));
        functionalMatrix.set(0, 1, zero);
        functionalMatrix.set(0, 2, args -> args.get(0));
        functionalMatrix.set(0, 3, args -> -3 * K.get(0) * args.get(5) * Math.pow(args.get(3), 2));
        functionalMatrix.set(0, 4, zero);
        functionalMatrix.set(0, 5, args -> -K.get(0) * Math.pow(args.get(3), 3));

        functionalMatrix.set(1, 0, zero);
        functionalMatrix.set(1, 1, args -> args.get(2));
        functionalMatrix.set(1, 2, args -> args.get(1));
        functionalMatrix.set(1, 3, args -> -K.get(1) * (1 - args.get(5)) * args.get(4));
        functionalMatrix.set(1, 4, args -> -K.get(1) * (1 - args.get(5)) * args.get(3));
        functionalMatrix.set(1, 5, args -> K.get(1) * args.get(3) * args.get(4));

        functionalMatrix.set(2, 0, zero);
        functionalMatrix.set(2, 1, zero);
        functionalMatrix.set(2, 2, args -> -3 * D.get(2));
        functionalMatrix.set(2, 3, args -> -D.get(3));
        functionalMatrix.set(2, 4, args -> -2 * D.get(4));
        functionalMatrix.set(2, 5, zero);

        functionalMatrix.set(3, 0, args -> -3 * D.get(0));
        functionalMatrix.set(3, 1, args -> -D.get(1));
        functionalMatrix.set(3, 2, zero);
        functionalMatrix.set(3, 3, args -> -D.get(3));
        functionalMatrix.set(3, 4, zero);
        functionalMatrix.set(3, 5, zero);

        functionalMatrix.set(4, 0, args -> -D.get(0));
        functionalMatrix.set(4, 1, args -> -D.get(1));
        functionalMatrix.set(4, 2, args -> D.get(2));
        functionalMatrix.set(4, 3, zero);
        functionalMatrix.set(4, 4, zero);
        functionalMatrix.set(4, 5, zero);

        functionalMatrix.set(5, 0, args -> -D.get(0) * (1 - args.get(5)));
        functionalMatrix.set(5, 1, args -> D.get(1) * args.get(5));
        functionalMatrix.set(5, 2, zero);
        functionalMatrix.set(5, 3, zero);
        functionalMatrix.set(5, 4, zero);
        functionalMatrix.set(5, 5, args -> -D.get(0) * (P.get(0) - args.get(0)) - D.get(1) * (P.get(1) - args.get(1)));

        return functionalMatrix;
    }
}

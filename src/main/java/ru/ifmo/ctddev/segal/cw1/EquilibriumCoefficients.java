package ru.ifmo.ctddev.segal.cw1;

import static ru.ifmo.ctddev.segal.cw1.Constants.P_A;
import static ru.ifmo.ctddev.segal.cw1.Constants.R;
import static ru.ifmo.ctddev.segal.cw1.Constants.Substance.*;

/**
 * Created by dimatomp on 24.05.16.
 */
public class EquilibriumCoefficients {
    public double calculateK(int number, double T) {
        double dG;
        switch (number) {
            case 1: dG = 2 * AL.G(T) + 2 * H_CL.G(T) - 2 * AL_CL.G(T) - H2.G(T); break;
            case 2: dG = AL.G(T) + 2 * H_CL.G(T) - AL_CL2.G(T) - H2.G(T); break;
            case 3: dG = 2 * AL.G(T) + 6 * H_CL.G(T) - 2 * AL_CL3.G(T) - 3 * H2.G(T); break;
            case 4: dG = 2 * GA.G(T) + 2 * H_CL.G(T) - 2 * GA_CL.G(T) - H2.G(T); break;
            case 5: dG = GA.G(T) + 2 * H_CL.G(T) - GA_CL2.G(T) - H2.G(T); break;
            case 6: dG = 2 * GA.G(T) + 6 * H_CL.G(T) - 2 * GA_CL3.G(T) - 3 * H2.G(T); break;
            case 7: dG = AL_CL.G(T) + N_H3.G(T) - AL_N.G(T) - H_CL.G(T) - H2.G(T); break;
            case 8: dG = 2 * AL_CL2.G(T) + 2 * N_H3.G(T) - 2 * AL_N.G(T) - 4 * H_CL.G(T) - H2.G(T); break;
            case 9: dG = AL_CL3.G(T) + N_H3.G(T) - AL_N.G(T) - 3 * H_CL.G(T); break;
            case 10: dG = GA_CL.G(T) + N_H3.G(T) - GA_N.G(T) - H_CL.G(T) - H2.G(T); break;
            case 11: dG = 2 * GA_CL2.G(T) + 2 * N_H3.G(T) - 2 * GA_N.G(T) - 4 * H_CL.G(T) - H2.G(T); break;
            case 12: dG = GA_CL3.G(T) + N_H3.G(T) - GA_N.G(T) - 3 * H_CL.G(T); break;
            default: throw new IllegalArgumentException("Invalid equation number: " + number);
        }
        return Math.exp(-dG / (R * T)) / P_A;
    }
}

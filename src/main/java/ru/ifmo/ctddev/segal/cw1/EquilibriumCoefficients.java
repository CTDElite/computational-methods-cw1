package ru.ifmo.ctddev.segal.cw1;

import static ru.ifmo.ctddev.segal.cw1.Constants.P_A;
import static ru.ifmo.ctddev.segal.cw1.Constants.R;
import static ru.ifmo.ctddev.segal.cw1.Constants.Substance.*;
import static ru.ifmo.ctddev.segal.cw1.Constants.T;

/**
 * Created by dimatomp on 24.05.16.
 */
public class EquilibriumCoefficients {
    public double calculateK(int number) {
        double dG;
        switch (number) {
            case 1: dG = 2 * AL.G() + 2 * H_CL.G() - 2 * AL_CL.G() - H2.G(); break;
            case 2: dG = AL.G() + 2 * H_CL.G() - AL_CL2.G() - H2.G(); break;
            case 3: dG = 2 * AL.G() + 6 * H_CL.G() - 2 * AL_CL3.G() - 3 * H2.G(); break;
            case 4: dG = 2 * GA.G() + 2 * H_CL.G() - 2 * GA_CL.G() - H2.G(); break;
            case 5: dG = GA.G() + 2 * H_CL.G() - GA_CL2.G() - H2.G(); break;
            case 6: dG = 2 * GA.G() + 6 * H_CL.G() - 2 * GA_CL3.G() - 3 * H2.G(); break;
            case 7: dG = AL_CL.G() + N_H3.G() - AL_N.G() - H_CL.G() - H2.G(); break;
            case 8: dG = 2 * AL_CL2.G() + 2 * N_H3.G() - 2 * AL_N.G() - 4 * H_CL.G() - H2.G(); break;
            case 9: dG = AL_CL3.G() + N_H3.G() - AL_N.G() - 3 * H_CL.G(); break;
            case 10: dG = GA_CL.G() + N_H3.G() - GA_N.G() - H_CL.G() - H2.G(); break;
            case 11: dG = 2 * GA_CL2.G() + 2 * N_H3.G() - 2 * GA_N.G() - 4 * H_CL.G() - H2.G(); break;
            case 12: dG = GA_CL3.G() + N_H3.G() - GA_N.G() - 3 * H_CL.G(); break;
            default: throw new IllegalArgumentException("Invalid equation number: " + number);
        }
        return Math.exp(-dG / (R * T)) / P_A;
    }
}

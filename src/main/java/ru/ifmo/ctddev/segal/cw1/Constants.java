package ru.ifmo.ctddev.segal.cw1;

import java.util.Arrays;
import java.util.function.Function;

import static ru.ifmo.ctddev.segal.cw1.Constants.Substance.*;

/**
 * Created by dimatomp on 24.05.16.
 */
public class Constants {
    public static final double R = 8.314;
    public static final double P_A = 1e4;

    public enum Substance {
        AL_CL(-51032., 318.9948, 36.94626, -0.001226431, 1.1881743, 5.638541, -5.066135, 5.219347, 62.4345, 3.58, 932., Double.NaN),
        AL_CL2(-259000., 427.2137, 56.56409, -0.002961273, 1.893842, 12.40072, -22.65441, 21.29898, 97.8875, 5.3, 825., Double.NaN),
        AL_CL3(-584100., 511.8114, 81.15042, -0.004834879, 2.752097, 13.40078, -21.28001, 16.92868, 133.3405, 5.13, 472., Double.NaN),
        GA_CL(-70553., 332.2718, 37.11052, -0.000746187, 1.1606512, 4.891346, -4.467591, 5.506236, 105.173, 3.696, 348.2, Double.NaN),
        GA_CL2(-241238., 443.2976, 57.745845, -0.002265112, 1.8755545, 3.66186, -9.356338, 15.88245, 140.626, 4.293, 465., Double.NaN),
        GA_CL3(-431573., 526.8113, 82.03355, -0.003486473, 2.6855923, 8.278878, -14.5678, 12.8899, 176.080, 5.034, 548.24, Double.NaN),
        N_H3(-45940., 231.1183, 20.52222, 0.000716251, 0.7677236, 244.6296, -251.69, 146.6947, 17.031, 3.0, 300., Double.NaN),
        H2(0., 205.5368, 29.50487, 0.000168424, 0.86065612, -14.95312, 78.18955, -82.78981, 2.016, 2.93, 34.1, Double.NaN),
        H_CL(-92310., 243.9878, 23.15984, 0.001819985, 0.6147384, 51.16604, -36.89502, 9.174252, 36.461, 2.737, 167.1, Double.NaN),
        N2(0., 242.8156, 21.47467, 0.001748786, 0.5910039, 81.08497, -103.6265, 71.30775, 28.0135, 3.798, 71.4, Double.NaN),
        AL(0., 172.8289, 50.51806, -0.00411847, 1.476107, -458.1279, 2105.75, -4168.337, 26.9815, Double.NaN, Double.NaN, 2690, AL_CL, AL_CL2, AL_CL3),
        GA(0., 125.9597, 26.03107, 0.001178297, 0.13976, -0.5698425, 0.04723008, 7.212525, 69.723, Double.NaN, Double.NaN, 5900, GA_CL, GA_CL2, GA_CL3),
        AL_N(-319000., 123.1132, 44.98092, -0.00734504, 1.86107, 31.39626, -49.92139, 81.22038, 40.988, Double.NaN, Double.NaN, 3200),
        GA_N(-114000., 160.2647, 52.86351, -0.00799055, 2.113389, 1.313428, -2.441129, 1.945731, 83.730, Double.NaN, Double.NaN, 6150);

        private double G(double T) {
            double x = T / 1e4;
            double phi = f1 + f2 * Math.log(x) + f3 / (x * x) + f4 / x + f5 * x + f6 * x * x + f7 * x * x * x;
            return H - phi * T;
        }

        private double sigma_N2() {
            return (sigma + N2.sigma) / 2;
        }

        private double eps_N2() {
            return Math.sqrt(eps * N2.eps);
        }

        private double mu_N2() {
            return 2 * mu * N2.mu / (mu + N2.mu);
        }

        private double omega(double T) {
            return 1.074 * Math.pow(T / eps_N2(), -0.1604);
        }

        public double D(double T) {
            return 2.628e-2 * T * Math.sqrt(T) / (P_A * sigma_N2() * omega(T) * Math.sqrt(mu_N2()));
        }

        public double G(double T, double dP, double delta) {
            return D(T) * dP / (R * T * delta);
        }

        final Substance chlorides[];
        final double H, f1, f2, f3, f4, f5, f6, f7, mu, sigma, eps, ro;

        public double V(double T, Function<Substance, Double> dP, double delta) {
            double sum = Arrays.stream(chlorides).mapToDouble(substance -> substance.G(T, dP.apply(substance), delta)).sum();
            return sum * GA.mu / GA.ro * 1e9;
        }

        Substance(double h, double f1, double f2, double f3, double f4, double f5, double f6, double f7, double mu, double sigma, double eps, double ro, Substance... chlorides) {
            H = h;
            this.f1 = f1;
            this.f2 = f2;
            this.f3 = f3;
            this.f4 = f4;
            this.f5 = f5;
            this.f6 = f6;
            this.f7 = f7;
            this.mu = mu;
            this.sigma = sigma;
            this.eps = eps;
            this.ro = ro;
            this.chlorides = chlorides;
        }
    }


    public static double K(int number, double T) {
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

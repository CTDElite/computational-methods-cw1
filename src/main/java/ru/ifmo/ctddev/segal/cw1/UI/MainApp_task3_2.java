package ru.ifmo.ctddev.segal.cw1.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import ru.ifmo.ctddev.segal.cw1.Constants;
import ru.ifmo.ctddev.segal.cw1.task3.Solver;

import java.util.Map;

/**
 * Created by Daria on 24.05.2016.
 */
public class MainApp_task3_2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        LineChart chart = new LineChart(new NumberAxis(), new NumberAxis());


        double delta = 0.01;
        Constants.Substance[] ii = new Constants.Substance[]{Constants.Substance.AL_CL3, Constants.Substance.GA_CL,
                Constants.Substance.N_H3, Constants.Substance.H_CL, Constants.Substance.H2};

        int T = 1100;
        double TK = T + 273.15;
        //G_i
//        for (int pos = 0; pos < 2; pos++) {
//            ObservableList<XYChart.Data<Object, Object>> points = FXCollections.observableArrayList();
//            for (double x_g = 0.0; x_g <= 1.0; x_g += 0.01) {
//                double px = x_g;
//                Pair<Map<Constants.Substance, Double>, Double> PP = Solver.solve2(TK, x_g);
//                double py_G = ii[pos].G(TK, PP.getKey().get(ii[pos]), delta);
//                points.add(new XYChart.Data<Object, Object>(px, py_G));
//                System.err.println("T = " + T + " G" + py_G + ", " + py_G);
//            }
//            chart.getData().add(new XYChart.Series<>("G_" + ii[pos].toString(), points));
//        }

        //V_i
//        ObservableList<XYChart.Data<Object, Object>> points = FXCollections.observableArrayList();
//        for (double x_g = 0.0; x_g <= 1.0; x_g += 0.05) {
//            double px = x_g;
//            Pair<Map<Constants.Substance, Double>, Double> PP = Solver.solve2(TK, x_g);
//            double py_V = Constants.VgAlGaN(TK, PP.getKey()::get, delta);
//            points.add(new XYChart.Data<Object, Object>(px, py_V));
////            System.err.println("T = " + T + " V = " + py_V);
//        }
//        chart.getData().add(new XYChart.Series<>("V_AL_GA_N", points));


        //x
        ObservableList<XYChart.Data<Object, Object>> points2 = FXCollections.observableArrayList();
        for (double x_g = 0.0; x_g <= 1.0; x_g += 0.05) {
            double px = x_g;
            Pair<Map<Constants.Substance, Double>, Double> PP = Solver.solve2(TK, x_g);
            double py_V = PP.getValue();
            points2.add(new XYChart.Data<Object, Object>(px, py_V));
        }
        chart.getData().add(new XYChart.Series<>("x = f(x_g)", points2));

        vBox.getChildren().add(chart);
        primaryStage.setScene(new Scene(vBox));
        primaryStage.show();
        chart.lookupAll("*").forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
    }

}

package ru.ifmo.ctddev.segal.cw1.UI;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.ifmo.ctddev.segal.cw1.Constants;
import ru.ifmo.ctddev.segal.cw1.task2.Solver;

import java.util.*;

/**
 * Created by Daria on 24.05.2016.
 */
public class MainApp_task2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        LineChart chart = new LineChart(new NumberAxis(), new NumberAxis());

        double delta = 0.01;
        Constants.Substance[] ii = new Constants.Substance[]{Constants.Substance.GA_CL, Constants.Substance.GA_CL2,
                Constants.Substance.GA_CL3, Constants.Substance.H_CL, Constants.Substance.H2};
        //G_i
        for (int pos = 0; pos < 3; pos++) {
            ObservableList<XYChart.Data<Object, Object>> points = FXCollections.observableArrayList();
            for (double T = 650; T <= 950; T += 0.2) {
                double TK = T + 273.15;
                double px = 1 / TK;
                Map<Constants.Substance, Double> PP = Solver.solve(TK);
                double py_G = ii[pos].G(TK, PP.get(ii[pos]), delta);
                double log_py_G = Math.log(-py_G);
                points.add(new XYChart.Data<Object, Object>(px, log_py_G));
//                System.err.println(ii[pos].toString() + " T = " + T + " G " + py_G + ", " + log_py_G);
            }
            chart.getData().add(new XYChart.Series<>("G_" + ii[pos].toString(), points));
        }

        //V_i
        ObservableList<XYChart.Data<Object, Object>> points = FXCollections.observableArrayList();
        for (int T = 650; T <= 950; T += 1) {
            double TK = T + 273.15;
            double px = 1 / TK;
            Map<Constants.Substance, Double> PP = Solver.solve(TK);
            double py_V = Constants.Substance.GA.V(TK, PP::get, delta);
            double log_py_V = Math.log10(Math.abs(py_V));
            points.add(new XYChart.Data<Object, Object>(px, log_py_V));
//            System.err.println("T = " + T + " V = " + log_py_V);
        }
        chart.getData().add(new XYChart.Series<>("V_GA", points));


        vBox.getChildren().add(chart);
        primaryStage.setScene(new Scene(vBox));
        primaryStage.show();
        chart.lookupAll("*").forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
    }
}

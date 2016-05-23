package ru.ifmo.ctddev.segal.cw1.ui;

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

import java.util.List;

/**
 * Created by Daria on 24.05.2016.
 */
public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static int n = 5;
    public static int x[] =  {50, 100, 150, 200, 250};
    public static int y[] =  {80, 200, 150, 320, 100};

    ObservableList<XYChart.Data<Object, Object>> points;
    ObservableList<XYChart.Data<Object, Object>> points2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        LineChart chart = new LineChart(new NumberAxis(), new NumberAxis());

        ObservableList<XYChart.Data<Object, Object>> points = FXCollections.observableArrayList();
        for (int i = 0; i < n; i++) {
            points.add(new XYChart.Data<Object, Object>(x[i], y[i]));
        }

        chart.getData().add(new XYChart.Series<>("CourseWork 1", points));

        ObservableList<XYChart.Data<Object, Object>> points2 = FXCollections.observableArrayList();
        for (int i = 0; i < n; i++) {
            points2.add(new XYChart.Data<Object, Object>(x[i] + 10, y[i] + 10));
        }

        chart.getData().add(new XYChart.Series<>("CourseWork 2", points2));

        vBox.getChildren().add(chart);
        primaryStage.setScene(new Scene(vBox));
        primaryStage.show();
        ((XYChart.Series) chart.getData().get(0)).getData().addAll(new XYChart.Data<>(5, 5));
        chart.lookupAll("*").forEach(c -> {
            System.out.println(c);
            System.out.println();
        });
    }
}

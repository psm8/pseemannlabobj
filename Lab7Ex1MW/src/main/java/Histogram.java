import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class Histogram extends Application {
    public static void main(String args[]){
        launch();
    }


    public void start(Stage primaryStage) {
        int[] data = getRandomData(12);
        Label labelInfo = new Label();
        labelInfo.setText(
                "java.version: " + System.getProperty("java.version") + "\n" +
                        "javafx.runtime.version: " + System.getProperty("javafx.runtime.version")
        );
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart =
                new BarChart<>(xAxis,yAxis);
        barChart.setCategoryGap(0);
        barChart.setBarGap(0);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Histogram");
        series1.setName("Histogram");
        for(int i = 0; i < data.length; i++) {
            series1.getData().add(new XYChart.Data(data[i], data[i]));
        }
        xAxis.setLabel("xLabel");
        yAxis.setLabel("yLabel");
        barChart.getData().addAll(series1);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(labelInfo, barChart);
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 800, 450);
        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private int[] getRandomData(int dataLength) {
        int[] data = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < dataLength; i++) {
            data[i] = random.nextInt(100);
        }
        return data;
    }
}
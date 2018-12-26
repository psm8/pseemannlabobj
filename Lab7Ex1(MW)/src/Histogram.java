import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;


public class Histogram extends Application{
    int[] data;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        data = getRandomData(10);
        Label labelInfo = new Label();
        labelInfo.setText("Histogram");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
        barChart.setCategoryGap(0);
        barChart.setBarGap(0);
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Histogram");
        for(int i = 0; i < data.length; i++) {
            series1.getData().add(new XYChart.Data(Integer.toString(data[i]), data[i]));
        }
        xAxis.setLabel("xLabel");
        yAxis.setLabel("yLabel");
        barChart.getData().addAll(series1);
        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            data = getRandomData(10);
            for(int i = 0; i < data.length; i++) {
                series1.getData().set(i, new XYChart.Data(Integer.toString(data[i]), data[i]));
            }
        });
        VBox vBox = new VBox();
        vBox.getChildren().addAll(labelInfo, barChart,refreshButton);
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        Scene scene = new Scene(root, 800, 450);
        primaryStage.setTitle("Histogram");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private static int[] getRandomData(int dataLength) {
        int[] data = new int[dataLength];
        Random random = new Random();
        for (int i = 0; i < dataLength; i++) {
            data[i] = random.nextInt(100);
        }
        return data;
    }
}
/*
    PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.2));
    int finalI = i;
                pauseTransition.setOnFinished(event -> series1.getData().set(finalI,new XYChart.Data(Integer.toString(data[finalI]), data[finalI])));
                        pauseTransition.play();*/

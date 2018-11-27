import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("x");
        yAxis.setLabel("f(x)");
        final LineChart<Number,Number> lineChart = new LineChart(xAxis,yAxis);

        MyLineChart myLineChart = new MyLineChart();
        Label label1 = new Label("Wpolczynnik przy zmiennych oddzielone ,:");
        TextField textField1 = new TextField();
        Label label2 = new Label("Poczatek przedzialu:");
        TextField textField2 = new TextField();
        Label label3 = new Label("Koniec przedzialu:");
        TextField textField3 = new TextField();
        Label label4 = new Label("Czestotliwosc probkowanie:");
        TextField textField4 = new TextField();
        VBox setParams = new VBox(10);
        setParams.getChildren().addAll(label1, textField1, label2, textField2, label3, textField3, label4, textField4);

        Button submitButton = new Button("Submit");
        HBox bottomHBox = new HBox();
        bottomHBox.getChildren().add(submitButton);
        bottomHBox.setSpacing(10);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Line Chart");
        submitButton.setOnAction( e -> {
            boolean validDataFlag = true;
            try {
                myLineChart.setParams(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText());
            }catch (Exception ex){
                WrongParamsBox.display();
                validDataFlag = false;
            }
            if(validDataFlag) {
                series1.getData().clear();
                double[] xData = myLineChart.getLineChartXData();
                double[] yData = myLineChart.getLineChartYData();
                for (int i = 0; i < myLineChart.getLineChartXData().length; i++) {
                    series1.getData().add(new XYChart.Data(xData[i], yData[i]));
                }
            }
        });



        lineChart.getData().add(series1);
        lineChart.setTitle("Line Chart");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(lineChart);
        BorderPane root = new BorderPane();
        root.setLeft(setParams);
        root.setCenter(vBox);
        root.setBottom(bottomHBox);
        Scene scene = new Scene(root, 800, 450);
        primaryStage.setTitle("Line Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

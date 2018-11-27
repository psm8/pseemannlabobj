package Lab7EX3;

import Lab7EX3.exception.FileIsNotPhotoException;
import Lab7EX3.exception.FolderDoesntExistException;
import Lab7EX3.exception.NoInternetConnectionException;
import io.indico.Indico;
import io.indico.api.utils.IndicoException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class ImageBox {
        public static void display(String imageAddress) {
            Map<String, Double> predictions = getPrediction(imageAddress);

            Image image = new Image(imageAddress);
            ImageView iv1 = new ImageView();
            iv1.setImage(image);
            VBox imageBox = new VBox(10);

            Label labelInfo = new Label();
            labelInfo.setText("Predictions");
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
            barChart.setCategoryGap(0);
            barChart.setBarGap(0);
            XYChart.Series series1 = new XYChart.Series();
            series1.setName("Predictions");
            for (Map.Entry<String, Double> entry : predictions.entrySet()) {
                series1.getData().add(new XYChart.Data(entry.getKey(), entry.getValue()));
            }
            xAxis.setLabel("xLabel");
            yAxis.setLabel("yLabel");
            barChart.getData().addAll(series1);
            VBox predictionsChart = new VBox();
            predictionsChart.getChildren().addAll(labelInfo, barChart);
            BorderPane borderPane = new BorderPane();
            borderPane.setLeft(imageBox);
            borderPane.setRight(predictionsChart);
            Scene scene = new Scene(borderPane);
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Image");
            window.setScene(scene);
            window.showAndWait();

        }

    public static Map<String, Double> getPrediction(String fileAddress){
        Map<String, Double> predictions = null;
        File image = new File(fileAddress);
       try {
            try {
                InternetConnection.check();
            }catch(NoInternetConnectionException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
            Indico indico = null;
            boolean CorrectKey =false;
            do{
                try {
                    indico = new Indico(JIn.getApiKey());
                    indico.imageRecognition.predict("data/foto/test");
                }catch(IndicoException e){
                    if(e.getMessage().equals("imagerecognition failed with error Invalid API key")){
                        System.out.println("Invalid API key");
                    }
                    else{
                        CorrectKey =true;
                    }
                }
            }while(CorrectKey == false);

            try {
                predictions = ImageSegregate.segregateImage(indico, image);
            }catch(FileIsNotPhotoException e ){
                System.out.println(e.getMessage());
            }catch(IndicoException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return predictions;
    }
}

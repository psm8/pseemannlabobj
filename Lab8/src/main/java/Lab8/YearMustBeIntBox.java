package Lab8;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class YearMustBeIntBox {
    public static void display() {
        Stage window = new Stage();
        window.setWidth(256);
        window.setHeight(144);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");
        Label label = new Label("Year Must Be Number");
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(label);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

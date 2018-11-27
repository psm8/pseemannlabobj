package Lab7EX3;

import Lab7EX3.exception.FileIsNotPhotoException;
import Lab7EX3.exception.FolderDoesntExistException;
import Lab7EX3.exception.NoInternetConnectionException;
import io.indico.Indico;
import io.indico.api.utils.IndicoException;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Map;



public class main extends Application{
    public static void main(String[] args) {launch(args);}
    @Override
    public void start(final Stage primaryStage) {
        ListView listView = new ListView();
        ObservableList<String> items = FXCollections.observableArrayList ();
        final Label labelSelectedDirectory = new Label();
        Button openDirectoryChooser = new Button("Open Directory Chooser");
        openDirectoryChooser.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            if(selectedDirectory == null){
                labelSelectedDirectory.setText("No Directory selected");
            }else{
                labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
            }
            File[] files = new File( labelSelectedDirectory.getText()).listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    try {
                        if (ImageSegregate.isImage(pathname)) {
                            return true;
                        }else{
                            return false;
                        }
                    }catch(Exception e){return false;}
                }
            });
            for(File file : files) {
                items.add(getFileName(String.valueOf(file)));
            }
            listView.setItems(items);
        });
        listView.setOnMouseClicked( e-> {
            if(!items.isEmpty()) {
                String photo = listView.getSelectionModel().getSelectedItem().toString();
                String photoAddress = labelSelectedDirectory.getText() + "\\" + photo;
                ImageBox.display(photoAddress);
            }
        });

        HBox choosePath = new HBox(10);
        choosePath.getChildren().addAll(openDirectoryChooser, labelSelectedDirectory);
        VBox mainBox = new VBox();
        mainBox.getChildren().add(listView);
        BorderPane boarderPane = new BorderPane();
        boarderPane.setTop(choosePath);
        boarderPane.setCenter(mainBox);
/*        boarderPane.setBottom(bottomHBox);*/
        Scene scene = new Scene(boarderPane, 800, 450);
        primaryStage.setTitle("Line Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String getFileName(String fileAddress){
        int fileNameStart = fileAddress.lastIndexOf('\\');
        StringBuilder fileNameSB = new StringBuilder();
        for (int i = fileNameStart +1; i < fileAddress.length(); i++) {
            fileNameSB.append(fileAddress.charAt(i));
        }
       return fileNameSB.toString();
    }
}

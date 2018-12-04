package Lab8;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.sql.*;

public class App extends Application{
    private TableView<Row> table;
    private TableColumn isbnCol;
    private TableColumn titleCol;
    private TableColumn authorCol;
    private TableColumn yearCol;
    private TextField addIsbn;
    private TextField addTitle;
    private TextField addAuthor;
    private TextField addYear;
    private Button addButton;
    private Label searchLabel;
    private TextField searchForName;
    private final DB db = new DB();
    private ObservableList<Row> data;

    public static void main( String[] args ){
        launch(args);
    }

    public void start(Stage primaryStage){

        primaryStage.setTitle("Books");
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        Scene scene = new Scene(new Group());
        final Label label = new Label("Books");
        label.setFont(new Font("Arial", 20));
        table = new TableView();
        initializeTable();

        makeAddButtons();
        final HBox addbox = new HBox(addIsbn,addTitle,addAuthor,addYear,addButton);
        addbox.setSpacing(3);
        addData();

        makeSearchButtons();
        final VBox searchbox = new VBox(searchLabel, searchForName);
        searchbox.setSpacing(3);
        searchFor();

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, addbox, searchbox);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeTable(){
        table.setEditable(true);
        isbnCol = new TableColumn("isbn");
        isbnCol.setMinWidth(200);
        isbnCol.setCellValueFactory(new PropertyValueFactory<Row, String>("isbn"));
        titleCol = new TableColumn("Title");
        titleCol.setMinWidth(200);
        titleCol.setCellValueFactory(new PropertyValueFactory<Row, String>("title"));
        authorCol = new TableColumn("Author");
        authorCol.setMinWidth(200);
        authorCol.setCellValueFactory(new PropertyValueFactory<Row, String>("author"));
        yearCol = new TableColumn("Year");
        yearCol.setMinWidth(200);
        yearCol.setCellValueFactory(new PropertyValueFactory<Row, String>("year"));
        db.buildData();
        data = db.getData();
        table.setItems(data);
        table.getColumns().addAll(isbnCol, titleCol, authorCol, yearCol);
    }

    private void makeAddButtons(){
        addIsbn = new TextField();
        addIsbn.setPromptText("isbn");
        addIsbn.setMaxWidth(isbnCol.getPrefWidth());
        addTitle = new TextField();
        addTitle.setMaxWidth(titleCol.getPrefWidth());
        addTitle.setPromptText("Title");
        addAuthor = new TextField();
        addAuthor.setMaxWidth(authorCol.getPrefWidth());
        addAuthor.setPromptText("Author");
        addYear = new TextField();
        addYear.setMaxWidth(yearCol.getPrefWidth());
        addYear.setPromptText("Year");
        addButton = new Button("Add");
    }
    private void addData(){
        addButton.setOnAction(e->{
            db.addData("'"+addIsbn.getText()+"'", "'"+addTitle.getText()+"'", "'"+addAuthor.getText()+"'", addYear.getText());
            addIsbn.clear();
            addTitle.clear();
            addAuthor.clear();
            addYear.clear();
            db.buildData();
            table.getColumns().clear();
            data = db.getData();
            table.setItems(data);
            table.getColumns().addAll(isbnCol, titleCol, authorCol, yearCol);
        });
    }

    private void makeSearchButtons(){
        searchLabel = new Label("Press [ENTER] to search:");
        searchForName = new TextField();
        searchForName.setPromptText("search for (author)...");
        searchForName.setMaxWidth(200);

    }

    private void searchFor(){
        searchForName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                    table.getItems().stream()
                            .filter(item -> item.getAuthor().equals(searchForName.getText()) ||
                                    item.getIsbn().equals(searchForName.getText()) ||
                                    item.getAuthorName().equals(searchForName.getText()))
                            .findAny()
                            .ifPresent(item -> {
                                table.getSelectionModel().select(item);
                                table.scrollTo(item);
                            });
                    System.out.println(table.getItems().get(1).getAuthor());
                    searchForName.clear();
                }
            }
        });
    }
}

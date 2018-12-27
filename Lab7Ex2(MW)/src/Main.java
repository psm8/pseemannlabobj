import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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



public class Main extends Application {
    private final DB db = new DB();
    private TableView<Person> table;
    private TableColumn fullNameCol;
    private TableColumn phoneCol;
    private TableColumn peselCol;
    private TextField addfullName;
    private TextField addphone;
    private TextField addpesel;
    private Button addButton;
    private Button removeButton;
    private Label searchLabel;
    private TextField searchByFirstName;
    private TextField searchByLastName;
    private TextField searchByAge;
    private TextField searchByPesel;


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        Person person1 = new Person("Jan Kowalski","123456789","94081278923");
        Person person2 = new Person("Joanna Kowalska","222256789","22080820123");
        Person person3 = new Person("Jan Nowak","987654321","98100954321");
        db.add(person1);
        db.add(person2);
        db.add(person3);
        primaryStage.setTitle("Person");
        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        Scene scene = new Scene(new Group());
        final Label label = new Label("Person");
        label.setFont(new Font("Arial", 20));
        table = new TableView();
        initializeTable();

        makeAddButtons();
        final HBox addbox = new HBox(addfullName, addphone, addpesel, addButton);
        addbox.setSpacing(3);
        addData();

        removeButton = new Button("Remove");
        final HBox removebox = new HBox(removeButton);
        addbox.setSpacing(3);
        removeData();

        makeSearchButtons();
        final VBox searchbox = new VBox(searchLabel, searchByFirstName, searchByLastName, searchByAge, searchByPesel);
        searchbox.setSpacing(3);
        setSearchByFirstName();
        setSearchByLastName();
        setSearchByAge();
        setSearchByPesel();

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label,table, addbox, removebox, searchbox);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeTable(){
        table.setEditable(true);
        fullNameCol = new TableColumn("Full Name");
        fullNameCol.setMinWidth(200);
        fullNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("fullName"));
        phoneCol = new TableColumn("Phone");
        phoneCol.setMinWidth(200);
        phoneCol.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
        peselCol = new TableColumn("Pesel");
        peselCol.setMinWidth(200);
        peselCol.setCellValueFactory(new PropertyValueFactory<Person, String>("pesel"));
        ObservableList<Person>  rows = FXCollections.observableArrayList();
        for(Person person:db) {
            rows.add(person);
        }
        table.setItems(rows);
        table.getColumns().addAll(fullNameCol, phoneCol, peselCol);
    }


    private void makeAddButtons(){
        addfullName = new TextField();
        addfullName.setPromptText("Full Name");
        addfullName.setMaxWidth(fullNameCol.getPrefWidth());
        addphone = new TextField();
        addphone.setMaxWidth(phoneCol.getPrefWidth());
        addphone.setPromptText("Phone");
        addpesel = new TextField();
        addpesel.setMaxWidth(peselCol.getPrefWidth());
        addpesel.setPromptText("Pesel");
        addButton = new Button("Add");
    }

    private void addData(){
        addButton.setOnAction(e->{
            db.add(new Person(addfullName.getText(), addphone.getText(), addpesel.getText()));
            addfullName.clear();
            addphone.clear();
            addpesel.clear();
            table.getColumns().clear();
            ObservableList<Person>  rows = FXCollections.observableArrayList();
            for(Person person:db) {
                rows.add(person);
            }
            table.setItems(rows);
            table.getColumns().addAll(fullNameCol, phoneCol, peselCol);
        });
    }

    private void removeData(){
        removeButton.setOnAction(e->{
            table.getItems().removeAll(
                    table.getSelectionModel().getSelectedItems()
            );
            for(Person person:table.getSelectionModel().getSelectedItems())
            db.remove(person);
        });
    }

    private void makeSearchButtons(){
        searchLabel = new Label("Press [ENTER] to search:");
        searchByFirstName = new TextField();
        searchByFirstName.setPromptText("search by (first name)...");
        searchByFirstName.setMaxWidth(200);
        searchByLastName = new TextField();
        searchByLastName.setPromptText("search by (last name)...");
        searchByLastName.setMaxWidth(200);
        searchByAge = new TextField();
        searchByAge.setPromptText("search by (age)...");
        searchByAge.setMaxWidth(200);
        searchByPesel = new TextField();
        searchByPesel.setPromptText("search by (pesel)...");
        searchByPesel.setMaxWidth(200);
    }

    private void setSearchByFirstName() {
        searchByFirstName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    table.getItems().stream()
                            .filter(item -> item.getFirstName().equals(searchByFirstName.getText()))
                            .findAny()
                            .ifPresent(item -> {
                                table.getSelectionModel().select(item);
                                table.scrollTo(item);
                            });
                    searchByFirstName.clear();
                }
            }
        });
    }

    private void setSearchByLastName() {
        searchByLastName.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    table.getItems().stream()
                            .filter(item -> item.getLastName().equals(searchByLastName.getText()))
                            .findAny()
                            .ifPresent(item -> {
                                table.getSelectionModel().select(item);
                                table.scrollTo(item);
                            });
                    searchByLastName.clear();
                }
            }
        });
    }

    private void setSearchByAge() {
        searchByAge.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    table.getItems().stream()
                            .filter(item -> item.getAge().equals(searchByAge.getText()))
                            .findAny()
                            .ifPresent(item -> {
                                table.getSelectionModel().select(item);
                                table.scrollTo(item);
                            });
                    searchByAge.clear();
                }
            }
        });
    }

    private void setSearchByPesel() {
        searchByPesel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    table.getItems().stream()
                            .filter(item -> item.getPesel().equals(searchByPesel.getText()))
                            .findAny()
                            .ifPresent(item -> {
                                table.getSelectionModel().select(item);
                                table.scrollTo(item);
                            });
                    searchByPesel.clear();
                }
            }
        });
    }
}
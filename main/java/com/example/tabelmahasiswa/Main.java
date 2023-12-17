package com.example.tabelmahasiswa;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private TableView<Mahasiswa> table = new TableView<>();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Test TableView");//windows
        stage.setWidth(450);
        stage.setHeight(550);

        final Label label = new Label("Daftar");
        label.setFont(new Font("Arial", 30));

        table.setEditable(true);//kolom
        TableColumn<Mahasiswa, String> nameCol = new TableColumn<>("Nama");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        TableColumn<Mahasiswa, String> nimCol = new TableColumn<>("NIM");
        nimCol.setCellValueFactory(cellData -> cellData.getValue().nimProperty());
        TableColumn<Mahasiswa, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        table.getColumns().addAll(nameCol, nimCol, emailCol);

        //awal
        final ObservableList<Mahasiswa> data = FXCollections.observableArrayList(
                new Mahasiswa("Larynt", "202110370311189", "laryntsa@gmail.com"),
                new Mahasiswa("Ahya", "202110370311187", "ayaa@gmail.com")
        );
        table.setItems(data);

        //add
        final TextField addName = new TextField();
        addName.setMaxWidth(nameCol.getPrefWidth());
        addName.setPromptText("Nama ");
        final TextField addNim = new TextField();
        addNim.setMaxWidth(nimCol.getPrefWidth());
        addNim.setPromptText("NIM");
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        final Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            data.add(new Mahasiswa(
                    addName.getText(),
                    addNim.getText(),
                    addEmail.getText()
            ));
            addName.clear();
            addNim.clear();
            addEmail.clear();
        });

        //layout n add
        final HBox hboxInput = new HBox();
        hboxInput.getChildren().addAll(addName, addNim, addEmail, addButton);
        hboxInput.setSpacing(10);

        final VBox vbox = new VBox();
        vbox.setSpacing(8);
        vbox.setPadding(new Insets(20, 10, 10, 10));
        vbox.getChildren().addAll(label, table, hboxInput);

        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }


    public static class Mahasiswa {
        private final StringProperty name;
        private final StringProperty nim;
        private final StringProperty email;

        public Mahasiswa(String name, String nim, String email) {
            this.name = new SimpleStringProperty(name);
            this.nim = new SimpleStringProperty(nim);
            this.email = new SimpleStringProperty(email);
        }

        public StringProperty nameProperty() {
            return name;
        }

        public StringProperty nimProperty() {
            return nim;
        }

        public StringProperty emailProperty() {
            return email;
        }

        // Getters for non-property fields (optional)
        public String getName() {
            return name.get();
        }

        public String getNim() {
            return nim.get();
        }

        public String getEmail() {
            return email.get();

        }
    }
}


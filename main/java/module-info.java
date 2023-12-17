module com.example.tabelmahasiswa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tabelmahasiswa to javafx.fxml;
    exports com.example.tabelmahasiswa;
}
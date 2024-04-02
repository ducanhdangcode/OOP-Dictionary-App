module com.example.superdictionaryproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;


    opens com.example.superdictionaryproject to javafx.fxml;
    exports com.example.superdictionaryproject;
}
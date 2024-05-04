module com.example.appdictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires freetts;


    opens com.example.appdictionary to javafx.fxml;
    exports com.example.appdictionary;
}
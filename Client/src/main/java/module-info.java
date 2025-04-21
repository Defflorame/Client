module bsuir.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;
    requires com.google.gson;


    opens bsuir.client to javafx.fxml;
    exports bsuir.client;
}
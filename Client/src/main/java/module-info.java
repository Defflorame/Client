module bsuir.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires static lombok;
    requires com.google.gson;
    requires jakarta.persistence;

    opens EntityDTO to com.google.gson;
    opens Enums to com.google.gson;
    opens network to com.google.gson;
    exports EntityDTO;

    opens bsuir.client to javafx.fxml;
    exports bsuir.client;
}
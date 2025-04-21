package bsuir.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import network.ClientSocket;

import java.io.IOException;

public class Main2 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ClientSocket.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(Main2.class.getResource("welcomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Shop!");
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void stop() {
        System.out.println("Приложение закрывается...");
        try {
            ClientSocket.getInstance().disconnect();  // вызываем disconnect при выходе
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
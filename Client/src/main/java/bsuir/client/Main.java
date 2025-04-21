package bsuir.client;

import network.ClientSocket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import network.ClientThread;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ClientSocket.getInstance().getSocket();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("welcomeScreen.fxml"));
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
        while (true) {
            if (ClientSocket.getInstance().testConnection()) {
                launch();
                break; // Выход из цикла после успешного запуска
            }
            else {
                ClientSocket.getInstance().getSocket();
            }

            try {
                Thread.sleep(5000); // Пауза 5 секунд
            } catch (InterruptedException e) {
                e.printStackTrace(); // Обработка исключения, если сон был прерван
            }
        }

    }
}
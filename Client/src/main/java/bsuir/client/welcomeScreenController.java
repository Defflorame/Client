package bsuir.client;

import Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent; // Важно: используем javafx.event.ActionEvent, а не java.awt.event.ActionEvent!

import java.io.IOException;
import java.util.Objects;

public class welcomeScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Label password_check;

    @FXML
    private Label login_check;

    @FXML
    private TextField username_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button regButton;

    @FXML
    void loginButton_pressed(ActionEvent event) throws IOException {
        if (username_field.getText().length() < 8 || username_field.getText().length() > 16) {
            login_check.visibleProperty().setValue(true);
            return;
        }
        login_check.visibleProperty().setValue(false);

        if(password_field.getText().length() < 8)
        {
            password_check.visibleProperty().setValue(true);
            return;
        }
        password_check.visibleProperty().setValue(false);

        User user = new User(username_field.getText(), PasswordHasher.hashPassword(password_field.getText()));
        // Здесь можно добавить логику входа
        // ТАКЖЕ СДЕСЬ БУДЕТ ХЕШИРОВАТЬСЯ ПАРОЛЬ ИЗ password_field
        // Посылаем запрос на сервер о валидности имени пользователя и пароля,
        // если верно делаем запрос на получение роли
        // Если user - userpanel, admin - adminpanel и тд
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userpanel.fxml"));
        Parent root = loader.load();

        // Получаем контроллер и передаем туда пользователя
        userPanelController controller = loader.getController();
        controller.setUser(user);

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void regButton_pressed(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register.fxml")));
        Stage stage = (Stage) regButton.getScene().getWindow(); // Получаем текущее окно
        stage.setScene(new Scene(root));
        stage.show(); // Обновляем сцену
    }

//    public User getUser()
//    {
//        return user
//    }


}
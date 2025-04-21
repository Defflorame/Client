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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;


public class registerController {

    @FXML
    private TextField adress_field;

    @FXML
    private ImageView getbackIcon;

    @FXML
    private Label login_check;

    @FXML
    private Label password_check;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField phoneNumber_field;

    @FXML
    private Button regButton;

    @FXML
    private Label reppassword_check;

    @FXML
    private PasswordField reppassword_field;

    @FXML
    private TextField username_field;


    @FXML
    public void getbackIcon_pressed(MouseEvent mouseEvent) throws IOException
    {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcomeScreen.fxml")));
        Stage stage = (Stage) regButton.getScene().getWindow(); // Получаем текущее окно
        stage.setScene(new Scene(root));
        stage.show(); // Обновляем сцену
    }
    @FXML
    public void regButton_pressed(ActionEvent event) throws IOException
    {
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

        if(!reppassword_field.getText().equals(password_field.getText()))
        {
            reppassword_check.visibleProperty().setValue(true);
            return;
        }
        reppassword_check.visibleProperty().setValue(false);

        User user = new User(username_field.getText(), PasswordHasher.hashPassword(password_field.getText()));


    }

}

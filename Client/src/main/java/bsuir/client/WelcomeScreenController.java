package bsuir.client;

import EntityDTO.UserDTO;
import Enums.RequestType;
import Enums.ResponseStatus;
import network.ReqResHandler;
import network.Response;
import com.google.gson.Gson;
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

public class WelcomeScreenController {

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

    public static int userId;

    @FXML
    void loginButton_pressed(ActionEvent event) throws IOException {
        if (username_field.getText().length() < 4 || username_field.getText().length() > 16) {
            login_check.visibleProperty().setValue(true);
            return;
        }
        login_check.visibleProperty().setValue(false);

        if(password_field.getText().length() < 4)
        {
            password_check.visibleProperty().setValue(true);
            return;
        }
        password_check.visibleProperty().setValue(false);

        UserDTO userDTO = new UserDTO(username_field.getText(), PasswordHasher.hashPassword(password_field.getText()));

        ReqResHandler.send(userDTO, RequestType.LOGIN);
        Response response = ReqResHandler.receive();

        if (response.getResponseStatus() == ResponseStatus.ERROR) {
            System.out.println(response.getMessage());
            return;
        }
        userDTO = new Gson().fromJson(response.getData(), UserDTO.class);

        userId = userDTO.getUserId();

        switch (userDTO.getRole()) {
            case Admin:
                loadPanel("AdminPanel.fxml", userDTO.getUserName(), loginButton);
                break;
            case User:
                loadPanel("UserPanel.fxml", userDTO.getUserName(), loginButton);
                break;
            case Manager:
                loadPanel("ManagerPanel.fxml", userDTO.getUserName(), loginButton);
                break;
        }
    }

    @FXML
    void regButton_pressed(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Register.fxml")));
        Stage stage = (Stage) regButton.getScene().getWindow(); // Получаем текущее окно
        stage.setScene(new Scene(root));
        stage.show(); // Обновляем сцену
    }

    static void loadPanel(String fxmlFile, String userName, Button loginButton) throws IOException {
        FXMLLoader loader = new FXMLLoader(WelcomeScreenController.class.getResource(fxmlFile));
        Parent root = loader.load();

        Object controller = loader.getController();
        if (controller instanceof UserPanelController) {
            ((UserPanelController) controller).setUser(userName);
        } else if (controller instanceof AdminPanelController) {
            ((AdminPanelController) controller).setUser(userName);
        } else if (controller instanceof ManagerPanelController) {
            ((ManagerPanelController) controller).setUser(userName);
        }

        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
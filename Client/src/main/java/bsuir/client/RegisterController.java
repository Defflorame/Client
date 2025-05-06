package bsuir.client;

import EntityDTO.BuyerDTO;
import EntityDTO.UserDTO;
import Enums.RequestType;
import Enums.ResponseStatus;
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
import network.ReqResHandler;
import network.Response;

import java.io.IOException;
import java.util.Objects;


public class RegisterController {

    @FXML
    private Label phone_check;
    @FXML
    private Label adress_check;
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
    void initialize()
    {
        login_check.setVisible(false);
        password_check.setVisible(false);
        reppassword_check.setVisible(false);
        adress_check.setVisible(false);
        phone_check.setVisible(false);
    }
    @FXML
    public void getbackIcon_pressed(MouseEvent mouseEvent) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WelcomeScreen.fxml")));
        Stage stage = (Stage) regButton.getScene().getWindow(); // Получаем текущее окно
        stage.setScene(new Scene(root));
        stage.show(); // Обновляем сцену
    }
    @FXML
    public void regButton_pressed(ActionEvent event) throws IOException
    {
        if (username_field.getText().length() < 4 || username_field.getText().length() > 16) {
            login_check.setVisible(true);
            return;
        }
        login_check.setVisible(false);

        if(password_field.getText().length() < 4)
        {
            password_check.setVisible(true);
            return;
        }
        password_check.setVisible(false);

        if(!reppassword_field.getText().equals(password_field.getText()))
        {
            reppassword_check.setVisible(true);
            return;
        }
        reppassword_check.setVisible(false);

        if (adress_field.getText().isEmpty()) {
            adress_check.setVisible(true);
            return;
        }
        adress_check.setVisible(false);

        if(phoneNumber_field.getText().isEmpty()) {
            phone_check.setVisible(true);
        }
        phone_check.setVisible(false);

        BuyerDTO buyerDTO = new BuyerDTO(adress_field.getText(), phoneNumber_field.getText());
        UserDTO userDTO = new UserDTO(username_field.getText(),
                PasswordHasher.hashPassword(password_field.getText()),
                buyerDTO);

        ReqResHandler.send(userDTO, RequestType.REGISTER);

        Response response = ReqResHandler.receive();
        if(response.getResponseStatus() == ResponseStatus.OK)
        {
            WelcomeScreenController.loadPanel("UserPanel.fxml", userDTO.getUserName(), regButton);
        }
        else {
            System.out.println(response.getMessage());
        }

    }

}

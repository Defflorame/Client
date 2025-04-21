package bsuir.client;

import Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import Entity.User;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class userPanelController {

    @FXML
    private Label username_field;

    private User user;

    public void setUser(User user)
    {
        this.user = user;
        username_field.setText(user.getUsername());
    }

    @FXML
    void initialize()
    {
        // делаем запрос на сервер, чтобы заполнить меню с покупками
    }


}

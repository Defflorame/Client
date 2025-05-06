package bsuir.client;


import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdminPanelController {

    @FXML
    private Label username_field;

    public void setUser(String username)
    {
        username_field.setText(username);
    }

    @FXML
    void initialize()
    {
    }


}

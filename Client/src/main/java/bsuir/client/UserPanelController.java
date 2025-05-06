package bsuir.client;

import Cart.Cart;
import EntityDTO.ItemDTO;
import EntityDTO.UserDTO;
import Enums.RequestType;
import Enums.ResponseStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import network.ReqResHandler;
import network.Response;

import java.io.IOException;
import java.lang.reflect.Type;

import java.util.List;

public class UserPanelController {

    @FXML
    private Button addButton;

    @FXML
    private Button cartButton;

    @FXML
    private Label username_field;

    @FXML
    private TableColumn<ItemDTO, Number> countColumn;

    @FXML
    private TableView<ItemDTO> itemTable;

    @FXML
    private TableColumn<ItemDTO, String> nameColumn;

    @FXML
    private TableColumn<ItemDTO, Number> priceColumn;

    public static TableView<ItemDTO> itemTableStatic;

    public static ObservableList<ItemDTO> items;


    public void setUser(String username)
    {
        username_field.setText(username);
    }

    @FXML
    void initialize() throws IOException
    {
        // делаем запрос на сервер, чтобы заполнить меню с покупками
        itemTableStatic = itemTable;

        ReqResHandler.send(null, RequestType.GET_ALL_ITEMS);

        Response response = ReqResHandler.receive();
        if (response.getResponseStatus() == ResponseStatus.ERROR) {
            System.out.println(response.getMessage());
            return;
        }
        // Используем TypeToken для десериализации
        Type listType = new TypeToken<List<ItemDTO>>() {}.getType();

        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        countColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCount()));
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()));

        List<ItemDTO> tmpList = new Gson().fromJson(response.getData(), listType);
        items = FXCollections.observableArrayList(tmpList);
        itemTable.setItems(items);
    }

    public void addButton_pressed(ActionEvent event) throws IOException
    {
        ItemDTO selectedItem = itemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;
        if (selectedItem.getCount() > 0) {
            selectedItem.setCount(selectedItem.getCount() - 1);
            Cart.getInstance().addToCart(new ItemDTO(
                    selectedItem.getItemId(),
                    selectedItem.getName(),
                    1,
                    selectedItem.getPrice()));
            itemTable.refresh();
        }
        Cart.getInstance().showCart();

    }

    public void cartButton_preseed(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Корзина");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL); // делает окно модальным
        stage.showAndWait(); // ждёт, пока пользователь закроет корзину
    }
    public static void refreshTableView() throws IOException
    {
        ReqResHandler.send(null, RequestType.GET_ALL_ITEMS);

        Response response = ReqResHandler.receive();
        if (response.getResponseStatus() == ResponseStatus.ERROR) {
            System.out.println(response.getMessage());
            return;
        }
        // Используем TypeToken для десериализации
        Type listType = new TypeToken<List<ItemDTO>>() {
        }.getType();

        List<ItemDTO> tmpList = new Gson().fromJson(response.getData(), listType);
        items.setAll(tmpList);
    }
}

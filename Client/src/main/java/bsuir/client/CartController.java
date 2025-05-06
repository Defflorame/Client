package bsuir.client;

import Cart.Cart;
import EntityDTO.ItemDTO;
import EntityDTO.UserDTO;
import Enums.RequestType;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import network.ReqResHandler;
import network.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class CartController {

    @FXML
    private Button incrButton;

    @FXML
    private Button buyButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button decrButton;

    @FXML
    private Button dellButton;

    @FXML
    private TableColumn<ItemDTO, Number> countColumn;

    @FXML
    private TableView<ItemDTO> cartTable;

    @FXML
    private TableColumn<ItemDTO, String> nameColumn;

    @FXML
    private TableColumn<ItemDTO, Number> priceColumn;

    @FXML
    private Label responseLabel;

    public void setItems()
    {

    }

    @FXML
    private void initialize()
    {
        responseLabel.setVisible(false);
        cartTable.setItems(Cart.getInstance().getItems());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        countColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCount()));
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrice()));
    }

    @FXML
    private void incrButton_pressed(ActionEvent event) throws IOException
    {
        ItemDTO selectedItem = cartTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;
        int index = Cart.getInstance().getItems().indexOf(selectedItem);
        int index1 = UserPanelController.items.indexOf(selectedItem);
        if (index != -1 && index1 != -1 && UserPanelController.items.get(index1).getCount() > 0) {
            Cart.getInstance().getItems().get(index).setCount(Cart.getInstance().getItems().get(index).getCount() + 1);

            UserPanelController.items.get(index1).setCount(UserPanelController.items.get(index1).getCount() - 1);
            cartTable.refresh();
            UserPanelController.itemTableStatic.refresh(); // Обновить склад

        } else System.out.println("Недостаток товара на складе. Добавление невозможно.");

    }

    @FXML
    private void decrButton_pressed(ActionEvent event) throws IOException
    {
        ItemDTO selectedItem = cartTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        int index = Cart.getInstance().getItems().indexOf(selectedItem);
        int index1 = UserPanelController.items.indexOf(selectedItem);

        if (index != -1 && index1 != -1) {
            ItemDTO cartItem = Cart.getInstance().getItems().get(index);
            ItemDTO stockItem = UserPanelController.items.get(index1);
            if (selectedItem.getCount() > 1) {
                cartItem.setCount(cartItem.getCount() - 1);
            } else {
                Cart.getInstance().getItems().remove(index);
            }
            stockItem.setCount(stockItem.getCount() + 1);
        }
        cartTable.refresh();
        UserPanelController.itemTableStatic.refresh(); // Обновить склад


    }

    @FXML
    private void dellButton_pressed(ActionEvent event) throws IOException
    {
        ItemDTO selectedItem = cartTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) return;

        int index = Cart.getInstance().getItems().indexOf(selectedItem);
        int index1 = UserPanelController.items.indexOf(selectedItem);

        if (index != -1 && index1 != -1) {
            ItemDTO cartItem = Cart.getInstance().getItems().get(index);
            ItemDTO stockItem = UserPanelController.items.get(index1);

            stockItem.setCount(stockItem.getCount() + cartItem.getCount());
            Cart.getInstance().getItems().remove(index);
        }
        cartTable.refresh();
        UserPanelController.itemTableStatic.refresh(); // Обновить склад


    }

    @FXML
    private void clearButton_pressed(ActionEvent event) throws IOException
    {
        if (Cart.getInstance().getItems().isEmpty()) return;

        // Копируем текущие элементы в отдельный список
        ArrayList<ItemDTO> itemsCopy = new ArrayList<>(Cart.getInstance().getItems());

        for (ItemDTO i : itemsCopy) {
            int index1 = UserPanelController.items.indexOf(i);
            if (index1 != -1) {
                ItemDTO stockItem = UserPanelController.items.get(index1);
                stockItem.setCount(stockItem.getCount() + i.getCount());
            }
        }

        Cart.getInstance().getItems().clear();
        cartTable.refresh();
        UserPanelController.itemTableStatic.refresh(); // Обновить склад
    }

    @FXML
    private void buyButton_pressed(ActionEvent event) throws IOException
    {
        if(Cart.getInstance().getItems().isEmpty()) return;
        Cart.getInstance().getItems().sort(Comparator.comparingInt(ItemDTO::getItemId));
        Map<Integer, ObservableList<ItemDTO>> order = new HashMap<Integer, ObservableList<ItemDTO>>();
        order.put(WelcomeScreenController.userId, Cart.getInstance().getItems());

        ReqResHandler.send(order, RequestType.MAKE_ORDER);

        Cart.getInstance().getItems().clear();

        Response response = ReqResHandler.receive();
        if(response == null) {
            System.out.println("Ошибка сервера!");
            return;
        }
        UserPanelController.refreshTableView();

        responseLabel.setVisible(true);
        responseLabel.setText(response.getMessage());


    }
}

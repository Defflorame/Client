package Cart;

import EntityDTO.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.util.ArrayList;

public class Cart {
    private static Cart cart;
    @Getter
    private static ObservableList<ItemDTO> items = FXCollections.observableArrayList();
    private static double total;
    private Cart() {}

    public static Cart getInstance()
    {
        if(cart == null) return cart = new Cart();
        return cart;
    }
    public static void addToCart(ItemDTO item)
    {
        int index = items.indexOf(item);
        if(index == -1) items.add(item);
        else
        {
            items.get(index).setCount(items.get(index).getCount() + 1);
        }
    }

    public static void showCart()
    {
        double tmp = 0;
        for (var item : items) {
            tmp += item.getPrice() * item.getCount();
            System.out.println(item.getItemId() + " " + item.toString());
        }
        total = tmp;
        System.out.println("Итоговая сумма корзины: " + total);
    }
}

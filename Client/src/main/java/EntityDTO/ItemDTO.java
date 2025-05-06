package EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private int itemId;
    private String name;
    private int count;
    private double price;

    public ItemDTO(String name, int count, double price)
    {
        this.name = name;
        this.count = count;
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Название товара: " + name + " Количество товара: " + count + " Цена: " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDTO itemDTO = (ItemDTO) o;
        return itemId == itemDTO.itemId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(itemId);
    }


}

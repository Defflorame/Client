package EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_ItemDTO {
    private int orderItemId;
    private OrderDTO orderDTO;
    private ItemDTO itemDTO;
    private int itemCount;
}

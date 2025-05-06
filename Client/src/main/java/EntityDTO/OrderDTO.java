package EntityDTO;



import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int orderId;
    private LocalDateTime orderDate;
    private UserDTO userDTO;
    private Set<Order_ItemDTO> orderItems;
}

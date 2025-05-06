package EntityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDTO {
    private int userId;
    private String buyerAddress;
    private String buyerPhone;

    public BuyerDTO(String buyerAddress, String buyerPhone)
    {
        this.buyerAddress = buyerAddress;
        this.buyerPhone = buyerPhone;
    }
}

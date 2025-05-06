package EntityDTO;

import Enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private int userId;
    private String userName;
    private String password;
    private Roles role;
    private BuyerDTO buyerDTO;

    public UserDTO(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(String userName, String password, BuyerDTO buyerDTO)
    {
        this.userName = userName;
        this.password = password;
        this.buyerDTO = buyerDTO;
    }

}

package network;

import Enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response implements Serializable {
    private ResponseStatus responseStatus; // Успешен ли запрос
    private String message; // Сообщение от сервера (к примеру, информация об ошибке)
    private String data; // Строка с JSON-информацией

}

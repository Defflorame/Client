package network;

import Enums.RequestType;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {
    @NonNull
    private RequestType requestType; // Необходимая операция
    private String data; // Строка с JSON-информацией
}
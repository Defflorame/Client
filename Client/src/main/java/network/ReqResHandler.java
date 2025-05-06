package network;
import Enums.RequestType;

import com.google.gson.Gson;

import java.io.IOException;

public class ReqResHandler {

    public static void send(Object data, RequestType type) {
        Request request = new Request();
        request.setRequestType(type);
        request.setData(new Gson().toJson(data));

        ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
        ClientSocket.getInstance().getOut().flush();
    }

    public static Response receive() throws IOException
    {
        String answer = ClientSocket.getInstance().getInStream().readLine();
        return new Gson().fromJson(answer, Response.class);
    }
}

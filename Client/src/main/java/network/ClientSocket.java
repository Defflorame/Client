package network;

import EntityDTO.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ResourceBundle;


public class ClientSocket {
    @Getter
    @Setter
    private UserDTO userDTO;
    private static Socket socket;
    private BufferedReader in;
    @Getter
    @Setter
    private PrintWriter out;

    private static final ClientSocket SINGLE_INSTANCE = new ClientSocket();

    private ClientSocket()
    {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("server");
            String serverIP = bundle.getString("SERVER_IP");
            int serverPort = Integer.parseInt(bundle.getString("SERVER_PORT"));

            socket = new Socket(serverIP, serverPort);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ClientSocket getInstance()
    {
        return SINGLE_INSTANCE;
    }
    public Socket getSocket()
    {
        return socket;
    }
    public BufferedReader getInStream()
    {
        return in;
    }

    public void disconnect() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();
            System.out.println("Disconnected from the server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean testConnection()
    {
        try {
            socket.isConnected();

        }catch (NullPointerException e) {
            System.out.println("Отсутсвтует подключние к серверу!");
            return false;
        }
        return true;
    }

}

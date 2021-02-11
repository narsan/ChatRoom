package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private final int port = 8765;
    private ArrayList<Socket> clients;
    static HashMap<String, ObjectOutputStream> clientConnecter;

    public Server() throws IOException {
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<>();
        clientConnecter = new HashMap<>();

    }

    @Override
    public void run() {
        while (true) {

            try {
              Socket tmp = serverSocket.accept();
                clients.add(tmp);
                ClientHandler clientHandler = new ClientHandler(tmp);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        try {
            Thread tr = new Thread(new Server());
            tr.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

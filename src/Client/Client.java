package Client;

import Requests.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.net.Socket;

public class Client implements Runnable {
    private Socket socket;
    private String username;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private final int port = 8765;

    public Client(String username) throws IOException {
        this.username = username;
        try {
            socket = new Socket("localhost",8765);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendMessage(new Message(username,"server",null));
    }

    @Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message tmp;

        while (true){

            try {

                tmp = (Message) objectInputStream.readObject();
                System.out.println("*************************************");
                System.out.println(tmp.getFrom() + " : " + tmp.getMessage());
                System.out.println("*************************************");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUsername() {
        return username;
    }
    public void sendMessage(Message message) throws IOException {
        objectOutputStream.writeObject(message);
    }
}

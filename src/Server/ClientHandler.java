package Server;

import Requests.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    Socket client;
    ObjectInputStream objectInputStream;

    public ClientHandler(Socket client) throws IOException {
        System.out.println("client accepted ");
        this.client = client;
        objectInputStream = new ObjectInputStream(client.getInputStream());
    }

    @Override
    public void run() {
        while (true){
            Message tmp ;
            try {
                tmp = (Message) objectInputStream.readObject();
                System.out.println("new message");
                if(tmp.getTo().equals("server")){
                    Server.clientConnecter.put(tmp.getFrom(),new ObjectOutputStream(client.getOutputStream()));
                }else {
                    Server.clientConnecter.get(tmp.getTo()).writeObject(tmp);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}

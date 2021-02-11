package Client;

import Requests.Message;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String username;
        Scanner sc = new Scanner(System.in);
        System.out.println("plz enter your name :");
        username=sc.next();
        Client client = new Client(username);
        new Thread(client).start();
        while (true){
            Message message;
            System.out.println("To :");
            String to = sc.next();
            System.out.println("plz enter your message");
            String msg = sc.next();
            message = new Message(client.getUsername(),to,msg);
            client.sendMessage(message);
        }

    }
}

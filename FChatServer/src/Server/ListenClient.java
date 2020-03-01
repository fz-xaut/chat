package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ListenClient extends Thread {

    private Socket client = null;
    private String receive = null;

    public ListenClient(Socket client) throws IOException {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            try {
                DataInputStream output = new DataInputStream(client.getInputStream());
                String message = output.readUTF();
                if (receive == null) {
                    this.receive = message;
                    RecallClient recall = new RecallClient(client, receive);
                    recall.start();
                    System.out.println("客户端收听开启...");
                    System.out.println(Main.map.size());
                } else {
                    String sender = message.substring(0, 5);
                    String receiver = message.substring(5, 10);
                    message = message.substring(10, message.length());
                    Main.put(sender, receiver, message);
                    System.out.println("put操作"+receiver+"--"+message);
                }

            } catch (Exception e) {
                break;
            }
        }
    }

}

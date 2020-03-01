package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class RecallClient extends Thread {

    private Socket client = null;
    private String receiver = null;

    public RecallClient(Socket client, String receiver) throws IOException {
        this.client = client;
        this.receiver = receiver;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized(this) {
                    ArrayList<String> list = Main.get(receiver);
                    System.out.println("get����"+receiver+list);
                    if (list != null) {
                        System.out.println("����client��...");
                        for (String msg : list) {
                            DataOutputStream input = new DataOutputStream(client.getOutputStream());
                            input.writeUTF(msg);
                        }
                        Main.map.remove(receiver);
                        System.out.println("ɾ����");
                    }
                }
                this.sleep(5000);
            } catch (Exception e) {
                break;
            }
        }
    }

}

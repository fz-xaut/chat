package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Server.ListenClient;

public class Main {

    public static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

    public static synchronized void put(String sender, String receiver, String message) {

        ArrayList<String> list = Main.map.get(receiver);
        if (list == null) {
            list = new ArrayList<String>();
            list.add(sender + message);
            Main.map.put(receiver, list);
        } else {
            list.add(sender + message);
            Main.map.put(receiver, list);
        }
    }

    public static synchronized ArrayList get(String receiver) {

        ArrayList<String> list = Main.map.get(receiver);
        return list;
    }

    public static synchronized void remove(String receiver) {

        Main.map.remove(receiver);
    }

    public static void main(String args[]) {

        try {
            ServerSocket server = new ServerSocket(8888);
            ExecutorService executor = Executors.newFixedThreadPool(100);
            while (true) {
                Socket client = server.accept();
                System.out.println("连接了一个");
                executor.execute(new ListenClient(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

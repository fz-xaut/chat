package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import view.chatting;

public class sender {

    public static String receiveMessage = null;

    public void sendMessage(String sender, String receiver, String message) throws InterruptedException {
            try {
                System.out.println(chatting.socket);
                DataOutputStream input = new DataOutputStream(chatting.socket.getOutputStream());
                input.writeUTF(sender+receiver+message);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}

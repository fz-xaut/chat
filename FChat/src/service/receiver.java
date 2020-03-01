package service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import view.chatting;

public class receiver extends Thread{
    
    public static String receiveMessage = null;
    
    public void run() {
        
        while (true) {
            try {
                DataInputStream output = new DataInputStream(chatting.socket.getInputStream());
                receiveMessage = output.readUTF();
                chatting.addrecord(receiveMessage.substring(0, 5), receiveMessage.substring(5, receiveMessage.length()));
                
                } catch (IOException e) {
                    System.out.println("读取信息error");
                    break;
                }      
        }   
    }
    
}

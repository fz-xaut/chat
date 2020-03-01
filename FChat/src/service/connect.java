package service;

import java.io.IOException;
import java.net.Socket;

import view.chatting;

public class connect {
    
    public void connecting() {
        
        try {
            chatting.socket =  new Socket("localhost", 8888);
            System.out.println("连接服务器成功...");
        } catch (IOException e) {
            System.out.println("连接服务器失败...");
        }
    }

}

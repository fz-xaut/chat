package service;

import java.io.IOException;
import java.net.Socket;

import view.chatting;

public class connect {
    
    public void connecting() {
        
        try {
            chatting.socket =  new Socket("localhost", 8888);
            System.out.println("���ӷ������ɹ�...");
        } catch (IOException e) {
            System.out.println("���ӷ�����ʧ��...");
        }
    }

}

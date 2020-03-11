package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.*;
import java.net.Socket;

import javax.swing.*;

import dao.searchDao;
import service.connect;
import service.receiver;
import service.sender;

@SuppressWarnings("serial")
public class chatting extends JFrame{
    
    public static String text;
    public static String text2;
    public static int flag = 0;
    public static String receiver = null;
    public static Socket socket = null;
    public static JTextArea record;
    
	public chatting(String username) {
	    
	    super("FChat");
	    
	    new connect().connecting();
	    sender sending = new sender();
	    try {
            sending.sendMessage(username);
        } catch (InterruptedException e2) {}
	    
	    receiver receive = new receiver();
	    receive.start();
	    
		Container cont = getContentPane();
		cont.setLayout(null);
		record = new JTextArea();
		record.setLayout(new BoxLayout(record,  BoxLayout.Y_AXIS));
		record.setFont(new Font("Serif",0,20));
		JTextArea myword = new JTextArea();
		JTextArea my = new JTextArea();
		JTextArea search = new JTextArea();
		JButton clear = new JButton();
		JButton searching = new JButton();
		JTextArea title = new JTextArea();
		JButton close = new JButton("关闭");
		JButton send = new JButton("发送");
        record.setEditable(false);
		myword.setEditable(false);
		
		String u = searchDao.getContacts(username);
		String users[] = u.split("#");
		
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel,  BoxLayout.Y_AXIS));
		
		for (int i = 0; i< users.length; i++) {
		    JTextArea newContact = new JTextArea(users[i]);
		    newContact.setEditable(false);
		    newContact.setFont(new Font("Serif",0,30));
		    newContact.setBorder (BorderFactory.createLineBorder(Color.gray,1));
		    newContact.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                
	                //这里开始插入聊天的功能
	                receiver = newContact.getText().trim();
	                title.setText("正在与"+receiver+"聊天...");
	                title.setFont(new Font("Serif",0,22));
	                title.setEditable(false);
	                myword.requestFocus();
	                myword.setFont(new Font("Serif",0,25));
	                myword.setEditable(true);
	            }
	        });
		    jPanel.add(newContact);
		}
		
		JScrollPane contacts=new JScrollPane(jPanel);
		contacts.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JScrollPane records = new JScrollPane(record);
		
		cont.add(records);
		cont.add(myword);
		cont.add(contacts);
		cont.add(my);
		cont.add(search);
		cont.add(title);
		cont.add(close);
		cont.add(send);
		cont.add(searching);
		cont.add(clear);
		
		my.setBounds(5,5,315,80);
		search.setBounds(5,90,145,30);
		clear.setBounds(155, 90, 80, 30);
		searching.setBounds(240, 90, 80, 30);
		contacts.setBounds(5,125,315,525);
		title.setBounds(325,5,552,30);
		records.setBounds(325,40,552,355);
		close.setBounds(710,400,80,30);
        send.setBounds(795,400,80,30);
		myword.setBounds(325,435,552,212);
		
		my.setText("FNo: "+username+"\n当前在线");
		my.setFont(new Font("Serif",0,30));
		my.setEditable(false);
		search.setFont(new Font("Serif",0,20));
		clear.setText("清空");
		searching.setText("搜索");
		clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search.setText("");
            }
        });
		searching.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String friend = search.getText().trim();
                
            }
        });
		close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myword.setEditable(false);
                title.setText("");
            }
        });
		send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = myword.getText().trim();
                myword.setText("");
                myword.requestFocus();
                try {
                    sending.sendMessage(username,receiver,message);
                    addmyrecordmy(message);
                } catch (InterruptedException e1) {}
                
            }
        });
		myword.addKeyListener(new KeyListener() {
		    
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    String message = myword.getText().trim();
                    if (message != null && !message.equals("")) {
                        try {
                            sending.sendMessage(username,receiver,message);
                            addmyrecordmy(message);
                        } catch (InterruptedException e1) {}
                        myword.setText("");
                        myword.requestFocus();
                    }
                } 
            }
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
 
        });
		
		setSize(900,700);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void addmyrecordmy(String message) {
        
        record.append("myself"+"："+message+"\n");
    }
	
	public static void addrecord(String receive, String receiveMessage) {
	    
	    record.append(receive+"："+receiveMessage+"\n");
	}
}

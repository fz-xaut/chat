package view;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import dao.searchDao;
import entity.user;

public class index extends JFrame{

	public static void main(String[] args) {
		new index();
	}
	public index(){
		super("FChat");
		Container cont = getContentPane();
		cont.setLayout(null);
		JLabel tip = new JLabel("welcome to login!");
		JLabel uName = new JLabel("username:");
		JLabel pWord = new JLabel("password:");
		JTextField userName = new JTextField();
		JTextField passWord = new JTextField();
		JButton login = new JButton("login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user user = new user();
				user.setUsername(userName.getText().toString());
				user.setPassword(passWord.getText().toString());
				String result = searchDao.SearchUserName(user);
				tip.setText(result);
				if (result.equals("success")) {
					setVisible(false);
					new chatting(user.getUsername());
				}
			}
		});
		cont.add(tip);
		cont.add(uName);
		cont.add(pWord);
		cont.add(userName);
		cont.add(passWord);
		cont.add(login);
		tip.setBounds(150,50,100,10);
		uName.setBounds(65,90,100,50);
		pWord.setBounds(65,140,100,50);
		userName.setBounds(135,95,150,40);
		passWord.setBounds(135,145,150,40);
		login.setBounds(150,220,100,30);
		setSize(400,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

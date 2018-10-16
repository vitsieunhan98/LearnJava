import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class ClientFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	JTextArea screen;
	JLabel lblChat;
	JTextField input;
	public static JTextArea list_online;
	public JTextField nick;
	
	JLabel lblnick;
	JButton btnOk;
	JButton btnSend;
	private JLabel lblOnline;
	
	JButton btnLogout;
	
	Client client;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame frame = new ClientFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		screen = new JTextArea();
		screen.setBackground(Color.MAGENTA);
		sl_contentPane.putConstraint(SpringLayout.NORTH, screen, 77, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, screen, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, screen, -37, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, screen, 707, SpringLayout.WEST, contentPane);
		screen.setEditable(false);
		contentPane.add(screen);
		
		lblChat = new JLabel("Chat");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblChat, 0, SpringLayout.WEST, screen);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblChat, -8, SpringLayout.SOUTH, contentPane);
		lblChat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblChat);
		
		input = new JTextField();
		input.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.WEST, input, 20, SpringLayout.EAST, lblChat);
		sl_contentPane.putConstraint(SpringLayout.EAST, input, 0, SpringLayout.EAST, screen);
		sl_contentPane.putConstraint(SpringLayout.NORTH, input, 7, SpringLayout.SOUTH, screen);
		contentPane.add(input);
		input.setColumns(10);
		
		list_online = new JTextArea();
		list_online.setEditable(false);
		list_online.setBackground(Color.CYAN);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list_online, 0, SpringLayout.NORTH, screen);
		sl_contentPane.putConstraint(SpringLayout.WEST, list_online, 6, SpringLayout.EAST, screen);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list_online, -37, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list_online, -14, SpringLayout.EAST, contentPane);
		contentPane.add(list_online);
		
		lblnick = new JLabel("Enter your nickname");
		lblnick.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblnick, 22, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblnick, 0, SpringLayout.WEST, screen);
		contentPane.add(lblnick);
		
		nick = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.EAST, nick, 274, SpringLayout.EAST, lblnick);
		sl_contentPane.putConstraint(SpringLayout.NORTH, nick, 0, SpringLayout.NORTH, lblnick);
		sl_contentPane.putConstraint(SpringLayout.WEST, nick, 6, SpringLayout.EAST, lblnick);
		contentPane.add(nick);
		nick.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(this);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnOk, -2, SpringLayout.NORTH, lblnick);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOk, 38, SpringLayout.EAST, nick);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnOk, 4, SpringLayout.SOUTH, lblnick);
		contentPane.add(btnOk);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(this);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnSend, -25, SpringLayout.SOUTH, lblChat);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnSend, 11, SpringLayout.EAST, input);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnSend, 0, SpringLayout.SOUTH, lblChat);
		contentPane.add(btnSend);
		
		lblOnline = new JLabel("Online");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblOnline, 0, SpringLayout.WEST, list_online);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblOnline, -12, SpringLayout.NORTH, list_online);
		contentPane.add(lblOnline);
		
		btnLogout = new JButton("Logout");
		btnLogout.setVisible(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnLogout, -2, SpringLayout.NORTH, lblnick);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnLogout, 73, SpringLayout.EAST, btnOk);
		contentPane.add(btnLogout);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnOk)) {
			if(!nick.getText().equals("")) {
				nick.setEditable(false);
				input.setEditable(true);
				btnOk.setVisible(false);
				btnLogout.setVisible(true);
				
				client = new Client();
				client.sendMSG(nick.getText());
				
			}
		}
		
		else if(e.getSource().equals(btnLogout)) {
			client.Logout();
		}
	}
}

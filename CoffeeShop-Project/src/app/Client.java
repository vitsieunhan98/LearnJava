package app;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import clientframe.ClientManager;
import clientframe.ClientSignup;
import db.DataStream;

public class Client extends JFrame implements ActionListener {

	final static String HostServer = "192.168.159.1";
	private Socket client;
    private DataOutputStream os;
	private DataInputStream is;
	public static DataStream datastream;
	private JPanel contentPane;
	public JTextField email;
	private JTextField password;
	private JButton btnLogin;
	private JButton btnSignUp;
	private Server sv;

	
	public void run() {
		try {	
			client = new Socket(HostServer, 9999);
			os = new DataOutputStream(client.getOutputStream());
			is = new DataInputStream(client.getInputStream());		
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public Client() {
		// TODO Auto-generated constructor stub
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		email = new JTextField();
		email.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		password = new JTextField();
		password.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		
		JLabel lblYouDontHave = new JLabel("You don't have user?");
		lblYouDontHave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(this);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(148)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLogin)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword)
								.addComponent(lblEmail))
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblYouDontHave)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSignUp)))
					.addContainerGap(188, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(89)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(18)
					.addComponent(btnLogin)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSignUp)
						.addComponent(lblYouDontHave))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		this.setVisible(true);
	}

	public void logout() {
		datastream.stopThread();
		try {
			sendMSG("log-out");
			os.close();
			is.close();
			client.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void sendMSG(String data) {
		try {
			os.writeUTF(data);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client().run();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnLogin)) {
			try {
				if(sv.isExisted(email.getText(), password.getText(), 2)) {
					this.setVisible(false);
					
					sendMSG(email.getText());
					datastream = new DataStream(this, this.is);
					
					new ClientManager(this).setVisible(true);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(btnSignUp)) {
			new ClientSignup().setVisible(true);
		}
	}
}

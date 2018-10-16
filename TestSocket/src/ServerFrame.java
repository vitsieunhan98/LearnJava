import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;

public class ServerFrame extends JFrame {

	private JPanel contentPane;
	static JTextArea txtrA;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerFrame frame = new ServerFrame();
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
	public ServerFrame() {
		super("Server");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblServerIsRunning = new JLabel("Server is running");
		lblServerIsRunning.setForeground(Color.GREEN);
		lblServerIsRunning.setFont(new Font("Tahoma", Font.PLAIN, 22));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblServerIsRunning, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblServerIsRunning, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblServerIsRunning);
		
		txtrA = new JTextArea();
		txtrA.setEditable(false);
		sl_contentPane.putConstraint(SpringLayout.NORTH, txtrA, 92, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, txtrA, 87, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, txtrA, -58, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, txtrA, -88, SpringLayout.EAST, contentPane);
		contentPane.add(txtrA);
		
		JLabel lblStatus = new JLabel("Status");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblStatus, 0, SpringLayout.WEST, txtrA);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblStatus, -10, SpringLayout.NORTH, txtrA);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblStatus);
		
		this.setVisible(true);
	}
	
	
}

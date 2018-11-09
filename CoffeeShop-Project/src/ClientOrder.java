import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ClientOrder extends JFrame {

	private JPanel contentPane;
	private JTextField address;
	private JTextField price;
	private JTextField date;
	private JComboBox comboBox;
	
	private static Client client;
	public static int pricee;
	public static List<String> picked_product;
	public static List<Integer> id_picked_product;
	private static Server sv;
	private JButton btnOrder;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientOrder frame = new ClientOrder(client, pricee, picked_product, id_picked_product);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addProduct(List<String> picked_product) {
		for(int i=0; i<picked_product.size(); i++) {
			String name = picked_product.get(i);
			comboBox.addItem(name);
		}
	}

	/**
	 * Create the frame.
	 */
	public ClientOrder(Client client, int pricee, List<String> picked_product, List<Integer> id_picked_product) {
		setTitle(client.email.getText());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 785, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		address = new JTextField();
		address.setColumns(10);
		
		price = new JTextField(Integer.toString(pricee));
		price.setEditable(false);
		price.setColumns(10);
		
		date = new JTextField(new Date().toString());
		date.setEditable(false);
		date.setColumns(10);
		
		comboBox = new JComboBox();
		addProduct(picked_product);
		
		btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i=0; i<picked_product.size(); i++) {
					try {
						client.sendMSG("order");
						client.sendMSG(Integer.toString(id_picked_product.get(i)));
						client.sendMSG(Integer.toString(sv.getIdUser(client.email.getText())));
						client.sendMSG(address.getText());
						client.sendMSG(date.getText());
						client.sendMSG(sv.getPrice(picked_product.get(i)));

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				new JOptionPane().showMessageDialog(client, "Order Successfull");
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(148)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddress)
						.addComponent(lblPrice)
						.addComponent(lblDate)
						.addComponent(lblProduct))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(date)
							.addComponent(price)
							.addComponent(address, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(135, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(70)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProduct)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate)
						.addComponent(date, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrice)
						.addComponent(price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnOrder)
					.addContainerGap(55, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}

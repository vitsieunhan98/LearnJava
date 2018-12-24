package clientframe;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import app.Client;
import app.Server;
import domain.Product;

import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientProduct extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Server sv;
	private static Client client;
	
	public List<String> picked_product;
	public List<Integer> id_picked_product;
	public int total;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientProduct frame = new ClientProduct(client);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void updateTable() throws SQLException {
		List product = new ArrayList<>(sv.list_product);
		
		for(int i=0; i<product.size(); i++) {
			Product one = (Product) product.get(i);
			Vector row = new Vector();
			row.add(one.getId());
			row.add(one.getName());
			row.add(one.getPrice());
			row.add((one.getId_type() == 1) ? "Drink" : "Food");
			row.add(false);
			model.addRow(row);
		}
		table.setModel(model);
	}
	
	public void getOrder() {
		for(int i=0; i<table.getRowCount(); i++) {
			int id = (int) table.getModel().getValueAt(i, 0);
			String name = (String) table.getModel().getValueAt(i, 1);
			String price = (String) table.getModel().getValueAt(i, 2);
			String type = (String) table.getModel().getValueAt(i, 3);
			Boolean pick = (Boolean) table.getModel().getValueAt(i, 4);
			
			if(pick) {
				picked_product.add(name);
				id_picked_product.add(id);
				total += Integer.parseInt(price);
			}
		}
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ClientProduct(Client client) throws SQLException {
		setTitle(client.email.getText());
		
		picked_product = new ArrayList<String>();
		id_picked_product = new ArrayList<Integer>();
		total = 0;
		
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Product", "Price", "Type", "Order"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getOrder();
				setVisible(false);
				new ClientOrder(client, total, picked_product, id_picked_product).setVisible(true);
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				new ClientManager(client).setVisible(true);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(606, Short.MAX_VALUE)
					.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBack))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack)
						.addComponent(btnOrder)))
		);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(model);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		sv.getListProduct();
		updateTable();
	}
}

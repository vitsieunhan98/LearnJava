import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClientHistory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	
	private static Client client;
	private static Server sv;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientHistory frame = new ClientHistory(client);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void updateTable(Client client) throws SQLException {
		sv.getListOrder();
		List order = new ArrayList<>(sv.list_order);
		
		for(int i=0; i<order.size(); i++) {
			Order one = (Order) order.get(i);
			if(sv.getEmailUser(one.getUser_id()).equals(client.email.getText())) {
				Vector row = new Vector();
				row.add(sv.getNameProduct(one.getId_product()));
				row.add(one.getDate_order());
				row.add(one.getPrice());
				
				model.addRow(row);
			}
		}
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ClientHistory(Client client) throws SQLException {
		setTitle(client.email.getText());
		model = new DefaultTableModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
		);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table.setBackground(Color.WHITE);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		Vector column = new Vector();
		column.add("Product");
		column.add("Date");
		column.add("Price");
		model.setColumnIdentifiers(column);
		updateTable(client);
		table.setModel(model);
	}
}

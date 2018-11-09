import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AdminFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	private JButton btnProductManager;
	private JButton btnListOrder;
	private JButton btnListUser;
	private JButton btnLogout;
	private GroupLayout gl_contentPane;
	
	private DefaultTableModel modelpro;
	private DefaultTableModel modelord;
	private DefaultTableModel modeluser;
	private Server sv;
	private JTable table_product;
	private JTable table_order;
	private JTable table_user;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void createProductTable() throws SQLException {
		Vector column = new Vector();
		column.add("Id");
		column.add("Product");
		column.add("Price");
		column.add("Type");
		modelpro.setColumnIdentifiers(column);
		sv.getListProduct();
	}
	
	public void createOrderTable() throws SQLException {
		Vector column = new Vector();
		column.add("Id");
		column.add("Product");
		column.add("User");
		column.add("Address");
		column.add("Date");
		column.add("Price");
		modelord.setColumnIdentifiers(column);
		sv.getListOrder();
	}
	
	public void createUserTable() throws SQLException {
		Vector column = new Vector();
		column.add("Id");
		column.add("Full Name");
		column.add("Email");
		column.add("Phone");
		column.add("Role");
		modeluser.setColumnIdentifiers(column);
		sv.getListUser();
	}
	
	public void updateProductTable() throws SQLException {
		List product = new ArrayList<>(sv.list_product);
		
		for(int i=0; i<product.size(); i++) {
			Product one = (Product) product.get(i);
			Vector row = new Vector();
			row.add(one.getId());
			row.add(one.getName());
			row.add(one.getPrice());
			row.add((one.getId_type() == 1) ? "Drink" : "Food");
			
			modelpro.addRow(row);
		}
	}
	
	public void updateOrderTable() throws SQLException {
		List order = new ArrayList<>(sv.list_order);
		
		for(int i=0; i<order.size(); i++) {
			Order one = (Order) order.get(i);
			Vector row = new Vector();
			row.add(one.getId());
			row.add(sv.getNameProduct(one.getId_product()));
			row.add(sv.getNameUser(one.getUser_id()));
			row.add(one.getOrder_placed());
			row.add(one.getDate_order());
			row.add(one.getPrice());
			
			modelord.addRow(row);
		}
	}
	
	public void updateUserTable() throws SQLException {
		List user = new ArrayList<>(sv.list_user);
		
		for(int i=0; i<user.size(); i++) {
			User one = (User) user.get(i);
			Vector row = new Vector();
			row.add(one.getId());
			row.add(one.getFullname());
			row.add(one.getEmail());
			row.add(one.getPhone());
			row.add((one.getId_role() == 1) ? "Admin" : "User");
			
			modeluser.addRow(row);
		}
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminFrame() throws SQLException {
		super("Manager");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnListOrder = new JButton("List Order");
		btnListOrder.addActionListener(this);
		
		btnProductManager = new JButton("Product Manager");
		btnProductManager.addActionListener(this);
		
		btnListUser = new JButton("List User");
		btnListUser.addActionListener(this);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(this);
		
		scrollPane = new JScrollPane();
		
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnListOrder)
					.addGap(250)
					.addComponent(btnProductManager)
					.addPreferredGap(ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
					.addComponent(btnListUser)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(387, Short.MAX_VALUE)
					.addComponent(btnLogout)
					.addGap(380))
				.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnListOrder)
						.addComponent(btnListUser)
						.addComponent(btnProductManager))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogout))
		);
		
		modelpro = new DefaultTableModel();
		modelord = new DefaultTableModel();
		modeluser = new DefaultTableModel();
		contentPane.setLayout(gl_contentPane);
		
		table_product = new JTable();
		table_product.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table_product.setBackground(Color.WHITE);
		createProductTable();
		updateProductTable();
		table_product.setModel(modelpro);
		table_product.setVisible(false);
		
		table_order = new JTable();
		table_order.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table_order.setBackground(Color.WHITE);
		createOrderTable();
		updateOrderTable();
		table_order.setModel(modelord);
		table_order.setVisible(false);
		
		table_user = new JTable();
		table_user.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table_user.setBackground(Color.WHITE);
		createUserTable();
		updateUserTable();
		table_user.setModel(modeluser);
		table_user.setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnProductManager)) {
			table_product.setVisible(true);
			this.scrollPane.setViewportView(table_product);
		}
		else if(e.getSource().equals(btnListOrder)) {
			table_order.setVisible(true);
			this.scrollPane.setViewportView(table_order);
		}
		else if(e.getSource().equals(btnListUser)) {
			table_user.setVisible(true);
			this.scrollPane.setViewportView(table_user);
		}
		else if(e.getSource().equals(btnLogout)) {
			this.setVisible(false);
			try {
				new ServerFrame().setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

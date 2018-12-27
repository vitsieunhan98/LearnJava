package svframe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.Server;
import domain.Order;
import domain.Product;
import domain.User;

import javax.swing.JButton;
import javax.swing.JComboBox;

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
import javax.swing.JTextField;

public class AdminFrame extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	private JButton btnProductManager;
	private JButton btnListOrder;
	private JButton btnListUser;
	private JButton btnAddProduct;
	private JButton btnDeleteProduct;
	private JButton btnRefresh;
	private GroupLayout gl_contentPane;
	
	private DefaultTableModel modelpro;
	private DefaultTableModel modelorder;
	private DefaultTableModel modeluser;
	
	public Server sv;
	private JTable table_product;
	private JTable table_order;
	private JTable table_user;
	private JScrollPane scrollPane;
	
	private Vector columnpro;
	private Vector columnord;
	private Vector columnuser;
	
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
	
	public void updateProductTable() throws SQLException {
		sv.getListProduct();
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
		
		table_product.setModel(modelpro);
		modelpro.fireTableDataChanged();
	}
	
	public void updateOrderTable() throws SQLException {
		sv.getListOrder();
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
			
			modelorder.addRow(row);
		}
		
		table_order.setModel(modelorder);
		modelorder.fireTableDataChanged();
	}
	
	public void updateUserTable() throws SQLException {
		sv.getListUser();
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
		
		table_user.setModel(modeluser);
		modeluser.fireTableDataChanged();
	}
	
	public void insert(String name, String price, int type) throws SQLException {
		int id = sv.getLastId() + 1;
		DefaultTableModel model = (DefaultTableModel) table_product.getModel();
		Vector row = new Vector();
		row.add(id);
		row.add(name);
		row.add(price);
		row.add(type);
		
		model.addRow(row);
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminFrame() throws SQLException {
		super("Manager");
		
		modelpro = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Product", "Price", "Type"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		
		modeluser = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Fullname", "Email", "Phone", "Role"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};	
		
		modelorder = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Product", "User", "Address", "Date", "Price"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
				
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
		
		scrollPane = new JScrollPane();
		
		btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(this);
		
		btnDeleteProduct = new JButton("Delete Product");
		btnDeleteProduct.addActionListener(this);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(this);
		
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
					.addContainerGap(158, Short.MAX_VALUE)
					.addComponent(btnDeleteProduct)
					.addGap(115)
					.addComponent(btnRefresh)
					.addGap(118)
					.addComponent(btnAddProduct)
					.addGap(154))
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddProduct)
						.addComponent(btnDeleteProduct)
						.addComponent(btnRefresh)))
		);
		
		contentPane.setLayout(gl_contentPane);
		
		table_product = new JTable();
		table_product.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table_product.setBackground(Color.WHITE);
		updateProductTable();
		table_product.setVisible(false);
		
		table_order = new JTable();
		table_order.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table_order.setBackground(Color.WHITE);
		updateOrderTable();
		table_order.setVisible(false);
		
		table_user = new JTable();
		table_user.setFont(new Font("Tahoma", Font.PLAIN, 17));
		table_user.setBackground(Color.WHITE);
		updateUserTable();
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
		else if(e.getSource().equals(btnAddProduct)) {
			new AddProductFrame().setVisible(true);
		}
		else if(e.getSource().equals(btnDeleteProduct)) {
			try {
				sv.list_product = new ArrayList<>();
				new DeleteProductFrame().setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource().equals(btnRefresh)) {
			modelpro.setRowCount(0);
			modelorder.setRowCount(0);
			modeluser.setRowCount(0);
			try {
				sv.list_product = new ArrayList<>();
				sv.list_order = new ArrayList<>();
				sv.list_user = new ArrayList<>();
				updateProductTable();
				updateOrderTable();
				updateUserTable();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

package svframe;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.Server;
import domain.Product;

public class DeleteProductFrame extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Server sv;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private List<String> picked_product;
	private List<Integer> id_picked_product;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteProductFrame frame = new DeleteProductFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void updateTable() throws SQLException {
		sv.getListProduct();
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
	
	public void getDelete() {
		for(int i=0; i<table.getRowCount(); i++) {
			int id = (int) table.getModel().getValueAt(i, 0);
			String name = (String) table.getModel().getValueAt(i, 1);
			String price = (String) table.getModel().getValueAt(i, 2);
			String type = (String) table.getModel().getValueAt(i, 3);
			Boolean pick = (Boolean) table.getModel().getValueAt(i, 4);
			
			if(pick) {
				picked_product.add(name);
				id_picked_product.add(id);
			}
		}
	}
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public DeleteProductFrame() throws SQLException{
		setTitle("Delete Product");
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Product", "Price", "Type", "Delete"
				}
			) {
				Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class, String.class, Boolean.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
			
		picked_product = new ArrayList<String>();
		id_picked_product = new ArrayList<Integer>();	
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 784, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getDelete();
				for(int i=0; i<id_picked_product.size(); i++) {
					try {
						sv.deleteProduct(id_picked_product.get(i));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				setVisible(false);
				new JOptionPane().showMessageDialog(null, "Delete Product Successfull");
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(653, Short.MAX_VALUE)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 415, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		updateTable();
	}
	
}

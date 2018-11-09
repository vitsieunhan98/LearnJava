import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class Server {

	private static final String URL = "jdbc:mysql://localhost:3306/coffeeshop";
	private static final String USER = "root";
	private static final String PASSWORD = "";	
	public static Connection conn;
	public static Statement state;
	public static ConnectDB cdb = new ConnectDB();
	private static InetAddress host;
	public static ServerSocket listener;
	public static List<Product> list_product = new ArrayList<Product>();
	public static List<User> list_user = new ArrayList<User>();
	public static List<Order> list_order = new ArrayList<Order>();
	public static final String[] PRODUCT_TYPE = new String[] {"Drink", "Food"};
	public static final String[] ROLE = new String[] {"Admin", "User"};
	
	private void run() throws IOException {
		try {
			host = InetAddress.getByName("192.168.100.6");
			listener = new ServerSocket(9999);
			
			
			while(true) {
				Socket client = listener.accept();
				new ClientConnect(this, client);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			listener.close();
		}
	}
	
	public static void getListProduct() throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "select * from product";
		ResultSet result = state.executeQuery(sql);
		
		while(result.next()) {
			Product new_product = new Product(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4));
			list_product.add(new_product);
		}
	}
	
	public static void getListOrder() throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "select * from `order`";
		ResultSet result = state.executeQuery(sql);
		
		while(result.next()) {
			Order new_order = new Order(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4), result.getString(5), result.getString(6));
			list_order.add(new_order);
		}
	}
	
	public static void getListUser() throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "select * from user";
		ResultSet result = state.executeQuery(sql);
		
		while(result.next()) {
			User new_user = new User(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6));
			list_user.add(new_user);
		}
	}
	
	public static String getNameProduct(int id) throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "select Name from product where Id = " + id;
		
		String rs = "";
		
		ResultSet result = state.executeQuery(sql);
		if(result.next()) {
			rs = result.getString("Name");
		}
		return rs;
	}
	
	public static String getPrice(String name) throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "select Price from product where Name = '" + name + "'";
		
		String rs = "";
		
		ResultSet result = state.executeQuery(sql);
		if(result.next()) {
			rs = result.getString("Price");
		}
		return rs;
	}
	
	public static String getNameUser(int id) throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "select Fullname from user where Id = " + id;
		
		String rs = "";
		
		ResultSet result = state.executeQuery(sql);
		if(result.next()) {
			rs = result.getString("Fullname");
		}
		return rs;
	}
	
	public static String getEmailUser(int id) throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "select Email from user where Id = " + id;
		
		String rs = "";
		
		ResultSet result = state.executeQuery(sql);
		if(result.next()) {
			rs = result.getString("Email");
		}
		return rs;
	}
	
	public static int getIdUser(String email) throws SQLException {
		state = new ConnectDB().connect(conn, URL, USER, PASSWORD, state);
		String sql = "select Id from user where Email = '" + email + "'";
		
		int rs = 0;
		
		ResultSet result = state.executeQuery(sql);
		if(result.next()) {
			rs = result.getInt("Id");
		}
		return rs;
	}
	
	public static boolean isExisted(String email, String password, int role_id) throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "select * from user where Email = '" + email +"' and Password = '" + password + "' and Id_Role = " + role_id;
		ResultSet rs = state.executeQuery(sql);
		return rs.next();
	}
	
	public static void getSignUp(String fullname, String email, String password, String phone) throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "insert into user(Fullname, Email, Password, Phone, Id_Role) values('" + fullname + "','" + email + "','" + password + "','" + phone + "',2)";
		state.execute(sql);
		state.close();
	}
	
	public static void getOrder(int product_id, int user_id, String address, String date, String price) throws SQLException {
		state = cdb.connect(conn, URL, USER, PASSWORD, state);
		String sql = "insert into `order`(Id_Product, User_Id, Order_Placed, Date_Order, Price) values(" + product_id + "," + user_id + ",'" + address + "','" + date + "','" + price + "')";
 		state.execute(sql);
		state.close();
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		new ServerFrame();
		new Server().run();
	}

	
	
}

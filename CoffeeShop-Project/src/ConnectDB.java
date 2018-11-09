import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ConnectDB {
	
	public ConnectDB() {
		// TODO Auto-generated constructor stub
	}
	
	public Statement connect(Connection conn, String url, String user, String password, Statement state) throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
		state = conn.createStatement();
		return state;
	}
	
	public static void close(Connection conn) throws SQLException {
		conn.close();
	}
}

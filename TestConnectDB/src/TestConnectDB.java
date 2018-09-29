import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectDB {
	public TestConnectDB() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement state = null;
		ResultSet result = null;
		
		String url = "jdbc:mysql://localhost:3306/hotelreserve";
		String user = "root";
		String password = "";
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			
			// Tạo 1 đối tượng lớp Statement để gửi các câu truy vấn lên csdl
			state = conn.createStatement();
			
			String sql = "select * from city";
			
			//Thực thi câu truy vấn
			result = state.executeQuery(sql);
			
			while(result.next()) {
				System.out.println("-------");
				System.out.println("City Id: " + result.getInt("Id"));
				System.out.println("City Name: " + result.getString("Name"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(result!=null) {
					result.close();
				}
				if(state!=null) {
					state.close();
				}
				if(conn!=null) {
					conn.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

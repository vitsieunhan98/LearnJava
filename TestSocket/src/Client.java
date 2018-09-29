import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String HostServer = "localhost";
		
		Socket client = null;
		
		BufferedWriter ous = null; // Luồng dữ liệu client gửi đi
		BufferedReader ins = null; // Luồng dữ liệu client nhận được
		
		try {
			//Gửi yêu cầu kết nối tới server
			client = new Socket(HostServer, 9999);
			
			//Luồng đầu ra từ client gửi tới server
			ous = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			
			//Luồng dầu vào tại client từ server gửi tới
			ins = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch(Exception e) {
			System.out.println(e);
			return;
		}
		
		try {
			ous.write("Hi");
			ous.newLine();
			ous.flush();
			
			ous.write("Hello");
			ous.newLine();
			ous.flush();
			
			ous.write("QUIT");
			ous.newLine();
			ous.flush();
			
			String response;
			// Đoc dữ liệu từ phía server gửi tới client
			while((response = ins.readLine()) != null) {
				System.out.println("Server: " + response);
				if(response.indexOf("OK") != -1) {
					break;
				}
			}
			
			ous.close();
			ins.close();
			client.close();
		} catch(Exception e) {
			System.out.println(e);
		}
	}

}

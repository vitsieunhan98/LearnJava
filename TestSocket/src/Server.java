import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public Server() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket listener = null;
		String mess;
		BufferedReader ins; // input từ client (đọc dữ liệu client nhập vào)
		BufferedWriter ous; // output tới client (gửi dữ liệu tới client)
		Socket server = null;
		
		try {
			listener = new ServerSocket(9999);
		}catch(Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		
		try {
			System.out.println("Server is waiting to accept user...");
			
			//Chấp nhận 1 yêu cầu kết nối từ client
			server = listener.accept();
			System.out.println("Accept a client!");
			
			//Mở luồng vào-ra trên Socket tại server
			ins = new BufferedReader(new InputStreamReader(server.getInputStream()));
			ous = new BufferedWriter(new OutputStreamWriter(server.getOutputStream()));
			
			while(true) {
				//đọc dữ liệu từ client gửi tới server
				mess = ins.readLine();
				
				// Ghi vào luồng ra của Socket tại server
				ous.write(">>" + mess);
				ous.newLine();
				ous.flush(); // Đẩy dữ liệu đi
				
				if(mess.equals("QUIT")) {
					ous.write(">> OK");
					ous.newLine();
					ous.flush();
					break;
				}
			}
		} catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("Server stopped!");
	}

}

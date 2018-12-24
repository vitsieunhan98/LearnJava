package clientframe;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import app.Server;

public class ClientConnect extends Thread {

	public Socket client;
	public Server sv;
	
	private DataInputStream is;
	private DataOutputStream os;
	private boolean run;
	
	private String email;
	
	public ClientConnect(Server sv, Socket client) {
		// TODO Auto-generated constructor stub
		try {
			this.sv = sv;
			this.client = client;
			run = true;
			
			is = new DataInputStream(client.getInputStream());
            os = new DataOutputStream(client.getOutputStream());
			
			this.start();
						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(run) {
			email = getMSG();
			System.out.println(email + " has joined");
			while(run) {
				String s = getMSG();
				if(s.equals("log-out")) {
					System.out.println(email + " has out!");
					run = false;
					exit();
				}
				else if(s.equals("order")) {
					int id_product = Integer.parseInt(getMSG());
					int user_id = Integer.parseInt(getMSG());
					String address = getMSG();
					String date = getMSG();
					String price = getMSG();
					try {
						sv.getOrder(id_product, user_id, address, date, price);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private void exit(){
		try {
			os.close();
			is.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private String getMSG() {
		String data = null;
		try {
			data = is.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void sendMSG(String title, String msg) {
		sendMSG(title);
		sendMSG(msg);
	}
	
	private void sendMSG(String msg) {
		try {
			os.writeUTF(msg);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

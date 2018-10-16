import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class Server {

	static InetAddress host;
	static ServerSocket listener;
	
	static int clientNumber = 0;
	
	public Hashtable<String, ClientConnect> listUser;
	
	private void run() {
		
		try {
			host = InetAddress.getByName("192.168.100.6");
			listener = new ServerSocket(9999);
			listUser = new Hashtable<String, ClientConnect>();
			
			while(true) {
				Socket client = listener.accept();
				new ClientConnect(this, client, ++clientNumber);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		new ServerFrame();
		new Server().run();
	}
	
	public String getAllUser() {
		String list = "";
		
		Enumeration e = listUser.keys();
		while(e.hasMoreElements()) {
			list += (String)e.nextElement() + "\n";
		}
		
		return list;
	}
	
	public void sendUpdateList(String nick) {
		String name = "";
		
		Enumeration e = listUser.keys();
		while(e.hasMoreElements()) {
			name = (String) e.nextElement();
			
			if(!name.equals(nick)) {
				listUser.get(name).sendMSG("online", getAllUser());
			}
		}
	}
	
	public void sendAll(String nick, String msg) {
		String name = "";
		
		Enumeration e = listUser.keys();
		while(e.hasMoreElements()) {
			name = (String) e.nextElement();
			
			if(!name.equals(nick)) {
				listUser.get(name).sendMSG("new_chat", msg);
			}
		}
	}
}

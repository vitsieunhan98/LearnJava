import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnect extends Thread {
	
	private int clientNumber;
	public Socket client;
	public Server sv;
	
	private DataInputStream is;
	private DataOutputStream os;
	
	private String nick;
	private boolean run;
	
	public ClientConnect(Server sv, Socket client, int clientNumber) {
		try {
			this.sv = sv;
			this.clientNumber = clientNumber;
			this.client = client;
			run = true;
			
			is = new DataInputStream(client.getInputStream());
            os = new DataOutputStream(client.getOutputStream());
			
			this.start();
			
			ServerFrame.txtrA.append("Client " + this.clientNumber + " has joined!\n");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void run() {
		String msg = "";
        while(run) {
        	nick = getMSG();
        	if(sv.listUser.containsKey(nick)) {
        		sendMSG("out");
        	}
        	else {
        		sv.sendAll(nick, nick + " has joined!\n");
            	sv.listUser.put(nick, this);
            	sv.sendUpdateList(nick);
                displayOnline();
                
                while(run) {
                	String s = getMSG();
                	if(s.equals("chatting")) {
                		msg = getMSG();
                		sv.sendAll(nick, nick + " : " + msg);
                	}
                	else if(s.equals("out")) {
                		run = false;
                		sv.listUser.remove(this.nick);
                		exit();
                	}
                }
        	}  
        }
	}
	
	private void exit(){
		try {
			sv.sendUpdateList(nick);
			sv.sendAll(nick, nick + " was out!\n");
			os.close();
			is.close();
			client.close();
			ServerFrame.txtrA.append("One user was out!\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void sendMSG(String msg) {
		try {
			os.writeUTF(msg);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMSG(String title, String msg) {
		sendMSG(title);
		sendMSG(msg);
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
	
	public void displayOnline() {
		String list = sv.getAllUser();
		sendMSG("online");
		sendMSG(list);
	}
	
	
}

import java.io.DataInputStream;
import java.io.IOException;

//Data to client
public class DataStream extends Thread {
	private boolean run;
	private DataInputStream is;
	private Client client;
	
	public DataStream(Client client,DataInputStream is) {
		run = true;
		this.client = client;
		this.is = is;
		
		this.start();
	}
	
	public void run() {
		String title, msg;
		
		while(run){
			try {
				title = is.readUTF();
				msg = is.readUTF();
				client.getMSG(title, msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void stopThread(){
		this.run = false;
	}

}

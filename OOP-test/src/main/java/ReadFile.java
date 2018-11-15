import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	
	public ReadFile() {
		// TODO Auto-generated constructor stub
	}
	
	public List<String> readFile(String path) throws IOException {
		List<String> result = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(path);
		InputStreamReader isr =  new InputStreamReader(fis);
		BufferedReader bfr = new BufferedReader(isr);
		
		String line = bfr.readLine();
		while(line!=null) {
			result.add(line);
			line = bfr.readLine();
		}
		
		fis.close();
		isr.close();
		bfr.close();
		
		return result;
	}
}

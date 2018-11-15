import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Source {

	private String link;
	private Date time_extracted;
	private List<String> list_link;
	private List<Date> list_time_extracted;
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public Date getTime_extracted() {
		return time_extracted;
	}

	public void setTime_extracted(Date time_extracted) {
		this.time_extracted = time_extracted;
	}

	public Source() {
		// TODO Auto-generated constructor stub
	}

	public Source(String link, Date time_extracted) {
		super();
		this.link = link;
		this.time_extracted = time_extracted;
		list_link = new ArrayList<String>();
		list_time_extracted = new ArrayList<Date>();
	}

	public String getRandomLink() {
		return "abc.xyz";
	}
	
	public Date getRandomTimeExtracted() {
		return new Date();
	}
}

import java.util.ArrayList;
import java.util.List;

public class Entity {
	
	protected String id;
	protected String description;
	protected Source source;
	
	private List<String> list_id;
	private List<String> list_description;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Entity() {
		// TODO Auto-generated constructor stub
	}
	
	public Entity(String id, String description, Source source) {
		super();
		this.id = id;
		this.description = description;
		this.source = source;
		list_id = new ArrayList<String>();
		list_description = new ArrayList<String>();
	}

	public String getRandomID() {
		return "1";
	}
	
	public String getRandomDescription() {
		return "This is a Organization";
	}
	
	
}

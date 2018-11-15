import java.io.IOException;

public class GenOrganization {
	
	public Organization generate() throws IOException {
		Entity ett = new Entity();
		Organization org = new Organization();
		GenSource gs = new GenSource();
		return new Organization(ett.getRandomID(), ett.getRandomDescription(), gs.getRandomSource(), org.getRandomHeadquarter(), org.getRandomLabel());
	}
}

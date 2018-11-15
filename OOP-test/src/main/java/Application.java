import java.io.IOException;

public class Application {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GenOrganization gen_org = new GenOrganization();
		Organization org = gen_org.generate();
		System.out.println(org.getId());
		System.out.println(org.getLabel());
		System.out.println(org.getDescription());
		System.out.println(org.getHeadquarter());
		System.out.println(org.getSource().getLink());
		System.out.println(org.getSource().getTime_extracted());
	}

}

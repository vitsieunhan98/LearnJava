package domain;

public class User {

	private int id;
	private String fullname;
	private String email;
	private String password;
	private String phone;
	private int id_role;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId_role() {
		return id_role;
	}

	public void setId_role(int id_role) {
		this.id_role = id_role;
	}

	public User(int id, String fullname, String email, String password, String phone, int id_role) {
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.id_role = id_role;
	}

}

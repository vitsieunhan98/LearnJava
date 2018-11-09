
public class Order {
	
	private int id;
	private int id_product;
	private int user_id;
	private String order_placed;
	private String date_order;
	private String price;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrder_placed() {
		return order_placed;
	}

	public void setOrder_placed(String order_placed) {
		this.order_placed = order_placed;
	}

	public String getDate_order() {
		return date_order;
	}

	public void setDate_order(String date_order) {
		this.date_order = date_order;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Order(int id, int id_product, int user_id, String order_placed, String date_order, String price) {
		this.id = id;
		this.id_product = id_product;
		this.user_id = user_id;
		this.order_placed = order_placed;
		this.date_order = date_order;
		this.price = price;
	}

}

package serial;

@SuppressWarnings("serial")
public class Book implements java.io.Serializable {
	public String name;
	public int id,price,quantity;
	private static int count =0;
	
	public Book(String name,int price,int quantity) {
		this.id = ++count;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public void changeQuantity(int change){
		this.quantity += change;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Integer getUniqueID() {
		
		return id;
	}
}

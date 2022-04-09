package ObjectedOrientedDesign.vendingMachine;

public class Product {

	public String name;
	public int price;
	public int quantity;

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
		this.quantity = 0;
	}

	public String toString() {
		return String.format("%s %d", this.name, this.price);
	}
}
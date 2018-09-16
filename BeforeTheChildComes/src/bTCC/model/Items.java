package bTCC.model;

public class Items {
	private String itemName;
	private double price;
	public Items() {}
	
	public Items(String itemName, double price) {
		this.setItemName(itemName);
		this.setPrice(price);
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		
		return itemName + " " + price;
	}
}

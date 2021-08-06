package application;

import javafx.beans.property.SimpleStringProperty;

public class Item {

	private SimpleStringProperty name;
	private SimpleStringProperty price;
	private SimpleStringProperty description;


	public Item(String name, String price, String description){
	      this.name = new SimpleStringProperty(name);
	      this.price = new SimpleStringProperty(price);
	      this.description = new SimpleStringProperty(description);
	}

	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	public String getPrice() {
		return price.get();
	}
	public void setPrice(String price) {
		this.price = new SimpleStringProperty(price);
	}
	public String getDescription() {
		return description.get();
	}
	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	}


}

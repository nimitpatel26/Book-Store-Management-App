package application;

import javafx.beans.property.SimpleStringProperty;

public class Person {

	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty streetAddress;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private SimpleStringProperty zipcode;
	private SimpleStringProperty gender;


	public Person(String firstName, String lastName, String streetAddress, String city, String state, String zipcode, String gender){
	      this.firstName = new SimpleStringProperty(firstName);
	      this.lastName = new SimpleStringProperty(lastName);
	      this.streetAddress = new SimpleStringProperty(streetAddress);
	      this.city = new SimpleStringProperty(city);
	      this.state = new SimpleStringProperty(state);
	      this.zipcode = new SimpleStringProperty(zipcode);
	      this.gender = new SimpleStringProperty(gender);
	}


	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}
	public String getStreetAddress() {
		return streetAddress.get();
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = new SimpleStringProperty(streetAddress);
	}
	public String getCity() {
		return city.get();
	}
	public void setCity(String city) {
		this.city = new SimpleStringProperty(city);
	}
	public String getState() {
		return state.get();
	}
	public void setState(String state) {
		this.state = new SimpleStringProperty(state);
	}
	public String getZipcode() {
		return zipcode.get();
	}
	public void setZipcode(String zipcode) {
		this.zipcode = new SimpleStringProperty(zipcode);
	}
	public String getGender() {
		return gender.get();
	}
	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}





}

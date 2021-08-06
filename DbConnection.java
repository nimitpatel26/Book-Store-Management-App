package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DbConnection {

	private Connection conn = null;
    private String url = "jdbc:mysql://sql5.freemysqlhosting.net:3306/sql5405681";
    private String username = "sql5405681";
    private String password = "BEdyyGJ3rc";

	public DbConnection(){

	}

	public void Connect(){

		try {
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(url, username, password);





		} catch (ClassNotFoundException e) {
		    // handle any errors
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block

		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: " + e.getSQLState());
		    System.out.println("VendorError: " + e.getErrorCode());
		}
	}


//	public void CreatTable(){
//		String sql = "CREATE TABLE Customers (FirstName varchar(255), LastName varchar(255), StreetAddress varchar(255), City varchar(255), State varchar(255), Zipcode varchar(255), Gender varchar(255))";
//		Execute(sql);
//		System.out.println("Table Created!");
//
//		sql = "CREATE TABLE Employees (FirstName varchar(255), LastName varchar(255), StreetAddress varchar(255), City varchar(255), State varchar(255), Zipcode varchar(255), Gender varchar(255))";
//		Execute(sql);
//		System.out.println("Table Created!");
//
//		sql = "CREATE TABLE Items (Name varchar(255), Price varchar(255), Description varchar(255))";
//		Execute(sql);
//		System.out.println("Table Created!");
//
//	}

	public void Add(Employee e){
		String sql = String.format("INSERT INTO Employees VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", e.getFirstName(), e.getLastName(), e.getStreetAddress(), e.getCity(), e.getState(), e.getZipcode(), e.getGender());
		Execute(sql);
	}

	public void Add(Customer e){
		String sql = String.format("INSERT INTO Customers VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", e.getFirstName(), e.getLastName(), e.getStreetAddress(), e.getCity(), e.getState(), e.getZipcode(), e.getGender());
		Execute(sql);
	}

	public void Add(Item i){
		String sql = String.format("INSERT INTO Items VALUES ('%s', '%s', '%s')", i.getName(), i.getPrice(), i.getDescription());
		Execute(sql);
	}


	public ObservableList<Person> GetEmployees(){
		String sql = "SELECT * FROM Employees";
		return GetPersonData(sql);
	}

	public ObservableList<Person> GetCustomers(){
		String sql = "SELECT * FROM Customers";
		return GetPersonData(sql);
	}

	public ObservableList<Item> GetItems(){
		String sql = "SELECT * FROM Items";
		return GetItemData(sql);
	}



	private void Execute(String sql){
		try {
			Statement s = conn.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ObservableList<Person> GetPersonData(String sql){
		ObservableList<Person> personData = FXCollections.observableArrayList();

		try {
			Statement s = conn.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()){
		        String firstName = rs.getString("FirstName");
		        String lastName = rs.getString("LastName");
		        String streetAddress = rs.getString("StreetAddress");
		        String city = rs.getString("City");
		        String state = rs.getString("State");
		        String zipcode = rs.getString("Zipcode");
		        String gender = rs.getString("Gender");

		        Person person = new Person(firstName, lastName, streetAddress, city, state, zipcode, gender);
		        personData.add(person);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personData;
	}

	private ObservableList<Item> GetItemData(String sql){
		ObservableList<Item> itemData = FXCollections.observableArrayList();

		try {
			Statement s = conn.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()){
		        String name = rs.getString("Name");
		        String price = rs.getString("Price");
		        String description = rs.getString("Description");

		        Item item = new Item(name, price, description);
		        itemData.add(item);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return itemData;
	}






	public void Disconnect(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}

package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;


import application.Controller;


public class Main extends Application {

	private static ObservableList<Person> employees;
	private static ObservableList<Person> customers;
	private static ObservableList<Item> items;

	@Override
	public void start(Stage primaryStage) {
		try {
	    	DbConnection db = new DbConnection();
	    	db.Connect();

			employees = db.GetEmployees();
			customers = db.GetCustomers();
			items = db.GetItems();

			db.Disconnect();


	        Parent root = FXMLLoader.load(getClass().getResource("MainUI.fxml"));
	        primaryStage.setTitle("Book Store Application");
	        primaryStage.setScene(new Scene(root, 800, 500));
	        primaryStage.show();

	        Controller c = new Controller();
	        c.setTableViewPerson(primaryStage.getScene());
	        c.setTableDataPerson(primaryStage.getScene(), customers);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}


	public static void setEmployees(ObservableList<Person> emp){
		employees.clear();
		employees.addAll(emp);
	}

	public static ObservableList<Person> getEmployees(){
		return employees;
	}

	public static void setCustomers(ObservableList<Person> cust){
		customers.clear();
		customers.addAll(cust);
	}

	public static ObservableList<Person> getCustomers(){
		return customers;
	}

	public static void setItems(ObservableList<Item> itm){
		items.clear();
		items.addAll(itm);
	}

	public static ObservableList<Item> getItems(){
		return items;
	}

}

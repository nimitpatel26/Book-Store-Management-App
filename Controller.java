package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controller {

	private final String CUSTOMER = "Customer";
	private final String EMPLOYEE = "Employee";
	private final String ITEM = "Item";

    @FXML
    private void setPageCustomer(ActionEvent event) {
    	Scene scene = getParent(event);
    	setTableViewPerson(scene);
    	cacheData(scene);
    	setLabelListing(scene, CUSTOMER);
    	setData(scene);

    }

    @FXML
    private void setPageEmployee(ActionEvent event) {
    	Scene scene = getParent(event);
    	setTableViewPerson(scene);
    	cacheData(scene);
    	setLabelListing(scene, EMPLOYEE);
    	setData(scene);
    }

    @FXML
    private void setPageItem(ActionEvent event) {
    	Scene scene = getParent(event);
    	setTableViewItem(scene);
    	cacheData(scene);
    	setLabelListing(scene, ITEM);
    	setData(scene);
    }

    @FXML
    private void addNew(ActionEvent event) {


    	Scene scene = getParentButton(event);
    	String label = getLabelListing(scene);


    	if (label.contains(CUSTOMER) || label.contains(EMPLOYEE)){

    		openPersonWindow(scene);

    	} else if (label.contains(ITEM)){

    		openItemWindow(scene);

    	}
    }

    @FXML
    private void addPerson(ActionEvent event){

    	Scene scene = getParentButton(event);
    	Scene parentScene = getParentScene(scene);

    	TextField tfFirstName = (TextField) scene.lookup("#tfFirstName");
    	TextField tfLastName = (TextField) scene.lookup("#tfLastName");
    	TextField tfStreet = (TextField) scene.lookup("#tfStreet");
    	TextField tfCity = (TextField) scene.lookup("#tfCity");
    	TextField tfZipcode = (TextField) scene.lookup("#tfZipcode");
    	ChoiceBox cBoxState = (ChoiceBox) scene.lookup("#cBoxState");
    	ChoiceBox cBoxGender = (ChoiceBox) scene.lookup("#cBoxGender");



    	String firstName = tfFirstName.getText();
    	String lastName = tfLastName.getText();
    	String street = tfStreet.getText();
    	String city = tfCity.getText();
    	String zipcode = tfZipcode.getText();
    	String state = cBoxState.getValue().toString();
    	String gender =  cBoxGender.getValue().toString();





    	DbConnection db = new DbConnection();
    	db.Connect();

    	String label = getLabelListing(parentScene);

    	if (label.contains(CUSTOMER)){

        	Customer cust = new Customer(firstName, lastName, street, city, state, zipcode, gender);
        	addToTable(parentScene, cust);
        	db.Add(cust);

    	}else if (label.contains(EMPLOYEE)){
        	Employee emp = new Employee(firstName, lastName, street, city, state, zipcode, gender);
        	addToTable(parentScene, emp);
        	db.Add(emp);
    	}


    	db.Disconnect();




    	scene.getWindow().hide();

    }

    @FXML
    private void addItem(ActionEvent event){
    	Scene scene = getParentButton(event);
    	Scene parentScene = getParentScene(scene);

    	TextField tfItemName = (TextField) scene.lookup("#tfItemName");
    	TextField tfPrice = (TextField) scene.lookup("#tfPrice");
    	TextArea taDescription = (TextArea) scene.lookup("#taDescription");

    	String name = tfItemName.getText();
    	String price = tfPrice.getText();
    	String description = taDescription.getText();


    	Item item = new Item(name, price, description);
    	addToTable(parentScene, item);

    	DbConnection db = new DbConnection();
    	db.Connect();

    	db.Add(item);

    	db.Disconnect();

    	scene.getWindow().hide();

    }



    @FXML
    private Scene getParent(ActionEvent event){
        event.consume();
        MenuItem menuItem = (MenuItem)event.getTarget();
        Stage owner = (Stage)menuItem.getParentPopup().getOwnerWindow();
        Scene scene = owner.getScene();
        return scene;
    }

    @FXML
    private Scene getParentButton(ActionEvent event){
        event.consume();
        Button button = (Button)event.getTarget();
        return button.getScene();
    }

    private Scene getParentScene(Scene scene){
    	Window thisWindow = scene.getWindow();
    	Window parentWindow = ((Stage) thisWindow).getOwner();
    	return parentWindow.getScene();
    }


    @FXML
    public void setTableViewPerson(Scene scene){

        TableView tvData = (TableView) scene.lookup("#tvData");

        TableColumn tcFirstName = new TableColumn("First Name");
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn tcLastName = new TableColumn("Last Name");
        tcLastName.setCellValueFactory(new PropertyValueFactory("lastName"));

        TableColumn tcStreetAddress = new TableColumn("Street Address");
        tcStreetAddress.setCellValueFactory(new PropertyValueFactory("streetAddress"));

        TableColumn tcCity = new TableColumn("City");
        tcCity.setCellValueFactory(new PropertyValueFactory("city"));

        TableColumn tcState = new TableColumn("State");
        tcState.setCellValueFactory(new PropertyValueFactory("state"));

        TableColumn tcZipcode = new TableColumn("Zipcode");
        tcZipcode.setCellValueFactory(new PropertyValueFactory("zipcode"));

        TableColumn tcGender = new TableColumn("Gender");
        tcGender.setCellValueFactory(new PropertyValueFactory("gender"));

        tvData.getColumns().clear();
        tvData.getColumns().addAll(tcFirstName, tcLastName, tcStreetAddress, tcCity, tcState, tcZipcode, tcGender);

    }

    @FXML
    public void setTableViewItem(Scene scene){

        TableView tvData = (TableView) scene.lookup("#tvData");

        TableColumn tcName = new TableColumn("Name");
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn tcPrice = new TableColumn("Price");
        tcPrice.setCellValueFactory(new PropertyValueFactory("price"));

        TableColumn tcDescription = new TableColumn("Description");
        tcDescription.setCellValueFactory(new PropertyValueFactory("description"));


        tvData.getColumns().clear();
        tvData.getColumns().addAll(tcName, tcPrice, tcDescription);

    }

    public void setLabelListing(Scene scene, String listing){
    	Label label = (Label) scene.lookup("#labelListing");
    	label.setText(listing.concat(" Listing"));
    }

    public String getLabelListing(Scene scene){
    	Label label = (Label) scene.lookup("#labelListing");
    	return label.getText();
    }



    private void openPersonWindow(Scene scene){
		try {
            Parent root = FXMLLoader.load(getClass().getResource("PersonInformationUI.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Person Information");

            stage.initOwner(scene.getWindow());


            stage.setScene(new Scene(root, 450, 450));
            stage.show();


            // set states
            // set gender

	        ChoiceBox cb = (ChoiceBox) root.lookup("#cBoxState");
	        String[] states = new String[]{"AK", "AL", "AR", "AS", "AZ", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MP", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UM", "UT", "VA", "VI", "VT", "WA", "WI", "WV", "WY"};
	        cb.getItems().addAll(states);

	        cb = (ChoiceBox) root.lookup("#cBoxGender");
	        String[] genders = new String[]{"Male", "Female"};
	        cb.getItems().addAll(genders);


		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    private void openItemWindow(Scene scene){
		try {
            Parent root = FXMLLoader.load(getClass().getResource("ItemInformationUI.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Item Information");
            stage.initOwner(scene.getWindow());
            stage.setScene(new Scene(root, 450, 450));
            stage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
    }


    private void addToTable(Scene scene, Person person){
    	TableView tvData = (TableView) scene.lookup("#tvData");
    	tvData.getItems().add(person);

    }

    private void addToTable(Scene scene, Item item){
    	TableView tvData = (TableView) scene.lookup("#tvData");
    	tvData.getItems().add(item);
    }

    private ObservableList<Person> getTableDataPerson(Scene scene){
    	TableView tvData = (TableView) scene.lookup("#tvData");
    	return tvData.getItems();
    }

    private ObservableList<Item> getTableDataItem(Scene scene){
    	TableView tvData = (TableView) scene.lookup("#tvData");
    	return tvData.getItems();
    }

    public void setTableDataPerson(Scene scene, ObservableList<Person> data){
    	TableView tvData = (TableView) scene.lookup("#tvData");
    	tvData.getItems().clear();
    	tvData.getItems().addAll(data);
    }

    private void setTableDataItem(Scene scene, ObservableList<Item> data){
    	TableView tvData = (TableView) scene.lookup("#tvData");
    	tvData.getItems().clear();
    	tvData.getItems().addAll(data);
    }




    private void cacheData(Scene scene){
    	String label = getLabelListing(scene);


    	if (label.contains(CUSTOMER)){

    		Main.setCustomers(getTableDataPerson(scene));

    	} else if (label.contains(EMPLOYEE)){

    		Main.setEmployees(getTableDataPerson(scene));


    	} else if (label.contains(ITEM)){

    		Main.setItems(getTableDataItem(scene));

    	}


    }

    private void setData(Scene scene){
    	String label = getLabelListing(scene);


    	if (label.contains(CUSTOMER)){

    		setTableDataPerson(scene, Main.getCustomers());

    	} else if (label.contains(EMPLOYEE)){

    		setTableDataPerson(scene, Main.getEmployees());


    	} else if (label.contains(ITEM)){

    		setTableDataItem(scene, Main.getItems());

    	}
    }









}

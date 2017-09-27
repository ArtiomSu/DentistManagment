package view;

import java.util.Locale;

import controller.PatientsViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import mainApp.Patient;
import utils.Components;

public class PatientsView {

	private Components c;
	private Patient selectedPatient = null;
	
	public PatientsView(Components cont){
		c = cont;
		start();
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
	}
	
	//patient is in the constructor so that when you return from invoice view the same patient will be selected
	public PatientsView(Components cont, Patient s){
		c = cont;
		selectedPatient = s;
		start();
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
	}
	
	private void start(){
		
		PatientsViewController cont = new PatientsViewController(c);
		
		BorderPane mainpane = new BorderPane();
		/*
		 * 
		 * 
		 * left part just a back button
		 *  
		 *
		 */
		VBox left = new VBox();
		//left.setPadding(new Insets(50,50,50,50));
		left.setAlignment(Pos.CENTER);
		left.setMinWidth(50);
		left.setMaxWidth(50);
		left.setOnMouseClicked(e->{cont.back();});
		
		Label back = new Label("<<<\nBack\n<<<");
		back.setOnMouseClicked(e->{cont.back();});
		
		left.getChildren().add(back);
		mainpane.setLeft(left);
		

		/*
		 * 
		 * 
		 * 
		 * right part details of patient
		 * 
		 * 
		 *  
		 */
		
		
		
		
		
		
		GridPane gridchange = new GridPane();
		gridchange.setMinWidth(150);
		
		
		Label firstNameLable = new Label("First name:");
		gridchange.add(firstNameLable, 0, 0);
		
		
		TextField FirstNameInput = new TextField();
		
		gridchange.add(FirstNameInput, 1, 0);
		FirstNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, FirstNameInput);
		});
		
				
		
		Label lastNameLabel = new Label("Last name:");
		gridchange.add(lastNameLabel, 0, 1);
		
		TextField lastNameInput = new TextField();
		gridchange.add(lastNameInput, 1, 1);
		lastNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, lastNameInput);

		});
		
		Label emailLabel = new Label("Email:");
		gridchange.add(emailLabel, 0, 2);
		
		TextField emailInput = new TextField();
		gridchange.add(emailInput, 1, 2);
		emailInput.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfemail(newValue, emailInput);

		});
		
		Label telLabel = new Label("Mobile number:");
		gridchange.add(telLabel, 0, 3);
		
		Label notetel = new Label("");
		gridchange.add(notetel, 2, 3);

		
		TextField telInput = new TextField();
		gridchange.add(telInput, 1, 3);
		telInput.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfnumber(newValue, telInput, notetel);
		});
		
		
		
		Label addressLabel = new Label("Address:");
		gridchange.add(addressLabel, 0, 4);
		
		TextField addressInput1 = new TextField();
		gridchange.add(addressInput1, 1, 4);
		addressInput1.setPromptText("App / House No");
		addressInput1.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, addressInput1);

		});
		
		TextField addressInput2 = new TextField();
		gridchange.add(addressInput2, 1, 5);
		addressInput2.setPromptText("Street");
		addressInput2.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, addressInput2);

		});
		
		TextField addressInput3 = new TextField();
		gridchange.add(addressInput3, 1, 6);
		addressInput3.setPromptText("City / Town");
		addressInput3.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, addressInput3);

		});
		
		TextField addressInput4 = new TextField();
		gridchange.add(addressInput4, 1, 7);
		addressInput4.setPromptText("Country");
		addressInput4.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, addressInput4);
			


		});
		
		
		
		
		/*
		 * 
		 * 
		 * 
		 * middle part table 
		 * 
		 * 
		 * 
		 */
		AnchorPane completeright = new AnchorPane();

		FlowPane rightbuttons = new FlowPane();
		TableView<Patient> patienttable = new TableView<Patient>();
		
		TableColumn<Patient, String> fnameColumn = new TableColumn<>("First Name");
		fnameColumn.setMinWidth(70);
		fnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<Patient, String> lnameColumn = new TableColumn<>("Last Name");
		lnameColumn.setMinWidth(70);
		lnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		TableColumn<Patient, Double> idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(40);
		idColumn.setMaxWidth(40);	
		idColumn.setCellValueFactory(new PropertyValueFactory<>("patientNo"));
		
		TableColumn<Patient, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setMinWidth(200);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		TableColumn<Patient, String> telColumn = new TableColumn<>("Telephone");
		telColumn.setMinWidth(150);
		telColumn.setCellValueFactory(new PropertyValueFactory<>("telepnone"));
		
		TableColumn<Patient, Double> outStandingColumn = new TableColumn<>("Outstanding Balance");
		outStandingColumn.setMinWidth(180);
		outStandingColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
		
		patienttable.getColumns().addAll(idColumn, fnameColumn, lnameColumn, emailColumn, telColumn, outStandingColumn);
		
		
		
		
		
		
		patienttable.setOnMouseClicked(e-> {
			try{
			patienttable.setItems(cont.tableClicked(patienttable, FirstNameInput, lastNameInput, emailInput, telInput, addressInput1, addressInput2, addressInput3, addressInput4, completeright, rightbuttons, gridchange));
			}
			catch(Exception exception){}
			//if you click anywhere on the table without selecting stuff first the console will be poring with exceptions
			//because there are so many I just used Exception
		});
		
		
		/*
		 * 
		 * 
		 * right part buttons needs to be bellow middle part for controller to work
		 * 
		 * 
		 * */
		FlowPane doneButtons = new FlowPane();
		ImageView donebutton[] = new ImageView[1];
		donebutton[0] = new ImageView(
				new Image("/images/patientDoneButton.png")
				);
		donebutton[0].setOnMouseClicked(e->{
			cont.checkIfAllValid(FirstNameInput, lastNameInput, emailInput, telInput, addressInput1, addressInput2, addressInput3, addressInput4, patienttable, completeright, rightbuttons, gridchange);
		});
		doneButtons.getChildren().add(donebutton[0]);
		
		
		rightbuttons.setPadding(new Insets(10,10,10, 0));
		rightbuttons.setHgap(5);
		
		ImageView buttons[] = new ImageView[4];
		buttons[0] = new ImageView(
			new Image("/images/patientEditButton.png")
		);
		buttons[0].setOnMouseClicked(e->{
			cont.editClicked(true, patienttable, FirstNameInput, lastNameInput, emailInput, telInput, addressInput1, addressInput2, addressInput3, addressInput4, completeright, rightbuttons, gridchange, doneButtons);
		});
		
		buttons[1] = new ImageView(
			new Image("/images/patientInvoiceButton.png")
		);
		buttons[1].setOnMouseClicked(e->{
				cont.invoiceView(patienttable);
		});
			
		buttons[2] = new ImageView(
			new Image("/images/patientDeleteButton.png")
		);
		buttons[2].setOnMouseClicked(e->{
			cont.delete(patienttable);
		});
		
		buttons[3] = new ImageView(
				new Image("/images/patientAddButton.png")
		);
		buttons[3].setOnMouseClicked(e->{
			cont.editClicked(false, patienttable, FirstNameInput, lastNameInput, emailInput, telInput, addressInput1, addressInput2, addressInput3, addressInput4, completeright, rightbuttons, gridchange, doneButtons);

		});
		
		
		rightbuttons.getChildren().addAll(buttons[3] ,buttons[0], buttons[1], buttons[2]);
	
		
		//completeright.setMinWidth(150);
		
		completeright.getChildren().addAll(rightbuttons, gridchange);
		
		completeright.setBottomAnchor(rightbuttons, 10.0);
		completeright.setLeftAnchor(rightbuttons, 10.0);
		completeright.setBottomAnchor(doneButtons, 10.0);
		completeright.setLeftAnchor(doneButtons, 150.0);
		completeright.setTopAnchor(gridchange, 20.0);
		completeright.setLeftAnchor(gridchange, 5.0);
		
		//put these down herer otherwise table doesnt fill up
		patienttable.setItems(cont.setItems(patienttable));
		if(selectedPatient == null){
		patienttable.getSelectionModel().selectFirst(); //avoids throwing exceptions
		}
		else{
			patienttable.getSelectionModel().select(selectedPatient);
		}
		try{
			patienttable.setItems(cont.tableClicked(patienttable, FirstNameInput, lastNameInput, emailInput, telInput, addressInput1, addressInput2, addressInput3, addressInput4, completeright, rightbuttons, gridchange));
			}
			catch(Exception exception){}
		
		
		mainpane.setCenter(patienttable);
		mainpane.setRight(completeright);
		Scene scene = new Scene(mainpane);
		
		c.window.setScene(scene);
		
		
		
	}
	
}

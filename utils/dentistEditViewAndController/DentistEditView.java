package utils.dentistEditViewAndController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Components;

public class DentistEditView {

	Components c;
	DentistEditController cont;
	public DentistEditView(Components com){
		c = com;
		cont = new DentistEditController(c);
		start();
	}
	
	private void start(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setOnCloseRequest(e -> {
			e.consume();
		});
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label firstNameLable = new Label("First name:");
		GridPane.setConstraints(firstNameLable, 0, 0);
		
		TextField FirstNameInput = new TextField();
		GridPane.setConstraints(FirstNameInput, 1, 0);
		FirstNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, FirstNameInput);
		});
		
		
		
		Label lastNameLabel = new Label("Last name:");
		GridPane.setConstraints(lastNameLabel, 0, 1);
		
		TextField lastNameInput = new TextField();
		GridPane.setConstraints(lastNameInput, 1, 1);
		lastNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, lastNameInput);

		});
		
		
		Label emailLabel = new Label("Email:");
		GridPane.setConstraints(emailLabel, 0, 2);
		
		TextField emailInput = new TextField();
		GridPane.setConstraints(emailInput, 1, 2);
		emailInput.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfemail(newValue, emailInput);

		});
		
		
		Label telLabel = new Label("Mobile number:");
		GridPane.setConstraints(telLabel, 0, 3);
		
		
		Label notetel = new Label("");
		GridPane.setConstraints(notetel, 2, 3);

		
		TextField telInput = new TextField();
		GridPane.setConstraints(telInput, 1, 3);
		telInput.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfnumber(newValue, telInput, notetel);
		});
		
		
		
		
		Label addressLabel = new Label("Address:");
		GridPane.setConstraints(addressLabel, 0, 4);
		
		TextField addressInput1 = new TextField();
		GridPane.setConstraints(addressInput1, 1, 4);
		addressInput1.setPromptText("App / House No");
		addressInput1.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, addressInput1);

		});
		
		
		TextField addressInput2 = new TextField();
		GridPane.setConstraints(addressInput2, 1, 5);
		addressInput2.setPromptText("Street");
		addressInput2.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, addressInput2);

		});
		
		
		TextField addressInput3 = new TextField();
		GridPane.setConstraints(addressInput3, 1, 6);
		addressInput3.setPromptText("City / Town");
		addressInput3.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, addressInput3);

		});
		
		
		TextField addressInput4 = new TextField();
		GridPane.setConstraints(addressInput4, 1, 7);
		addressInput4.setPromptText("Country");
		addressInput4.textProperty().addListener((observable, oldValue, newValue) -> {
			cont.checkIfBlank(newValue, addressInput4);

		});
		
		
		Label note0 = new Label("Username must be unique. You will use this to logon");
		GridPane.setConstraints(note0, 0, 8, 2, 1);
		
		Label usenameLabel = new Label("Username:");
		GridPane.setConstraints(usenameLabel, 0, 9);
		
		Label usernameError = new Label("");
		GridPane.setConstraints(usernameError, 2, 9);
		
		TextField usernameInput = new TextField();
		GridPane.setConstraints(usernameInput, 1, 9);
		usernameInput.textProperty().addListener((observable, oldValue, newValue) -> {
		    cont.checkIfUsername(newValue,usernameInput,usernameError);
		});
		
		
		
		cont.fillAtStart(FirstNameInput, lastNameInput, emailInput, telInput,  addressInput1, addressInput2, addressInput3, addressInput4, usernameInput);
		usernameInput.setEditable(false);

		
		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 10);
		
		PasswordField passInput = new PasswordField();
		passInput.setPromptText("Min 8 characters");
		GridPane.setConstraints(passInput, 1, 10);
		passInput.textProperty().addListener((observable, oldValue, newValue) -> {
		    cont.checkIfPassword(newValue, passInput);
		});
		
		Button submit = new Button("Submit");
		submit.setOnAction(e->cont.checkIfAllValid(window, FirstNameInput, lastNameInput, emailInput, telInput, addressInput1, addressInput2, addressInput3, addressInput4, usernameInput, passInput));
	
	
		
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e->{
			cont.cancel(window);
		});
		GridPane.setConstraints(cancel, 2, 11);
		
		HBox buttons = new HBox(5);
		GridPane.setConstraints(buttons, 1, 11, 2, 1);
		buttons.getChildren().addAll(submit, cancel);
		
		grid.getChildren().addAll(firstNameLable, FirstNameInput, lastNameLabel, lastNameInput, emailLabel, emailInput, telLabel, telInput, addressLabel, addressInput1, addressInput2, addressInput3, addressInput4, note0, usenameLabel, usernameInput, passLabel, passInput, notetel, usernameError, buttons);
		
		grid.setAlignment(Pos.CENTER);

		Scene scene = new Scene(grid);
		
		
		window.setScene(scene);
		window.showAndWait();

		
		
		
	}
	
}

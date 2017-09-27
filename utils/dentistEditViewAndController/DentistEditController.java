package utils.dentistEditViewAndController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainApp.Dentist;
import utils.AnoyingPopUp;
import utils.Components;
import utils.Hashes;
import view.MainView;

public class DentistEditController {

	Components c;
	
	public DentistEditController(Components com){
		c = com;
	}
	
	boolean checkvalidgroup1 = false; 
	boolean validated[] = new boolean[4]; //must match a specific number to unlock register button
	

	public void checkIfBlank(String s, TextField t){
		if(s.length() == 0){
			t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-text-fill: red; -fx-border-radius: 5 5 5 5;");
			t.setPromptText("Cant be left blank");
		}
		else{
			t.setStyle(null);
			

		}
	}
	public void checkIfemail(String s, TextField t){
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(s);
		
		if(matcher.find() && s.length() > 4){
			t.setStyle(null);
			validated[0] = true;
		}
		else{
			
			t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-text-fill: red; -fx-border-radius: 5 5 5 5;");
			t.setPromptText("Cant be left blank");
			validated[0] = false;
		}
	}
	public void checkIfnumber(String s, TextField t, Label note){
		String checked = t.getText();
		if (checked.matches("[0-9]+") && checked.length() > 12) {
			t.setStyle(null);
			note.setText(null);
			validated[1] = true;
		}
		else{
			t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-text-fill: red; -fx-border-radius: 5 5 5 5;");
			t.setPromptText("must be a number use 00 not +");
			note.setText("format: 00(countrycode)123456789");
			note.setStyle("-fx-text-fill: red;");
			validated[1] = false;
		}
		
	}
	public void checkIfUsername(String s, TextField t, Label error){
		
		//can't be edited therefore its fine
			validated[2] = true;

		}
	
	public void checkIfPassword(String s, PasswordField t){
		if(s.length() <= 7){
			t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-text-fill: red; -fx-border-radius: 5 5 5 5;");
			t.setPromptText("Cant be left blank");
			validated[3] = false;
		}
		else{
			t.setStyle(null);
			validated[3] = true;

		}
	}
	
	public void checkIfAllValid(Stage window, TextField fname, TextField lname, TextField email, TextField tel, TextField a1, TextField a2, TextField a3, TextField a4, TextField username, PasswordField pass){
		String fName = fname.getText();
		String lName = lname.getText();
		String emailS = email.getText();
		String telS = tel.getText();
		String ad1= a1.getText();
		String ad2= a2.getText();
		String ad3= a3.getText();
		String ad4= a4.getText();
		String user= username.getText();
		String paSS= pass.getText();


		//group 1 each have distinct listners while the rest such as name address share the same listner therefore they have to be checked again seperately.
		
		for(int i = 0; i < 4; i++){
		if(validated[i] == false){
			checkvalidgroup1 = false;
			break;
		}
		else{
			checkvalidgroup1 = true;
		}
		}
		
		//checking the others
		
		boolean checkvalidgroup2 = false;
		
		if(fName.length() == 0 || lName.length() == 0 || ad1.length() == 0 || ad2.length() == 0 || ad3.length() == 0 || ad4.length() == 0){
			checkvalidgroup2 = false;
		}
		else{
			checkvalidgroup2 = true;
		}
		
		if(checkvalidgroup2 == true && checkvalidgroup1 == true){
			//create dentist and login
			
			
			
			String addres[] = new String[4];
			addres[0] = ad1;
			addres[1] = ad2;
			addres[2] = ad3;
			addres[3] = ad4;

			
			
			Hashes hash = new Hashes();
			paSS = hash.hashWord(paSS);
			String salt = hash.getSalt();
			
			
			
			
			String query = "UPDATE Dentist set firstName = ? , lastName = ?, email = ?, telephone = ?, address1 = ?, address2 = ?, address3 = ?, address4 = ?, password = ?, salt = ? WHERE dentistID = ?";

			try {
				PreparedStatement preparedStmt = c.connection.prepareStatement(query);
				
			     preparedStmt.setString(1, fName);
			     preparedStmt.setString(2, lName);
			     preparedStmt.setString(3, emailS);
			     preparedStmt.setString(4, telS);
			     preparedStmt.setString(5, ad1);
			     preparedStmt.setString(6, ad2);
			     preparedStmt.setString(7, ad3);
			     preparedStmt.setString(8, ad4);
			     preparedStmt.setString(9, paSS);
			     preparedStmt.setString(10, salt);
			     preparedStmt.setInt(11, c.currentDentistId);

			     
			     preparedStmt.executeUpdate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			window.close();

		}
		else{
			AnoyingPopUp pop = new AnoyingPopUp("Some information is not valid or is empty");
		}
		
		
	}
	
	public void cancel(Stage window){
		window.close();
	}
	
	public void fillAtStart(TextField FirstNameInput,TextField lastNameInput,TextField emailInput,TextField telInput,TextField  addressInput1,TextField addressInput2,TextField addressInput3,TextField addressInput4,TextField usernameInput){
		try {
			Statement statement = c.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(            
				      "SELECT * FROM Dentist where dentistID = '" + c.currentDentistId + "';");
			  
		        
		        	while(resultSet.next()){
		        	FirstNameInput.setText(resultSet.getString("firstName"));
		        	lastNameInput.setText(resultSet.getString("lastName"));
		        	emailInput.setText(resultSet.getString("email"));
		        	telInput.setText(resultSet.getString("telephone"));
		        	addressInput1.setText(resultSet.getString("address1"));
		        	addressInput2.setText(resultSet.getString("address2"));
		        	addressInput3.setText(resultSet.getString("address3"));
		        	addressInput4.setText(resultSet.getString("address4"));
		        	usernameInput.setText(resultSet.getString("username"));

		        	

		        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

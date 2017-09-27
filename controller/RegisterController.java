package controller;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mainApp.Dentist;
import utils.AnoyingPopUp;
import utils.Components;
import utils.Hashes;
import utils.dataBaseFiles.DataBase;
import view.MainView;

public class RegisterController {

	private Components c;
	private boolean checkvalidgroup1 = false; 
	private boolean validated[] = new boolean[4]; //must match a specific number to unlock register button
	private DataBase db;
	
	public RegisterController(Components co){
		c = co;
		db = new DataBase(c.connection);
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
	}
	
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
		Hashes hash = new Hashes();
		boolean matched = false;
		matched = db.checkIfUserNameExists(s);
		
		
		if(matched || s.length() == 0){
			t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-text-fill: red; -fx-border-radius: 5 5 5 5;");
			t.setPromptText("Cant be left blank");
			error.setText("User " + s + " already exists");
			error.setStyle("-fx-text-fill: red;");
			validated[2] = false;
		}
		else{
			t.setStyle(null);
			error.setText(null);
			validated[2] = true;

		}
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
	
	public void checkIfAllValid(TextField fname, TextField lname, TextField email, TextField tel, TextField a1, TextField a2, TextField a3, TextField a4, TextField username, PasswordField pass){
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
			
			
			
			Hashes hash = new Hashes();
			paSS = hash.hashWord(paSS);
			String salt = hash.getSalt();
			
			
			
			int id = db.insertDentist(salt, user, paSS, fName, lName, emailS, ad1, ad2, ad3, ad4, telS);
			
			if(id == -1){
				AnoyingPopUp pops = new AnoyingPopUp("There is a problem with inserting data into the database ");
			}
			else{
				c.currentDentistId = id;
				MainView m = new MainView(c);		
				}
			

		}
		else{
			AnoyingPopUp pop = new AnoyingPopUp("Some information is not valid or is empty");
		}
		
		
	}
	
}

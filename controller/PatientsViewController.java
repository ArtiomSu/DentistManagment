package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import mainApp.Patient;
import utils.AnoyingPopUp;
import utils.Components;
import view.InvoiceView;
import view.MainView;

public class PatientsViewController {

	private Components c;
	
	public PatientsViewController(Components cont){
		c = cont;
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
	}
	
	public ObservableList<Patient> setItems(TableView<Patient> p){
		ObservableList<Patient> items = FXCollections.observableArrayList();
		System.out.println("in set items");
		
		
		try {
			Statement statement = c.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(            
				      "SELECT * FROM Patient where dentistID = '" + c.currentDentistId + "';");
			  
	        
			while(resultSet.next()){
			
				int patientID = resultSet.getInt("patientID");
				int dentistID = resultSet.getInt("dentistID");

				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String email = resultSet.getString("email");
				String address1 = resultSet.getString("address1");
				String address2 = resultSet.getString("address2");
				String address3 = resultSet.getString("address3");
				String address4 = resultSet.getString("address4");
				String telephone = resultSet.getString("telephone");
				String balance = resultSet.getString("balance");
				
				String[] add = {address1,address2,address3,address4};
				System.out.println("balance="+balance);
				//if(balance == "0.0"){
					System.out.println("goes into balance check");
					Statement statement1 = c.connection.createStatement();
					ResultSet resultSet1 = statement1.executeQuery(            
						      "SELECT invoiceLeftToPay FROM Invoice where patientID = '" + patientID + "';");
					Double total = 0.0;
					String bob;
					while(resultSet1.next()){
						
						bob = resultSet1.getString("invoiceLeftToPay");
						total = total + Double.parseDouble(bob);
					}
					
					
					
					balance = Double.toString(total);
					
					String query = "UPDATE Patient set balance = ? WHERE PatientID = ?";
					
						PreparedStatement preparedStmt = c.connection.prepareStatement(query);
						
					     preparedStmt.setString(1, balance);
					     preparedStmt.setInt(2, patientID);
					     
					     
					     preparedStmt.executeUpdate();
					  
				//}
				
				
				Patient tmp = new Patient(patientID, dentistID, firstName, lastName, email, telephone, balance, add);
				items.add(tmp);
				
			}
		    		

		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		System.out.println(c.currentDentist.getPatientList().size());
//		System.out.println(c.currentDentist.getFirstName());
//		for(int i = 0; i< c.currentDentist.getPatientList().size(); i++){
//			c.currentDentist.getPatientList().get(i).calcBalance();
//items.add(c.currentDentist.getPatientList().get(i));
//			System.out.println(c.currentDentist.getPatientList().get(i).toString());
//		}
		
		
		return items;
		
	}
	
	public void back(){
		MainView m = new MainView(c);
	}
	
	private boolean iseditclicked = false;
	
	//this will handle both adding a patient and editing a patient 
	//if mode is true then edit else add
	public void editClicked(boolean mode, TableView<Patient> Patienttable, TextField fname, TextField lname, TextField email, TextField tel, TextField a1, TextField a2, TextField a3, TextField a4, AnchorPane anchor, FlowPane rightbuttons, GridPane grid, FlowPane donebutton){
		ObservableList<Patient> items, selected;
		items = Patienttable.getItems();
		
		selected = Patienttable.getSelectionModel().getSelectedItems();
		
		iseditclicked = true;
		
		if(iseditclicked){
			fname.setEditable(true);
			lname.setEditable(true);
			email.setEditable(true);
			tel.setEditable(true);
			a1.setEditable(true);
			a2.setEditable(true);
			a3.setEditable(true);
			a4.setEditable(true);
			
			if(mode == false){
				iseditclicked = false;
				fname.clear();
				lname.clear();
				email.clear();
				tel.clear();
				a1.clear();
				a2.clear();
				a3.clear();
				a4.clear();
			}
			if(selected.get(0) != null && mode == true){
			anchor.getChildren().remove(0, anchor.getChildren().size());
			
			anchor.getChildren().addAll(grid, donebutton);
			}
			else if(mode == false){
				anchor.getChildren().remove(0, anchor.getChildren().size());
				
				anchor.getChildren().addAll(grid, donebutton);
			}
			
			
		}
		
		
		
		
		
	}
	
	public void delete(TableView<Patient> Patienttable){
		ObservableList<Patient> items, selected;
		items = Patienttable.getItems();
		
		selected = Patienttable.getSelectionModel().getSelectedItems();
		if(selected.get(0) != null){
			
			if(selected.get(0).getBalance() == 0){
				int id = selected.get(0).getPatientNo();
				
				try {
					
					String query = "Delete FROM Patient where patientID = ?;";
					  
					PreparedStatement preparedStmt = c.connection.prepareStatement(query);
				      preparedStmt.setInt(1, id);
				      
				      preparedStmt.execute();

				        
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				selected.forEach(items::remove); 
			}
			else{
				AnoyingPopUp deny = new AnoyingPopUp("You are trying to delete a patient who owes you\n" + selected.get(0).getBalance() + " Euro\nI will not let you do this!!!!!");
			}
//				for(int i = 0; i< c.currentDentist.getPatientList().size(); i++){
//				if(c.currentDentist.getPatientList().get(i).getPatientNo() == selected.get(0).getPatientNo()){
//					c.currentDentist.getPatientList().remove(i);
//					
//					selected.forEach(items::remove); //this needs to be here otherwise the stuff will not be removed from the actual table
//					break;
//				}
//				}
//			}
//			else{
//				AnoyingPopUp deny = new AnoyingPopUp("You are trying to delete a patient who owes you\n" + selected.get(0).getBalance() + " Euro\nI will not let you do this!!!!!");
//			
			
		}
	}
	
	public ObservableList<Patient> tableClicked(TableView<Patient> Patienttable, TextField fname, TextField lname, TextField email, TextField tel, TextField a1, TextField a2, TextField a3, TextField a4, AnchorPane completeright, FlowPane rightbutton, GridPane grid){
		ObservableList<Patient> items, selected;
		items = Patienttable.getItems();
		selected = Patienttable.getSelectionModel().getSelectedItems();
		if(selected != null){
		iseditclicked = false;
			
		if(iseditclicked == false){
			
			completeright.getChildren().remove(0, completeright.getChildren().size());
			completeright.getChildren().addAll(rightbutton, grid);
			
			fname.setEditable(false);
			fname.setText(selected.get(0).getFirstName());
			lname.setEditable(false);
			lname.setText(selected.get(0).getLastName());
			email.setEditable(false);
			email.setText(selected.get(0).getEmail());
			tel.setEditable(false);
			tel.setText(selected.get(0).getTelepnone());
			a1.setEditable(false);
			a1.setText(selected.get(0).getAddress()[0]);
			a2.setEditable(false);
			a2.setText(selected.get(0).getAddress()[1]);
			a3.setEditable(false);
			a3.setText(selected.get(0).getAddress()[2]);
			a4.setEditable(false);
			a4.setText(selected.get(0).getAddress()[3]);
			
			
	
		}
		
		System.out.println(selected.get(0).getFirstName());
		
		
		}
		
		
		return items;
	}
	
	boolean checkvalidgroup1 = false; 
	boolean validated[] = new boolean[2];
	
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
	public void checkIfAllValid(TextField fname, TextField lname, TextField email, TextField tel, TextField a1, TextField a2, TextField a3, TextField a4, TableView<Patient> Patienttable, AnchorPane completeright, FlowPane rightbutton, GridPane grid){
		String fName = fname.getText();
		String lName = lname.getText();
		String emailS = email.getText();
		String telS = tel.getText();
		String ad1= a1.getText();
		String ad2= a2.getText();
		String ad3= a3.getText();
		String ad4= a4.getText();
		String address[] = new String[]{ad1,ad2,ad3,ad4}; 


		//group 1 each have distinct listners while the rest such as name address share the same listner therefore they have to be checked again seperately.
		
		for(int i = 0; i < 2; i++){
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
			ObservableList<Patient> items, selected;
			items = Patienttable.getItems();
			selected = Patienttable.getSelectionModel().getSelectedItems();
			if(iseditclicked == true){
			//update patient
			
			
			selected.get(0).setFirstName(fName);
			selected.get(0).setLastName(lName);
			selected.get(0).setEmail(emailS);
			selected.get(0).setTelepnone(telS);
			
			
			selected.get(0).setAddress(address);
			
			String query = "UPDATE Patient set firstName = ?, lastName = ?, email = ?, telephone = ?, address1 = ?, address2 = ?, address3 = ?, address4 = ? WHERE patientID = ?";
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
			     preparedStmt.setInt(9, selected.get(0).getPatientNo());
			     
			     preparedStmt.executeUpdate();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			}
			else{ //add patient
				
				String query =  "insert into Patient (firstName, lastName, email,  address1, address2, address3, address4, telephone, balance, dentistID)" + " values (?,?,?,?,?,?,?,?,?,?);";
				int lastid = -1;
			      try {
					PreparedStatement preparedStmt = c.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					
					
					preparedStmt.setString(1, fName);
				     preparedStmt.setString(2, lName);
				     preparedStmt.setString(3, emailS);
				     preparedStmt.setString(4, ad1);
				     preparedStmt.setString(5, ad2);
				     preparedStmt.setString(6, ad3);
				     preparedStmt.setString(7, ad4);
				     preparedStmt.setString(8, telS);
				     preparedStmt.setString(9, "0.0");

				     preparedStmt.setInt(10, c.currentDentistId);
				     
				      preparedStmt.execute();
				      
				      ResultSet rs = preparedStmt.getGeneratedKeys();
		                if(rs.next())
		                {
		                    lastid = rs.getInt(1);
		                }
				     
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      
				Patient tmp = new Patient();
				tmp.setAddress(address);
				tmp.setEmail(emailS);
				tmp.setFirstName(fName);
				tmp.setLastName(lName);
				tmp.setTelepnone(telS);
				tmp.setDentistID(c.currentDentistId);
				tmp.setPatientNo(lastid);
	//			c.currentDentist.AddPatient(tmp);
				items.add(tmp);
				
			}
			completeright.getChildren().remove(0, completeright.getChildren().size());
			completeright.getChildren().addAll(rightbutton, grid);
			if(selected.get(0)!=null){ //empty patientlist throws exceptions here so need if statement
			
			fname.setText(selected.get(0).getFirstName());
			lname.setText(selected.get(0).getLastName());
			email.setText(selected.get(0).getEmail());
			tel.setText(selected.get(0).getTelepnone());
			a1.setText(selected.get(0).getAddress()[0]);
			a2.setText(selected.get(0).getAddress()[1]);
			a3.setText(selected.get(0).getAddress()[2]);
			a4.setText(selected.get(0).getAddress()[3]);
			}
			fname.setEditable(false);
			lname.setEditable(false);
			email.setEditable(false);
			tel.setEditable(false);
			a1.setEditable(false);
			a2.setEditable(false);
			a3.setEditable(false);
			a4.setEditable(false);

			
		}
		else{
			AnoyingPopUp pop = new AnoyingPopUp("Some information is not valid or is empty");
		}
		
		
	
}
	
	public void invoiceView(TableView<Patient> t){
		ObservableList<Patient> selected;
		selected = t.getSelectionModel().getSelectedItems();
		if(selected.get(0)!=null){
			InvoiceView v = new InvoiceView(c, selected.get(0));

		}
		
	}
	
}

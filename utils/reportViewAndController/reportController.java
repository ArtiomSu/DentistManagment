package utils.reportViewAndController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import utils.AnoyingPopUp;
import utils.Components;
import utils.dataBaseFiles.DataBase;

public class reportController {

	Components c;

	public reportController(Components com){
		c = com;

	}
	
	
	public void Queries(Text t, int query){
		DataBase bd = new DataBase();
		boolean tmp = bd.getConnection();
		String longString = "";
		String queryfordb;
		Statement statement;
		ResultSet resultSet;
		if(query == 1 || query == 3){
			
			if(query == 1){
				queryfordb = "SELECT * From Patient ORDER BY firstName, lastName ASC;";
			}
			else{
				queryfordb = "SELECT * From Patient WHERE dentistID = '" + c.currentDentistId + "' ORDER BY firstName, lastName ASC;";
			}

			try {
		        

				statement = bd.getConnections().createStatement();
				resultSet = statement.executeQuery( queryfordb );
				String firstName, lastName, email, address1, address2, address3, address4, telephone, balance;
		         int count = 0;
		         String paymentAmnt,paymentType,date,queryfordb2,queryfordb3,procName,proc,pdate;
		         while(resultSet.next()){
		        	 firstName = resultSet.getString("firstName");
		        	 lastName = resultSet.getString("lastName");
		        	 email = resultSet.getString("email");
		        	 address1 = resultSet.getString("address1");
		        	 address2 = resultSet.getString("address2");
		        	 address3 = resultSet.getString("address3");
		        	 address4 = resultSet.getString("address4");
		        	 telephone = resultSet.getString("telephone");
		        	 balance = resultSet.getString("balance");
		        	 longString = longString + "\n\nPatient:" + count + "\n";
		        	 longString = longString + "First Name\t\t" + "Last Name\t\t" + "Email\t\t" + "Address\t\t" + "Telephone\t\t" + "Balance\n";
		        	 longString = longString + firstName + "\t\t" + lastName + "\t\t" + email + "\t\t" + address1 + "\t\t" + telephone + "\t\t" + balance + "\n";
		        	 longString = longString + "          \t\t" + "         \t\t" + "     \t\t" + address2 + "\n";
		        	 longString = longString + "          \t\t" + "         \t\t" + "     \t\t" + address3 + "\n";	
		        	 longString = longString + "          \t\t" + "         \t\t" + "     \t\t" + address4 + "\n";
		        	 
				     queryfordb2 = "Select Invoice.patientID, Invoice.InvoiceID, Payment.InvoiceID, Payment.paymentAmnt, Payment.paymentType, Payment.date from Payment, Invoice where Invoice.PatientID = '"+resultSet.getInt("PatientID")+"' AND Invoice.InvoiceID = Payment.InvoiceID";
				     Statement statement1 = bd.getConnections().createStatement();
				     ResultSet resultSet1 = statement1.executeQuery( queryfordb2 );
				     
		        	 longString = longString + "\n\t\tPayment Amount\t\t" + "Payment Type\t\t" + "Date\n";
		        	 
				     while(resultSet1.next()){
				    	 paymentAmnt = resultSet1.getString("paymentAmnt");
				    	 paymentType = resultSet1.getString("paymentType");
				    	 date = resultSet1.getString("date");
				    	 
				    	 longString = longString + "\t\t"+ paymentAmnt +"\t\t" + paymentType +"\t\t" + date +"\n";
				    	 
				     }
				     
				     queryfordb3 = "Select Invoice.patientID, Invoice.InvoiceID, Pprocedure.InvoiceID, Pprocedure.procName, Pprocedure.proc, Pprocedure.date from Pprocedure, Invoice where Invoice.PatientID = '"+resultSet.getInt("PatientID")+"' AND Invoice.InvoiceID = Pprocedure.InvoiceID";
				     Statement statement2 = bd.getConnections().createStatement();
				     ResultSet resultSet2 = statement2.executeQuery( queryfordb3 );
				     
				     longString = longString + "\n\t\tProcedure Cost\t\t" + "Procedure Description\t\t" + "Date\n";
				     while(resultSet2.next()){
				    	 procName = resultSet2.getString("procName");
				    	 proc = resultSet2.getString("proc");
				    	 pdate = resultSet2.getString("date");
				    	 
				    	 longString = longString + "\t\t"+ procName +"\t\t" + proc +"\t\t" + pdate +"\n";
				    	 
				     }
		        	 
		        	 		
		        	 count ++;
		        	 
		         } 
		         
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		         
			
			
		}
		else{
			if(query == 2){
				queryfordb = "SELECT * From Patient WHERE balance <> '0.0' ORDER BY balance DESC;";
				
			
				
				
			}
			else{
				queryfordb = "SELECT * From Patient WHERE dentistID = '" + c.currentDentistId + "' AND balance <> '0.0' ORDER BY firstName, lastName ASC;";
			}
try {
		        

				statement = bd.getConnections().createStatement();
				resultSet = statement.executeQuery( queryfordb );
				String firstName, lastName, email, address1, address2, address3, address4, telephone, balance;
		         int count = 0;
		         String paymentAmnt,paymentType,date,queryfordb2,queryfordb3,procName,proc,pdate;
		         while(resultSet.next()){
		        	 firstName = resultSet.getString("firstName");
		        	 lastName = resultSet.getString("lastName");
		        	 email = resultSet.getString("email");
		        	 address1 = resultSet.getString("address1");
		        	 address2 = resultSet.getString("address2");
		        	 address3 = resultSet.getString("address3");
		        	 address4 = resultSet.getString("address4");
		        	 telephone = resultSet.getString("telephone");
		        	 balance = resultSet.getString("balance");
		        	 longString = longString + "\n\nPatient:" + count + "\n";
		        	 longString = longString + "First Name\t\t" + "Last Name\t\t" + "Email\t\t" + "Address\t\t" + "Telephone\t\t" + "Balance\n";
		        	 longString = longString + firstName + "\t\t" + lastName + "\t\t" + email + "\t\t" + address1 + "\t\t" + telephone + "\t\t" + balance + "\n";
		        	 longString = longString + "          \t\t" + "         \t\t" + "     \t\t" + address2 + "\n";
		        	 longString = longString + "          \t\t" + "         \t\t" + "     \t\t" + address3 + "\n";	
		        	 longString = longString + "          \t\t" + "         \t\t" + "     \t\t" + address4 + "\n";
		        	 
				     queryfordb2 = "Select Invoice.patientID, Invoice.InvoiceID, Payment.InvoiceID, Payment.paymentAmnt, Payment.paymentType, Payment.date from Payment, Invoice where Invoice.PatientID = '"+resultSet.getInt("PatientID")+"' AND Invoice.InvoiceID = Payment.InvoiceID";
				     Statement statement1 = bd.getConnections().createStatement();
				     ResultSet resultSet1 = statement1.executeQuery( queryfordb2 );
				     
		        	 longString = longString + "\n\t\tPayment Amount\t\t" + "Payment Type\t\t" + "Date\n";
		        	 
				     while(resultSet1.next()){
				    	 paymentAmnt = resultSet1.getString("paymentAmnt");
				    	 paymentType = resultSet1.getString("paymentType");
				    	 date = resultSet1.getString("date");
				    	 
				    	 longString = longString + "\t\t"+ paymentAmnt +"\t\t" + paymentType +"\t\t" + date +"\n";
				    	 
				     }
				     
				     queryfordb3 = "Select Invoice.patientID, Invoice.InvoiceID, Pprocedure.InvoiceID, Pprocedure.procName, Pprocedure.proc, Pprocedure.date from Pprocedure, Invoice where Invoice.PatientID = '"+resultSet.getInt("PatientID")+"' AND Invoice.InvoiceID = Pprocedure.InvoiceID";
				     Statement statement2 = bd.getConnections().createStatement();
				     ResultSet resultSet2 = statement2.executeQuery( queryfordb3 );
				     
				     longString = longString + "\n\t\tProcedure Cost\t\t" + "Procedure Description\t\t" + "Date\n";
				     while(resultSet2.next()){
				    	 procName = resultSet2.getString("procName");
				    	 proc = resultSet2.getString("proc");
				    	 pdate = resultSet2.getString("date");
				    	 
				    	 longString = longString + "\t\t"+ procName +"\t\t" + proc +"\t\t" + pdate +"\n";
				    	 
				     }
		        	 
		        	 		
		        	 count ++;
		        	 
		         } 
		         
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		t.setText(longString);
		
		
	}
	
	
	public void save(Text t, Window primaryStage){
		if(t.getText().length() != 0 || t.getText() != null){
		String text = t.getText();
		
		FileChooser fileChooser = new FileChooser();
		  
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        
        if(file != null){
        	try {
                FileWriter fileWriter = null;
                 
                fileWriter = new FileWriter(file);
                fileWriter.write(text);
                fileWriter.close();
            } catch (IOException ex) {
                AnoyingPopUp p = new AnoyingPopUp("Failed to save file check read and write permission");
            }
        }
		
		
		
		
		}
	}
	
	
	
	
	
	
}

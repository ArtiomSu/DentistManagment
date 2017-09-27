package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import mainApp.Invoice;
import mainApp.Patient;
import mainApp.Payment;
import mainApp.Procedure;
import utils.Components;
import utils.DateHandlerForDB;
import utils.invoiceAddViewAndController.InvoiceAddView;
import utils.paymentAddViewAndController.PaymentAddView;
import utils.procedureAddViewAndController.ProcedureAddView;
import view.PatientsView;

public class InvoiceViewController {

	private Components c;
	private Patient patient;
	private boolean isInvoiceEmpty = true;
	
	public InvoiceViewController(Components co, Patient pati){
		c = co;
		patient = pati;
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
	}
	
	public void back(){
		PatientsView p = new PatientsView(c, patient);
	}
	
	
	public ObservableList<Invoice> setItems(TableView<Invoice> t){
		ObservableList<Invoice> items = FXCollections.observableArrayList();

		
		/*
		if(patient.getPatientInvoiceList().size() == 0){
			
		}
		else{
			isInvoiceEmpty = false;
		
		for(int i = 0; i< patient.getPatientInvoiceList().size(); i++){
			double dump = patient.getPatientInvoiceList().get(i).calcLefttoPay();			
			items.add(patient.getPatientInvoiceList().get(i));
		}
		
		}
		
		
		
		*/
		
		try {
			Statement statement = c.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(            
				      "SELECT * FROM Invoice where patientID = '" + patient.getPatientNo() + "';");
			  
	        
			while(resultSet.next()){
			
				int invoiceID = resultSet.getInt("invoiceID");
				int patientID = resultSet.getInt("patientID");

				String invoiceAmnt = resultSet.getString("invoiceAmnt");
				String invoiceLeftToPay = resultSet.getString("invoiceLeftToPay");
				String date = resultSet.getString("date");
				String isPaid = resultSet.getString("isPaid");
				
				DateHandlerForDB datebd = new DateHandlerForDB();
				Date d = datebd.getDateFromString(date);
				System.out.println(d);
				boolean paid = false;
				
				if(isPaid.charAt(0) == 'Y'){
					paid = true;
				}
				
				Invoice tmp = new Invoice(d, patientID, Double.parseDouble(invoiceAmnt), Double.parseDouble(invoiceLeftToPay), paid);
				tmp.setInvoiceNo(invoiceID);
				
				items.add(tmp);
				isInvoiceEmpty = false;
				
				
			}
		    		

		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return items;
		
	}
	
	public ObservableList<Procedure> setItemsproc(TableView<Procedure> t, TableView<Invoice> invoice){
		ObservableList<Procedure> items = FXCollections.observableArrayList();
		if(isInvoiceEmpty == false){
		ObservableList<Invoice> selectedInvoice;
		selectedInvoice = invoice.getSelectionModel().getSelectedItems();
		
		try {
			Statement statement = c.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(            
				      "SELECT * FROM Pprocedure where InvoiceID = '" + selectedInvoice.get(0).getInvoiceNo() + "';");
			  
	        
			while(resultSet.next()){
			
				int invoiceID = resultSet.getInt("invoiceID");
				int procedureID = resultSet.getInt("procedureID");

				String procName = resultSet.getString("procName");
				String proc = resultSet.getString("proc");
				String date = resultSet.getString("date");
				
				
				DateHandlerForDB datebd = new DateHandlerForDB();
				Date d = datebd.getDateFromString(date);
				
				
				Procedure tmp = new Procedure(procedureID, procName, Double.parseDouble(proc), d, invoiceID);
				
				
				items.add(tmp);
				
			}
		    		

		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		}
		return items;
		
	}
	
	public ObservableList<Payment> setItemspay(TableView<Payment> t, TableView<Invoice> invoice){
		ObservableList<Payment> items = FXCollections.observableArrayList();
		
		if(isInvoiceEmpty == false){

		
		ObservableList<Invoice> selectedInvoice;
		selectedInvoice = invoice.getSelectionModel().getSelectedItems();
		
		try {
			Statement statement = c.connection.createStatement();
			ResultSet resultSet = statement.executeQuery(            
				      "SELECT * FROM Payment where InvoiceID = '" + selectedInvoice.get(0).getInvoiceNo() + "';");
			  
	        
			while(resultSet.next()){
			
				int invoiceID = resultSet.getInt("invoiceID");
				int paymentID = resultSet.getInt("paymentID");

				String paymentAmnt = resultSet.getString("paymentAmnt");
				String paymentType = resultSet.getString("paymentType");
				String date = resultSet.getString("date");
				
				
				DateHandlerForDB datebd = new DateHandlerForDB();
				Date d = datebd.getDateFromString(date);
				LocalDate omg = new java.sql.Date(d.getTime()).toLocalDate();
				
				Payment tmp = new Payment(Double.parseDouble(paymentAmnt), paymentType, paymentID, omg, invoiceID);
				
				
				items.add(tmp);
				
			}
		    		

		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		}
		return items;
		
	}
	
	public void invoiceTableclicked(TableView<Invoice> invoiceTable, TableView<Procedure> procedureTable, TableView<Payment> payTable){
		if(isInvoiceEmpty == false){
		
		
		procedureTable.setItems(this.setItemsproc(procedureTable, invoiceTable));
		payTable.setItems(this.setItemspay(payTable, invoiceTable));
		procedureTable.getSelectionModel().selectFirst();
		payTable.getSelectionModel().selectFirst();
		
	}
	}
	
	public void addClicked(int mode, TableView<Invoice> invoiceTable, TableView<Procedure> procedureTable, TableView<Payment> payTable){
		
		
		if(mode == 0){ //add invoice
			InvoiceAddView i = new InvoiceAddView();
			LocalDate d = i.start();
			Date todb = java.sql.Date.valueOf(d);
			DateHandlerForDB handler = new DateHandlerForDB();
			todb = handler.parseDate(todb);
			
			if(d != null){
				String query =  "insert into Invoice (invoiceAmnt, invoiceLeftToPay, date, isPaid, patientID)" + " values (?,?,?,?,?);";

				int lastid = -1;
			      try {
					PreparedStatement preparedStmt = c.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					
					String todatabase = handler.convertDate(todb);
					
					preparedStmt.setString(1, "0.0");
				     preparedStmt.setString(2, "0.0");
				     preparedStmt.setString(3, todatabase);
				     preparedStmt.setString(4, "T");
				     preparedStmt.setInt(5, patient.getPatientNo());
				   				     
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
				
				
				
				Invoice a = new Invoice(todb, patient.getPatientNo(), 0.0, 0.0, true);
				a.setInvoiceNo(lastid);
				invoiceTable.getItems().add(a);
				isInvoiceEmpty = false;
			}
		}
		
		
		
		
		
		
		//matters for procedures and payments since invoice needs to exist
		if(isInvoiceEmpty == false){
			ObservableList<Invoice> selectedInvoice;
			selectedInvoice = invoiceTable.getSelectionModel().getSelectedItems();
			if(selectedInvoice.size() != 0 || selectedInvoice.get(0) == null){

			if(mode == 1){ //add procedure
				ProcedureAddView v = new ProcedureAddView();
				Procedure newproc = v.start(false, null);
				if(newproc != null){
					
					
					String query =  "insert into Pprocedure (procName, proc, date, InvoiceID)" + " values (?,?,?,?);";

					int lastid = -1;
				      try {
						PreparedStatement preparedStmt = c.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
						
						Date todb = newproc.getProcDate();
						DateHandlerForDB handler = new DateHandlerForDB();
						todb = handler.parseDate(todb);

						String todatabase = handler.convertDate(todb);
						
						preparedStmt.setString(1, newproc.getProcName());
					     preparedStmt.setString(2, Double.toString(newproc.getProccost()));
					     preparedStmt.setString(3, todatabase);
					     preparedStmt.setInt(4, selectedInvoice.get(0).getInvoiceNo());
					     
					   				     
					      preparedStmt.execute();
					      
					      ResultSet rs = preparedStmt.getGeneratedKeys();
			                if(rs.next())
			                {
			                    lastid = rs.getInt(1);
			                }
					     
			                newproc.setProcNo(lastid);
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					procedureTable.getItems().add(newproc);
					for(int i = 0; i< procedureTable.getColumns().size(); i ++){
						payTable.getColumns().get(i).setVisible(false);
						payTable.getColumns().get(i).setVisible(true);
						}
						for(int i = 0; i< invoiceTable.getColumns().size(); i ++){
							invoiceTable.getColumns().get(i).setVisible(false);
							invoiceTable.getColumns().get(i).setVisible(true);
							}
				}
			}
			
			
			
			if(mode == 2){ //add a payment
				PaymentAddView v = new PaymentAddView();
				Payment newpay = v.start(false, null, invoiceTable);
				
				if(newpay != null){
					
						String query =  "insert into Payment (paymentAmnt, paymentType, date, InvoiceID)" + " values (?,?,?,?);";

						int lastid = -1;
					      try {
							PreparedStatement preparedStmt = c.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
							
							Date todb = java.sql.Date.valueOf(newpay.getPaymentDate());
							DateHandlerForDB handler = new DateHandlerForDB();
							todb = handler.parseDate(todb);

							String todatabase = handler.convertDate(todb);
							
							preparedStmt.setString(1, Double.toString(newpay.getPaymentAmnt()));
						     preparedStmt.setString(2, newpay.getPaymentType());
						     preparedStmt.setString(3, todatabase);
						     preparedStmt.setInt(4, selectedInvoice.get(0).getInvoiceNo());
						     
						   				     
						      preparedStmt.execute();
						      
						      ResultSet rs = preparedStmt.getGeneratedKeys();
				                if(rs.next())
				                {
				                    lastid = rs.getInt(1);
				                }
						     
							newpay.setInvoiceID(lastid);
							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					
					payTable.getItems().add(newpay);
					
					for(int i = 0; i< payTable.getColumns().size(); i ++){
						payTable.getColumns().get(i).setVisible(false);
						payTable.getColumns().get(i).setVisible(true);
						}
					
						for(int i = 0; i< invoiceTable.getColumns().size(); i ++){
							invoiceTable.getColumns().get(i).setVisible(false);
							invoiceTable.getColumns().get(i).setVisible(true);
							}
					
				}
				
			}
			
			
			
			}//end off 			if(selectedInvoice.size() == 0){

			
			
		}
		
	}
	
public void editClicked(int mode, TableView<Invoice> invoiceTable, TableView<Procedure> procedureTable, TableView<Payment> payTable){
		
		if(isInvoiceEmpty == false){
			ObservableList<Invoice> selectedInvoice;
			selectedInvoice = invoiceTable.getSelectionModel().getSelectedItems();
			if(selectedInvoice.size() != 0 || selectedInvoice.get(0) != null){
			if(mode == 1){ //edit procedure
				
				
				
				
				
					if(procedureTable.getSelectionModel().getSelectedItems().get(0) != null){
					ProcedureAddView v = new ProcedureAddView();
					Procedure newproc = v.start(true, procedureTable.getSelectionModel().getSelectedItems().get(0));
					if(newproc != null){
						
						
						String query = "UPDATE Pprocedure set procName = ?, proc = ? WHERE procedureID = ?";
						try {
							PreparedStatement preparedStmt = c.connection.prepareStatement(query);
							
							
							
						     preparedStmt.setString(1, newproc.getProcName());
						     preparedStmt.setString(2, Double.toString(newproc.getProccost()));
						     preparedStmt.setInt(3, procedureTable.getSelectionModel().getSelectedItems().get(0).getProcNo());
						     
						     
						     preparedStmt.executeUpdate();

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						
						
						
						
						procedureTable.getSelectionModel().getSelectedItems().get(0).setProccost(newproc.getProccost());
						procedureTable.getSelectionModel().getSelectedItems().get(0).setProcName(newproc.getProcName());
						//refreshes the information
						for(int i = 0; i< procedureTable.getColumns().size(); i ++){
						procedureTable.getColumns().get(i).setVisible(false);
						procedureTable.getColumns().get(i).setVisible(true);
						}
						for(int i = 0; i< invoiceTable.getColumns().size(); i ++){
							invoiceTable.getColumns().get(i).setVisible(false);
							invoiceTable.getColumns().get(i).setVisible(true);
							}
					}
			}
			}
			
			
			if(mode == 2){ //edit payment
				if(payTable.getSelectionModel().getSelectedItems().get(0) != null){
					PaymentAddView v = new PaymentAddView();
					Payment newpay = v.start(true, payTable.getSelectionModel().getSelectedItems().get(0), invoiceTable);
					if(newpay != null){
						
						String query = "UPDATE Payment set paymentAmnt = ?, paymentType = ?, date = ? WHERE paymentID = ?";
						try {
							PreparedStatement preparedStmt = c.connection.prepareStatement(query);
							
							Date todb = java.sql.Date.valueOf(newpay.getPaymentDate());
							DateHandlerForDB handler = new DateHandlerForDB();
							todb = handler.parseDate(todb);

							String todatabase = handler.convertDate(todb);
							
						     preparedStmt.setString(1, Double.toString(newpay.getPaymentAmnt()));
						     preparedStmt.setString(2, newpay.getPaymentType());
						     preparedStmt.setString(3, todatabase);

						     preparedStmt.setInt(4, payTable.getSelectionModel().getSelectedItems().get(0).getPaymentNo());
						     
						     
						     preparedStmt.executeUpdate();

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}		
						
						
						
						payTable.getSelectionModel().getSelectedItems().get(0).setPaymentType(newpay.getPaymentType());
						payTable.getSelectionModel().getSelectedItems().get(0).setdate(newpay.getPaymentDate());
						payTable.getSelectionModel().getSelectedItems().get(0).setPaymentAmnt(newpay.getPaymentAmnt());

						//refreshes the information
						for(int i = 0; i< payTable.getColumns().size(); i ++){
						payTable.getColumns().get(i).setVisible(false);
						payTable.getColumns().get(i).setVisible(true);
						}
						for(int i = 0; i< invoiceTable.getColumns().size(); i ++){
							invoiceTable.getColumns().get(i).setVisible(false);
							invoiceTable.getColumns().get(i).setVisible(true);
							}
					}
			}
			}
			
			
			}// end of 			if(selectedInvoice.size() == 0){

			
			
			
		}
		
	}
	
}

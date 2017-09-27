package mainApp;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import utils.dataBaseFiles.DataBase;

public class Invoice{
	


	private int patientID;
	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}


	private int invoiceNo;
	private double invoiceAmnt;
	private double invoiceLefttoPay;
	private Date invoiceDate;
	private boolean isPaid;
	private String invoiceDateString = "bob";
	
	
	

	public Invoice(Date dinvoiceDate, int patientID, double invoiceAmnt, double invoiceLefttoPay, boolean isPaid){
		this.invoiceDate = dinvoiceDate;
		this.patientID = patientID;
		
		this.invoiceAmnt = invoiceAmnt;
		this.invoiceLefttoPay = invoiceLefttoPay;
		this.isPaid = isPaid;
		this.invoiceDateString = dinvoiceDate.toString(); //table view has some problem with Date
		
	}
	//to do ++++++++++++++++++++++++++++
	//manage the lists
	
	//if left to pay is 0 and procedure is not null this will set ispaid to true
	//this must be called whenever you deal with invoices
	public double calcLefttoPay(){
		invoiceLefttoPay = 0.0;
		calcfullamount();

		  double totalPayment =0;
		
		try {
			DataBase db = new DataBase();
			boolean t = db.getConnection();
			Statement statement = db.getConnections().createStatement();
			ResultSet resultSet = statement.executeQuery(            
					"SELECT paymentAmnt FROM Payment where paymentAmnt <>'0.0' AND InvoiceID = '" + this.invoiceNo + "';");
			  
	        
			
			
			while(resultSet.next()){
			
				totalPayment = totalPayment + Double.parseDouble(resultSet.getString("paymentAmnt"));
				
			}
		    		
			invoiceLefttoPay = invoiceAmnt - totalPayment;
			if(invoiceLefttoPay == 0.0){
				isPaid = true;
			}
			
			String query = "UPDATE Invoice set invoiceLeftToPay = ?, isPaid = ? WHERE invoiceID = ?";
			
			PreparedStatement preparedStmt = db.getConnections().prepareStatement(query);
			
		     preparedStmt.setString(1, Double.toString(invoiceLefttoPay));
		     
		     if(invoiceLefttoPay == 0.0){
		     preparedStmt.setString(2, "T");
		     }
		     else{
			     preparedStmt.setString(2, "F");

		     }

		     
		     preparedStmt.setInt(3, invoiceNo);
		     
		     
		     preparedStmt.executeUpdate();
		        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			
			
		return invoiceLefttoPay;
	}
	
	public void calcfullamount(){
		invoiceAmnt = 0.0;
		try {
			DataBase db = new DataBase();
			boolean t = db.getConnection();
			Statement statement = db.getConnections().createStatement();
			ResultSet resultSet = statement.executeQuery(            
				      "SELECT proc FROM Pprocedure where proc <>'0.0' AND InvoiceID = '" + this.invoiceNo + "';");
			  
	        
			while(resultSet.next()){
			
				invoiceAmnt = invoiceAmnt + Double.parseDouble(resultSet.getString("proc"));
				
			}
			
			
			String query = "UPDATE Invoice set invoiceAmnt = ? WHERE invoiceID = ?";
			
				PreparedStatement preparedStmt = db.getConnections().prepareStatement(query);
				
			     preparedStmt.setString(1, Double.toString(invoiceAmnt));
			     preparedStmt.setInt(2, invoiceNo);
			     
			     
			     preparedStmt.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public double getInvoiceLefttoPay() {
		calcLefttoPay();
		return invoiceLefttoPay;
	}

	public void setInvoiceLefttoPay(double invoiceLefttoPay) {
		this.invoiceLefttoPay = invoiceLefttoPay;
	}

	
	

	public Invoice(){
		//only use this for temps
	}
	
	public int getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public double getInvoiceAmnt() {
		calcfullamount();
		return invoiceAmnt;
	}


	public void setInvoiceAmnt(double invoiceAmnt) {
		this.invoiceAmnt = invoiceAmnt;
	}


	public Date getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public boolean isPaid() {
		return isPaid;
	}


	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public String getInvoiceDateString() {
		return invoiceDateString;
	}

	public void setInvoiceDateString(String invoiceDateString) {
		this.invoiceDateString = invoiceDateString;
	}
	
}

package mainApp;


import java.util.Date;

public class Procedure {

	


	public void setProcNo(int procNo) {
		this.procNo = procNo;
	}


	
	private int procNo;
	private String procName;
	private double proccost = 0.0;
	private Date procDate;
	private int invoiceID;
	
	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public Procedure(String n, double c){
		procDate = new Date();
		procName = n;
		proccost = c; 
		
	}

	public Procedure(int procNo, String procName, double proccost, Date procDate, int invoiceID){
		this.procNo = procNo;
		this.procName = procName;
		this.proccost = proccost;
		this.procDate = procDate;
		this.invoiceID = invoiceID;
	}

	

	public int getProcNo() {
		return procNo;
	}

	public String getProcName() {
		return procName;
	}



	public void setProcName(String procName) {
		this.procName = procName;
	}



	public double getProccost() {
		return proccost;
	}



	public void setProccost(double proccost) {
		this.proccost = proccost;
	}




	public Date getProcDate() {
		return procDate;
	}




	public void setProcDate(Date procDate) {
		this.procDate = procDate;
	}
	
	
	
	
}

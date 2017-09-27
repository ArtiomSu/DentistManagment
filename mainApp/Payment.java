package mainApp;
import java.io.Serializable;
import java.time.LocalDate;

public class Payment implements Serializable{

	


	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	private int invoiceID;
	

	private int paymentNo;
	private double paymentAmnt;
	private LocalDate paymentDate;
	private String paymentType;
	
	public Payment(double p, String type){
		
		
		paymentAmnt = p;
		paymentType = type;
		
	}

public Payment(double p, String type, int paymentNo, LocalDate paymentDate, int invoiceID){
		
		
		paymentAmnt = p;
		paymentType = type;
		this.invoiceID = invoiceID;
		this.paymentNo = paymentNo;
		this.paymentDate = paymentDate;

	}

	public String getPaymentType() {
		return paymentType;
	}


	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


	public int getPaymentNo() {
		return paymentNo;
	}


	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}


	public double getPaymentAmnt() {
		return paymentAmnt;
	}


	public void setPaymentAmnt(double paymentAmnt) {
		this.paymentAmnt = paymentAmnt;
	}


	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	
	public void setdate(LocalDate d){
		paymentDate = d;
	}
			
	
	public int getInvoiceID() {
		return invoiceID;
	}


	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}
}

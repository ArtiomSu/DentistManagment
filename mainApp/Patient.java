package mainApp;
import java.io.Serializable;
import java.util.ArrayList;

public class Patient extends Person{

	
	
	
	private int patientNo;
	private ArrayList<Invoice> patientInvoiceList;
	private double balance = 0.0;
	private int dentistID = 0;
	

	public int getDentistID() {
		return dentistID;
	}
	public void setDentistID(int dentistID) {
		this.dentistID = dentistID;
	}
	public Patient(){
		super();
		patientInvoiceList = new ArrayList<Invoice>();
		
	}
	public Patient(int a, int b, String firstName, String lastName ,String email , String telephone, String balance, String[] address){
		super();
		patientInvoiceList = new ArrayList<Invoice>();
		this.patientNo = a;
		this.dentistID = b;
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setTelepnone(telephone);
		this.balance = Double.parseDouble(balance); 
		this.setAddress(address);
		
	}
	
	
	public ArrayList<Invoice> getPatientInvoiceList() {
		return patientInvoiceList;
	}

	public void setPatientInvoiceList(ArrayList<Invoice> patientInvoiceList) {
		this.patientInvoiceList = patientInvoiceList;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setPatientNo(int patientNo) {
		this.patientNo = patientNo;
	}
	
	public int getPatientNo(){
		return patientNo;
	}
	
	public void addInvoice(Invoice i){
		patientInvoiceList.add(i);
	}
	//calculates balance from all invoices
	public void calcBalance(){
		balance = 0; //very important otherwise everytime manage patients is selected the balance increases
		if(patientInvoiceList.size() == 0){
			
		}
		else{
			for(int i = 0; i < patientInvoiceList.size(); i ++ ){
				balance = balance + patientInvoiceList.get(i).calcLefttoPay();
			}
		}
	}
	
	//get by invoce number
	public Invoice getInvoice(int num){
		Invoice inv = new Invoice();
		Invoice tmp;
		for(int i = 0; i < patientInvoiceList.size(); i++){
			tmp = patientInvoiceList.get(i);
			if(tmp.getInvoiceNo() == num){
				inv = tmp;
			}
		}
		
		return inv;
	}
	
	
	
}

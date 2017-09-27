package mainApp;

import java.io.Serializable;

public class Person implements Serializable{

	private String firstName;
	private String lastName;
	private String email;
	private String[] address; 
	private String telepnone;
	
	
	public Person(){
		firstName = "";
		lastName = "";
		this.email = "";
		address = new String[4];
		telepnone = "";
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String[] getAddress() {
		return address;
	}


	public void setAddress(String[] address) {
		this.address = address;
	}


	public String getTelepnone() {
		return telepnone;
	}


	public void setTelepnone(String telepnone) {
		this.telepnone = telepnone;
	}
}

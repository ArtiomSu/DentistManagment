package utils.paymentAddViewAndController;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainApp.Invoice;
import mainApp.Payment;
import mainApp.Procedure;
import utils.AnoyingPopUp;

public class PaymentAddController {

	boolean isNotDouble = true; 
	double cost = -1;
	Payment p = null;
	public PaymentAddController(){
		
	}
	
	
	
	public Payment getPayment(){
		return p;
	}
	
	public void clearPayment(){
		p = null;
	}
	
	
	
	public void checkIfDouble(String s, TextField t){
		try{
			cost = Double.parseDouble(s);
			if(s.length() != 0 && cost > 0.0){
				t.setStyle(null);
				isNotDouble = false;
			}
		}
		catch(Exception ex){
			t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-text-fill: red; -fx-border-radius: 5 5 5 5;");
			t.setPromptText("must be a number");
			isNotDouble = true;
		}
		
	}
	
	public void createPayment(Stage w, ComboBox<String> name,DatePicker d, TableView<Invoice> invoiceTable, boolean isedit, Payment p1){
		if(isNotDouble == false){
			double total = invoiceTable.getSelectionModel().getSelectedItems().get(0).calcLefttoPay();
			if(isedit){
				total = total + p1.getPaymentAmnt();//needs to be like this dont change
			}
			if(cost <= total){
			p = new Payment(cost, name.getValue());
			p.setdate(d.getValue());
			
			System.out.println(p.toString());
		
			w.close();
			}
			else{
				AnoyingPopUp s = new AnoyingPopUp("You have " + total + " left to pay\nAnd you are trying to pay " + cost + "\nwhich is too much");
			}
		}
		else{
			AnoyingPopUp pop = new AnoyingPopUp("Some of the Information is incorrect");
		}
	}
	
	public void fill(TextField cost, ComboBox<String> name, DatePicker d, Payment p2){
		name.setValue(p2.getPaymentType());
		cost.setText(""+p2.getPaymentAmnt());
		d = new DatePicker(p2.getPaymentDate());
		
	}
	
}

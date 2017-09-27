package utils.procedureAddViewAndController;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainApp.Procedure;
import utils.AnoyingPopUp;

public class ProcedureAddController {
	boolean isBlank = true;
	boolean isNotDouble = true; 
	double cost = -1;
	String name = null;
	Procedure p = null;
	public ProcedureAddController(){
		
	}
	
	
	
	public Procedure getProcedure(){
		return p;
	}
	
	public void clearProcedure(){
		p = null;
	}
	
	public void checkIfBlank(String s, TextField t){
		if(s.length() == 0){
			t.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-text-fill: red; -fx-border-radius: 5 5 5 5;");
			t.setPromptText("Cant be left blank");
			isBlank = true;
		}
		else{
			t.setStyle(null);
			isBlank = false;
			name = s;

		}
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
	
	public void createProcedure(Stage w){
		if(isBlank == false && isNotDouble == false){
			p = new Procedure(name,cost);
			w.close();
		}
		else{
			AnoyingPopUp p = new AnoyingPopUp("Some of the Information is incorrect");
		}
	}
	
	public void fill(TextField name, TextField cost, Procedure p){
		name.setText(p.getProcName());
		cost.setText(""+p.getProccost());
		
	}
}

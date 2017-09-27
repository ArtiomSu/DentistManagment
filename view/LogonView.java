package view;

import controller.LogonViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import utils.Components;

public class LogonView{
	
	private GridPane grid = new GridPane();
	private Components components;
	private LogonViewController cont;
	
	public LogonView(Components c){
		components = c;
		cont = new LogonViewController(c);
		start();
	}

	public void start(){
		
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label nameLable = new Label("Username");
		GridPane.setConstraints(nameLable, 0, 0);
		
		TextField nameInput = new TextField();
		GridPane.setConstraints(nameInput, 1, 0);
		
		
		Label passLable = new Label("Password");
		GridPane.setConstraints(passLable, 0, 1);
		
		PasswordField passInput = new PasswordField();
		GridPane.setConstraints(passInput, 1, 1);
		
		
		Button loginb = new Button("Login");
		loginb.setOnAction(e -> cont.LoginButton(nameInput, passInput));
		//GridPane.setConstraints(loginb, 1, 2);
		
		Button register = new Button("Register new Dentist");
		register.setOnAction(e -> {
			cont.RegisterButton();
		});
		//GridPane.setConstraints(register, 2, 2);
		
		HBox twobuttons = new HBox();
		twobuttons.getChildren().addAll(loginb, register);
		GridPane.setConstraints(twobuttons, 1, 2);
		
		grid.getChildren().addAll(nameLable, passInput, passLable, nameInput, twobuttons);
		
		grid.setAlignment(Pos.CENTER);
		
		
		Scene scene = new Scene(grid, 480, 240);
		

	
		
		components.window.setScene(scene);
		components.window.show();

		System.out.println("here");
		
		
	}
	
	
	
	/*private int findDentist(){
		int index = -1;
		for(int i = 0; i < components.dentistList.size(); i++){
			if(components.cu.getDentistNo() == components.dentistList.get(i).getDentistNo()){
				return i;
			}
		}
		return index;
	}
	*/
	
}

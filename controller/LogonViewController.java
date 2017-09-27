package controller;

import java.util.Locale;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import utils.AnoyingPopUp;
import utils.Components;
import utils.Hashes;
import utils.dataBaseFiles.DataBase;
import view.MainView;
import view.RegisterView;

public class LogonViewController {

	Components c;
	
	public LogonViewController(Components c){
		this.c = c;
		c.currentDentistId = -1;
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
	}
	
	public void LoginButton(TextField name, PasswordField pass){
		String username = name.getText();
		String userpass = pass.getText();
		
		
		boolean found = false;
		DataBase db = new DataBase(c.connection);
		
		int doctorsid = db.checkLogin(username, userpass);
		
			
				
				if(doctorsid != -1){
					c.currentDentistId = doctorsid;
					MainView mainview = new MainView(c);
					c.connection = db.getConnections();
					mainview.start();
				}
				else{
					AnoyingPopUp b = new AnoyingPopUp("credidentials not found");
				}
			
	}
	
	public void RegisterButton(){
		RegisterView r = new RegisterView(c);
	}
	
}

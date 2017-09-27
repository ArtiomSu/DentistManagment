package mainApp;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.AnoyingPopUp;
import utils.Components;
import utils.GenData;
import utils.Load_save;
import utils.dataBaseFiles.DataBase;
import view.LogonView;

public class Main extends Application{
	
	private Components components = new Components();
	

	//if dentist is not there index is -1
	
	
	public static void main(String[] args) {
		
		launch(args);
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
		
		/*Dentist temp = new Dentist("password");
		String address[] = new String[] {"HOuse 1", "bakers street", "London"};
		temp.setAddress(address);
		temp.setEmail("dentistical@d.com");
		temp.setFirstName("johny");
		temp.setLastName("Last");
		temp.setTelepnone("012354336");
		dentistList.add(temp);
		
		Patient ptemp = new Patient();
		ptemp.setAddress(address);
		ptemp.setEmail("dentisffftical@d.com");
		ptemp.setFirstName("jotthny");
		ptemp.setLastName("Layyst");
		ptemp.setTelepnone("0123456447");
		
		temp.AddPatient(ptemp);
		*/
		
	}
	

	private void closehelper(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setOnCloseRequest(e -> {
			e.consume();
		});
		
		HBox hbox = new HBox();
		//hbox.setPadding(new Insets(20,20,20,20));
		hbox.setSpacing(10);
		VBox vbox = new VBox();
		//vbox.setPadding(new Insets(20,20,20,20));
		Button b1 = new Button("Yes");
		b1.setOnAction(e ->{
			
			window.close();
			components.window.close();

		});
		Button b2 = new Button("No");
		b2.setOnAction(e-> {
			
			window.close();
		});
		
		
		Label quest = new Label("Are you sure you want to exit");
		hbox.getChildren().addAll( b1, b2);
		vbox.getChildren().add(quest);
		
		AnchorPane anchor = new AnchorPane();
		anchor.getChildren().addAll(hbox,vbox);
		anchor.setBottomAnchor(hbox, 20.0);
		anchor.setRightAnchor(hbox, 20.0);
		anchor.setTopAnchor(vbox, 20.0);
		anchor.setLeftAnchor(vbox, 20.0);
		
		Scene scene = new Scene(anchor,320,160);
		window.setScene(scene);
		window.show();
		
		
	
	}
	
/*	private void closeProgram(){
		Object class1 = components.window.getClass();
		closehelper();
		if(choiceexit == 0){
			components.window.close();
		}
	}
	*/
	
	public void start(Stage primaryStage){
		DataBase db = new DataBase();
		components.window.setOnCloseRequest(e->{
			e.consume();
			closehelper();
		});
		
		boolean foundDB = db.checkifDataBaseExists();
		
		
		
		
		
		
		if(foundDB == false){
			AnoyingPopUp pop = new AnoyingPopUp();
			int result = pop.dbhelp("Database not found restart mysql server or create new empty database");
			if(result == 1){
				boolean created = db.createNewDB();
				boolean tablesCreated = db.createTables();
				if(created = false){
					AnoyingPopUp p = new AnoyingPopUp("It seems like mysql server is down. Program will quit");
					System.exit(5);
				}
				else if(tablesCreated = false){System.out.println("Something wrong with tables");}
			}
			else{
				System.exit(4);
			}
			
			
		}
		
		
		boolean foundTables = db.checkiftablesExists();
		System.out.println("Continue " + foundTables);
		if(foundTables == false){
			System.out.println("Inside");
			AnoyingPopUp pop = new AnoyingPopUp("DataBase is missing tables. Contact database guy. Program shutting down");
			
		}
		
		if(foundDB && foundTables){
		components.connection = db.getConnections();
		LogonView loginview = new LogonView(components);
		}
		
		
	
		
	}

}

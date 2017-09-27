package utils.reportViewAndController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Components;
import utils.dentistEditViewAndController.DentistEditController;

public class ReportView {

	Components c;
	reportController cont;
	
	public ReportView(Components com){
		c = com;
		cont = new reportController(c);
		start();
	}
	
	
	private void start(){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setOnCloseRequest(e -> {
			e.consume();
		});
		
		
		
		VBox whole = new VBox();
		
		HBox text = new HBox();
		text.setPadding(new Insets(20,20,20,20));
		
		Text textbox = new Text();
		

		
		text.getChildren().add(textbox);
		
		
		
		VBox buttons = new VBox(20);
		buttons.setPadding(new Insets(20,20,20,20));
		buttons.setAlignment(Pos.CENTER);
		Button q1 = new Button("FOR ALL DENTISTS: Generate a report on all patients sorted by name showing all information in the system (Patients, procedures and payments)");
		Button q2 = new Button("FOR ALL DENTISTS: Generate a report on patients who owe money");
		Button q3 = new Button("FOR CURRENTLY LOGGED IN DENTIST: Generate a report on all patients sorted by name showing all information in the system (Patients, procedures and payments)");
		Button q4 = new Button("FOR CURRENTLY LOGGED IN DENTIST: Generate a report on patients who owe money");
		
		q1.setOnAction(e -> {
			cont.Queries(textbox, 1);
		});
		q2.setOnAction(e -> {
			cont.Queries(textbox, 2);
		});
		q3.setOnAction(e -> {
			cont.Queries(textbox, 3);
		});
		q4.setOnAction(e -> {
			cont.Queries(textbox, 4);
		});
		
		
		
		buttons.getChildren().addAll(q1,q2,q3,q4);
		
		
		
		HBox save = new HBox(20);
		
		save.setPadding(new Insets(20,20,20,20));
		Button saveTofile = new Button("Save to File");
		saveTofile.setOnAction(e ->{
			cont.save(textbox, window);
		});
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			window.close();
		});
		save.getChildren().addAll(saveTofile, cancel);
		
		
		
		
		
		whole.getChildren().addAll(buttons, text, save);
		whole.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(whole);
		window.setScene(scene);
		
		
		window.showAndWait();
	}
}

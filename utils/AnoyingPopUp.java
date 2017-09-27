package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AnoyingPopUp {

	
	public AnoyingPopUp(String message){
	
	Stage window = new Stage();
	window.initModality(Modality.APPLICATION_MODAL);
	window.setOnCloseRequest(e -> {
		e.consume();
	});
	
	VBox vbox = new VBox(10);
	
	Button b2 = new Button("OK");
	b2.setOnAction(e-> {
		
		window.close();
	});
	Label quest = new Label(message);
	vbox.getChildren().addAll(quest,b2);
	vbox.setAlignment(Pos.CENTER);
	vbox.setPadding(new Insets(10,10,10,10));
	
	Scene scene = new Scene(vbox);
	window.setScene(scene);
	window.show();
}
	public AnoyingPopUp(){
		
	}
	
	int desicion = 0;

	public int dbhelp(String message){
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setOnCloseRequest(e -> {
			e.consume();
		});
		
		VBox vbox = new VBox(10);
		
		Button b2 = new Button("Close Program");
		b2.setOnAction(e-> {
			desicion = 2;
			window.close();
		});
		Button b3 = new Button("Create new Database");
		b3.setOnAction(e-> {
			desicion = 1;
			window.close();
		});
		
		Label quest = new Label(message);
		vbox.getChildren().addAll(quest,b2,b3);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10,10,10,10));
		
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.showAndWait();
		return desicion;
	}
}

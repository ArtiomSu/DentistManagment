package utils.procedureAddViewAndController;

import java.util.Observable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mainApp.Procedure;

public class ProcedureAddView {
	ProcedureAddController c;
	public ProcedureAddView(){
		c = new ProcedureAddController();
	}
	
	public Procedure start(boolean isedit, Procedure p){
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setOnCloseRequest(e -> {
			e.consume();
		});
		
		VBox vbox = new VBox(50);
		
		GridPane grid = new GridPane();
		Label desclabel = new Label("Procedure Name");
		Label costlabel = new Label("Cost of Procedure");
		
		TextField descfield = new TextField();
		descfield.textProperty().addListener((observable, oldValue, newValue) -> {
			c.checkIfBlank(newValue, descfield);
		});
		TextField costfield = new TextField();
		costfield.textProperty().addListener((observable, oldValue, newValue) -> {
			c.checkIfDouble(newValue, costfield);
		});
		
		grid.add(desclabel, 0, 0);
		grid.add(descfield, 1, 0);
		
		grid.add(costlabel, 0, 1);
		grid.add(costfield, 1, 1);

	
		if(isedit == true){
			c.fill(descfield, costfield, p);
		}
		
		
		Button b1 = new Button("OK");
		b1.setOnAction(e-> {
			c.createProcedure(window);
		});
		Button b2 = new Button("Cancel");
		b2.setOnAction(e-> {
			c.clearProcedure();
			window.close();
		});
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
		hb.getChildren().addAll(b1,b2);
		
		
		vbox.getChildren().addAll(grid,hb);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10,10,10,10));
		
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.showAndWait();
		
		
		
		return c.getProcedure();
	}

}

package utils.invoiceAddViewAndController;

import java.time.LocalDate;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class InvoiceAddView {
	LocalDate date;
	
	public InvoiceAddView(){
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
		
	}
	
	public LocalDate start(){
		date = LocalDate.now();
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setOnCloseRequest(e -> {
			e.consume();
		});
		
		VBox vbox = new VBox(100);
		
		DatePicker dp = new DatePicker(date);
		dp.setOnAction(e->{
			date = dp.getValue();
			
		});
		
		
		Button b1 = new Button("OK");
		b1.setOnAction(e-> {
			window.close();
		});
		Button b2 = new Button("Cancel");
		b2.setOnAction(e-> {
			date = null;
			window.close();
		});
		
		HBox hb = new HBox();
		hb.setPadding(new Insets(10,10,10,10));
hb.getChildren().addAll(b1,b2);
		
		
		vbox.getChildren().addAll(dp, hb);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10,10,10,10));
		
		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.showAndWait();
		return date;
		
	}
	
	
	

}

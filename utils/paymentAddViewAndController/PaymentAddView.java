package utils.paymentAddViewAndController;

import java.time.LocalDate;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mainApp.Invoice;
import mainApp.Payment;

public class PaymentAddView {

	
		PaymentAddController c;
		public PaymentAddView(){
			c = new PaymentAddController();
		}
		
		public Payment start(boolean isedit, Payment p, TableView<Invoice> invoiceTable){
			
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setOnCloseRequest(e -> {
				e.consume();
			});
			
			VBox vbox = new VBox(50);
			
			GridPane grid = new GridPane();
			grid.setVgap(5);
			grid.setHgap(5);
			Label desclabel = new Label("PaymentType");
			Label costlabel = new Label("Amount Payed");
			Label datelabel = new Label("Date Payed");
			
			ComboBox<String> combob = new ComboBox<String>();
		    combob.getItems().addAll("Cash","Credit","Debit","Check","Kidney");
		    combob.setValue("Cash");
			
			TextField costfield = new TextField();
			costfield.textProperty().addListener((observable, oldValue, newValue) -> {
				c.checkIfDouble(newValue, costfield);
			});
			
			Locale ireland = new Locale("en","IE");
			Locale.setDefault(ireland);
			
			LocalDate date = LocalDate.now();
			DatePicker dp = new DatePicker(date);
			
			
			
			grid.add(desclabel, 0, 0);
			grid.add(combob, 1, 0);
			
			grid.add(costlabel, 0, 1);
			grid.add(costfield, 1, 1);
			
			grid.add(datelabel, 0, 2);
			grid.add(dp,1,2);
			
			if(isedit == true){
				c.fill(costfield, combob, dp, p);
			}
			
			
			Button b1 = new Button("OK");
			b1.setOnAction(e-> {
				c.createPayment(window, combob, dp, invoiceTable, isedit, p);
			});
			Button b2 = new Button("Cancel");
			b2.setOnAction(e-> {
				c.clearPayment();
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
			
			
			
			return c.getPayment();
		}

}

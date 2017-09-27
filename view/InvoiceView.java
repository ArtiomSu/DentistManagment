package view;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import controller.InvoiceViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mainApp.Invoice;
import mainApp.Patient;
import mainApp.Payment;
import mainApp.Procedure;

import utils.Components;

public class InvoiceView {

	private Components c;
	private Patient patient;
	public InvoiceView(Components co, Patient pa){
		c = co;
		patient = pa;
		start();
		
	}
	
	private void start(){
		InvoiceViewController cont = new InvoiceViewController(c, patient);
		
		/*
		 * 
		 * 
		 * 
		 * left part same as on patientsview
		 * 
		 * 
		 */
		HBox allscreen = new HBox();
		
		VBox back = new VBox();
		back.setAlignment(Pos.CENTER);
		back.setMinWidth(50);
		back.setMaxWidth(50);
		
		back.setOnMouseClicked(e->{
			cont.back();
			});
		
		Label backbut = new Label("<<<\nBack\n<<<");
		back.setOnMouseClicked(e->{
			cont.back();
			});
		
		back.getChildren().add(backbut);
		
		/*
		 * 
		 * 
		 * invoice table
		 * 
		 * 
		 * */
		
		TableView<Invoice> invoiceTable = new TableView<Invoice>();
		
		TableColumn<Invoice, Double> invoiceno = new TableColumn<>("#");
		invoiceno.setMinWidth(40);
		invoiceno.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
		
		TableColumn<Invoice, Double> invoicetotal = new TableColumn<>("Total Cost");
		invoicetotal.setMinWidth(80);
		invoicetotal.setCellValueFactory(new PropertyValueFactory<>("invoiceAmnt"));
		
		
		TableColumn<Invoice, Double> invoiceLeft = new TableColumn<>("Left To Pay");
		invoiceLeft.setMinWidth(80);
		invoiceLeft.setCellValueFactory(new PropertyValueFactory<>("invoiceLefttoPay"));
		
		TableColumn<Invoice, String> invoiceDate = new TableColumn<>("Date");
		invoiceDate.setMinWidth(210);
		invoiceDate.setCellValueFactory(new PropertyValueFactory<>("invoiceDateString"));
		
		invoiceTable.getColumns().addAll(invoiceno, invoiceDate, invoiceLeft, invoicetotal);
		
		
		
		/*AnchorPane one = new AnchorPane();
		one.getChildren().addAll(invoiceTable, invoicebuttons);
		
		one.setBottomAnchor(invoicebuttons, 10.0);
		one.setLeftAnchor(invoicebuttons, 10.0);
		one.setTopAnchor(invoiceTable, 0.0);
		one.setLeftAnchor(invoiceTable, 10.0);
*/
		
		
		/*
		invoiceTable.setMinWidth(invoiceno.getWidth() + invoicetotal.getWidth() + invoiceLeft.getWidth() + invoiceDate.getWidth());
		invoiceTable.setMaxWidth(invoiceno.getWidth() + invoicetotal.getWidth() + invoiceLeft.getWidth() + invoiceDate.getWidth());
		one.setMinWidth(invoiceno.getWidth() + invoicetotal.getWidth() + invoiceLeft.getWidth() + invoiceDate.getWidth());
		one.setMaxWidth(invoiceno.getWidth() + invoicetotal.getWidth() + invoiceLeft.getWidth() + invoiceDate.getWidth());
		/*
		 * 
		 * 
		 * procedures table
		 * 
		 * 
		 * */
		
		TableView<Procedure> procedureTable = new TableView<Procedure>();
		
		
		
		TableColumn<Procedure, Double> procno = new TableColumn<>("#");
		procno.setMinWidth(40);
		procno.setCellValueFactory(new PropertyValueFactory<>("procNo"));
		
		TableColumn<Procedure, String> procName = new TableColumn<>("procedure Name");
		procName.setMinWidth(200);
		procName.setCellValueFactory(new PropertyValueFactory<>("procName"));
		
		
		TableColumn<Procedure, Double> proccost = new TableColumn<>("Cost");
		proccost.setMinWidth(80);
		proccost.setCellValueFactory(new PropertyValueFactory<>("proccost"));
		
		TableColumn<Procedure, Date> procDate = new TableColumn<>("Date");
		invoiceDate.setMinWidth(210);
		invoiceDate.setCellValueFactory(new PropertyValueFactory<>("procDate"));
		
		procedureTable.getColumns().addAll(procno, procName, proccost, procDate);
		
		
		
		/*AnchorPane two = new AnchorPane();
		two.getChildren().addAll(procedureTable, procbuttons);
		
		two.setBottomAnchor(procbuttons, 10.0);
		two.setLeftAnchor(procbuttons, 10.0);
		two.setTopAnchor(procedureTable, 0.0);
		two.setLeftAnchor(procedureTable, 10.0);
		*/
		
		/*
		procedureTable.setMinWidth(procno.getWidth() + procName.getWidth() + proccost.getWidth());
		procedureTable.setMaxWidth(procno.getWidth() + procName.getWidth() + proccost.getWidth());
		two.setMinWidth(procno.getWidth() + procName.getWidth() + proccost.getWidth());
		two.setMaxWidth(procno.getWidth() + procName.getWidth() + proccost.getWidth());
		
		/*
		 * 
		 * 
		 * payment table
		 * 
		 * 
		 * */
		
		TableView<Payment> payTable = new TableView<Payment>();

		TableColumn<Payment, Double> payno = new TableColumn<>("#");
		payno.setMinWidth(40);
		payno.setCellValueFactory(new PropertyValueFactory<>("paymentNo"));
		
		TableColumn<Payment, String> payType = new TableColumn<>("Payment Type");
		payType.setMinWidth(150);
		payType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
		
		TableColumn<Payment, LocalDate> payDate = new TableColumn<>("Payment LocalDate");
		payDate.setMinWidth(150);
		payDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
		
		
		TableColumn<Payment, Double> payAmnt = new TableColumn<>("Amount Payed");
		payAmnt.setMinWidth(80);
		payAmnt.setCellValueFactory(new PropertyValueFactory<>("paymentAmnt"));
		
		payTable.getColumns().addAll(payno, payType, payDate, payAmnt);
		
		
		
		/*
		AnchorPane three = new AnchorPane();
		three.getChildren().addAll(payTable, paybuttons);
		
		three.setBottomAnchor(paybuttons, 10.0);
		three.setLeftAnchor(paybuttons, 10.0);
		three.setTopAnchor(payTable, 0.0);
		three.setLeftAnchor(payTable, 10.0);
		*/
		
		
		/*
		payTable.setMinWidth(payno.getWidth() + payType.getWidth() + payDate.getWidth() + payAmnt.getWidth());
		payTable.setMaxWidth(payno.getWidth() + payType.getWidth() + payDate.getWidth() + payAmnt.getWidth());
		three.setMinWidth(payno.getWidth() + payType.getWidth() + payDate.getWidth() + payAmnt.getWidth());
		three.setMaxWidth(payno.getWidth() + payType.getWidth() + payDate.getWidth() + payAmnt.getWidth());
		
		/*
		 * 
		 * 
		 * stuff that needs to be at the bottom
		 * 
		 * 
		 * */

		//buttons need to be at the bottom cause they need to see all tables
		FlowPane invoicebuttons = new FlowPane();
		invoicebuttons.setPadding(new Insets(5,0,0,0));
		ImageView invoicebut[] = new ImageView[1];
		invoicebut[0] = new ImageView(
			new Image("/images/InvoiceAddButton.png")
		);
		invoicebut[0].setOnMouseClicked(e->{
			cont.addClicked(0, invoiceTable, procedureTable, payTable);

		});
		
		invoicebuttons.getChildren().addAll(invoicebut[0]);
		
		FlowPane procbuttons = new FlowPane();
		procbuttons.setPadding(new Insets(5,0,0,0));
		procbuttons.setHgap(5);
		ImageView procbut[] = new ImageView[2];
		procbut[0] = new ImageView(
			new Image("/images/ProcedureAddButton.png")
		);
		procbut[0].setOnMouseClicked(e->{
			cont.addClicked(1, invoiceTable, procedureTable, payTable);

		});
		
		procbut[1] = new ImageView(
				new Image("/images/ProcedureEditButton.png")
		);
		procbut[1].setOnMouseClicked(e->{
			cont.editClicked(1,invoiceTable, procedureTable, payTable);
		});
		
		procbuttons.getChildren().addAll(procbut[0], procbut[1]);
		
		FlowPane paybuttons = new FlowPane();
		paybuttons.setPadding(new Insets(5,0,0,0));
		paybuttons.setHgap(5);
		ImageView paybut[] = new ImageView[2];
		paybut[0] = new ImageView(
			new Image("/images/PayAddButton.png")
		);
		paybut[0].setOnMouseClicked(e->{
			cont.addClicked(2, invoiceTable, procedureTable, payTable);

			
		});
		paybut[1] = new ImageView(
			new Image("/images/PayEditButton.png")
		);
		paybut[1].setOnMouseClicked(e->{
			cont.editClicked(2,invoiceTable, procedureTable, payTable);
		});
		
		paybuttons.getChildren().addAll(paybut[0], paybut[1]);
		
		BorderPane one = new BorderPane();
		one.setCenter(invoiceTable);
		one.setBottom(invoicebuttons);
		one.setPadding(new Insets(0,0,5,0));
		one.setAlignment(invoiceTable,Pos.TOP_LEFT);
		one.setAlignment(invoicebuttons, Pos.BOTTOM_LEFT);
		
		BorderPane two = new BorderPane();
		two.setCenter(procedureTable);
		two.setBottom(procbuttons);
		two.setPadding(new Insets(0,0,5,5));
		two.setAlignment(procedureTable,Pos.TOP_LEFT);
		two.setAlignment(procbuttons, Pos.BOTTOM_LEFT);
		
		BorderPane three = new BorderPane();
		three.setCenter(payTable);
		three.setBottom(paybuttons);
		three.setPadding(new Insets(0,0,5,5));
		three.setAlignment(payTable,Pos.TOP_LEFT);
		three.setAlignment(paybuttons, Pos.BOTTOM_LEFT);
		
		invoiceTable.setItems(cont.setItems(invoiceTable));
		invoiceTable.getSelectionModel().selectFirst();
		procedureTable.setItems(cont.setItemsproc(procedureTable, invoiceTable));
		procedureTable.getSelectionModel().selectFirst();
		payTable.setItems(cont.setItemspay(payTable, invoiceTable));
		payTable.getSelectionModel().selectFirst();


		invoiceTable.setOnMouseClicked(e-> {
			cont.invoiceTableclicked(invoiceTable, procedureTable, payTable);
		});
		
		
		allscreen.getChildren().addAll(back, one, two, three);
		Scene scene = new Scene(allscreen);
		c.window.setScene(scene);
		
	}
	
}

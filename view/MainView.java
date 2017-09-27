package view;
import controller.MainViewController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import utils.Components;

public class MainView {

	
	private BorderPane borderpane = new BorderPane();
	private Components components;
	public MainView(Components c){
		components = c;
		start();
	}
	
	public void start(){
		
		MainViewController cont = new MainViewController(components);
		
		FlowPane flowpane = new FlowPane();
		flowpane.setPadding(new Insets(10,10,10,10));
		flowpane.setAlignment(Pos.CENTER);
		flowpane.setVgap(50);
		flowpane.setHgap(50);
		
		ImageView pages[] = new ImageView[4];
		pages[0] = new ImageView(
			new Image("/images/logout.png")
		);
		pages[0].setOnMouseClicked(e->{
			cont.logout();
		});
		
		pages[3] = new ImageView(
				new Image("/images/changeDetails.png")
			);
			pages[3].setOnMouseClicked(e->{
				cont.changeDetails();
			});
		
		pages[1] = new ImageView(
				new Image("/images/patients.png")
			);
		pages[1].setOnMouseClicked(e->{
				cont.managePatients();
		});
			
		pages[2] = new ImageView(
					new Image("/images/report.png")
				);
		pages[2].setOnMouseClicked(e->{
					cont.report();
				});
		
		
	
		flowpane.getChildren().addAll(pages[0],pages[3], pages[1], pages[2]);
		
		Scene scene = new Scene(flowpane);
		
		
		components.window.setScene(scene);
		
		
	}
	
}

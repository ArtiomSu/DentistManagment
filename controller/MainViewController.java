package controller;

import utils.Components;
import utils.Load_save;
import utils.dentistEditViewAndController.DentistEditView;
import utils.reportViewAndController.ReportView;
import view.LogonView;
import view.PatientsView;

public class MainViewController {

	private Components c;
	
	public MainViewController(Components com){
		c = com;
	}
	
	public void logout(){
		LogonView l = new LogonView(c);
	}
	
	public void managePatients(){
		PatientsView p = new PatientsView(c);
	}
	
	public void report(){
		
		ReportView v = new ReportView(c);
		
	}
	
	public void changeDetails(){
		DentistEditView v = new DentistEditView(c);
	}
	
	
	
}

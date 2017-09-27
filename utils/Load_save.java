package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import mainApp.Dentist;
import mainApp.Invoice;
import mainApp.Patient;
import mainApp.Payment;
import mainApp.Procedure;

public class Load_save {

	Components c;
	
	public Load_save(Components com){
		c = com;
	}
	
	
	public void save(){
		try {
	         FileOutputStream fileOut = new FileOutputStream("./data/data.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(c.dentistList);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in data.ser");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	   }
	
	//since serializable doesn't save static variables they will be saved in another file 
	public void saveStatic(){
		
		int staticDentist = Dentist.getNextDentistNo();
		
		
		int staticPatient = Patient.getNextPatientNo();
		
		
		
		int staticInvoice = Invoice.getNextInvoiceNo();

		int staticPayment = Payment.getNextPaymentNo();
		
		int staticProcedure = Procedure.getNextProcNo();
	
	
		
		try {
            FileWriter fileWriter =
                new FileWriter("./data/StaticVariables.vars");
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);
            bufferedWriter.write(""+staticDentist);
            bufferedWriter.newLine();
            bufferedWriter.write(""+staticPatient);
            bufferedWriter.newLine();
            bufferedWriter.write(""+staticInvoice);
            bufferedWriter.newLine();
            bufferedWriter.write(""+staticPayment);
            bufferedWriter.newLine();
            bufferedWriter.write(""+staticProcedure);
            bufferedWriter.newLine();

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file");
          
        }
		
		
		
		
	}

	public int read(){
		try {
			FileInputStream fileIn = new FileInputStream("./data/data.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			c.dentistList = (ArrayList<Dentist>)in.readObject();
			in.close();
			fileIn.close();
		} catch(IOException i){
			i.printStackTrace();
			return 1;
		} catch(ClassNotFoundException ex){
			ex.printStackTrace();
			System.out.println("File messed up");
			c.dentistList = new ArrayList<Dentist>();
			return 2;
		}
		return 0;
	}
	
	public int readStatic(){
		int status = 0;
		
		int data[] = new int[5];
		
		String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader("./data/StaticVariables.vars");

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            int i = 0;
            while((line = bufferedReader.readLine()) != null && i < 5) {
                try{
            	data[i] = Integer.parseInt(line);
            	i++;
                }
                catch(Exception ex){
                	c.dentistList = new ArrayList<Dentist>();
                	return 2; //for messed up file
                	
                }
            			
                }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file");
            return 1;
        }
        catch(IOException ex) {
            System.out.println("Error reading file");    
            return 3;
            
        }
        
		Dentist.setNextDentistNo(data[0]);
		
		Patient.setNextPatientNo(data[1]);
		
	
		Invoice.setNextInvoiceNo(data[2]);

		Payment.setNextPaymentNo(data[3]);
		
		Procedure.setNextProcNo(data[4]);
	
		
		
		return status;
	}

}
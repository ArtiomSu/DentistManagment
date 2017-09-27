package utils;

import java.time.LocalDate;

import java.util.Locale;

import mainApp.Dentist;
import mainApp.Invoice;
import mainApp.Patient;
import mainApp.Payment;
import mainApp.Procedure;

public class GenData {
	
	Components c;
	public GenData(Components com){
		c = com;
		Locale ireland = new Locale("en","IE");
		Locale.setDefault(ireland);
	}
	
	public void genDentists(){
		
		Hashes hash = new Hashes();
		Dentist one = new Dentist();
		String add[] = new String[4];
		add[0] = "52 house";
		add[1] = "Noice street";
		add[2] = "london";
		add[3] = "England";
		one.setAddress(add);
		one.setFirstName("bob");
		one.setLastName("mcsweenery");
		one.setTelepnone("00892258952");
		one.setEmail("bob@gmail.com");
		one.setUsername("bob");
		one.setPassword(hash.hashWord("password"));
		one.setSalt(hash.getSalt());
		
		
		Patient p = new Patient();
		p.setFirstName("bobby");
		p.setLastName("Mc Doogy");
		p.setAddress(add);
		p.setTelepnone("0012544455888");
		p.setEmail("bobby@gmail.com");
		
		LocalDate d = LocalDate.now();
		Invoice i = new Invoice(d);
		Procedure proc = new Procedure("install teeth", 250);
		Payment pay = new Payment(50, "Cash");
		
		i.addProcedure(proc);
		i.addPayment(pay);
		p.addInvoice(i);
		
		LocalDate dd = LocalDate.now();
		Invoice ii = new Invoice(dd);
		Procedure procd = new Procedure("spray paint teeth gold", 300);
		Payment payd = new Payment(90, "credit");
		
		ii.addProcedure(procd);
		ii.addPayment(payd);
		p.addInvoice(ii);
		
		LocalDate ddd = LocalDate.now();
		Invoice iii = new Invoice(ddd);
		Procedure procdd = new Procedure("install vampire fangs", 300);
		
		
		iii.addProcedure(procdd);
		p.addInvoice(iii);
		
		one.AddPatient(p);
	
		
		p = new Patient();
		p.setFirstName("kimmy");
		p.setLastName("Mc james");
		p.setAddress(add);
		p.setTelepnone("0012548898558");
		p.setEmail("Kimmy@gmail.com");
		
		d = LocalDate.now();
		i = new Invoice(d);
		proc = new Procedure("repair gums", 500);
		pay = new Payment(230, "Check");
		
		i.addProcedure(proc);
		i.addPayment(pay);
		p.addInvoice(i);
		one.AddPatient(p);
		
		p = new Patient();
		p.setFirstName("hicle");
		p.setLastName("Mc prick");
		p.setAddress(add);
		p.setTelepnone("00125555898558");
		p.setEmail("hivkle@gmail.com");
		
		
		one.AddPatient(p);
		
		
		
		c.dentistList.add(one);
		
		hash = new Hashes();
		
		Dentist two = new Dentist();
		add = new String[4];
		add[0] = "51 app";
		add[1] = "under bridge";
		add[2] = "san fran";
		add[3] = "America";
		two.setAddress(add);
		two.setFirstName("john");
		two.setLastName("sleeps");
		two.setTelepnone("008923433952");
		two.setEmail("john@gmail.com");
		two.setUsername("john");
		two.setPassword(hash.hashWord("password"));
		two.setSalt(hash.getSalt());
		
		c.dentistList.add(two);
		
		hash = new Hashes();
		
		Dentist thr = new Dentist();
		add = new String[4];
		add[0] = "toyota car";
		add[1] = "outside";
		add[2] = "berlin";
		add[3] = "germany";
		thr.setAddress(add);
		thr.setFirstName("ben");
		thr.setLastName("hen");
		thr.setTelepnone("0089222223952");
		thr.setEmail("ben@gmail.com");
		thr.setUsername("ben");
		thr.setPassword(hash.hashWord("password"));
		thr.setSalt(hash.getSalt());
		
		c.dentistList.add(thr);
		
		
		
		
		
	}
	
	
	
	
	

}

package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandlerForDB {

	public DateHandlerForDB(){
		
	}
	
	
	public Date getDate(){
		Date parsedDate = null;
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String dateInString = new java.text.SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss").format(cal.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
		try {
			parsedDate = formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return parsedDate;
		
	}
	
	
	public String convertDate(Date d){
		
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
		String date = formatter.format(d);
		return date;
	}
	
	public Date getDateFromString(String s){
		Date parsedDate = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
		try {
			parsedDate = formatter.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return parsedDate;
		
	}
	public Date parseDate(Date d){
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
		String dateInString = new java.text.SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss").format(cal.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd/MM/yyyy/hh:mm:ss");
		try {
			d = formatter.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return d;
		
	}
}

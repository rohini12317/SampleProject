package frameworks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class UtilityMethods {
	public static HashMap<String,String> getDateparts(Date dt){
		HashMap<String,String> dateparts=new HashMap<>();
		Calendar c=Calendar.getInstance();
		c.setTime(dt);
		dateparts.put("MONTH_NUMBER",String.valueOf(c.get(Calendar.MONTH)+1) );
		dateparts.put("MONTH_NAME",c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) );
		dateparts.put("YEAR",String.valueOf(c.get(Calendar.YEAR)) );
		dateparts.put("DAY_NUMBER",String.valueOf(c.get(Calendar.DAY_OF_MONTH)) );
		dateparts.put("DAY_NAME",c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH) );
		
		return dateparts;
	}
	public static void getfuturedate_businessdays(int numberofdays) {
		Date dt=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(dt);
		for(int i=0;i<=numberofdays;i--) {
		c.add(Calendar.DAY_OF_MONTH, 1);
		int dayInweek=c.get(Calendar.DAY_OF_WEEK);
		if(dayInweek==1| dayInweek==7) {
			i++;
		}
		}
	}
	public static Date getfutureDate(Dateinterval interval,int total) {
        Date dt=new Date();
		
		Calendar c=Calendar.getInstance();
		c.setTime(dt);
		
		switch (interval) {
		case DAY:
			c.add(Calendar.DAY_OF_MONTH, total);
			break;
		case MONTH:
			c.add(Calendar.MONTH, total);
			break;
			
		case YEAR:
			c.add(Calendar.YEAR, total);
			break;
		case HOUR:
			c.add(Calendar.HOUR, total);
			break;
		case MINUTE:
			c.add(Calendar.MINUTE, total);
			break;
			
		default:
			c.add(Calendar.SECOND, total);
			break;
		}
		dt=c.getTime();
		return dt;	
	}
	public static Date getpastDate(Dateinterval interval,int total) {
		Date dt=new Date();
		
		Calendar c=Calendar.getInstance();
		c.setTime(dt);
		
		switch (interval) {
		case DAY:
			c.add(Calendar.DAY_OF_MONTH, -total);
			break;
		case MONTH:
			c.add(Calendar.MONTH, -total);
			break;
			
		case YEAR:
			c.add(Calendar.YEAR, -total);
			break;
		case HOUR:
			c.add(Calendar.HOUR, -total);
			break;
		case MINUTE:
			c.add(Calendar.MINUTE, -total);
			break;
			
		default:
			c.add(Calendar.SECOND, -total);
			break;
		}
		dt=c.getTime();
		return dt;
	}
	//to convert date from string type to date object type
	public static Date convertToDate(String date,String dateformat) {
		Date dt=null;
		SimpleDateFormat  sdf=new SimpleDateFormat(dateformat);
		try {
			 dt=sdf.parse(date);
			
		} catch (ParseException e) {
			System.out.println("given date is not in the valid format");
		}
		return dt;
		
	}
	//to set date to a particular format
	public static String formatdate(Date dt,String dateformat) {
		SimpleDateFormat  sdf=new SimpleDateFormat(dateformat);
		String formateddate=sdf.format(dt);
		return formateddate;
	}
	public static void staticwait(int milliseconds) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getCurrentTimestamp() {
		Date dt=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(dt);
		int hour=c.get(Calendar.HOUR);
		int min=c.get(Calendar.MINUTE);
		int second=c.get(Calendar.SECOND);
		int month=c.get(Calendar.MONTH);
		int day=c.get(Calendar.DAY_OF_MONTH);
		
		String timestamp=""+hour+min+second+month+day;
		return timestamp;
	}
	
	public static String  formatnumber(String number) {
		double dblNum=Double.parseDouble(number);
		int intNum=(int) dblNum;
		
		return String.valueOf(intNum);
		
	}
	public static String  formatlongnumber(String number) {
		double dblNum=Double.parseDouble(number);
		long intNum=(long) dblNum;
		
		return String.valueOf(intNum);
		
	}
	

}

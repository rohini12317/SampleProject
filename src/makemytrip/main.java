package makemytrip;

import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;

import frameworks.CommonMethods;
import frameworks.Commonmethods2;
import frameworks.Data;
import frameworks.Data2;
import frameworks.Dateinterval;
import frameworks.EventMethods;
import frameworks.Trip1;
import frameworks.UtilityMethods;

public class main {

	public static void main(String[] args) {
		String month="December 2020";
		String day="2";
		Trip1.launchapplication("chrome", "https://www.makemytrip.com");
		Data2.driver.manage().window().maximize();
		
        Trip1.clickdeparturedate();
        
        HashMap<String,String>dateparts=UtilityMethods.getDateparts(UtilityMethods.getfutureDate(Dateinterval.MONTH, 4));
        String monthyear=dateparts.get("MONTH_NAME")+" "+dateparts.get("YEAR");
        //boolean isMonthDisplayed=false;
        
        while(true) {
        	boolean ismonthyearexists=EventMethods.verify_element_existence(By.xpath("//div[text()='"+monthyear+"']"),3);
        	if(ismonthyearexists) {
        		String dayxpath="//div[contains(text(),'"+dateparts.get("MONTH_NAME")+"')]/following::p[text()='"+dateparts.get("DAY_NUMBER")+"']";
        		EventMethods.click_button(By.xpath(dayxpath), "click on day");
        		break;
        	}else {
        		EventMethods.click_button(By.xpath("//span[@role='button' and @aria-label='Next Month']"), "click on next");
        	}
        	
        }
        
        
        
        
        
        
        
        
        
      /*  while(true) {
		String text=Data2.driver.findElement(By.xpath("//div[@class='DayPicker-Caption'][1]")).getText();
		System.out.println(text);
		
		if(text.equals(month)) 
		{
			EventMethods.click_button(By.xpath("//div[contains(text(),'November 2020')]/following::p[text()='"+day+"']"), "selecting date");
			break;
		}else {
			EventMethods.click_button(By.xpath("//span[@role='button' and @aria-label='Next Month']"), "clicking next month button");
		}
        }*/
        
       
        
	}
	
}


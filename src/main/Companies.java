package main;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import frameworks.Commonmethods2;
import frameworks.Data2;
import frameworks.DataUtil;
import frameworks.Dateinterval;
import frameworks.EventMethods;
import frameworks.UtilityMethods;
import moduleMethods.Method1;

public class Companies {
static Properties configproperties;
	public static void main(String[] args) {
        Commonmethods2.closeExistingbrowsers();
	    
		String datafilepath=Data2.Project_Folder_path+"//config//newcogmento.xlsx";
		String sheetname="Create Company";
		String propfilepath=Data2.Project_Folder_path+"//configure//Masterdata.properties";
		try {
		configproperties=DataUtil.getpropertiesfile(propfilepath);
		}catch(IOException e) {
			System.out.println("unable to read the data from properties file");
			System.exit(0);
		}
		
         Commonmethods2.launchapplication(configproperties.getProperty("browser"),configproperties.getProperty("url"));
         Commonmethods2.login(configproperties.getProperty("username"), configproperties.getProperty("password"));
         
         Method1.navigate_to_companies_page();
         Method1.new_company();
         
         
         
         HashMap<String,String> tcData=DataUtil.getTestcaseData(datafilepath, sheetname, "CC_02");
        String companyname= enterCompanyinfo(tcData);
         UtilityMethods.staticwait(500);
         Method1.navigate_to_companies_page();
         Method1.companyexists(companyname);
         EventMethods.click_button(By.xpath("//span[text()='Calendar']"), "Clicking on calendar");
         EventMethods.click_button(By.xpath("//button[@class='ui linkedin button']"), "Click on new button");
         EventMethods.click_button(By.xpath("//div[@class='react-datepicker__input-container']"), "clicking on start date");
         if( EventMethods.verify_element_existence(By.xpath("//div[text()='December 2019']"))) {
         	System.out.println("Present month calendar is opened");
         }else {
         	System.out.println("Present month calendar is not opened");
         }
         HashMap<String,String>dateparts=UtilityMethods.getDateparts(UtilityMethods.getfutureDate(Dateinterval.MONTH,1 ));
         String monthyear=dateparts.get("MONTH_NAME")+" "+dateparts.get("YEAR");
         String dayxpath="//div[contains(text(),'"+dateparts.get("MONTH_NAME")+"')]/following::p[text()='"+dateparts.get("DAY_NUMBER")+"']";
 		EventMethods.click_button(By.xpath(dayxpath), "click on day");
        
        
      // EventMethods.click_button(By.xpath("//input[@class='calendarField react-datepicker-ignore-onclickoutside']"), "Clicking on end date");
        
        
        
        
        
        
        
         //Commonmethods2.logout();
	}
	
	
	
	@Test
	public static String enterCompanyinfo(HashMap<String,String> tcData) {
		 String companyname=tcData.get("COMPANY_NAME")+UtilityMethods.getCurrentTimestamp();
		 //System.out.println(companyname);
		 EventMethods.enter_value_in_the_textfield(By.xpath("//label[text()='Name']/following-sibling::div/descendant::input"), companyname, "Enter company name");
         Method1.selectaccesslevel(tcData.get("ACCESS_LEVEL"));
         
         EventMethods.enter_value_in_the_textfield(By.name("url"), tcData.get("WEBSITE"), "Enter Company url");
         EventMethods.enter_value_in_the_textfield(By.name("address"), tcData.get("ADDRESS"), "Enter Company address");
         EventMethods.enter_value_in_the_textfield(By.name("city"), tcData.get("CITY"), "Enter Company city");
         EventMethods.enter_value_in_the_textfield(By.name("state"), tcData.get("STATE"), "Enter Company state");
         EventMethods.enter_value_in_the_textfield(By.name("zip"),UtilityMethods.formatnumber(tcData.get("POSTAL_CODE")), "Enter Company pincode");
         
         Commonmethods2.Selectcountryfromlist(By.name("country"),By.xpath("//span[text()='"+tcData.get("COUNTRY")+"']"));
         Commonmethods2.Selectcountryfromlist(By.xpath("//div[@name='hint']"), By.xpath("(//span[text()='"+tcData.get("COUNTRY")+"'])[2]"));
         EventMethods.enter_value_in_the_textfield(By.xpath("//input[@name='value' and @placeholder='Number']"), UtilityMethods.formatlongnumber(tcData.get("PHONE")), "Enter phone number");
         EventMethods.click_button(By.xpath("//button[text()='Save']"),"Click on save button after writing companies info");
         String xpathforHeader="//div[text()='"+companyname+"']";
         UtilityMethods.staticwait(5000);
         if(EventMethods.verify_element_existence(By.xpath(xpathforHeader))) {
        	 System.out.println("new company is created succefully");
        	 Commonmethods2.capturescreenshot("Create Company");
         }else {
        	 System.out.println("new company is not created");
         }
         return companyname;
}
	
}

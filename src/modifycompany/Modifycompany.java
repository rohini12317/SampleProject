package modifycompany;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import frameworks.CommonMethods;
import frameworks.Commonmethods2;
import frameworks.Companysearchoperator;
import frameworks.Companysearchoptions;
import frameworks.Data2;
import frameworks.DataUtil;
import frameworks.EventMethods;
import frameworks.UtilityMethods;
import moduleMethods.Method1;

public class Modifycompany {
	static Properties configProperties;
	public static void main(String[] args) {
		
		Commonmethods2.closeExistingbrowsers();
		String propfilepath=Data2.Project_Folder_path+"//configure//Masterdata.properties";
		try {
		 configProperties=DataUtil.getpropertiesfile(propfilepath);
		} catch (IOException e) {
			System.out.println("unable to read the data from properties file");
			System.exit(0);
		}

		Commonmethods2.launchapplication(configProperties.getProperty("browser"), configProperties.getProperty("url"));
		Commonmethods2.login(configProperties.getProperty("username"), configProperties.getProperty("password"));

		Method1.navigate_to_companies_page();
		UtilityMethods.staticwait(200);
		String companyname="godrej";
		boolean performFilter=performfiltersearch(Companysearchoptions.NAME,Companysearchoperator.CONTAINS,companyname);
		if(performFilter) {
			System.out.println("search has returned the results");
			if(verifycompanysearchresult_byname_equality(companyname)) {
				System.out.println("results are as per the selection criteria");
			}else {
				System.out.println("results are not as per the selection criteria");
			}
		}else {
			System.out.println("search has not returned any records");
		}
		UtilityMethods.staticwait(100);
		Commonmethods2.logout();
		Commonmethods2.closebrowser();
	}
	
	public static boolean verifycompanysearchresult_byname_equality(String companyname) {
		boolean iscompanynameMatched=true;
		
		List<WebElement> allcompanynameElement= Data2.driver.findElements(By.xpath("//table/tbody/tr/td[2]"));
			for(WebElement CompanyElement:allcompanynameElement) {
				if(!CompanyElement.getText().equalsIgnoreCase(companyname)) {
					iscompanynameMatched=false;
					break;
				}else {
					System.out.println("");
				}
				
			}
			return iscompanynameMatched;
		}
	
	
public static boolean performfiltersearch(Companysearchoptions searchoption,Companysearchoperator searchoperator,String searchvalue) {
	//select searchoption
	if(showfilters()) {
		EventMethods.click_button(By.xpath("//div[@name='name']"), "click on search option");
		switch(searchoption) {
		case NAME:
			EventMethods.click_button(By.xpath("//div[@name='name']/input/following::span[text()='Name']"), "select name");
			break;
		case WEBSITE:
			EventMethods.click_button(By.xpath("//div[@name='name']/input/following::span[text()='Website']"), "select website");
			break;
		case PHONE:
			EventMethods.click_button(By.xpath("//div[@name='name']/input/following::span[text()='Phone']"), "select phone");
			break;
		case ADDRESS:
			EventMethods.click_button(By.xpath("//div[@name='name']/input/following::span[text()='Address']"), "select address");
			break;
		default:
			EventMethods.click_button(By.xpath("//div[@name='name']/input/following::span[text()='Tags']"), "select website");
			break;
		}
		//select operator
		EventMethods.click_button(By.xpath("//div[@name='operator']"), "click on operator to show all operators");
		switch(searchoperator) {
		case EQUALS:
			EventMethods.click_button(By.xpath("//span[text()='EQUALS']"), "select equals");
			break;
		case CONTAINS:
			EventMethods.click_button(By.xpath("//span[text()='Contains']"), "select contains");
			break;
		case STARTS_WITH:
			EventMethods.click_button(By.xpath("//span[text()='Starts with']"), "select starts with");
			break;
			default:
				EventMethods.click_button(By.xpath("//span[text()='Ends with']"), "select ends with");
				break;
		}
		EventMethods.enter_value_in_the_textfield(By.xpath("//input[@name='value']"), searchvalue,"Enter value in the search option" );
		EventMethods.click_button(By.xpath("//button/i[contains(@class,'search')]"), "click on search button after entering the related options");
		if(EventMethods.verify_element_existence(By.xpath("//td[contains(text()='"+searchvalue+"')]"))){
			return true;
			
		}else {
			return false;
		}
	}else {
		System.out.println("filter options are not populated");
		return false;
	}
}
		
		

public static boolean showfilters(){
	EventMethods.click_button(By.xpath("//button[text()='Show Filters']"), "clicking on show filters");
	
	if(EventMethods.verify_element_existence(By.xpath("//div[text()='Filter']"))) {
		return true;
	}else {
		return false;
	}

}
}


package moduleMethods;

import java.util.HashMap;

import org.openqa.selenium.By;

import frameworks.Data2;
import frameworks.EventMethods;

public class Method1 {
	public static void companyexists(String companyname) {
		 while(true) {
        	 if(EventMethods.verify_element_existence(By.xpath("//td[text()='"+companyname+"']"))) {
        		 System.out.println("created company is found");
            	 break;
        	 }else {
        		String rightlinkText=Data2.driver.findElement(By.xpath("//i[contains(@class,'right')]/parent::a")).getAttribute("class") ;
        		
        		if(rightlinkText.endsWith("disabled")) {
        			System.out.println("company is not found even after searching till last page");
        		}else {
        			EventMethods.click_button(By.xpath(""), "Click right arrow  to display next page companies ");
        		}
        	 }
        	
         }
	}
	
	public static void new_company() {
		EventMethods.click_button(By.xpath("//button[@class='ui linkedin button']//following::i[@class='edit icon']"),"new company");
		if(EventMethods.verify_element_existence(By.xpath("//div[text()='Create new Company']"))) {
			System.out.println("Application is navigated to new companies page");
		}else {
			System.out.println("Application is not navigated to ew companies page after clicking new button");
		}
	}
	
		
	
	public static void navigate_to_companies_page() {
		EventMethods.click_button(By.xpath("//a[@class='item']//following::i[@class='building icon']"),"navigating to companies page");
		
		if(EventMethods.verify_element_existence(By.xpath("//div[text()='Companies']"))) {;
		System.out.println("Application is naviated to create new company page");
		}else {
		System.out.println("Application is not navigated to companies page even after cicking companies button");
		}
	}
	
	public static void selectaccesslevel(String accesslevel) {
		
		String buttontext=Data2.driver.findElement(By.xpath("//label[text()='Access']/following-sibling::div/descendant::button")).getText();
		switch(accesslevel.toLowerCase()) {
		case "public":
			if(buttontext.contains("Private")) {
				EventMethods.click_button(By.xpath("//label[text()='Access']/following-sibling::div/descendant::button"),"Click on access button");
			}
			break;
			default:
				if(buttontext.contains("Public")) {
					EventMethods.click_button(By.xpath("//label[text()='Access']/following-sibling::div/descendant::button"),"click on access button");	
				}
				break;
		}	
	}
		
	

	public static void enterCompanyinfo(HashMap<String,String> tcData) {
		EventMethods.enter_value_in_the_textfield(By.xpath("//input[@name='name']//parent::div"), tcData.get("COMPANY_NAME"), "Enter company name");
		String accesslevel=tcData.get("ACCESS_LEVEL");
		
	}
}


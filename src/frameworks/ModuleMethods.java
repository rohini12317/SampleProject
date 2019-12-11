package frameworks;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;

public class ModuleMethods {
	
	
	public static void ClickNew() {
		
		Data.driver.findElement(By.xpath("//button[@class='ui linkedin button']//following::i[@class='edit icon']")).click();
		try {
			Data.driver.findElement(By.xpath("//div[text()='Create new Company']"));
			System.out.println("Application is navigated to new companies page");
			CommonMethods.captureScreenshot("New Companies page");
		}catch(NoSuchElementException e) {
			System.out.println("Application is not navigated to new companies page");
			CommonMethods.captureScreenshot("New Companies page");
			System.exit(0);
		}
	}
	
	
	public static void navigate_to_companies_page() {
		Data.driver.findElement(By.xpath("//a[@class='item']//following::i[@class='building icon']")).click();
		try {
			Data.driver.findElement(By.xpath("//div[text()='Companies']"));
			System.out.println("Application is navigated to companies page");
			CommonMethods.captureScreenshot("Companies page");
		}catch(NoSuchElementException e) {
			System.out.println("Application is not navigated to companies page");
			CommonMethods.captureScreenshot("Companies page");
			System.exit(0);
		}
	
	}
	
	
	
	
	
	
	}

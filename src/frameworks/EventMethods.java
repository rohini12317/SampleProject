package frameworks;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EventMethods {
	
	public static void highlight_element(WebElement element) {
		JavascriptExecutor jse=(JavascriptExecutor)Data2.driver;
		jse.executeScript("arguments[0].setAttribute('style','border:2px solid purple;');",element);
	}
	public static  boolean verify_element_existence(By locator,int timeout) {
		try {
		WebDriverWait wait=new WebDriverWait(Data2.driver,timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		wait.pollingEvery(Duration.ofMillis(200));
		return true;
		}catch(Exception nse) {
			return false;
			
		}
	}
	public static  boolean verify_element_existence(By locator) {
		try {
		Data2.driver.findElement(locator);
		return true;
		}catch(NoSuchElementException nse) {
			return false;
			
		}
	}
	
	public static void click_button(By locator,String stepname) {
		try {
			highlight_element(Data2.driver.findElement(locator));
			Data2.driver.findElement(locator).click();
		}catch(NoSuchElementException nse) {
			System.out.println( "unable to find the element");
		}catch(ElementNotInteractableException eni) {
			try {
				JavascriptExecutor jse=(JavascriptExecutor)Data2.driver;
				jse.executeScript("arguments[0].click();", Data2.driver.findElement(locator));
			}catch(Exception e) {
				System.out.println("invalid element");
				System.exit(0);
			}
			System.out.println("unable to enter the value as the element is hidden or disabled");
	}
	}
	
	public static void enter_value_in_the_textfield(By locator,String input,String stepname){
		try {
			highlight_element(Data2.driver.findElement(locator));
			Data2.driver.findElement(locator).sendKeys(input);
		}catch(NoSuchElementException nse) {
			System.out.println(stepname + "unable to find the element");
		}catch(ElementNotInteractableException eni) {
			System.out.println(stepname +"unable to enter the value as the element is hidden or disabled");
		}catch(IllegalArgumentException iae) {
			System.out.println(stepname + "nullvalue or invalid value is passing to the element");
		}	
	}

}

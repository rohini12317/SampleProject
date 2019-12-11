package frameworks;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Trip1 {
	public static void selectdate() {
		String text=Data2.driver.findElement(By.xpath("//div[@class='DayPicker-Caption'][1]")).getText();
		
		String month="December 2020";
		String day="2";
		String str="required date";
		if(str.contains(""))
		EventMethods.click_button(By.xpath("//span[@role='button' and @aria-label='Next Month']"), "clicking next month button");
		
		
	}
	public static void clickdeparturedate() {
		EventMethods.click_button(By.xpath("//div[@class='fsw_inputBox dates inactiveWidget ']"), "cliking departure button");
		
	    if(EventMethods.verify_element_existence(By.xpath("//div[text()='December 2019']"))) {
	    	System.out.println("present month calendar is dispayed");
	    }else {
	    	System.out.println("present month calendar is not dispayed");
	    }
		
	 }
	public static void launchapplication(String browser, String url) {
		try {
			switch (browser.trim().toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",Data2.CHROME_DRIVER_PATH);
				Data2.driver=new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", Data2.FIREFOX_DRIVER_PATH);
				Data2.driver=new FirefoxDriver();
				break;
			default:
				System.out.println("The given browser is invalid");
				System.exit(0);
				break;	
		}
			

            Data2.driver.get(url);
           
            Data2.driver.manage().timeouts().implicitlyWait(Data2.IMPLICIT_WAIT_IN_SECONDS,TimeUnit.SECONDS);
            Data2.driver.findElement(By.xpath("//a[@class='active makeFlex hrtlCenter column']"));
			System.out.println("Application is launched  and navigated to home page");

		}catch(IllegalStateException | SessionNotCreatedException sne) {
			System.out.println("unable to launch the browser since the exception has been found");
			System.exit(0);
		}catch(NoSuchElementException nse) {
			System.out.println("Application is not navigated to home page");
			System.exit(0);
		}
		}

}

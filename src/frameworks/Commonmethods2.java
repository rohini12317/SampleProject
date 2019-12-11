package frameworks;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;

public class Commonmethods2 {
	public static void logout() {
		 EventMethods.click_button(By.xpath("//i[@class='settings icon' and @aria-hidden='true']/parent::div"), "click on settings icon");
         EventMethods.click_button(By.xpath("//span[text()='Log Out']"), "click on logout button");
         
         if(EventMethods.verify_element_existence(By.name("email"))) {
        	 System.out.println("logged out successfully");
        	 
        	 Commonmethods2.capturescreenshot("logout");
         }else {
        	 System.out.println("didn't logged out properly");
         }
	}
	
	public static void closeExistingbrowsers() {
		String[] allprocesses= {"chrome.exe","firefox.exe"};
		for(String processname :allprocesses){
			try {
				Runtime.getRuntime().exec("taskkill/F/IM/"+processname);
			} catch (IOException e1) {
				
			}
		}
	}
	
	public static void capturescreenshot(String imagename) {
		String imagepath=Data2.Project_Folder_path+"\\Screenshots\\"+imagename+".png";
		
		TakesScreenshot ts=(TakesScreenshot) Data2.driver;
		
		File image=ts.getScreenshotAs(OutputType.FILE);
		//this method return the file object
		
		try {
			Files.move(image, new File(imagepath));
			//files.move(((takesscreenshot)data2.driver).getscreenshotas(outputtype.file).newfile(imagepath));
			//or we can write as below
		}catch(IOException e){
			System.out.println("unable to save the image:"+imagepath);
			
		}
		
	}
	public static void Selectcountryfromlist(By locatorForList,By locatorForCountry) {
		EventMethods.click_button(locatorForList,"Click on country list to populate countries");
		EventMethods.click_button(locatorForCountry, "Click on country list to populate countries");
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
            Data2.driver.findElement(By.name("email"));
			System.out.println("Application is launched  and navigated to login page");

		}catch(IllegalStateException | SessionNotCreatedException sne) {
			System.out.println("unable to launch the browser since the exception has been found");
			System.exit(0);
		}catch(NoSuchElementException nse) {
			System.out.println("Application is not navigated to login page");
			System.exit(0);
		}
		}

		public static void login(String username,String password) {
			EventMethods.enter_value_in_the_textfield(By.name("email"), username, "Enter username value");
			EventMethods.enter_value_in_the_textfield(By.name("password"), password, "Enter the password");
			EventMethods.click_button(By.xpath("//div[text()='Login']"),"Login button");
			EventMethods.verify_element_existence(By.xpath("//a[@class='item']//child::i[@class='home icon']"));
		}
		public static void closebrowser() {
			try {
			Data2.driver.close();
			Data2.driver.quit();
		}catch(WebDriverException we) {
			System.out.println("Browser is already closed");
		}
	}
}


package frameworks;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.io.Files;



public class CommonMethods {

	
	
	
	//first method is launchapplication//
		public static void launchapplication(String browser,String url) {
			
			browser = (browser==null)?"chrome":browser;
			switch (browser.toLowerCase()) {
			 case "chrome":
				 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ashok\\eclipse-workspace\\SeleniumProject\\Drivers\\chromedriver.exe");
				Data.driver=new ChromeDriver();
				break;
				
			 case "firefox":
				Data.driver=new FirefoxDriver();
				break;

			 case "Edge":
				Data.driver=new EdgeDriver();
			 default:
				System.out.println("The browser :"+browser+ "browser is not valid");
				System.exit(0);
			}
			
			Data.driver.get(url);
			
			Data.driver.manage().window().maximize();
			Data.driver.manage().timeouts().implicitlyWait(Data.IMPLICIT_TIME_OUT, TimeUnit.SECONDS);
			
		try {
			Data.driver.findElement(By.name("email"));
			System.out.println("Application is succesfully navigated to login page");
			captureScreenshot("login");
			
		}catch(NoSuchElementException e) {
			System.out.println("Application is not navigated to login page.unable to continue");
			System.exit(0);
		}
			
			
		}

		//second method is login//
		public static void login(String username, String password) {
			Data.driver.findElement(By.name("email")).sendKeys(username);;
			Data.driver.findElement(By.name("password")).sendKeys(password);;
			Data.driver.findElement(By.xpath("//div[text()='Login']")).click();
			
			
			try {
				Data.driver.findElement(By.xpath("//a[@class='item']//child::i[@class='home icon']"));
				System.out.println("Application is navigated to home page");
			}catch(NoSuchElementException e) {
				System.out.println("Invalid credentials");
				System.exit(0);
			}		
			
		}

		
		public static void captureScreenshot(String imageName) {
			String imagePath=System.getProperty("user.dir")+"\\Screenshots\\"+imageName+".png";
			
			TakesScreenshot ts=(TakesScreenshot) Data.driver;
			File image=ts.getScreenshotAs(OutputType.FILE);
			File dest=new File(imagePath);
			//as image path 
			try {
				Files.move(image, dest);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}




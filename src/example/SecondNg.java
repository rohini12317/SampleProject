package example;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import frameworks.ConfigurationClass;
import frameworks.Data2;

public class SecondNg extends ConfigurationClass{
	@Test
	public void Launchapplication() {
		System.setProperty("webdriver.chrome.driver", Data2.CHROME_DRIVER_PATH);
		WebDriver driver=new ChromeDriver();
		driver.get("https://ui.cogmento.com");
		driver.findElement(By.name("email")).sendKeys("nandasele69@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Test@1234");
		driver.findElement(By.xpath("//div[text()='Login']")).click();
		driver.findElement(By.xpath("//i[@class='settings icon' and @aria-hidden='true']/parent::div")).click();
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[text()='Log Out']")).click();
		driver.close();
		
	}
	
	
	
	//invocation count makes the method to loop for desired number of times
	@Test(priority = 0,invocationCount = 2)
	public void testcase1() {
		System.out.println("R");
	}
	//makes the test case disabled
	@Test(priority = 2)
	public void testcase2() {
		System.out.println("o");
		
	}
	

	@Test(priority = 5)
	public void testcase3() {
		System.out.println("h");
		
	}
    @Test(priority = 4)
	public void testcase4() {
		System.out.println("i");
	}
    @Test(priority = 3)
    public void testcase5() {
		System.out.println("n");
	}
    @Test()
    public void testcase6() {
		System.out.println("i");
	}


}

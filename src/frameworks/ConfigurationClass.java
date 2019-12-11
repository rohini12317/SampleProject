package frameworks;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class ConfigurationClass {
	
	
   @BeforeTest
   public void beforetest() {
	   System.out.println("before test");
   }
	
   @BeforeSuite
   public void beforesuite() {
	   System.out.println("before suite");
   }
   @BeforeClass
   public void beforeclass() {
	   System.out.println("before class");
   }
   @BeforeMethod
   public void beforemethod() {
	   System.out.println("before method");
   }
   @AfterTest
   public void aftertest() {
	   System.out.println("after test");
   }
	
   @AfterSuite
   public void aftersuite() {
	   System.out.println("after suite");
   }
   @AfterClass
   public void afterclass() {
	   System.out.println("after class");
   }
   @AfterMethod
   public void aftermethod() {
	   System.out.println("after method");
   }
	
	
	
}

package example;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Configuration {
	@AfterMethod
	public void xyzL() {
		System.out.println("good Morning");
		
	}
	@BeforeMethod
	public void xyz() {
		System.out.println("good evening");
		
	}
}

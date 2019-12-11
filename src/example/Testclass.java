package example;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import frameworks.ConfigurationClass;

public class Testclass extends ConfigurationClass {
	
	@DataProvider
	public Object[][] loginInfo(){
		Object[][] data=new Object[3][2];
		data[0][0]="chrome";
		data[0][1]="http://google.com";
		data[1][0]="firefox";
		data[1][1]="http://google.com";
		data[2][0]="chrome";
		data[2][1]="http://facebook.com";
		
		return data;
				
	}
	@Test(dataProvider = "loginInfo")
	public void Create(String browser,String url) {
		System.out.println();
	}
	
	@Test
	public void Create1() {
		System.out.println("dsgafd");
		
	}
	@Test
	public void Create2() {
		System.out.println("kjteu");
	}
	
}
	
	
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import com.google.common.io.Files;

import frameworks.CommonMethods;
import frameworks.Data;
import frameworks.Excelutil;
import frameworks.ModuleMethods;

public class CreateCompany {
	
	
		public static void main(String[] args) throws IOException {
			String dataFilepath=System.getProperty("user.dir")+"\\Data\\demo.xlsx";
			/*Create_company_with_new_status();
			createCompany();
			clicknew();
			String dataFilepath=System.getProperty("user.dir")+"\\Data\\parameters.xlsx";
			HashMap<String,String> tcData=Excelutil.getdata(dataFilepath, "CreateCompany", "CC_01");
			System.out.println(tcData.get("TC_ID"));
			
			FileInputStream fis=new FileInputStream(dataFilepath);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet datasheet=wb.getSheet("CreateCompany");
			Excelutil.get_col_position(datasheet,"TC_ID");*/
			//Excelutil.Createnewexcelfile(dataFilepath, "samplesheet");
			Excelutil.Writedatainntoexistingfile(dataFilepath, "sheet12");
			
			
			
			
		}
		public static void createCompany() {
		ModuleMethods.navigate_to_companies_page();
			
		}
		public static void clicknew() {
			
		ModuleMethods.ClickNew();	
			
		}
		
		public static void Create_company_with_new_status() {
			String tcid="CC_01";
			
			FileInputStream fis=null;
			Properties props=null;
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"//configure//Masterdata.properties");
			    props=new Properties();
				props.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CommonMethods.launchapplication(props.getProperty("browser"),props.getProperty("url"));
			CommonMethods.login(props.getProperty("username"), props.getProperty("password"));
			
		}
		

	}




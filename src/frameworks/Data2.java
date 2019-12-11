package frameworks;

import org.openqa.selenium.WebDriver;

public class Data2 {
	
	public static WebDriver driver;
    public static final String Project_Folder_path=System.getProperty("user.dir");
    public static final String  CHROME_DRIVER_PATH=Project_Folder_path+"\\Data\\chromedriver.exe";
    public static final String FIREFOX_DRIVER_PATH=Project_Folder_path+"\\Data\\geckodriver.exe";
    public static final int IMPLICIT_WAIT_IN_SECONDS=20;
}

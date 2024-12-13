package Base_Package;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Constants_package.constants;
import Utilities_Package.ScreenShots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Class {
	
	public WebDriver driver;
	Properties prop=new Properties();
	public Base_Class()
	{
		try {
			FileInputStream file=new FileInputStream(constants.CONFIG_FILEPATH);
			prop.load(file);
		} catch (Exception e) {
			
		}
	}
	
	public void Initilise(String browser,String url)
	{
		if(browser.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if
		(browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browser.equals("safari"))
		{
			WebDriverManager.safaridriver().setup();
			driver= new SafariDriver();
		}
		else if(browser.equals("opera"))
		{
			WebDriverManager.operadriver().setup();
		//	driver=new OperaDriver();
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(constants.IMPLICIT_WAIT));
		driver.manage().deleteAllCookies();
		}
	@BeforeMethod
	public void LaunchBrowser()
	{
	String Browser=prop.getProperty("browser");
	String Url=prop.getProperty("url");
	Initilise(Browser,Url);
	}
	@AfterMethod
	public void TerminateSession(ITestResult itestResult)
	{
		if(itestResult.getStatus()==ITestResult.FAILURE)
		{
			ScreenShots.TakeScreenShot(driver,itestResult.getName());
		}
	}

}

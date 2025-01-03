package Pages_Package;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Constants_package.constants;

public class Login_Page {
	public WebDriver driver;
	Properties prop=new Properties();
	
	public Login_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		try {
			FileInputStream file=new FileInputStream(constants.CONFIG_FILEPATH);
			prop.load(file);
		} catch (Exception e) {
			
		}
	}
	
	@FindBy(xpath="//input[@id='mobile']")
	WebElement usernameTextField;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement passwordTextField;
	
	@FindBy(xpath="//button[@class='btn btn-danger btn-lg btn-block']")
	WebElement loginButton;
	
	public void enterUsername(String username)
	{
		usernameTextField.sendKeys(username);
	}
	public void enterPassword(String password)
	{
		passwordTextField.sendKeys(password);
	}
	public void clickLoginButton()
	{
		loginButton.click();
	}
		
		public void login(String username,String password)
		{
			enterUsername(username);
			enterPassword(password);
			clickLoginButton();
			}
		public void login()
		{
			String username=prop.getProperty("username");
			String password=prop.getProperty("password");
		}
}

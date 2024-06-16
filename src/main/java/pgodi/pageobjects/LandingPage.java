package pgodi.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pgodi.AbstractComponents.AbstractComponent;

public class LandingPage  extends AbstractComponent{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	//PageFactory
	
//	WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys(username);
	@FindBy(id="userEmail")
	WebElement userEmail;
	

	
//	WebElement userPassword = driver.findElement(By.id("userPassword")).sendKeys(password);
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public String getErrorMessage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
			
	public ProductCatalogue loginToApplication(String email,String pwd) {
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
		
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	
}

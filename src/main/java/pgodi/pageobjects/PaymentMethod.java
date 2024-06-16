package pgodi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pgodi.AbstractComponents.AbstractComponent;

public class PaymentMethod extends AbstractComponent{
	
	WebDriver driver;
	public PaymentMethod(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(xpath ="//input[@placeholder='Select Country']")
	WebElement countryInput;
	
	@FindBy(xpath="//span[@class='ng-star-inserted']")
	List<WebElement> countryNameList;
	
	@FindBy(css=".btnn")
	WebElement placeOrderBtn;
	
//	  act.moveToElement(driver.findElement(By.cssSelector(".btnn"))).click().build().perform();
	
	By countryList = By.cssSelector(".list-group");
	By label = By.cssSelector("label[class='ng-star-inserted']");
	
	public void fillShippingInformation(String countryName) {
		countryInput.sendKeys(countryName);
		waitForElementToAppear(countryList);
		
		   for(int i=0;i<countryNameList.size();i++) {
			   String name = countryNameList.get(i).getText();
//			   System.out.println(i+name);
			   if (name.equalsIgnoreCase("India")) {
				   countryNameList.get(i).click();
			   }
		   }
		
		   clickButtonActionMethod(placeOrderBtn);
		   waitForElementToAppear(label);
	}
	

}

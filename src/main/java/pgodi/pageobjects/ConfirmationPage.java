package pgodi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pgodi.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
		
		WebDriver driver;
		public ConfirmationPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
			
		}
		
		@FindBy(css = "label[class='ng-star-inserted']")
		WebElement orderID;
		
		@FindBy(css = ".cartSection h3")
		WebElement itemsInCart;
		
		
		
		By labelText = By.cssSelector("label[class='ng-star-inserted']");

		
		//some comment	
		public String getOrderId() {
			waitForElementToAppear(labelText);
			return orderID.getText().replace("|","").trim();
		}
		
		
}

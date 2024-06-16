package pgodi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import pgodi.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
		
		WebDriver driver;
		public OrderPage(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
			
		}
		
		@FindBy(css = "label[class='ng-star-inserted']")
		WebElement orderID;
		
		@FindBy(css = "td:nth-child(3)")
		List<WebElement> itemsInOrderHistory;
		
		
		
		By labelText = By.cssSelector("label[class='ng-star-inserted']");

		
		public boolean verifyOrderDisplay(String productName) {
			boolean itemFound =itemsInOrderHistory.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
			return itemFound;

		}
			
		}



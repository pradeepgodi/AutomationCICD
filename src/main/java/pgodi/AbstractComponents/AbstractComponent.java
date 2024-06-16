package pgodi.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pgodi.pageobjects.ConfirmationPage;
import pgodi.pageobjects.OrderPage;

public class AbstractComponent {
		WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
	}
	
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(findBy));
		
	}
	
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
		
	}
	
	
	public void clickButtonActionMethod(WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).click().build().perform();
		
		
	}
	
	
	public ConfirmationPage goToOrderPage() {
		orderHeader.click();
		ConfirmationPage cp = new ConfirmationPage(driver);
		return cp;
		
	}
	
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage op = new OrderPage(driver);
		return op;
		
		
	}
	
	
}

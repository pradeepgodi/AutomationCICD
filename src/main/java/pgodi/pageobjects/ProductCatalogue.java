package pgodi.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pgodi.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	//PageFactory
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement clickOnCart;
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productsAddedInCart;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
		
	By productsBy =By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button:last-of-type");
	By toastMessage = By.cssSelector(".toast-message");
	By header1 = By.cssSelector("h1");

	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
	}
	

	public WebElement getProductByName(String productName) {	
		WebElement prod = getProductList().stream().filter(p -> p.findElement(By.cssSelector("h5"))
				.getText().equals(productName)).findFirst().orElse(null);
		
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod  = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);	
		waitForElementToDisappear(spinner);
		clickOnCart.click();
		waitForElementToAppear(header1);
		boolean itemAdded =productsAddedInCart.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
//		Assert.assertTrue(itemAdded,"Items not added successfully");
		if(itemAdded) {
		System.out.println("Item Added");
	}
		clickButtonActionMethod(checkout);
		
		
	}
	
	
}

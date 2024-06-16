package pgodi.pageobjects;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class LoginTestStandAlone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
	
		
		String productName="ZARA COAT 3";
		String url = "https://rahulshettyacademy.com/client/";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(url);
		
		
		System.out.println(driver.getTitle());
		
		String username= "gpradeep.srs@gmail.com";
		String password = "Paddi_1984";
		driver.findElement(By.id("userEmail")).sendKeys(username);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		
		List<WebElement> names= driver.findElements(By.cssSelector("h5:nth-child(1)"));		
		for (int i=0;i<names.size();i++) {
			System.out.println(names.get(i).getText());			
		}
	
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(p -> p.findElement(By.cssSelector("h5"))
				.getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector("button:last-of-type")).click();
				
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			
			driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
			
			List<WebElement> productsAddedInCart = driver.findElements(By.cssSelector(".cartSection h3"));			
			boolean itemAdded = productsAddedInCart.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		
			if(itemAdded) {
				System.out.println("Item Added");
			}
			
			Thread.sleep(5000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Checkout']")));
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//button[text()='Checkout']"))).click().build().perform();
//			act.moveToElement(driver.findElement(By.cssSelector(".totalRow button"))).click().build().perform();
			
//			driver.findElement(By.cssSelector(".totalRow button")).click();
			
			WebElement countryInput=driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
			countryInput.sendKeys("India");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
			
			List<WebElement> countryNameList = driver.findElements(By.xpath("//span[@class='ng-star-inserted']"));
			System.out.println(countryNameList.size());
			
		   for(int i=0;i<countryNameList.size();i++) {
			   String name = countryNameList.get(i).getText();
//			   System.out.println(i+name);
			   if (name.equalsIgnoreCase("India")) {
				   countryNameList.get(i).click();
			   }
		   }
		   
		   
		   act.moveToElement(driver.findElement(By.cssSelector(".btnn"))).click().build().perform();
			
		   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("label[class='ng-star-inserted']")));
		   String orderID = driver.findElement(By.cssSelector("label[class='ng-star-inserted']")).getText();
		   orderID=orderID.replace("|","").trim();
		   System.out.println("Order = "+orderID);
		

			
			driver.close();
		

	}

}

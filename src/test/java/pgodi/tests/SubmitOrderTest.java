package pgodi.tests;

import java.io.File;
import java.io.IOException;

//package pgodi;


import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pgodi.TestComponents.BaseTest;
import pgodi.pageobjects.LandingPage;
import pgodi.pageobjects.OrderPage;
import pgodi.pageobjects.ConfirmationPage;
import pgodi.pageobjects.PaymentMethod;
import pgodi.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	String username= "gpradeep.srs@gmail.com";
	String password = "Paddi_1984";	
	String productName="ZARA COAT 3";

	@Test(dataProvider = "getData",groups= {"Purchase"})
//	public void submitOrder(String username,String password, String productName ) throws IOException, InterruptedException {

	public void submitOrder(HashMap<String,String> input ) throws IOException, InterruptedException {
		System.out.println("came to submit order test method");
		ProductCatalogue pc =lp.loginToApplication( input.get("username"), input.get("password"));	
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("productName"));	
		PaymentMethod pm = new PaymentMethod(driver);
		pm.fillShippingInformation("India");	
		ConfirmationPage cp = new ConfirmationPage(driver);
		System.out.println(cp.getOrderId());
		

		
		
		
}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() {
		
		ProductCatalogue pc =lp.loginToApplication( username, password);
		OrderPage op = pc.goToOrdersPage();
		Assert.assertTrue(op.verifyOrderDisplay(productName));

		
	}
	
//Passing data as HashMap
	
//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String,String> map = new HashMap();
//		map.put("username", "gpradeep.srs@gmail.com");
//		map.put("password", "Paddi_1984");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap();
//		map1.put("username", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "ADDIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};

	// Passing data as objects	
		
//		@DataProvider
//		public Object[][] getData() {
		
//		return new Object[][] {{"anshika@gmail.com","Iamking@000","ADDIDAS ORIGINAL"},
//			{"gpradeep.srs@gmail.com","Paddi_1984","ZARA COAT 3"}};
//		
//	}
	
	
	//Passing data from JSON file as a HashMap list
	@DataProvider
	public Object[][] getData() throws IOException {
		
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\pgodi\\data\\PurchaseOrder.json";
		System.out.println(path);
		List<HashMap<String, String>> data= getJsonDataToMap(path);
		return new Object[] [] {{data.get(0)} , {data.get(1)}};
		
	}
	
	

	
}



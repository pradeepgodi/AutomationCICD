package pgodi.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pgodi.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage lp;
	String browserName;
	
	public WebDriver intitializeDriver() throws IOException {
		
		
		//properties class
		
		Properties prop = new Properties();
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\pgodi\\resources\\GlobalData.properties";
//		System.out.println(path);
		FileInputStream fis = new FileInputStream(path);
		
		
		prop.load(fis);
		browserName =System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
//		browserName = prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			System.out.println("Launching = "+browserName);		
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			driver.manage().window().setSize( new Dimension(1440,900));
			if (browserName.contains("headless")) {
				{	
					System.out.println("Launching headless= "+browserName);	
					options.addArguments("headless");
				}
			 options.addArguments("--remote-allow-origins=*");
			 driver = new ChromeDriver(options);

			}
	} else if (browserName.equalsIgnoreCase("firefox")) {
		System.out.println("Launching = "+browserName);
		System.setProperty("webdriver.gecko.driver", "E:\\selenium\\Installers\\drivers\\geckodriver-v0.33.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	 else if (browserName.equalsIgnoreCase("edge")) {
		System.out.println("Launching = "+browserName);
		 driver = new EdgeDriver();
	}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
}
	
	@BeforeMethod (alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		
		driver=intitializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();	
		return lp;
		
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		System.out.println("Closing browser ="+browserName);
		driver.close();
	}
	
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		
//		String path = System.getProperty("user.dir") + "\\src\\test\\java\\pgodi\\data\\PurchaseOrder.json";
//		System.out.println(path);
		String jsonContent = FileUtils.readFileToString(new File (jsonFilePath),StandardCharsets.UTF_8);
		
		//String to HashMap using Jackson databind
		ObjectMapper mapper = new ObjectMapper();		
		List<HashMap<String,String>>data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){;
		});
		return data;
	}
	
	
	public String  getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File  source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		File dest = new File(path);	
		FileUtils.copyFile(source, dest );
		return path;
		
	}
	
	
}

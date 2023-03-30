package dotdashcom.utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	private static final Logger LOGGER=LogManager.getLogger(DriverManager.class);
	public static WebDriver driver;
	
	
	
	
	
	public static WebDriver getDriver() {
		
		LOGGER.info("Loading a properties");
		Commonutils commonutils=new	Commonutils();
		commonutils.loadProperties();
		
		if(driver==null) {
			
			//WebDriverManager.chromedriver().setup();
			WebDriverManager.firefoxdriver().setup();
			LOGGER.info("Chrome browser is launching");
			//driver = new ChromeDriver();
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		return driver;
	
	}
	
}

package dotdashcom.pageobjectmodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import dotdashcom.constant.Constant;

public class AllPagesobject {

	WebDriver driver;
	
	public AllPagesobject(WebDriver driver) {
		
		this.driver=driver;
		
		}
	
	
	By Username=By.id("username");
	By password=By.id("password");
	By Loginbtn=By.xpath("//button[@type='submit']");
	By SuccessMsg=By.xpath("//h4[@class='subheader']");
	By Errormsg=By.xpath("//div[1]/div[1]/div[1]");
	By checkbox1=By.xpath("//input[@type='checkbox'][1]");
	By checkbox2=By.xpath("//input[@type='checkbox'][2]");
	By removebtn=By.xpath("//p[@id='message']");
	
	
	
	
	
	
	public void EnterValidUserlogin() {
		
		driver.findElement(Username).sendKeys("tomsmith");
		driver.findElement(password).sendKeys("SuperSecretPassword!");
	}
	
	public void clickonloginbutton() throws InterruptedException {
		
		
		Thread.sleep(100);
		driver.findElement(Loginbtn).click();
		
		}
	
	public void validatePage() {
		
		String Success=driver.findElement(SuccessMsg).getText();
		Assert.assertEquals(Success,Constant.ActualSuccessMsg);
		
		
	}

	public void EnterInValidUserlogin() {
		
		driver.findElement(Username).sendKeys("tomsmit12");
		driver.findElement(password).sendKeys("SuperSecretPassword!");
	}
	
	
public void validateErrorMsg() {
		
		String Error=driver.findElement(Errormsg).getText();
		Assert.assertTrue(Error.trim().contains("invalid"));
		
	}

public void clickoncheckboxes() throws InterruptedException {
	
	Thread.sleep(100);
	WebElement check1=driver.findElement(checkbox1);
	check1.click();
	WebElement check2=driver.findElement(checkbox2);
	
	
	Assert.assertEquals("true",check1.getAttribute("checked"));
	Assert.assertTrue(true, check2.getAttribute(""));
		
}



	
	
}

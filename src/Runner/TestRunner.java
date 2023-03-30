package Runner;

import java.time.Duration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import org.testng.Assert;
import dotdashcom.constant.Constant;
import dotdashcom.pageobjectmodel.AllPagesobject;
import dotdashcom.utilities.DriverManager;

public class TestRunner {

		WebDriver driver;
	AllPagesobject all;
	
	{
       driver=DriverManager.getDriver();
      all=new AllPagesobject(driver);
	
	}
	
	@AfterSuite	
	public void quitDriver() {
		
		driver.close();
	}
	
	@AfterTest
	public void quitmethod() throws InterruptedException {
		
		Thread.sleep(200);
		driver.close();
		
	}
	
	
	@Test(priority=1)
	public void Loginscenario() throws InterruptedException {
		
		
		driver.get(Constant.loginurl);
		all.EnterValidUserlogin();
		all.clickonloginbutton();
		all.validatePage();
		
	}
	

	@Test(priority=2)
	public void LoginInvalidscenario() throws InterruptedException {
		
		
		driver.get(Constant.loginurl);
		all.EnterInValidUserlogin();
		all.clickonloginbutton();
		all.validateErrorMsg();
		
	}
	
	@Test(priority=3)
	public void Checkboxscenario() throws InterruptedException {
		
		
		driver.get(Constant.Checkboxurl);
		all.clickoncheckboxes();
				
	}
	
	@Test(priority=4)
	public void ContextMenu() throws InterruptedException {
		
		
		driver.get(Constant.Contextmenuurl);
		Actions action = new Actions(driver);
        WebElement rightClickBox = driver.findElement(By.id("hot-spot"));
        action.contextClick(rightClickBox).perform();
        
        Thread.sleep(50);
        String alertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(alertMessage.trim(), "You selected a context menu");

        driver.switchTo().alert().accept();
				
	}
	
	@Test(priority=5)
	public void DragandDrop() {
		
		driver.get(Constant.draganddropurl);
		
		WebElement Element_A=driver.findElement(By.xpath("//div[2]/div[1]/div[1]/div[1]/div"));
		WebElement Element_B=driver.findElement(By.xpath("//div[2]/div[1]/div[1]/div[1]/div[2]"));
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String dragAndDropScript =
                "var source = arguments[0];" +
                        "var target = arguments[1];" +
                        "const dataTransfer = new DataTransfer();" +
                        "const dragstartEvent = new DragEvent('dragstart', { dataTransfer });" +
                        "source.dispatchEvent(dragstartEvent);" +
                        "target.dispatchEvent(new DragEvent('dragenter', { dataTransfer }));" +
                        "target.dispatchEvent(new DragEvent('dragover', { dataTransfer }));" +
                        "target.dispatchEvent(new DragEvent('drop', { dataTransfer }));" +
                        "source.dispatchEvent(new DragEvent('dragend', { dataTransfer }));";

        jsExecutor.executeScript(dragAndDropScript, Element_B, Element_A);
        
        Assert.assertEquals(Element_A.getText(), "B");
        Assert.assertEquals(Element_B.getText(), "A");
       
        
        				
	}
	
	@Test(priority=6)
	public void DropDown() {
		
		driver.get(Constant.dropdownurl);
		
				
		Select dropdown=new Select(driver.findElement(By.xpath("//select[@id='dropdown']")));
		dropdown.selectByIndex(1);
		
		WebElement Option1=driver.findElement(By.xpath("//select[@id='dropdown']/option[2]"));
		Assert.assertEquals("true",Option1.getAttribute("selected"));
		
		dropdown.selectByIndex(2);
		WebElement Option2=driver.findElement(By.xpath("//select[@id='dropdown']/option[3]"));
		Assert.assertEquals("true",Option2.getAttribute("selected"));
		
		
	}
	
	
	@Test(priority=7)
	public void DynamicContent() {
		
		driver.get(Constant.dynamiccontenturl);
		
		 
		for (int i = 1; i <= 3; i++) {
            String GivenContent = driver.findElement(By.xpath("//div[@id='content']/div["+i+"]/div[2]")).getText();
            driver.navigate().refresh();
            System.out.println("Content"+i+GivenContent);
            Assert.assertNotEquals(GivenContent,driver.getPageSource());

        }
	}
		

		@Test(priority=8)
		public void DynamicControls() {
			
			driver.get(Constant.dynamiccontrolurl);	
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			
			WebElement Removebtn=driver.findElement(By.xpath("//button[@type='button'][1]"));
			Removebtn.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
			String Msg=driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertTrue( Msg.contains("gone"));
			
		
			WebElement Enablebtn=driver.findElement(By.xpath("//form[@id='input-example']/button"));
			Enablebtn.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
			String Msg2=driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertTrue( Msg2.contains("enabled"));
			
			WebElement Addbtn=driver.findElement(By.xpath("//button[@type='button'][1]"));
			Addbtn.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
			String Msg3=driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertTrue( Msg3.contains("back"));
		
			WebElement disablebtn=driver.findElement(By.xpath("//form[@id='input-example']/button"));
			disablebtn.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='message']")));
			String Msg4=driver.findElement(By.xpath("//p[@id='message']")).getText();
			Assert.assertTrue( Msg4.contains("disabled"));
		
	}
		
	

		@Test(priority=9)
		public void DynamicLoading() {
			
			driver.get(Constant.dynamicloading);	
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement dynamicLoadingStart = driver.findElement(By.xpath("//div[@id='start']/button"));
	        dynamicLoadingStart.click();
	        WebElement until = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[text()='Hello World!']")));
	        Assert.assertTrue(until.getText().contains("Hello World!"));
	
		}
	
		@Test(priority=10)
		public void FileDownload() throws InterruptedException {
			
	
			
			Map<String, Object> chromPref = new HashMap<>();
	        chromPref.put("download.default_directory", System.getProperty("user.dir") + "\\src\\test\\resources\\downloads");
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("prefs", chromPref);

	        driver = new ChromeDriver(options);
			driver.get(Constant.FileDownload);	
	        WebElement fileToDwnload = driver.findElement(By.xpath("//a[text()='some-file.txt']"));
	        fileToDwnload.click();

	        String fileName = fileToDwnload.getText().trim();

	        Thread.sleep(2000);
	        File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\downloads\\" + fileName);
	        assert file.exists();
	        file.delete();	
			

}
		
		@Test(priority=11)
		public void FileUpload() throws InterruptedException {
			
			   Thread.sleep(100);
			driver.get("http://localhost:7080/upload");	
			WebElement fileUpload = driver.findElement(By.id("file-upload"));
	        fileUpload.sendKeys("C:\\Users\\bsethur\\Desktop\\Lavanya Assignment\\Test.txt");
	        
	        driver.findElement(By.xpath("//input[@class='button']")).click();
	        String msg=driver.findElement(By.xpath("//div[2]/div/div")).getText();
	        
	        Thread.sleep(100);
	        Assert.assertTrue(msg.contains("Uploaded"));
	        
	        	
		}	
		

		@Test(priority=12)
		public void FloatingMenu() throws InterruptedException {
			
			   Thread.sleep(100);
			driver.get(Constant.FloatingMenu);	
			WebElement floatingmenu=driver.findElement(By.xpath("//div[@id='menu']"));
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("window.scrollBy(0,250)", "");
            
            Assert.assertEquals(true, floatingmenu.isDisplayed());

		}

		@Test(priority=13)
		public void iframe() throws InterruptedException {
			
			   Thread.sleep(100);
			driver.get(Constant.iframeurl);	
			
			WebElement content=driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
			WebElement alertmsg=driver.findElement(By.xpath("//div[@class='tox-notification tox-notification--in tox-notification--warning']"));
			alertmsg.click();
			driver.switchTo().frame(content);
			
			WebElement texteditor=driver.findElement(By.xpath("//body[@id='tinymce']"));
			texteditor.clear();
			texteditor.sendKeys("Hello");
			Assert.assertEquals(texteditor.getText(), "Hello");
		
			
		}

	@Test(priority=14)
	public void MouseHover() throws InterruptedException {
		
		   Thread.sleep(100);
		driver.get(Constant.MouseHover);	
		
		WebElement image1=driver.findElement(By.xpath("//div[@class='example']/div[1]/img"));
		WebElement image2=driver.findElement(By.xpath("//div[@class='example']/div[2]/img"));
		WebElement image3=driver.findElement(By.xpath("//div[@class='example']/div[3]/img"));
		
		Actions action=new Actions(driver);
		action.moveToElement(image1).perform();
		WebElement addinfo1=driver.findElement(By.xpath("//div[@class='example']/div[1]/div[1]"));
		addinfo1.isDisplayed(); 
		
		action.moveToElement(image2).perform();
		WebElement addinfo2=driver.findElement(By.xpath("//div[@class='example']/div[2]/div[1]"));
		addinfo2.isDisplayed();
		
		action.moveToElement(image3).perform();
		WebElement addinfo3=driver.findElement(By.xpath("//div[@class='example']/div[3]/div[1]"));
		addinfo3.isDisplayed();
		
				
	}
	
	@Test(priority=15)
	public void JavaScriptAlert(){
		
		  
		driver.get(Constant.JavaScriptAlert);
		WebElement JsAlert=driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
		JsAlert.click();
		Alert msg=driver.switchTo().alert();
		Assert.assertEquals(msg.getText(), "I am a JS Alert");
		msg.accept();
		
		WebElement JsConfirm=driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
		JsConfirm.click();
		Alert msg2=driver.switchTo().alert();
		Assert.assertEquals(msg2.getText(), "I am a JS Confirm");
		msg.accept();
		
		WebElement JsPrompt=driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));
		JsPrompt.click();
		Alert msg3=driver.switchTo().alert();
		msg3.sendKeys("ok");
		Assert.assertEquals(msg3.getText(), "I am a JS prompt");
		msg.accept();
		

	}
	
	@Test(priority=16)
	public void JavaScriptError(){
		
		  
		driver.get(Constant.JavaScriptError);
		
		String errormsg=driver.findElement(By.xpath("//p")).getText();
		Assert.assertNotEquals(errormsg, "xyz");
		
	
	
	}	
	
	@Test(priority=17)
	public void windowhandlefunc(){
		
		  
		driver.get(Constant.Window);
		
		WebElement Clickhere=driver.findElement(By.xpath("//a[@href='/windows/new']"));
		Clickhere.click();
		Set<String> windows=driver.getWindowHandles();
		for(String window:windows) {
			
			driver.switchTo().window(window);
		}
	
		Assert.assertEquals(driver.getTitle(), "New Window");
	
	}	
	
	@Test(priority=18)
	public void NotificationMessage(){
		
		  
		driver.get(Constant.Notificationmsg);
		WebElement Clickhere=driver.findElement(By.xpath("//a[@href='/notification_message']"));
		Clickhere.click();
		
		String errormsg=driver.findElement(By.xpath("//div[@id='flash']")).getText();
		
	    if(errormsg.contains("Action unsuccesful"))
	    {
	    	Assert.assertTrue(true);
	    }else if(errormsg.contains("Action unsuccesful, please try again"))
	    {
	    	Assert.assertTrue(true);
	    }
	
	}
}
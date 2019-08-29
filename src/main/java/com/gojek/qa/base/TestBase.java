package com.gojek.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.gojwk.qa.util.TestUtil;
import com.gojwk.qa.util.WebEventListener;

 

public class TestBase {
	
	
	
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
    public static WebEventListener eventListener;
	
	public TestBase(){
		try {
			PageFactory.initElements(driver, this);
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/gojek"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void initialization(){
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")){             
			System.setProperty("webdriver.chrome.driver", "/Users/DELL 3/eclipse-workspace/Gojek/webdriver/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "/Users/DELL 3/eclipse-workspace/Gojek/webdriver/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
/*
		Set <String>handles = driver.getWindowHandles();//To handle multiple windows
		firstWinHandle = driver.getWindowHandle();
		handles.remove(firstWinHandle);
		String winHandle=handles.iterator().next();
		if (winHandle!=firstWinHandle){
		     secondWinHandle=winHandle;

		driver.switchTo().window(secondWinHandle);   //Switch to popup window
		Thread.sleep(2000);
		
	}	
	
	*/

	
	
	
	

}
	
}




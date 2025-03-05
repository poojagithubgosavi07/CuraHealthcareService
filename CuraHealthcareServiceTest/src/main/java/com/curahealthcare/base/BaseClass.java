package com.curahealthcare.base;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Properties;
	import java.util.concurrent.TimeUnit;

	import org.apache.log4j.xml.DOMConfigurator;
	import org.ietf.jgss.Oid;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
	import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

	import com.beust.jcommander.Parameter;
	import com.curahealthcare.actiondriver.Action;
	//import com.mystore.utility.ExtentManager;
import com.curahealthcare.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

	/**
	 * @author Hitendra: BaseClass is used to load the config file and Initialize 
	 * WebDriver
	 *  
	 */
	public class BaseClass {
		public static Properties prop;
		public static WebDriver driver;
		
		
		//do this change for parallel execution
		//Declare ThreadLocal Driver
		//public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
		
		//loadConfig method is to load the configuration
		@BeforeTest()
		public void loadConfig() throws IOException 
		{
			ExtentManager.setExtent();
			DOMConfigurator.configure("log4j.xml");
			
			try {
				prop = new Properties();
				System.out.println("super condtructor invoked");
				FileInputStream ip = new FileInputStream((System.getProperty("user.dir") + "\\Configuration\\config1.properties"));
				prop.load(ip);
				System.out.println("driver:" + driver);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		/*public static WebDriver getDriver()
		{
			//Get driver from threadLocalmap
			return driver.get();
		}*/
		
	public static void launchApp() {
			WebDriverManager.chromedriver().setup();
			String browserName = prop.getProperty("browser");
			
			if (browserName.equalsIgnoreCase("Chrome")) 
			{
				WebDriverManager.chromedriver().setup();
				// Set Browser to ThreadLocalMap
				driver = new ChromeDriver();
				//driver.set(new ChromeDriver());
			} else if (browserName.equalsIgnoreCase("FireFox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				//driver.set(new FirefoxDriver());
			} else if (browserName.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				//driver.set(new InternetExplorerDriver());
			}
			
			//driver = new ChromeDriver();
			//Maximize the screen
			driver.manage().window().maximize();
			//Delete all the cookies
			//getDriver().manage().deleteAllCookies();
			//Implicit TimeOuts
			Action.implicitWait(driver,100); //20 
			//PageLoad TimeOuts
			Action.pageLoadTimeOut(driver,600); //40 
			//Launching the URL
			//getDriver().get(prop.getProperty("url"));
			driver.get(prop.getProperty("url"));
		}

		@AfterTest()
		public void afterSuite() {
			ExtentManager.endReport();
		}
}


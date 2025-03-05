package com.curahealthcare.testcases;


import java.util.logging.Logger;

import org.apache.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.curahealthcare.pageobjects.HomePage;
import com.curahealthcare.pageobjects.LoginPage;
import com.curahealthcare.utility.Log;
import com.curahealthcare.base.BaseClass;

public class LoginPageTest extends BaseClass {

	 LoginPage lp;
	 HomePage hp;
	 
	 //private static final org.apache.log4j.Logger logger = LogManager.getLogger(LoginPageTest.class);
	
	 //@Parameters("browser")
	 @BeforeTest()
	 public void setup() 
	 {
		launchApp();  
		//BaseClassP.initialization();
	 }
	 
	 @Test(priority = 1)
	 public void verifyTitle()
	 {
		 lp = new LoginPage();
		 String title = lp.getcuraHealthcareAppTitle();
		 Assert.assertEquals(title, "CURA Healthcare Service");
	 }
	
	 @Test(priority = 2)
		public void loginTest() throws Throwable
		//public void loginTest() throws Throwable
		{
		
		Log.startTestCase("loginTest");
		lp = new LoginPage();
		Log.info("Enter Username and Password");
		//hp=lg.login(uname,pswd);
		hp=lp.login(prop.getProperty("username"), prop.getProperty("password"));
		//String actualURL=hp.validategetCurrURL();
		String actualURL = driver.getCurrentUrl();
		String expectedURL="https://katalon-demo-cura.herokuapp.com/#appointment";
		Log.info("Verifying if user is able to login");
		Assert.assertEquals(actualURL, expectedURL);
		Log.info("Login is Sucess");
		//Log.endTestCase("loginTest");
	}
	 
	@AfterTest()
	public void tearDown() {
		driver.quit();
	}

}

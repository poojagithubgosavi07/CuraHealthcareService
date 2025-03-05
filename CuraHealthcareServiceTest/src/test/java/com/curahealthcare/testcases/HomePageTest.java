package com.curahealthcare.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.curahealthcare.base.BaseClass;
import com.curahealthcare.pageobjects.HomePage;
import com.curahealthcare.pageobjects.LoginPage;

public class HomePageTest extends BaseClass {

	LoginPage lp;
	HomePage hp;
	
		//@Parameters("browser")
	 	@BeforeTest()
	 	public void setup() throws Throwable 
	 	{
	 		launchApp();  
		//BaseClass.initialization();
		lp = new LoginPage();
	    hp = lp.login(prop.getProperty("username"), prop.getProperty("password"));
		//hp=lp.login(uname,pswd);
	    //hp.validateHealthcareProgram();
	}
	
	@Test(priority=1)
	public void verifyFacility() throws InterruptedException
	{
	 	hp.validateFacility();
	 	Thread.sleep(2000);
	 	hp.validateHealthcareProgram();
	}
	 	
	@Test(priority=2)
	public void verifyVisitDate() throws InterruptedException
	{
		hp.validateVisitDate();
	}
	
	
	@Test(priority=3)
	public void verifyBookAppointment() throws Throwable
	{
		hp.validateBookAppointment();
		String acctualURL = driver.getCurrentUrl();
		String expectedURL = "https://katalon-demo-cura.herokuapp.com/appointment.php#summary";
		Assert.assertEquals(acctualURL, expectedURL);
	}
	
	
	/*@AfterTest()
	public void tearDown() {
		driver.quit();
	}*/

}

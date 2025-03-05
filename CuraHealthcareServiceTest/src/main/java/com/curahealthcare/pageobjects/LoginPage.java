package com.curahealthcare.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.curahealthcare.base.BaseClass;
import com.curahealthcare.pageobjects.HomePage;
import com.curahealthcare.actiondriver.Action;

public class LoginPage extends BaseClass {
	
	Action action= new Action();
	
	@FindBy(id="btn-make-appointment")
	WebElement makeAppointment;
	
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(css="input[type='password']")
	WebElement password;
	
	@FindBy(xpath="//button[@class='btn btn-default']")
	WebElement login;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public String getcuraHealthcareAppTitle()
	{
		return Action.getTitle(driver);
	}

	public HomePage login(String uname, String pswd) throws Throwable {
		//Action.scrollByVisibilityOfElement(getDriver(), userName);
		Action.click(driver,makeAppointment);
		Action.type(username, uname);
		Action.type(password, pswd);
		Action.click(driver, login);
		Thread.sleep(2000);
		return new HomePage();
	}
	
}

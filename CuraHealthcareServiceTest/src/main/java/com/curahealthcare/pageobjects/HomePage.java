package com.curahealthcare.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.curahealthcare.base.BaseClass;
import com.curahealthcare.actiondriver.Action;

public class HomePage extends BaseClass {
		
		//Action action= new Action();
		Actions act;
		Select so;
	
		@FindBy(css="select[name='facility']")
		WebElement clickFacility;
	
		@FindBy(xpath="//select[@id='combo_facility']/option[2]")
		WebElement facility;
		
		//select[@id='combo_facility']/option[2]
		//option[@value='Hongkong CURA Healthcare Center']
		
		@FindBy(css="input[id='radio_program_medicaid']")
		WebElement healthcareProgram;
		
		@FindBy(xpath="//div[@class='input-group date']/input")
		WebElement clickVisitDate;
		
		//textarea[placeholder='Comment']
		@FindBy(xpath="textarea[name='comment']")
		WebElement Comment;
		
		@FindBy(xpath="//button[@class='btn btn-default']")
		WebElement bookAppointment;
		
		public HomePage() {
			PageFactory.initElements(driver, this);
		}
		
		public void validateFacility() throws InterruptedException
		{
			//Thread.sleep(2000);
			Action.click(driver, clickFacility);
			Thread.sleep(2000);
			//Action.selectByIndex(facility, 1);
			//Thread.sleep(1000);
			act = new Actions(driver);
			act.keyDown(Keys.ARROW_DOWN);
			
			//act.keyDown(Keys.ARROW_DOWN).build().perform();
			Action.selectByIndex(facility, 1);
			act.click().build().perform();
			//Thread.sleep(4000);
			//Action.click(driver, facility);
			
			/*WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='combo_facility']/option[2]")));*/
			
			
			
			//Action.JSClick(driver, facility);
			
			//WebElement faci = driver.findElement(By.id("//select[@id='combo_facility']/option[2]"));
			//so = new Select(faci);
			//so.selectByIndex(1);
		}
		
		public void validateHealthcareProgram() throws InterruptedException
		{
			//Thread.sleep(2000);
			//act.click(healthcareProgram);
			Action.JSClick(driver, healthcareProgram);
		}
		
		public void validateVisitDate() throws InterruptedException
		{
			/*Thread.sleep(2000);
			Action.JSClick(driver, clickVisitDate);
			clickVisitDate.sendKeys("30/01/2025");*/
			
			Thread.sleep(2000);
			
			//Every data should be in String format.
			String monthandyear="May 2025";
			//String month="May";
			String date="20";
			
			//Method 1:
			Action.JSClick(driver, clickVisitDate);
			//clickVisitDate.sendKeys("30/01/2025");
			
			//Method 2:
			//select month & year
			while(true)
			{
				String currentMonthandYear=driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[2]")).getText();
				
				if(currentMonthandYear.equals(monthandyear))
				{
					break;
				}
				
				driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[3]")).click();  //next
				//driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[1]")).click(); //previous
			}
			
			//td[@class='active day']
			//driver.findElement(By.xpath("//div[@class='datepicker-days']/table/tbody/tr[4]/td[3]")).click();
			//Thread.sleep(2000);
			
			driver.findElement(By.xpath("//div[@class='datepicker-days']/table/tbody/tr[4]/td[3]")).click();
			//WebElement clickOnDate = driver.findElement(By.xpath("//div[@class='datepicker-days']/table/tbody/tr[4]/td[3]"));
			//Action.JSClick(driver, clickOnDate);
			act.keyDown(Comment, Keys.TAB).release().sendKeys(Comment,"Test").build().perform();			
			
			//date
			//div[@class='datepicker-days']/table/tbody/tr[4]/td[3]	
		}
		

			/*
			//.click(clickVisitDate);
			
			//div[@class='input-group date']/input
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='datepicker-days']/table/tbody/tr[4]/td[2]")));
			
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'datepicker-dropdown')]")));
			
			//div[@class='datepicker-days']/table/tbody/tr[4]/td[2]
			
			
			//div[contains(@class,'datepicker-dropdown')]
			//datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top
			
			/*String currentMonthYear = driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[2]")).getText();
			
			
			while(currentMonthYear.equals("December 2024"))
			{
				driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[3]")).click();
				driver.findElement(By.xpath("//div[@class='datepicker-days']/table/thead/tr[2]/th[2]")).getText();
			}
			
			driver.findElement(By.xpath("//div[@class='datepicker-days']/table/tbody/tr[4]/td[2]")).click(); //16 dec 2024 */
			
		
		public BookAppointmentPage validateBookAppointment() throws InterruptedException
		{
			Thread.sleep(2000);
			//act.keyDown(Comment, Keys.TAB).release().sendKeys(Comment,"Test").build().perform();
			/*Thread.sleep(2000);
			act.sendKeys(Comment, "Test");*/
			
			Action.click(driver, bookAppointment);
			Thread.sleep(2000);
			return new BookAppointmentPage();
		}
}

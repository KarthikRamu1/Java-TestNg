package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class DashboardPage extends SeleniumWebDriver{
	private By readyLocator = By.id("TicketSummary");
	private By Chaticon =By.id("chat_available_icon");
	private By Agentsidebar =By.id("sidebar");
	private By ChatDashboard =By.id("chat-dashboard");

	
	public DashboardPage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);	
		SeleniumWebDriver.isElementPresent(readyLocator);
	}
	
 public boolean chaticonsverification(WebDriver driver)
 {
	try {
		assertTrue(isElementPresent(driver,Chaticon), "Couldn't find chat availability icon" , driver, className, methodName);
		assertTrue(isElementPresent(driver,Agentsidebar), "Couldn't find Agent bar at bottomleft corner" , driver, className, methodName);
		assertTrue(isElementPresent(driver,ChatDashboard), "Couldn't find chat mini dashboard" , driver, className, methodName);
		
		
	}
	catch(Exception e){
		e.printStackTrace();
		
	}
	 
	return true;
	
 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

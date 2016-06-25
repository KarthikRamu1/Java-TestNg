package com.projectname.testutils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.projectname.testutils.seleniumutils.SeleniumWebDriver;

public class LivechatAdminPage extends SeleniumWebDriver {
	private By readyLocator = By.id("admin_search");
	private By Livechaticon= By.xpath("//div[text()='Live Chat']");
	private By Livetoggle = By.xpath("//div[@id='chat_header']//span[@class='toggle-container']//span[@class='toggle-handle']");
	
	
	
	public LivechatAdminPage(){
		PageFactory.initElements(SeleniumWebDriver.driver, this);	
		SeleniumWebDriver.isElementPresent(readyLocator);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

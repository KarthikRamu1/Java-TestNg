package com.projectname.testutils.seleniumutils;


import static org.testng.internal.EclipseInterface.ASSERT_LEFT;
import static org.testng.internal.EclipseInterface.ASSERT_MIDDLE;
import static org.testng.internal.EclipseInterface.ASSERT_RIGHT;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.bcel.verifier.exc.LoadingException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.projectname.testutils.genericutility.Config;
import com.projectname.testutils.genericutility.Constants;
import com.projectname.testutils.genericutility.ExceptionHandler;
import com.thoughtworks.selenium.Selenium;




public class SeleniumWebDriver {

	/**
	 * Creating the web driver object to be used
	*/
public SeleniumWebDriver(){
		}
	
	public SeleniumWebDriver(WebDriver driver) {
		SeleniumWebDriver.driver = driver;
	}
	public static WebDriver driver;
	public String className=null;
	public String methodName=null;
	static WebDriverWait wait;
	private static String returnString="";
	private static Boolean result = true;
	public By lblImageLoad=By.cssSelector("img.loading");
	private By Current_Item1=By.xpath("(//*[contains(@id,'CurrentItem')])[1]");	
	private By Current_Item2=By.xpath("(//*[contains(@id,'CurrentItem')])[2]");
	private By Current_Item3=By.xpath("(//*[contains(@id,'CurrentItem')])[3]");
	private By Current_Item4=By.xpath("(//*[contains(@id,'CurrentItem')])[4]");
	private By Current_Item5=By.xpath("(//*[contains(@id,'CurrentItem')])[5]");
	private By current_Item1=By.id("currentItem_1");
	private By current_Item2=By.id("currentItem_2");
	private By current_Item3=By.xpath("(//*[contains(@id,'currentItem')])[3]");
	private By current_Item4=By.xpath("(//*[contains(@id,'currentItem')])[4]");
    private By current_Item5=By.xpath("(//*[contains(@id,'currentItem')])[5]");
    private By current_Item6=By.xpath("(//*[contains(@id,'currentItem')])[6]");
    private By current_Item7=By.xpath("(//*[contains(@id,'currentItem')])[7]");
    private By current_Item8=By.xpath("(//*[contains(@id,'currentItem')])[8]");
    private By current_Item9=By.xpath("(//*[contains(@id,'currentItem')])[9]");
    private By current_Item10=By.xpath("(//*[contains(@id,'currentItem')])[10]");
    private  By  secondItem=By.xpath("//div[6]/div/div/div/div/div[2]");
    private  By  thirdItem=By.xpath("//div[7]/div/div/div/div/div[2]");

	//Time to wait for page to load
	private static int secondsToWait = 20;
	//Time to wait for page to load
		public static int DELAY_TIME = 2;
		public static int MINOR_WAIT_TIME = 7;
		public static int WAIT_TIME = 15;
		public static int AVG_WAIT_TIME_FOR_ELEMENT = 15;
		public static int MAX_WAIT_TIME_FOR_ELEMENT = 30;
		private Boolean requireToWrite = true; 

		
	public boolean waitForElement(WebDriver driver,final By ajaxElementName, int timeOutValue){
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, timeOutValue);
		try {
			ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.findElement(ajaxElementName).isDisplayed();
				}
			};
			wait.until(e);
		
		} catch (Exception e) {
			
			result = false;
			
		}
		 if(requireToWrite){
		      result=true;
		    }
          return result;

	}
	
	public boolean isAlertPresent(WebDriver driver)
    {
        try
        {
            driver.switchTo().alert();
            return true;
        }   // try
        catch (RuntimeException e)
        {
            return false;
        }   // catch
    }
	
	public boolean alertAccept(WebDriver driver)
    {
        try
        {
        	Alert alert = driver.switchTo().alert();
			alert.accept();
            return true;
        }   // try
        catch (RuntimeException e)
        {
            return false;
        }   // catch
    } 
	public boolean isVisible(WebDriver driver,By element){
		try {
			Dimension d = driver.findElement(element).getSize();
			result = (d.getHeight() > 0 && d.getWidth() > 0);
			//result= driver.findElement(element).isDisplayed();
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}
	
	/**
	 * Verify the presence of a text in the page.
	 * @param driver
	 * @param text
	 * @return true/false
	 * @throws IOException 
	 */
	public static boolean isTextPresent(WebDriver driver,String text){
		try {
			result= driver.findElement(By.tagName("body")).getText().contains(text);
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), text.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), text.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}
	
	/**getXpathcount
	 * get the count of the XPath ID's 
	 * @param By
	 * @param text
	 * @return true/false
	 * @throws IOException 
	 */
	 public int getXpathCount( WebDriver driver, By elementXpath)
     {
            int finalxPathCount=0;
            try{
                   List<WebElement> located_elements= driver.findElements(elementXpath);
                   for(@SuppressWarnings("unused") WebElement located_element: located_elements)
                                {
                                finalxPathCount++;
                                }
                   status = "Pass";
            }
            catch(Exception e)
            {
                   Assert.fail(e.getMessage());
                   status = "Fail";
                   new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementXpath.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
            }
            if(Config.requireToWrite){
    			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementXpath.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
    		}
            
                   return finalxPathCount;
            }

	 /**
		 * waitForElementToEnable
		 * function is used to wait until the elemnt is enabled 
		 * @param 		driver 
		 * @param       elementName
		 * @return		boolean
		 * @since  	     May 05, 2010
		 */	
		public boolean waitForElementToEnable(WebDriver driver,By elementName){
			boolean isEditable = false;
			try {
				for(int i=1; i<=120;i++){
					if (driver.findElement(elementName).isEnabled()){
						Thread.sleep(1000);
						continue;
					}else {
						isEditable = true;
						status = "Pass";
						break;
					}
				}
			} catch (Exception e) {
				status = "Fail";
				new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
			}
			if(Config.requireToWrite){
				logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
			}				
			
			return isEditable;
		} 
		public static int getElementCount(By element){
			int totalCount;			
			totalCount=driver.findElements(element).size();
			return totalCount;
		}
	 
	 
	/**
	 * Verify the presence of a element in the page. 
	 * @param By
	 * @param text
	 * @return true/false
	 * @throws IOException 
	 */
	public static boolean isElementPresent(By element){
		boolean exists = true;
		try {
			exists = (driver.findElements(element).size()!=0);
			if(exists == true){
			status = "Pass";}
			else{
				status = "Fail";
				
			}
			
		} catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return exists;
	}

	/**
	 * Wait for page to load
	 */
	public static void causeMinorTimeDelay() {
		driver.manage().timeouts()
				.implicitlyWait(Config.DELAY_TIME, TimeUnit.SECONDS);
	}

	/**
	 * Wait for page to load
	 */
	public static void causeTimeDelay() {
		try {
			int counter = 0;
			Thread.sleep(2000);
			while (true) {
				String ajaxIsComplete = ((JavascriptExecutor) driver)
						.executeScript("return Ajax.activeRequestCount")
						.toString();
				if (Integer.parseInt(ajaxIsComplete) == 0){
					break;
				}
				if (counter == 100){
					break;
				}
				Thread.sleep(100);
			}
		} catch (Exception e) {

		}

	}

	/**
	 * To Capture Screenshot
	 * @param driver
	 * @return
	 */
	public static File takeScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	public static boolean waitForElement(final By ajaxElementName, int timeOutValue){
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, timeOutValue);
		try {
			ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return driver.findElement(ajaxElementName).isDisplayed();
				}
			};
			wait.until(e);
			status = "Pass";
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;

	}
	
	/**
	 * To wait for element to load
	 * @param driver
	 * @return
	 */
	
	public  void waitForElementToLoad(WebDriver driver, By elementId)
			throws InterruptedException {
		int i = 0;
		while (!isElementPresent(driver,elementId)) {
			i++;
			Thread.sleep(3000);
			if (i == 9) {
				// Assert.fail("Time out :-CounldNotFind the Element With ID  : "+elementId
				// );
				break;
			}
		}
	
	}
	/**
	 * To type text on the field
	 * @param driver
	 * @return
	 */

	public boolean type(WebDriver driver,By elementLocator, String value) {
		try {
//			driver.findElement(elementLocator).clear();
		sendKeys(elementLocator, value);
			
		} catch (Exception e) {
			
			result = false;
		}
		return result;
	}

	/**
	 * To type text on the field
	 * @param driver
	 * @return
	 */

	public static boolean sendKeys(By elementLocator, String value) {
		try {
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.findElement(elementLocator).clear();
			driver.findElement(elementLocator).sendKeys(value);
			status = "Pass";
			result = true;
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementLocator.toString(), value, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementLocator.toString(), value, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}
	/**
	 * Verify the presence of a element in the page. 
	 * @param By
	 * @param text
	 * @return true/false
	 * @throws IOException 
	 */
	public boolean isElementPresent(WebDriver driver,By element){
	/*	boolean exists = true;
		try {
			exists = (driver.findElements(element).size()!=0);
			
		} catch (Exception e) {
			
			return false;
		}
		
		
		return exists;*/
		boolean exists = true;
		try {
			exists = (driver.findElements(element).size()!=0);
			if(exists == true){
			status = "Pass";}
			else{
				status = "Fail";
				
			}
			
		} catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return exists;
	}

	/**
	 * To click an element on the screen
	 * @param driver
	 * @return
	 */
	
	public boolean click(final By ajaxElementName){
		try {
			waitForElement(ajaxElementName, Config.AVG_WAIT_TIME_FOR_ELEMENT);
			if (isElementPresent(ajaxElementName)&&isVisible(driver, ajaxElementName) ) {
				driver.findElement(ajaxElementName).click();
				status = "Pass";
			} 
			
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}
	/**
	 * To click an element on the screen
	 * @param driver
	 * @return
	 */
	
	public boolean click(WebDriver driver,final By ajaxElementName){
		try {
			waitForElement(driver,ajaxElementName,  Config.AVG_WAIT_TIME_FOR_ELEMENT);
			if (isElementPresent(ajaxElementName)&& isVisible(driver, ajaxElementName)) {
				driver.findElement(ajaxElementName).click();
				result= true;
			} 
			
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
			
		}
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}
	
	/**
	 * To get css Attribute value of an element on the screen
	 * @param ajaxElementName- element Id
	 * @param cssAttribute- Attirbute for which value is needed
	 * @return
	 */
	public String getCssValue(By ajaxElementName, String cssAttribute){
		String  cssValue=null;
	try{
			WebElement newweb=driver.findElement(ajaxElementName);
			cssValue=newweb.getCssValue(cssAttribute);
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
			
		}
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return cssValue;
	}
	
	/**
	 * To verify the tabbing order of a page
	 * @param tabOrder- List of element ids in order of tab
	 * @return boolean
	 */
	public boolean verifyTabbingOrder(By[] tabOrder){
		 	for (By currentField: tabOrder ){    
	        WebElement expected = driver.findElement(currentField);
	        String assertMessage  = "Incorrect tab order when trying to tab to: "+currentField;
	        assertTrue(expected.equals( driver.switchTo().activeElement()), assertMessage, driver,className,methodName);
	        driver.switchTo().activeElement().sendKeys(Keys.TAB);
	    }
		return true;
	}
	/**
	 * isChecked function to verify if the AJAX based Checkbox is checked
	 * @param selenium
	 * @param ajaxCheckboxName (Name of the ajax Checkbox)
	 * @throws IOException 
	 * @throws MyException
	 * @since March 04, 2013
	 */
	
	public static boolean isChecked(final By ajaxCheckboxName){
		try{
			if (waitForElement(ajaxCheckboxName,
					Config.AVG_WAIT_TIME_FOR_ELEMENT)) {
				driver.findElement(ajaxCheckboxName).isSelected();
				boolean checkBoxStatus = driver.findElement(ajaxCheckboxName)
						.isSelected();
				if (checkBoxStatus) {
					status = "Pass";
					result = true;
				} else {
					status = "Fail";
					result = false;
				}
			} else {
				status = "Fail";
				result = false;
			}
		}
		catch (Exception e) {
			status = "fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxCheckboxName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxCheckboxName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}
 /**
	 * waitForPageLoad
	 * function is used to wait a page to load completely 
	 * @param 		selenium 
	 * @return		boolean
	 
	 * @since  	  OCt 31,2014
	 */
	
	public boolean waitForPageLoad(WebDriver driver) {
		try {
			
			
			int j = 0;
			try {
				/*while(!isElementPresent(driver,lblImageLoad) && i<(WAIT_TIME/2)){
					Thread.sleep(100);
					i++;
				}*/
				Thread.sleep(3000);
				if(isElementPresent(driver,lblImageLoad)){
					while(isElementPresent(driver,lblImageLoad) && j<(WAIT_TIME)){
						Thread.sleep(100);
						j++;
					}
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * To wait for page to load
	 * @param driver
	 * @return
	 */
	
	public static void waitForPageToLoad() {
		try {
			int counter = 0;
			
			while (true) {
				String readyState = ((JavascriptExecutor) driver)
						.executeScript("return document.readyState")
						.toString();

				//verify jquery loaded 
				int jQueryComplete =Integer.parseInt(((JavascriptExecutor) driver)
						.executeScript("return jQuery.active")
						.toString());
				
				if ((readyState.equals("complete")) && (jQueryComplete == 0))
					break;
				if (counter == secondsToWait){
					throw new LoadingException("PageNotLoadedException"); 
				}
				Thread.sleep(1000);
				counter++;
			}
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), empty, empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(Config.requireToWrite)
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), empty, empty, status, empty, getCallingMethodAndLineNumber()));		
	}

	

	/**
	 * To mouse over on an element
	 * @param driver
	 * @return
	 */
	
	public static void mouseOver(By element){
		try {
			WebElement we = driver.findElement(element);
			String code = "var fireOnThis = arguments[0];"
					+ "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initEvent( 'mouseover', true, true );"
					+ "fireOnThis.dispatchEvent(evObj);";
			((JavascriptExecutor) driver).executeScript(code, we);
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
	}

	/**
	 * To mouse over on an String element
	 * @param driver
	 * @return
	 */
	
	public static void mouseOver(String element){
		try {
			WebElement we = driver.findElement(By.id(element));

			String javaScript = "var evObj = document.createEvent('MouseEvents');" +
            "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
            "arguments[0].dispatchEvent(evObj);";

			((JavascriptExecutor)driver).executeScript(javaScript, we);
			
			//((JavascriptExecutor) driver).executeScript(code, element);
			
			status = "Pass";
		}catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), element.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
	}
	/**
	 * To select an option from the list
	 * @param driver
	 * @return
	 */
	public static boolean select(By listName, String valueForSelection){
		valueForSelection = valueForSelection != null ? valueForSelection
				.trim() : "";
		try {
			waitForElement(listName, Config.AVG_WAIT_TIME_FOR_ELEMENT);
			if (driver.findElement(listName).isDisplayed()) {
				Select elSelect = new Select(driver.findElement(listName));
				elSelect.selectByVisibleText(valueForSelection);
				status = "Pass";
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			status = "Fail";
			result = false;
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), listName.toString(), valueForSelection, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), listName.toString(), valueForSelection, status, empty, getCallingMethodAndLineNumber()));
		}
		return result;
	}

	/**
	 * To get the text of a field
	 * @param driver
	 * @return
	 */
	
	public static String getText(WebDriver driver,By elementName) throws ExceptionHandler, IOException {

		try {
				returnString=driver.findElement(elementName).getText();
				status = "Pass";
		}  catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return returnString;
	}

	/**
	 * To get the value of a field
	 * @param driver
	 * @return
	 */
	public static String getValue(WebDriver driver,By elementName){

		try {
			if (waitForElement(elementName, Config.AVG_WAIT_TIME_FOR_ELEMENT)) {
				returnString=driver.findElement(elementName).getAttribute("value");
				status = "Pass";
			} 
		} catch (Exception e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
		}
		if(Config.requireToWrite){
			logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
		}
		return returnString;
	}

	// Customized Assert block starts
	
	/**
	 * Asserts that a condition is true. If it isn't,
	 * an AssertionError, with the given message, is thrown.
	 * @param condition the condition to evaluate
	 * @param message the assertion error message
	 */
		 public static void assertTrue(boolean condition, String message, WebDriver driver,String className,String methodName) {
		    if(!condition) {
  	
			    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String workingdirectory = System.getProperty("user.dir");
				String scrpath=workingdirectory +"/custom-test-report/Failure_Screenshot/"+getCallingMethodAndLineNumber()+".jpg";
				File scrFile1 = new File(scrpath);
					
				try {
					FileUtils.copyFile(scrFile, scrFile1);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					
//				log.info("Customized Assert true block executed...Temprory function, Need to enhance if You wish scrrenshot in report. Failure screenshot in 'custom-test-report/Failure_Screenshot/AssertFailure.jpg");
					
//				if(Config.requireToWrite){
//					logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), empty, empty, status, scrpath, getCallingMethodAndLineNumber()));		
//			}
//				failNotEquals( Boolean.valueOf(condition), Boolean.TRUE, message);
				driver.close();
			    }
		}
			  
			  
		 static private void failNotEquals(Object actual , Object expected, String message ) {
			  Assert.fail(format(actual, expected, message));
		  }

		 static String format(Object actual, Object expected, String message) {
		    String formatted = "";
			    if (null != message) {
			      formatted = message + " ";
			    }
			    return formatted + ASSERT_LEFT + expected + ASSERT_MIDDLE + actual + ASSERT_RIGHT;
			  }
			  
	// Customized Assert block Ends
			  
			  
	// Customized Verify block starts
			
	  /**
	   * Asserts that a condition is true. If it isn't,
	   * an AssertionError, with the given message, is thrown.
	   * @param condition the condition to evaluate
	   * @param message the assertion error message
	   */
		 public static void verifyTrue(boolean condition, String message, WebDriver driver) {
		    if(!condition) {
			    	
		    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				String workingdirectory = System.getProperty("user.dir");
				File scrFile1 = new File(workingdirectory +"/custom-test-report/Failure_Screenshot/AssertFailure.jpg");
			try {
				FileUtils.copyFile(scrFile, scrFile1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
					
			//log.info("Customized Verify True block executed...Temprory function, Need to enhance if You wish scrrenshot in report. Failure screenshot in 'custom-test-report/Failure_Screenshot/AssertFailure.jpg");
					
			 }
		 }
			  
	// Customized Verify block Ends
	
	//Report Part
	private static final String deliminator = "####";
	private static final String empty = "";
	
	private static final String dot = ".";	
	
	private static String status = null;
	

	private static ITestResult logCustomMessage() {
		return Reporter.getCurrentTestResult();
	}
	
	/**
	 * used for get the calling method name with line number
	 * @return
	 */
	protected static String getCallingMethodAndLineNumber(){
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		
		String callingMethodWithLineNumber = stackTraceElements[3].getClassName() + dot + stackTraceElements[3].getMethodName() + dot + stackTraceElements[3].getLineNumber() ;
		
		return callingMethodWithLineNumber;
	}
	
	
	/**
	 * This method returns the current date and time in format HH-mm-ss.SSS
	 * 
	 * @return time - in the above mentioned format
	 */
	private static String getCurrentDateAndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		Date date = new Date();
		String time = sdf.format(date);
		return time;
	}
	
	/**
	 * used to get the custom attribute value
	 * @param operation
	 * @param elementLocator1
	 * @param optional
	 * @param status
	 * @param screenShot
	 * @param callingMethodAndLineNumber
	 * @return
	 */
	protected static String getCustomAttributeValue(String operation,String elementLocator1, String optional,String status, String screenShot, String callingMethodAndLineNumber){
		
		System.out.println(operation + deliminator + elementLocator1 + deliminator + optional + deliminator + status + deliminator + screenShot + deliminator + callingMethodAndLineNumber);
		
		return operation + deliminator + elementLocator1 + deliminator + optional + deliminator + status + deliminator + screenShot + deliminator + callingMethodAndLineNumber;
		
		
	}
	
	//End of code for reporting
	
	
	/**
	 * used for get the current method name
	 * @return
	 */
	public static String getCurrentMethodName(){
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		
		String currentMethodName = stackTraceElements[2].getMethodName();
		
		return currentMethodName;
	}
	
	public boolean selectValueFromAjaxList(WebDriver driver, By textboxName,
			String valueForSelection)  {
		
		valueForSelection = valueForSelection != null ? valueForSelection
				.trim() : "";
		int count = 0;
		if(valueForSelection.equals("")){
			return true;
		}
		boolean clicked = false;
		try {
			if (isElementPresent(driver, textboxName)) {
				Thread.sleep(3000);
				driver.findElement(textboxName).sendKeys("");
				driver.findElement(textboxName).clear();
				driver.findElement(textboxName).click();
				driver.findElement(textboxName).sendKeys(valueForSelection);
				driver.findElement(textboxName).sendKeys(Keys.DOWN);
				driver.findElement(textboxName).sendKeys(Keys.UP);
				waitForPageLoad(driver);
				
//				waitForPageLoad(driver);
					while (count < WAIT_TIME) {
						if (driver.findElements(current_Item1).size()!=0
								&& (getText(driver, current_Item1).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item1);
							clicked = true;
							break;
						} else if (driver.findElements(current_Item2)
								.size()!=0
								&& (getText(driver, current_Item2)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item2);
							clicked = true;
							break;
						} else if (driver.findElements(Current_Item3)
								.size()!=0
								&& (getText(driver, Current_Item3)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, Current_Item3);
							clicked = true;
							break;
						} else if (driver.findElements(Current_Item3)
								.size()!=0
								&& (getText(driver, Current_Item3)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, Current_Item3);
							clicked = true;
							break;
						} else if (driver.findElements(Current_Item4)
								.size()!=0
								&& (getText(driver, Current_Item4)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, Current_Item5);
							clicked = true;
							break;
						} else if (driver.findElements(Current_Item5)
								.size()!=0
								&& (getText(driver, Current_Item5)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, Current_Item5);
							clicked = true;
							break;
						}
						else if (driver.findElements(current_Item3).size()!=0
								&& (getText(driver, current_Item3).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item3);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item4).size()!=0
								&& (getText(driver, current_Item4).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item4);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item5).size()!=0
								&& (getText(driver, current_Item5).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item5);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item6).size()!=0
								&& (getText(driver, current_Item6).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item6);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item7).size()!=0
								&& (getText(driver, current_Item7).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item7);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item8).size()!=0
								&& (getText(driver, current_Item8).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item8);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item9).size()!=0
								&& (getText(driver, current_Item9).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item9);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item10).size()!=0
								&& (getText(driver, current_Item10).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item10);
							clicked = true;
							break;
						} else if(isElementPresent(driver,By.xpath("//body/div[6]/div/div/div/div/div" ))
									 && (getText(driver, By.xpath("//body/div[6]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
								click(driver,By.xpath("//body/div[6]/div/div/div/div/div"));
								clicked = true;
	                           break;
						}
						else if(isElementPresent(driver,By.xpath("//body/div[4]/div/div/div/div/div"))
								 && (getText(driver, By.xpath("//body/div[4]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[4]/div/div/div/div/div"));	
							clicked = true;
							break;
						}
						 else if(isElementPresent(driver,By.xpath("//body/div[5]/div/div/div/div/div"))
									&& (getText(driver, By.xpath("//body/div[5]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
								click(driver,By.xpath("//body/div[5]/div/div/div/div/div"));
								clicked = true;
								break;
							}
						 else if(isElementPresent(driver,By.xpath("//body/div[6]/div/div/div/div/div[2]" ))
								 && (getText(driver, By.xpath("//body/div[6]/div/div/div/div/div[2]")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[6]/div/div/div/div/div[2]"));
							clicked = true;
                          break;
						 }
//						 else if(isElementPresent(driver,By.xpath("//div[5]/div/div/div/div/div"))
//								 && (getText(driver, By.xpath("//div[5]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[5]/div/div/div/div/div"));	
//							clicked = true;
//							break;
//						}
//						 else if(isElementPresent(driver,By.xpath("//body//div[9]/div/div/div/div/div"))
//									&& (getText(driver,By.xpath( "//body//div[9]/div/div/div/div/div")).toLowerCase()).contains(valueForSelection.toLowerCase()))
//						 {
//								click(driver,By.xpath("//body//div[9]/div/div/div/div/div"));
//								clicked = true;
//	                            break;
//							}
						 else if(isElementPresent(driver,By.xpath("//body/div[7]/div/div/div/div/div"))
									&& (getText(driver, By.xpath("//body/div[7]/div/div/div/div/div")).toLowerCase()).contains(valueForSelection.toLowerCase()))
						 {
								click(driver,By.xpath("//body/div[7]/div/div/div/div/div"));
								clicked = true;
	                            break;
							}//body/div[5]/div/div/div/div/div
						
						else if(isElementPresent(driver,By.xpath("//body/div[3]/div/div/div/div/div"))
								&& (getText(driver,By.xpath( "//body/div[3]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[3]/div/div/div/div/div"));
							clicked = true;
							break;
						}else if(isElementPresent(driver,By.xpath("//div[7]/div/div/div/div/div"))
								&& (getText(driver, By.xpath("//div[7]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//div[7]/div/div/div/div/div"));
							clicked = true;
							break;
					
						}
						else if(isElementPresent(driver,By.xpath( "//body/div[7]/div/div/div/div/div[2]"))
								&& (getText(driver, By.xpath("//body/div[7]/div/div/div/div/div[2]")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[7]/div/div/div/div/div[2]"));
							clicked = true;
							break;
						}
//						else if(isElementPresent(driver,By.xpath( "//div[5]/div/div/div/div/div"))
//								&& (getText(driver, By.xpath("//div[5]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[5]/div/div/div/div/div"));
//							clicked = true;
//							break;
//						}

//						else if(isElementPresent(driver, By.xpath("//body/div[5]/div/div/div/div/div/div/span"))
//								&& (getText(driver, By.xpath("//body/div[5]/div/div/div/div/div/div/span")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//body/div[5]/div/div/div/div/div/div/span"));
//							clicked = true;
//							break;
//						}
						
//						else if(isElementPresent(driver,By.xpath( "//body/div[4]/div/div/div/div/div/div/span"))
//								&& (getText(driver,By.xpath( "//body/div[4]/div/div/div/div/div/div/span")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//body/div[4]/div/div/div/div/div/div/span"));
//							clicked = true;
//							break;
//						}
						
//						else if(isElementPresent(driver, By.xpath("//body/div[8]/div/div/div/div/div"))
//								&& (getText(driver, By.xpath("//body/div[8]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//body/div[8]/div/div/div/div/div"));
//							clicked = true;
//							break;
//						}
//						else if(isElementPresent(driver, By.xpath("//div[5]/div/div/div/div/input"))
//								&& (getText(driver,By.xpath( "//div[5]/div/div/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[5]/div/div/div/div/input"));
//							clicked = true;
//							break;
//						}
//						else if(isElementPresent(driver, By.xpath("//div[7]/div/div/div/div/input"))
//								&& (getText(driver, By.xpath("//div[7]/div/div/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div[7]/div/div/div/div/input"));
//							clicked = true;
//							break;
//						}
						 
						 
//						else if(isElementPresent(driver, By.xpath("//div[4]/div/div/div/div/div/span"))
//								&& (getText(driver, By.xpath("//div[4]/div/div/div/div/div/span")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div[4]/div/div/div/div/div/span"));
//							clicked = true;
//							break;
//						}
//						 
//						else if(isElementPresent(driver, By.xpath("//div[4]/div/div/div/div/div/span"))
//								&& (getText(driver, By.xpath("//div[4]/div/div/div/div/div/span")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div[4]/div/div/div/div/div/span"));
//							clicked = true;
//							break;
//						}
						 
//						 
//						else if(isElementPresent(driver,By.xpath( "//div[4]/div/div/div[2]/div/div/div/input"))
//								&& (getText(driver,By.xpath( "//div[4]/div/div/div[2]/div/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div[4]/div/div/div[2]/div/div/div/input"));
//							clicked = true;
//							break;
//						}
//						else if(isElementPresent(driver,By.xpath( "//div[3]/div/div/input"))
//								&& (getText(driver,By.xpath( "//div[3]/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div[3]/div/div/input"));
//							clicked = true;
//							break;
//						}
					 
//						else if(isElementPresent(driver,By.xpath( "//div[6]/div/div[2]/div/div[2]/div/input"))
//								&& (getText(driver, By.xpath("//div[6]/div/div[2]/div/div[2]/div/input")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div[6]/div/div[2]/div/div[2]/div/input"));
//							clicked = true;
//							break;
//						}
//				 
//						else if(isElementPresent(driver,By.xpath( "//div/div/div[2]/div/div/input"))
//								&& (getText(driver, By.xpath("//div/div/div[2]/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div/div/div[2]/div/div/input"));
//							clicked = true;
//							break;
//						}
//					 
//						else if(isElementPresent(driver,By.xpath( "//div[3]/div/div/input"))
//								&& (getText(driver,By.xpath( "//div[3]/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div[3]/div/div/input"));
//							clicked = true;
//							break;
//						}
//				 
//						else if(isElementPresent(driver,By.xpath( "//div[4]/div/div/input"))
//								&& (getText(driver,By.xpath( "//div[4]/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div[4]/div/div/input"));
//							clicked = true;
//							break;
//						}
//			 
//						else if(isElementPresent(driver, By.xpath("//div/div[2]/div[2]/div/div/input"))
//								&& (getText(driver, By.xpath("//div/div[2]/div[2]/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase())))
//						{
//							click(driver,By.xpath("//div/div[2]/div[2]/div/div/input"));
//							clicked = true;
//							break;
//						}
						
//						else if(isElementPresent(driver, By.xpath("//div[6]/div/div/div/div/div/div/div/div/a"))
//								&& (getText(driver, By.xpath("//div[6]/div/div/div/div/div/div/div/div/a")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[6]/div/div/div/div/div/div/div/div/a"));
//							clicked = true;
//							break;
//						}
				 
						
//						else if(isElementPresent(driver, By.xpath("//div[2]/div[3]/div/div/input"))
//								&& (getText(driver, By.xpath("//div[2]/div[3]/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[2]/div[3]/div/div/input"));
//							clicked = true;
//							break;
//						}
				 
//						else if(isElementPresent(driver,By.xpath( "//div[3]/div/div/div[2]/div/div/div/input"))
//								&& (getText(driver,By.xpath( "//div[3]/div/div/div[2]/div/div/div/input")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[3]/div/div/div[2]/div/div/div/input"));
//							clicked = true;
//							break;
//						}
//						else if(isElementPresent(driver, By.xpath("//div[4]/div/div/div/div/div"))
//								&& (getText(driver,By.xpath( "//div[4]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))) {
//							click(driver,By.xpath("//div[4]/div/div/div/div/div"));
//							clicked = true;
//							break;
//						}else if(isElementPresent(driver,By.xpath("//div[6]/div/div/div/div/div"))
//								&& (getText(driver, By.xpath("//div[6]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[6]/div/div/div/div/div"));
//							clicked = true;
//                           break;
//						}else if(isElementPresent(driver,By.xpath("//body/div[9]/div/div/div/div/div"))
//								&& (getText(driver, By.xpath("//body/div[9]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//body/div[9]/div/div/div/div/div"));
//							clicked = true;
//                           break;
//						}else if(isElementPresent(driver,By.xpath("css=span.gwt-InlineLabel"))
//								&& (getText(driver, By.xpath("css=span.gwt-InlineLabel")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("css=span.gwt-InlineLabel"));
//							clicked = true;
//                           break;
//						}
//						 else if(isElementPresent(driver,By.xpath("//div[5]/div/div/div/div/div/span"))
//								 && (getText(driver,By.xpath( "//div[5]/div/div/div/div/div/span")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[5]/div/div/div/div/div/span"));
//							clicked = true;
//							break;
//						}
//						 else if(isElementPresent(driver,By.xpath("//div[6]/div/div/div/div/div[2]"))
//								 && (getText(driver,By.xpath( "//div[6]/div/div/div/div/div[2]")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[6]/div/div/div/div/div[2]"));
//							clicked = true;
//							break;
//						}
						count = count + 1;
					}
					return clicked;
				}
		//	}

		} catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionHandler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return clicked;
	}
	
	public boolean selectOtherValueFromAjaxList(WebDriver driver, By textboxName, String valueToSearch,
			String valueForSelection)  {
		
		valueForSelection = valueForSelection != null ? valueForSelection
				.trim() : "";
		int count = 0;
		if(valueForSelection.equals("")){
			return true;
		}
		boolean clicked = false;
		try {
			if (isElementPresent(driver, textboxName)) {
				Thread.sleep(3000);
				driver.findElement(textboxName).sendKeys("");
				driver.findElement(textboxName).clear();
				driver.findElement(textboxName).click();
				driver.findElement(textboxName).sendKeys(valueToSearch);
				driver.findElement(textboxName).sendKeys(Keys.DOWN);
				driver.findElement(textboxName).sendKeys(Keys.UP);
				waitForPageLoad(driver);
				
//				waitForPageLoad(driver);
					while (count < WAIT_TIME) {
						if (driver.findElements(current_Item1).size()!=0
								&& (getText(driver, current_Item1).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item1);
							clicked = true;
							break;
						} else if (driver.findElements(current_Item2)
								.size()!=0
								&& (getText(driver, current_Item2)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item2);
							clicked = true;
							break;
						} else if (driver.findElements(Current_Item3)
								.size()!=0
								&& (getText(driver, Current_Item3)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, Current_Item3);
							clicked = true;
							break;
						} else if (driver.findElements(Current_Item3)
								.size()!=0
								&& (getText(driver, Current_Item3)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, Current_Item3);
							clicked = true;
							break;
						} else if (driver.findElements(Current_Item4)
								.size()!=0
								&& (getText(driver, Current_Item4)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, Current_Item5);
							clicked = true;
							break;
						} else if (driver.findElements(Current_Item5)
								.size()!=0
								&& (getText(driver, Current_Item5)
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, Current_Item5);
							clicked = true;
							break;
						}
						else if (driver.findElements(current_Item1).size()!=0
								&& (getText(driver, current_Item1).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item1);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item2).size()!=0
								&& (getText(driver, current_Item2).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item2);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item3).size()!=0
								&& (getText(driver, current_Item3).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item3);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item4).size()!=0
								&& (getText(driver, current_Item4).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item4);
							clicked = true;
							break;
						} 
						else if (driver.findElements(current_Item5).size()!=0
								&& (getText(driver, current_Item5).trim()
										.toLowerCase()
										.contains(valueForSelection
												.toLowerCase()))) {
							click(driver, current_Item5);
							clicked = true;
							break;
						} 
						else
						if(isElementPresent(driver,By.xpath("//body/div[6]/div/div/div/div/div" ))
								 && (getText(driver, By.xpath("//body/div[6]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[6]/div/div/div/div/div"));
							clicked = true;
                           break;
						 }
						else if(isElementPresent(driver,By.xpath("//body/div[4]/div/div/div/div/div"))
								 && (getText(driver, By.xpath("//body/div[4]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[4]/div/div/div/div/div"));	
							clicked = true;
							break;
						}
						 else if(isElementPresent(driver,By.xpath("//body/div[5]/div/div/div/div/div"))
									&& (getText(driver, By.xpath("//body/div[5]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
								click(driver,By.xpath("//body/div[5]/div/div/div/div/div"));
								clicked = true;
								break;
							}
						 else if(isElementPresent(driver,By.xpath("//body/div[6]/div/div/div/div/div[2]" ))
								 && (getText(driver, By.xpath("//body/div[6]/div/div/div/div/div[2]")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[6]/div/div/div/div/div[2]"));
							clicked = true;
                          break;
						 }
//						 else if(isElementPresent(driver,By.xpath("//div[5]/div/div/div/div/div"))
//								 && (getText(driver, By.xpath("//div[5]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
//							click(driver,By.xpath("//div[5]/div/div/div/div/div"));	
//							clicked = true;
//							break;
//						}
//						 else if(isElementPresent(driver,By.xpath("//body//div[9]/div/div/div/div/div"))
//									&& (getText(driver,By.xpath( "//body//div[9]/div/div/div/div/div")).toLowerCase()).contains(valueForSelection.toLowerCase()))
//						 {
//								click(driver,By.xpath("//body//div[9]/div/div/div/div/div"));
//								clicked = true;
//	                            break;
//							}
						 else if(isElementPresent(driver,By.xpath("//body/div[7]/div/div/div/div/div"))
									&& (getText(driver, By.xpath("//body/div[7]/div/div/div/div/div")).toLowerCase()).contains(valueForSelection.toLowerCase()))
						 {
								click(driver,By.xpath("//body/div[7]/div/div/div/div/div"));
								clicked = true;
	                            break;
							}//body/div[5]/div/div/div/div/div
						
						else if(isElementPresent(driver,By.xpath("//body/div[3]/div/div/div/div/div"))
								&& (getText(driver,By.xpath( "//body/div[3]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[3]/div/div/div/div/div"));
							clicked = true;
							break;
						}else if(isElementPresent(driver,By.xpath("//div[7]/div/div/div/div/div"))
								&& (getText(driver, By.xpath("//div[7]/div/div/div/div/div")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//div[7]/div/div/div/div/div"));
							clicked = true;
							break;
					
						}
						else if(isElementPresent(driver,By.xpath( "//body/div[7]/div/div/div/div/div[2]"))
								&& (getText(driver, By.xpath("//body/div[7]/div/div/div/div/div[2]")).toLowerCase().contains(valueForSelection.toLowerCase()))){
							click(driver,By.xpath("//body/div[7]/div/div/div/div/div[2]"));
							clicked = true;
							break;
						}
//						
						count = count + 1;
					}
					return clicked;
				}
		//	}

		} catch (RuntimeException e) {
			e.printStackTrace();
			Assert.fail();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExceptionHandler e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return clicked;
	}
	
     /**
 	 * check whether the check box is checked or not
 	 * @param 		driver (an instance of current driver browser)
 	 * @param       checkBox (element ID of the checkBox)
 	 * @throws IOException 
 	 * @throws ExceptionHandler 
 	 * @since  	  Nov 24 ,2014
 	 */
	public boolean isChecked(WebDriver driver, By checkBox) {
		boolean checked=true;
		try {
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			checked = driver.findElement(checkBox).isSelected();
		} catch (RuntimeException e) {
			status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(
					getCurrentMethodName(), checkBox.toString(), empty, status,
					empty, getCallingMethodAndLineNumber()));
			return false;
		}
		if (Config.requireToWrite) {
			logCustomMessage().setAttribute(
					getCurrentDateAndTime(),
					getCustomAttributeValue(getCurrentMethodName(),
							checkBox.toString(), empty, status, empty,
							getCallingMethodAndLineNumber()));
		}
		return checked;
	}
        /** selectSecondMatchingValueFromAjaxList
		 * Select second matching value in the ajax list
		 * @param driver
		 * @param textboxName
		 * @param valueForSelection
		 * @return true/false
		 * @throws IOException 
		 */
     public boolean selectSecondMatchingValueFromAjaxList(WebDriver driver, By textboxName, String valueForSelection){
         valueForSelection = valueForSelection != null ? valueForSelection.trim() : "";
         try{
                if(isElementPresent(driver,textboxName)){
                      driver.findElement(textboxName).sendKeys("");
                      driver.findElement(textboxName).clear();                      
                      driver.findElement(textboxName).sendKeys(valueForSelection);
                      driver.findElement(textboxName).sendKeys(Keys.DOWN);
                      driver.findElement(textboxName).sendKeys(Keys.UP);
                      if(isElementPresent(driver,secondItem)){
                             click(driver,secondItem);
                             waitForPageLoad(driver);
                      }
                      else if(isElementPresent(driver,thirdItem)){
                             click(driver,thirdItem);
                             waitForPageLoad(driver);
                      }
                      return true;
                }else{
                      return false;
                }
         }catch (RuntimeException e) {
        	 status = "Fail";
			new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), textboxName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
         }
         if(Config.requireToWrite){
				logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), textboxName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
			} 
         return false;
     }
     
     
	   /** enterDate
		 * To enter data in Date field
		 * @param By
		 * @param text
		 * @return true/false
		 * @throws IOException 
		 */
		public boolean enterDate(WebDriver driver, By dateField, String date)
		{
			try{
				
				driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				driver.findElement(dateField).clear();
				driver.findElement(dateField).sendKeys(date);
				driver.findElement(dateField).sendKeys(Keys.ENTER);
				status ="pass";
			}
			catch(Exception e){
				status = "Fail";
				new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), dateField.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
			}
			if(Config.requireToWrite){
				logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), dateField.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
			}
			return true;
		
		}
	
	//To check the check box if it is not checked 
			public boolean check(WebDriver driver, By elementName){
				boolean checked;
				try{
					driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);	
					checked=driver.findElement(elementName).isSelected();	
					if(!checked)
					{
						driver.findElement(elementName).click();
						
					}
					
				}
				catch(Exception e)
				{
					new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
					return false;
				}
				if(Config.requireToWrite){
					logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), elementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
				}
				return true;	
			}

			public boolean clickClassName(WebDriver driver, String elementName){
				try{elementName = elementName != null ? elementName.trim() : "";
					if(!elementName.equals("")){
						driver.findElement(By.className(elementName)).click();
						
						return true;
					}else{
						return false;
					}
				}catch(RuntimeException e){
					e.printStackTrace();
					return false;
				}
			}
			public boolean isXPathElementPresent(WebDriver driver, String btnEdit){

				btnEdit = btnEdit != null ? btnEdit.trim():"";
				if(waitForXPATHElement(driver,btnEdit,Constants.WAIT_TIME)){
					return true;
				}else{
					return false;
				}
			}
			public boolean waitForXPATHElement(WebDriver driver,String ajaxElementName, int timeOutValue ){
				try{
					
					WebDriverWait wait = new WebDriverWait(driver, timeOutValue);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ajaxElementName)));
					wait.withMessage("The Element '" + ajaxElementName + "' did not appear  within "
							+ timeOutValue + " ms.");			
					
					return true;
				}catch(RuntimeException e){
					return false;	
				}
			}
			
			
			/**
			 * To click an element on the screen by Web element technique 
			 * 
			 * @param driver
			 * @return
			 */
			public boolean webElementclick(WebDriver driver,final By ajaxElementName){
				try {
					
					waitForElement(driver,ajaxElementName,  Config.AVG_WAIT_TIME_FOR_ELEMENT);
				
					if (isElementPresent(ajaxElementName)&& isVisible(driver, ajaxElementName)) {				
						WebElement elementtoclick = driver.findElement(ajaxElementName);
						elementtoclick.click();
						result= true;
								}
					
							} catch (Exception e) {
					status = "Fail";
					result = false;
					new ExceptionHandler(e, driver, getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()) );
					
				}
				if(Config.requireToWrite){
					logCustomMessage().setAttribute(getCurrentDateAndTime(), getCustomAttributeValue(getCurrentMethodName(), ajaxElementName.toString(), empty, status, empty, getCallingMethodAndLineNumber()));
				}
				return result;
			}
			
}
	

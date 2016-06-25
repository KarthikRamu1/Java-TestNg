package com.projectname.test.functional.livechat;

import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.projectname.testutils.baseclass.TestBaseClass;
import com.projectname.testutils.genericutility.testDataLib.livechatLib;
import com.projectname.testutils.pages.LoginPage;
import com.projectname.testutils.retryAnalyser.RetryRule;
import com.projectname.testutils.pages.DashboardPage;
@Listeners(com.projectname.testutils.baseclass.CustomizedReporter.class)



public class livechatenableverification extends TestBaseClass{
	
	private  DashboardPage DashboardPage;
	@Test(retryAnalyzer = RetryRule.class, groups = { "Regression" }, description = "Test for Verify Referral By Practice Option")
	@Parameters({"userAccount","webSite","browser","seleniumHost","seleniumPort"})

	public void Verifychatsanity1(String userAccount,String website,String browser,String seleniumHost,String seleniumPort) throws Exception {
		className=this.getClass().getName().substring(39);	
		methodName= Thread.currentThread().getStackTrace()[1].getMethodName();
		livechatLib chattestdata = new livechatLib();
		chattestdata.workSheetName = "livechat";
		chattestdata.testCaseId = "LC_001";
		chattestdata.fetchLiveTestData();
		verifychaticonsindashboard(chattestdata);
}
//	@Test(retryAnalyzer = RetryRule.class, groups = { "Regression" }, description = "Test for Verify Referral By Practice Option")
//	@Parameters({"userAccount","webSite","browser","seleniumHost","seleniumPort"})

	public void Verifychatsanity2(String userAccount,String website,String browser,String seleniumHost,String seleniumPort) throws Exception {
		className=this.getClass().getName().substring(39);	
		methodName= Thread.currentThread().getStackTrace()[1].getMethodName();
		livechatLib chattestdata = new livechatLib();
		chattestdata.workSheetName = "livechat";
		chattestdata.testCaseId = "LC_001";
		chattestdata.fetchLiveTestData();
		verifychaticonsindashboard(chattestdata);
}
//	@Test(retryAnalyzer = RetryRule.class, groups = { "Regression" }, description = "Test for Verify Referral By Practice Option")
//	@Parameters({"userAccount","webSite","browser","seleniumHost","seleniumPort"})

	public void Verifychatsanity3(String userAccount,String website,String browser,String seleniumHost,String seleniumPort) throws Exception {
		className=this.getClass().getName().substring(39);	
		methodName= Thread.currentThread().getStackTrace()[1].getMethodName();
		livechatLib chattestdata = new livechatLib();
		chattestdata.workSheetName = "livechat";
		chattestdata.testCaseId = "LC_001";
		chattestdata.fetchLiveTestData();
		verifychaticonsindashboard(chattestdata);
}
//	@Test(retryAnalyzer = RetryRule.class, groups = { "Regression" }, description = "Test for Verify Referral By Practice Option")
//	@Parameters({"userAccount","webSite","browser","seleniumHost","seleniumPort"})

	public void Verifychatsanity4(String userAccount,String website,String browser,String seleniumHost,String seleniumPort) throws Exception {
		className=this.getClass().getName().substring(39);	
		methodName= Thread.currentThread().getStackTrace()[1].getMethodName();
		livechatLib chattestdata = new livechatLib();
		chattestdata.workSheetName = "livechat";
		chattestdata.testCaseId = "LC_001";
		chattestdata.fetchLiveTestData();
		verifychaticonsindashboard(chattestdata);
}
	
/**
 * Verification for live chat icons in dashboard after admin enabled live chat
 *
 * @since  June 23, 2016
 */	
	public boolean verifychaticonsindashboard(livechatLib chattestdata) throws Exception{
		boolean returnValue=true;
		try{
			
			//--------------------------------------------------------------------//
			//  Step-1: Step -1 Login into Freshservice as admin agent			 //
			//--------------------------------------------------------------------//
			logTitleMessage("Login to application");
			LoginPage loginPage=new LoginPage();
			DashboardPage=loginPage.loginFS(driver,chattestdata.userName, chattestdata.userPassword);
			logTitleMessage("Logined to application successfully");
			//--------------------------------------------------------------------//
			//  Step-1:Navigate to Dashboard and verify chat icons after enabled chats//
			//--------------------------------------------------------------------//
			logTitleMessage("Navigate to Dashboard and look for chat icons");
			DashboardPage.chaticonsverification(driver);
			logTitleMessage("Successfully verified all Dashboard chat icons");
		
			if(chattestdata.testCaseId== "LC_002")
			{
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return returnValue;
}
		
}

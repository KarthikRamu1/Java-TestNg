package com.projectname.testutils.genericutility;

public class Config {

	//Config
	
	//Flag for to On/Off detailed log  
	public static Boolean requireToWrite = true; 
	
	
	//Retry Analyzer Initialization
	public static final int RETRYCOUNTER = 0; 
	public static int retryCount=0;
	
	//Browser
	public static String browser="FIREFOX";

	//Credentials
	public static String URL="https://108.161.43.79:8443";
	
	//Identifier Types
	public static final String NAME = "name";
	public static String CSS = "cssSelector";
	public static String ID = "id";
	public static String XPATH = "xpath";
	public static String LINK = "link";

	//Time Variables
	public static int DELAY_TIME = 2;
	public static int MINOR_WAIT_TIME = 7;
	public static int WAIT_TIME = 10;
	public static int AVG_WAIT_TIME_FOR_ELEMENT = 15;
	public static int MAX_WAIT_TIME_FOR_ELEMENT = 30;
	
	
}

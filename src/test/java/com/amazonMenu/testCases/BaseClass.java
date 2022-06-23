package com.amazonMenu.testCases;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.amazonMenu.utilities.ReadConfig;


public class BaseClass {
	
	//-------READING VALUES FROM CONFIG FILES------//
	ReadConfig readconfig=new ReadConfig();
	public String baseURL=readconfig.getApplicationURL();
	public String Musicname=readconfig.getMusicname();
	public static WebDriver driver;
	 
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)	
	{
		if(br.equals("chrome"))
		{System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		}
		
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
			driver=new FirefoxDriver();
		}
		
		driver.get(baseURL);
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	
	{
		//driver.quit();
	}

}




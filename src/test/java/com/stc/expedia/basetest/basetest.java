package com.stc.expedia.basetest;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Utilities.Browser;
import Utilities.Utilities;





public class basetest {
	protected WebDriver driver;
	private String browserName;
	private String url;
	private Browser browser = new Browser();
	@BeforeMethod
	public void setup() {
		Map<String, String> applicationData = Utilities.getApplicationData();
		browserName = applicationData.get("Browser");
		url = applicationData.get("Url");
		driver = browser.getDriver(browserName);
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}
	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
	  public WebDriver  getDriver() {
	        return driver;
	    }
}

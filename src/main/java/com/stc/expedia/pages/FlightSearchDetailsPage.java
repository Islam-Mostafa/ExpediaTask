package com.stc.expedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightSearchDetailsPage {
	 WebDriver driver;
	 WebDriverWait wait ;

	
	public FlightSearchDetailsPage(WebDriver _driver) {
		driver = _driver;
		wait =new WebDriverWait(driver,60);
	}
	public void Select_Flights_Sorting(String sortingtype) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("listings-sort")));
		Select sortinglist=new Select(driver.findElement(By.id("listings-sort")));
		sortinglist.selectByVisibleText(sortingtype);
		
		
	}

}

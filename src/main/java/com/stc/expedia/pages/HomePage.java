package com.stc.expedia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	 WebDriver driver;
	 WebDriverWait wait ;

	public HomePage(WebDriver _driver) {
		// TODO Auto-generated constructor stub
		driver = _driver;
		wait =new WebDriverWait(driver,60);
	}
	
	public void Open_Flights_Tab()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='?pwaLob=wizard-flight-pwa']")));
		WebElement flights_tab=driver.findElement(By.xpath("//a[@href='?pwaLob=wizard-flight-pwa']"));
		flights_tab.click();
	}
	public void Select_GoingTo(String destination) {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-stid='location-field-leg1-destination-menu-trigger']")));
		WebElement triger_menu_btn=driver.findElement(By.xpath("//button[@data-stid='location-field-leg1-destination-menu-trigger']"));
		triger_menu_btn.click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='location-field-leg1-destination']")));
		WebElement going_txtbx=driver.findElement(By.xpath("//input[@id='location-field-leg1-destination']"));
		going_txtbx.sendKeys(destination);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@data-stid='location-field-leg1-destination-results']/li//strong")));
		WebElement going_result=driver.findElement(By.xpath("//ul[@data-stid='location-field-leg1-destination-results']/li//strong[text()='"+  destination +"']"));
	
		going_result.click();
	}
	public void Check_Add_Flight() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-flight-switch")));
		WebElement addflight_chk=driver.findElement(By.id("add-flight-switch"));
		addflight_chk.click();
	}
	public void Select_Leaving_From(String leaving) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-stid='location-field-leg1-origin-menu-trigger']")));
		WebElement triger_menu_btn=driver.findElement(By.xpath("//button[@data-stid='location-field-leg1-origin-menu-trigger']"));
		triger_menu_btn.click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='location-field-leg1-origin']")));
		WebElement leaving_txtbx=driver.findElement(By.xpath("//input[@id='location-field-leg1-origin']"));
		leaving_txtbx.sendKeys(leaving);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@data-stid='location-field-leg1-origin-results']/li//strong")));
		WebElement leaving_result=driver.findElement(By.xpath("//ul[@data-stid='location-field-leg1-origin-results']/li//strong[text()='"+  leaving +"']"));
	
		leaving_result.click();
	}
	public void ClickSearch() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-testid='submit-button']")));
		WebElement search_btn=driver.findElement(By.xpath("//button[@data-testid='submit-button']"));
		search_btn.click();
		
	}
}

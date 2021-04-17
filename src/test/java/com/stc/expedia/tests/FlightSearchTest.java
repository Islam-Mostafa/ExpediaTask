package com.stc.expedia.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.stc.expedia.basetest.basetest;
import com.stc.expedia.pages.FlightSearchDetailsPage;
import com.stc.expedia.pages.HomePage;

import TestUtilities.Listeners.TestListener;
import Utilities.ExcelUtilities;
@Listeners({TestListener.class})
public class FlightSearchTest extends basetest {

	@Test
	public void SearchFlight() {
		ExcelUtilities excelUtilities=new ExcelUtilities();
		Map<String, String> testdata=null;
		try {
			testdata=excelUtilities.getData2("src\\test\\resources\\Data\\TestData.xlsx", "TaskData", "SearchFlight");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HomePage homePage=new HomePage(driver);
		homePage.Open_Flights_Tab();
		homePage.Select_Leaving_From(testdata.get("LeavingFrom"));
		homePage.Select_GoingTo(testdata.get("GoingTo"));
		homePage.ClickSearch();
		
		FlightSearchDetailsPage flightSearchDetailsPage=new FlightSearchDetailsPage(driver);
		flightSearchDetailsPage.Select_Flights_Sorting("Arrival (Earliest)");
		assertTrue(driver.getCurrentUrl().contains("Flights-Search?"));
	}
}

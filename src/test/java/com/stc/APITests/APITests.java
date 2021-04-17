package com.stc.APITests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class APITests {
	String baseUrl = "https://restcountries.eu/rest/v2";
	@Test
	public void PositiveTest()
	{

		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		Response response = request.get("/all?fields=name;capital;currencies;latlng");
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();
		String jsonString = response.asString();

		Assert.assertTrue(jsonString.contains("capital"));

		String capital = JsonPath.from(jsonString).getList("capital").get(0).toString();
		response=request.get("/capital/"+capital+"?fields=name;capital;currencies;latlng;regionalBlocs");
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();

	}
	@Test
	public void NegativeTest()
	{
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		String capital ="invalidcapital";
		Response response=request.get("/capital/"+capital+"?fields=name;capital;currencies;latlng;regionalBlocs");
		Assert.assertEquals(response.getStatusCode(), 404);
		response.then().log().all();

	}
}


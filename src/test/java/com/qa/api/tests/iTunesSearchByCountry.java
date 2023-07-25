package com.qa.api.tests;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.api.endpoints.SearchEndpoints;
import com.qa.api.utilities.TestConfigs;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class iTunesSearchByCountry extends TestConfigs{

	@Test (dataProvider = "searchWithValidCountry", dataProviderClass = TestConfigs.class)
	public void testSearch_withValidCountry(final String country) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("country", country);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("resultCount", equalTo(0))
				.body("results.size()",	equalTo(0));

	}

	@Test (dataProvider = "searchWithInvalidCountry", dataProviderClass = TestConfigs.class)
	public void testSearch_withInvalidCountry(final String country) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("country", country);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(400)
				.body("errorMessage", equalTo("Invalid value(s) for key(s): [country]"));

	}

}

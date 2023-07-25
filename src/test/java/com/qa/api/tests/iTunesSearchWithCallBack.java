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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class iTunesSearchWithCallBack extends TestConfigs{

	@Test (dataProvider = "searchWithValidCallback", dataProviderClass = TestConfigs.class)
	public void testSearch_withValidCallback(final String term, final String country, final String callback) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("callback", callback);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		String strResponse = response
								.then()
								.statusCode(200)
								.extract().asString();
		System.out.println(strResponse);
        Assert.assertTrue(strResponse.contains(callback));

	}

	@Test (dataProvider = "searchWithInvalidCallback", dataProviderClass = TestConfigs.class)
	public void testSearch_withInvalidCallback(final String term, final String country, final String callback) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("callback", callback);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.statusCode(400)
				.body("errorMessage", equalTo("Invalid value(s) for key(s): [callback]"));

	}

}

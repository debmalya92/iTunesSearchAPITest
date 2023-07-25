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

public class iTunesSearchAllParams extends TestConfigs {


	@Test
	public void testSearch_noResult() {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("country", "US");
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
	

	@Test(dataProvider = "searchQueries_withAllParams", dataProviderClass = TestConfigs.class)
	public void testSearch_withAllParams(HashMap<String, Object> queryParam) {

		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		String strResponse = response
			.then()
				.log()
				.all()
				.statusCode(200)
				.extract().asString();
		Assert.assertTrue(strResponse.contains(queryParam.get("callback").toString()));
		Assert.assertTrue(strResponse.contains(queryParam.get("term").toString()));
		Assert.assertTrue(strResponse.contains("resultCount"));
		Assert.assertTrue(strResponse.contains("results"));
	}

}

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

public class iTunesSearchByLanguage extends TestConfigs{

	@Test (dataProvider = "searchWithValidLang", dataProviderClass = TestConfigs.class)
	public void testSearch_withValidLang(final String term, final String mediaType, final String lang) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("media", mediaType);
			put("lang", lang);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results.size()",	greaterThan(0));

	}

	@Test (dataProvider = "searchWithInvalidLang", dataProviderClass = TestConfigs.class)
	public void testSearch_withInvalidLang(final String term, final String mediaType, final String lang) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("media", mediaType);
			put("lang", lang);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(400)
				.body("errorMessage", equalTo("Invalid value(s) for key(s): [language]"));

	}

}

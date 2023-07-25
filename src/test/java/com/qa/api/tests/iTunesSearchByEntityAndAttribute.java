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

public class iTunesSearchByEntityAndAttribute extends TestConfigs{

	@Test (dataProvider = "searchWithValidEntityAndAttribute", dataProviderClass = TestConfigs.class)
	public void testSearch_withValidEntityAndAttribute(final String term, final String entity, final String attribute) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("entity", entity);
			put("attribute", attribute);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results.artistName", hasItem(term))
				.body("results.size()",	greaterThan(0));

	}

	@Test (dataProvider = "searchWithInvalidAttribute", dataProviderClass = TestConfigs.class)
	public void testSearch_withInvalidAttribute(final String term, final String entity, final String attribute) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("entity", entity);
			put("attribute", attribute);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(400)
				.body("errorMessage", equalTo("Invalid value(s) for key(s): [attributeType]"));

	}

	@Test (dataProvider = "searchWithInvalidEntity", dataProviderClass = TestConfigs.class)
	public void testSearch_withInvalidEntity(final String term, final String entity, final String attribute) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("entity", entity);
			put("attribute", attribute);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(400)
				.body("errorMessage", equalTo("Invalid value(s) for key(s): [entityType]"));

	}
}

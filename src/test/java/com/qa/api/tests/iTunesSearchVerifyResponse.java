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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class iTunesSearchVerifyResponse extends TestConfigs{

	@Test (dataProvider = "verifyReturnedKeys", dataProviderClass = TestConfigs.class)
	public void verifyResponseFormat(final String term, final String country, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.contentType(ContentType.JSON);
		
	}


	@Test (dataProvider = "verifyReturnedKeys", dataProviderClass = TestConfigs.class)
	public void verifyArtworkUrls(final String term, final String country, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results[0]",	hasKey("artworkUrl100"))
				.body("results[0]",	hasKey("artworkUrl60"))
				.body("results[0]",	hasKey("artworkUrl30"));
		
	}

	@Test (dataProvider = "verifyReturnedKeys", dataProviderClass = TestConfigs.class)
	public void verifyViewPreviewUrls(final String term, final String country, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results[0]",	hasKey("previewUrl"))
				.body("results[0]",	hasKey("artistViewUrl"))
				.body("results[0]",	hasKey("collectionViewUrl"))
				.body("results[0]",	hasKey("trackViewUrl"));	
	}

	@Test (dataProvider = "verifyReturnedKeys", dataProviderClass = TestConfigs.class)
	public void testSearch_verifyTrackTime(final String term, final String country, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results.wrapperType", hasItem("track"))
				.body("results[0]",	hasKey("trackTimeMillis"));
		
		
	}

	@Test (dataProvider = "verifyReturnedKeys", dataProviderClass = TestConfigs.class)
	public void verifyExplicitness(final String term, final String country, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results[0]",	hasKey("trackExplicitness"))
				.body("results[0]",	hasKey("collectionExplicitness"));
		
		
	}

	
	@Test (dataProvider = "verifyReturnedKeys", dataProviderClass = TestConfigs.class)
	public void verifyCensoredName(final String term, final String country, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results[0]",	hasKey("collectionCensoredName"))
				.body("results[0]",	hasKey("trackCensoredName"));
		
		
	}

	@Test (dataProvider = "verifyReturnedKeys", dataProviderClass = TestConfigs.class)
	public void verifyContentKind(final String term, final String country, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results[0]",	hasKey("kind"));
		
		
	}
	
	@Test (dataProvider = "verifyReturnedKeys", dataProviderClass = TestConfigs.class)
	public void testSearch_verifyReturnedKeys(final String term, final String country, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results.artistName",	not(emptyOrNullString()))
				.body("results[0]",	hasKey("wrapperType"))
				.body("results[0]",	hasKey("artistId"))
				.body("results[0]",	hasKey("trackName"))
				.body("results[0]",	hasKey("artistName"))
				.body("results[0]",	hasKey("collectionName"))
				.body("results[0]",	hasKey("country"));
		
	}
	
	@Test (dataProvider = "verifySpecifiedValues", dataProviderClass = TestConfigs.class)
	public void verifySpecifiedValues(final String term, final String entity, final int limit) {
		
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("entity", entity);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
				.log()
				.all()
				.statusCode(200)
				.body("results.wrapperType", everyItem(in(Arrays.asList("track", "collection", "artist"))));
		
	}

}

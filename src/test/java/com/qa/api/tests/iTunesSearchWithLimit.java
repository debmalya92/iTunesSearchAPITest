package com.qa.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import com.qa.api.endpoints.SearchEndpoints;
import com.qa.api.utilities.TestConfigs;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

public class iTunesSearchWithLimit extends TestConfigs{

	@Test (dataProvider = "searchWithLimit", dataProviderClass = TestConfigs.class)
	public void testSearch_atLimitValue(final String term, final String country, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("country", country);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
		.then()
			.log()
			.all()
			.statusCode(200)
			.body("results.size()",	equalTo(limit));


	}

	@Test (dataProvider = "searchWithinMaxLimit", dataProviderClass = TestConfigs.class)
	public void testSearch_withinMaxLimit(final String term, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
			.statusCode(200)
			.body("results.size()",	lessThan(limit));

	}

	@Test (dataProvider = "searchWithInvalidLimit", dataProviderClass = TestConfigs.class)
	public void testSearch_withInvalidLimit(final String term, final String media, final int limit) {
		Map<String, Object> queryParam = new HashMap<String, Object>(){{
			put("term", term);
			put("media", media);
			put("limit", limit);
		}};
		
		Response response = SearchEndpoints.iTunesSearch(queryParam); 
		response
			.then()
			.statusCode(400);

	}
}

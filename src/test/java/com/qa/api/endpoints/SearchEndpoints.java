package com.qa.api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SearchEndpoints {
	
	public static final String baseURL = "https://itunes.apple.com";

	
	
	public static Response iTunesSearch(Map<String, Object> queryParam) {
		
		Response response = 
				given()
					.contentType("application/json")
					.accept(ContentType.JSON)
					.queryParams(queryParam)
				.when()
					.get("/search");
		
		return response;
	}
}

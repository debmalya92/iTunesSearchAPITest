package com.qa.api.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.qa.api.endpoints.SearchEndpoints;

import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestConfigs {

	@BeforeTest
	public void setup() {
		RestAssured.baseURI = SearchEndpoints.baseURL;
	}


	@DataProvider(name = "searchWithValidTerms")
	public Object[][] searchWithValidTerms(){

		return new Object[][]
				{
			{"Jack Johnson"},
			{"Maroon 5"}
				};
	}

	@DataProvider(name = "searchWithInvalidTerms")
	public Object[][] searchWithInvalidTerms(){

		return new Object[][]
				{
			{""},
			{"___---___"}
				};
	}


	@DataProvider(name = "searchWithValidCountry")
	public Object[][] searchWithValidCountry(){

		return new Object[][]
				{
			{"US"},
			{"IN"}
				};
	}

	@DataProvider(name = "searchWithInvalidCountry")
	public Object[][] searchWithInvalidCountry(){

		return new Object[][]
				{
			{"USA"},
			{"India"}
				};
	}

	@DataProvider(name = "searchWithValidMediaType")
	public Object[][] searchWithValidMediaType(){

		return new Object[][]
				{
			{"Now You See Me", "US", "movie"},
			{"Maroon", "US", "music"}
				};
	}

	@DataProvider(name = "searchWithInvalidMediaType")
	public Object[][] searchWithInvalidMediaType(){

		return new Object[][]
				{
			{"Prey", "IN", "  "},
			{"Maroon", "US", "123"},
			{"Maroon", "US", "Invalid_country"}
				};
	}

	@DataProvider(name = "searchWithValidEntityAndAttribute")
	public Object[][] searchWithValidEntityAndAttribute(){

		return new Object[][]
				{
			{"Walter Martin", "audiobook", "genreIndex"},
			{"Leonardo Torres", "movieArtist", "actorTerm"},

				};
	}

	@DataProvider(name = "searchWithInvalidAttribute")
	public Object[][] searchWithInvalidAttribute(){

		return new Object[][]
				{
			{"See", "audiobook", "  "},
			{"Leonardo", "movieArtist", "123"},

				};
	}

	@DataProvider(name = "searchWithInvalidEntity")
	public Object[][] searchWithInvalidEntity(){

		return new Object[][]
				{
			{"See", "  ", "genreIndex"},
			{"Leonardo", "123", "actorTerm"},

				};
	}

	@DataProvider(name = "searchWithValidCallback")
	public Object[][] searchWithValidCallback(){

		return new Object[][]
				{
			{"Jack Johnson", "US", "wsSearchCB"},
			{"Walter Martin", "US", "test_callback"}
				};
	}

	@DataProvider(name = "searchWithInvalidCallback")
	public Object[][] searchWithInvalidCallback(){

		return new Object[][]
				{
			{"Jack Johnson", "US", "   "},
			{"Walter Martin", "US", "%$%%"}
				};
	}

	@DataProvider(name = "searchWithLimit")
	public Object[][] searchWithLimit(){

		return new Object[][]
				{
			{"Jack Johnson", "US", 5},
			{"Walter Martin", "US", 49}
				};
	}

	@DataProvider(name = "searchWithinMaxLimit")
	public Object[][] searchWithinMaxLimit(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", 200}
				};
	}

	@DataProvider(name = "searchWithInvalidLimit")
	public Object[][] searchWithInvalidLimit(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", 2000},
			{"Jack Johnson", "audiobook", -10}
				};
	}
	
	@DataProvider(name = "searchWithValidLang")
	public Object[][] searchWithValidLang(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", "en_us"},
			{"Jack Johnson", "audiobook", "ja_jp"}
				};
	}

	@DataProvider(name = "searchWithInvalidLang")
	public Object[][] searchWithInvalidLang(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", "   "},
			{"Jack Johnson", "audiobook", "123"},
			{"Jack Johnson", "audiobook", "$%#$"}
				};
	}

	@DataProvider(name = "searchWithValidVersion")
	public Object[][] searchWithValidVersion(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", 1},
			{"Jack Johnson", "audiobook", 2},
				};
	}

	@DataProvider(name = "searchWithInvalidVersionType")
	public Object[][] searchWithInvalidVersionType(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", 0},
			{"Jack Johnson", "audiobook", "Test"},
			{"Jack Johnson", "audiobook", "%$#"},
			{"Jack Johnson", "audiobook", "   "}
				};
	}

	@DataProvider(name = "searchWithInvalidVersionNo")
	public Object[][] searchWithInvalidVersionNo(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", 0},
			{"Jack Johnson", "audiobook", 10}
				};
	}

	@DataProvider(name = "searchWithValidExplicitType")
	public Object[][] searchWithValidExplicitType(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", "Yes"},
			{"Jack Johnson", "audiobook", "No"}
				};
	}

	@DataProvider(name = "searchWithInvalidExplicitType")
	public Object[][] searchWithInvalidExplicitType(){

		return new Object[][]
				{
			{"Jack Johnson", "audiobook", "123"},
			{"Jack Johnson", "audiobook", "   "},
			{"Jack Johnson", "audiobook", "$%$%"},
			{"Jack Johnson", "audiobook", "test"}
				};
	}

	@DataProvider(name = "searchQueries_withAllParams")
	public Object[][] searchQueries_withAllParams(){
		Map<String, Object> mapData = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<>();
		Object[][] returnObject = null;
		

		mapData.put("term", "Ed Sheeran");
		mapData.put("country", "US");
		mapData.put("media", "music");
		mapData.put("entity", "movieArtist");
		mapData.put("attribute", "genreIndex");
		mapData.put("callback", "wsSearchCB");
		mapData.put("limit", 5);
		mapData.put("lang", "en_US");
		mapData.put("version", 2);
		mapData.put("explicit", "No");
		
		list.add(mapData);
		
		// Putting the hash map into an Object Array
		returnObject = new Object[list.size()][1];
		for (int iIndex = 0; iIndex < list.size(); iIndex++) {
			returnObject[iIndex][0] = list.get(iIndex);
		}

		return returnObject;

	}
}

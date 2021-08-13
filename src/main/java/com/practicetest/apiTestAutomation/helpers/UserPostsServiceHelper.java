package com.practicetest.apiTestAutomation.helpers;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.practicetest.apiTestAutomation.constants.EndPoints;
import com.practicetest.apiTestAutomation.model.Posts;
import com.practicetest.apiTestAutomation.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserPostsServiceHelper {
	//to fetch the data from the endpoints
	//GET/POST/PATCH/UPDATE/GET Single
	
	//We need to read the config variables
	//Rest Assured about the URL, Port
	//Make a Get Request on this URL and send the data through TestGETPersons
	
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");
	
	public UserPostsServiceHelper() {
		
		RestAssured.baseURI = BASE_URL;//.concat("localhost:3000");
		RestAssured.urlEncodingEnabled = true;
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	public List<Posts> getAllPosts(){  //retrieving all the posts and fetch IDs of a specific users
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.get(EndPoints.GET_ALL_POSTS)
				.andReturn();
				
		
		Type type = new TypeReference<List<Posts>>(){
		}.getType();
		
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Ok");
		
		System.out.println(RestAssured.baseURI);
		
		List<Posts> postList = response.as(type);
		System.out.println(response.getBody());	
		
		return postList;
	
	}
	
	public List<Posts> getNoPostsForInvalidUser() {
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.get(EndPoints.GET_NO_POSTS)
				.andReturn();
		
		Type type = new TypeReference<List<Posts>>(){
		}.getType();
		
		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Ok");
		List<Posts> emptyPostList = response.as(type);
		return emptyPostList;
	}
	
	public Response getNotFoundErrorWithInvalidEndpoint() {
	   String invalidEndPoint = StringUtils.chop(EndPoints.GET_ALL_POSTS);
	   System.out.println(invalidEndPoint);
		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.get(invalidEndPoint)
				.andReturn();
		assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND, "Not Found");
		return response;
	}
}

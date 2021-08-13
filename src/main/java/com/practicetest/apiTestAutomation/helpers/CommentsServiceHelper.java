package com.practicetest.apiTestAutomation.helpers;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.practicetest.apiTestAutomation.constants.EndPoints;
import com.practicetest.apiTestAutomation.model.Comments;
import com.practicetest.apiTestAutomation.model.Posts;
import com.practicetest.apiTestAutomation.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CommentsServiceHelper {
	private static final String BASE_URL = ConfigManager.getInstance().getString("baseUrl");

	public CommentsServiceHelper() {
		RestAssured.baseURI = BASE_URL;//.concat("localhost:3000");
		RestAssured.urlEncodingEnabled = true;
		RestAssured.useRelaxedHTTPSValidation();
	}

	//To retrieve all the comments for a post by user
	public ArrayList<Comments> getAllComments(Integer postId) {

		Response response = RestAssured
				.given()// 
				.basePath(EndPoints.GET_ALL_COMMENTS)
				.pathParam("postID", postId)
				.log().all()
				.contentType(ContentType.JSON)
				.get("/{postID}/comments")
				.andReturn();

		Type type = new TypeReference<ArrayList<Comments>>(){
		}.getType();

		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Ok");		
		ArrayList<Comments> comments = response.as(type);
		System.out.println(response.getBody());	

		return comments;

	}

	public ArrayList<Comments> getNoCommentsForInvalidPosts() {

		Response response = RestAssured
				.given()
				.log().all()
				.contentType(ContentType.JSON)
				.get(EndPoints.GET_NO_COMMENTS)
				.andReturn();

		Type type = new TypeReference<ArrayList<Comments>>(){
		}.getType();

		assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Ok");		
		ArrayList<Comments> emptyCommentsList = response.as(type);
		System.out.println(response.getBody());	

		return emptyCommentsList;

	}
	public Response getNotFoundErrorWithInvalidEndpoint() {
		   String invalidEndPoint = StringUtils.chop(EndPoints.GET_ALL_COMMENTS);
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

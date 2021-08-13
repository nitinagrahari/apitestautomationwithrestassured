package com.practicetest.apiTestAutomation.tests;

//import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.practicetest.apiTestAutomation.helpers.CommentsServiceHelper;
import com.practicetest.apiTestAutomation.helpers.UserPostsServiceHelper;
import com.practicetest.apiTestAutomation.model.Comments;
import com.practicetest.apiTestAutomation.model.Posts;
import com.practicetest.apiTestAutomation.utils.CommonUtils;

import io.restassured.response.Response;

public class TestIntegration {

	private UserPostsServiceHelper userPostsServiceHelper;
	private CommentsServiceHelper commentsServiceHelper;
	private HashMap<Integer, ArrayList<Comments>> map;
	CommonUtils util;
	 

	@BeforeClass
	public void init() {
		userPostsServiceHelper = new UserPostsServiceHelper();
		commentsServiceHelper = new CommentsServiceHelper();
		map = new HashMap<Integer, ArrayList<Comments>>();
		util = new CommonUtils();
	}

	@Test
	public void verifyEmailformatInComments() {
		List<Posts> postList = userPostsServiceHelper.getAllPosts();
		assertNotNull(postList, "Post list is not empty");
		assertFalse(postList.isEmpty(), "Post list is not true");

		for(Posts post :postList){ //To retrieve comments list against each posts by a user
			ArrayList<Comments>	comments = commentsServiceHelper.getAllComments(post.getId());
			assertNotNull(postList, "Comments list is not empty");
			assertFalse(postList.isEmpty(), "Comments list is not true");
			map.put(post.getId(),comments);
		}
		
		for (Entry<Integer, ArrayList<Comments>> entry : map.entrySet()) {//to retrieve email from each comments for each Posts

			for(Comments comment : entry.getValue() ){

				System.out.println("Post id: " + entry.getKey() + "Email :" + comment.getEmail());
				assertEquals((util.valEmailFormat(comment.getEmail())), true);

			}

		}

	}
	
	
}	

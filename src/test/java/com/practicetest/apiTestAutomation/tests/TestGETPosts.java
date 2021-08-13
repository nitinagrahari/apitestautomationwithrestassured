package com.practicetest.apiTestAutomation.tests;

import static org.testng.Assert.assertTrue;
import java.util.List;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.practicetest.apiTestAutomation.helpers.UserPostsServiceHelper;
import com.practicetest.apiTestAutomation.model.Posts;



public class TestGETPosts {
	
	private UserPostsServiceHelper userPostsServiceHelper;
	
	
	@BeforeClass
	public void init() {
		userPostsServiceHelper = new UserPostsServiceHelper();
		
	}
	
	@Test
	public void verifyNoPostsAvailableForInvalidUser() {
		List<Posts> emptyPostList= userPostsServiceHelper.getNoPostsForInvalidUser();
		assertTrue(emptyPostList.isEmpty(), "No response for Invalid User");
		
	}
	@Test
	public void  verifyNotFoundStatusForInvalidEndpointForPostsService() {
		userPostsServiceHelper.getNotFoundErrorWithInvalidEndpoint();
	}

}

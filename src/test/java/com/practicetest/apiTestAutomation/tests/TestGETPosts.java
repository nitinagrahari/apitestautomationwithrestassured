package com.practicetest.apiTestAutomation.tests;

import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import java.util.List;

import org.junit.Rule;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.practicetest.apiTestAutomation.helpers.UserPostsServiceHelper;
import com.practicetest.apiTestAutomation.model.Posts;
import com.practicetest.apiTestAutomation.utils.ExecutionWatcher;
import com.practicetest.apiTestAutomation.utils.ExtendReportInstance;


import io.restassured.response.Response;

public class TestGETPosts {
	
	private UserPostsServiceHelper userPostsServiceHelper;
	
	
	@BeforeClass
	public void init() {
		userPostsServiceHelper = new UserPostsServiceHelper();
		
	}
	@Rule
	public ExecutionWatcher executionWatcher = new ExecutionWatcher(ExtendReportInstance.getInstance());
	
	
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

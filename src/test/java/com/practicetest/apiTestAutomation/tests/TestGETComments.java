package com.practicetest.apiTestAutomation.tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.practicetest.apiTestAutomation.helpers.CommentsServiceHelper;
import com.practicetest.apiTestAutomation.model.Comments;


public class TestGETComments {
	
	private CommentsServiceHelper commentsServiceHelper;
	
	@BeforeClass
	public void init() {
		commentsServiceHelper = new CommentsServiceHelper();
		
	}
	
	@Test
	public void verifyNoCommentsAvailableForInvalidPostID() {
		ArrayList<Comments>	emptyCommentsList = commentsServiceHelper.getNoCommentsForInvalidPosts();
		assertTrue(emptyCommentsList.isEmpty(), "No comments for Invalid Post ID");
		}
	
	@Test
	public void  verifyNotFoundStatusForInvalidEndpointForCommentsService() {
		commentsServiceHelper.getNoCommentsForInvalidPosts();
	}
}

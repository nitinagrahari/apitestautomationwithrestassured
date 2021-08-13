package com.practicetest.apiTestAutomation.utils;

import java.io.IOException;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ExecutionWatcher extends TestWatcher {

	private ExtentTest test;
	private ExtentReports extent;

	public ExecutionWatcher(ExtentReports extent) {
		this.extent = extent;
	}

	@Override
	protected void starting(Description description) {
		String testCaseName = className(description.getClassName()) + "." + description.getMethodName();
		test = extent.createTest(testCaseName);
	}

	private String className(String fullClassName) {
		String[] split = fullClassName.split("tests");
		return split[1].substring(1);
	}

	@Override
	protected void succeeded(Description description) {
		test.log(Status.PASS, "Test executed successfully");
	}

	@Override
	protected void failed(Throwable e, Description description) {
		test.log(Status.FAIL, "Test failed");
		test.log(Status.FAIL, "With error: " + e);
	
	//	test.addScreenCaptureFromPath(ExecutionWatcher.(description.getMethodName()));
	/*
	 * try { test.fail("details",
	 * MediaEntityBuilder.createScreenCaptureFromBase64String("base64String").build(
	 * )); } catch (IOException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); }
	 */
	
}
}
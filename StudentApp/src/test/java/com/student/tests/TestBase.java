package com.student.tests;
//S24, Ex 89 - created this file & application.properties file
//S24, Ex 90 - refactoring: added init of property reader, read from property file 
//S24, Ex 91 - 
//S24, Ex 96: Create TestListeners w/TestWatcher

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.student.util.PropertyReader;

import io.restassured.RestAssured;

public class TestBase {
	
	public static PropertyReader prop; //initialize property reader in this class
	
	@Rule
	public TestRule listener = new TestWatcher() {
		
		@Override
		protected void succeeded(Description description) {
			System.out.println("+++++++++++++++++++++++++++++++");
			System.out.println(description.getMethodName().toUpperCase());
			System.out.println("+++++++++++++++++++++++++++++++");
		}
		@Override
		protected void failed(Throwable e, Description description) {
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.err.println("Test Failed : " + description.getMethodName().toUpperCase());
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}

		@Override
		protected void starting(Description description) {
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Starting Test: " + description.getMethodName().toUpperCase());
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}

		@Override
		protected void finished(Description description) {
			
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("Ending Test: " + description.getMethodName().toUpperCase());
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		
	};
	
	@BeforeClass  //annotation ensures this is executed before any method in the test class
	public static void initUrl() {
		//get the instance:
		prop = PropertyReader.getInstance();
		//refactor original lines after creating PropertyReader & application.properties file
		//RestAssured.baseURI = "http://localhost/student";
		//RestAssured.port = 8080;
		
		RestAssured.baseURI = prop.getProperty("baseurl"); //get property from the instance
		RestAssured.port = Integer.valueOf(prop.getProperty("port")); //convert port to integer from string
		//Type mismatch: method returns a string value so we convert ; get property returns string
	}

}

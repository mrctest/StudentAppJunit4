package com.student.specs;

import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;

import com.student.tests.TestBase;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationFactory extends TestBase {
	
	//create methods for generic response specification to be used in other tests
	public static synchronized ResponseSpecification getGenericResponseSpec() {
		
		ResponseSpecBuilder responseSpec;
		ResponseSpecification responseSpecification;
		//create new object then build the specification: 
		responseSpec = new ResponseSpecBuilder();
		responseSpec.expectHeader("Content-Type",  "application/json;charset=UTF-8");
		responseSpec.expectHeader("Transfer-Encoding", "chunked");
		responseSpec.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		
		responseSpecification = responseSpec.build();
		
		return responseSpecification;
		
	}
	
	//re-factor to incorporate logging request-responses; S24/Ex94
	public static synchronized RequestSpecification logPayloadResponseInfo() {
		
		RequestSpecBuilder logBuilder;
		RequestSpecification logSpecification;
		
		logBuilder = new RequestSpecBuilder();
		
		if(prop.getProperty("log").equals("ENABLE")) {
			logBuilder.addFilter(new AllureRestAssured());
			
		}
		
		logSpecification = logBuilder.build();
		return logSpecification;
		
		//now goto RestClient.java & re-factor doPostRequest method
	}
	
}

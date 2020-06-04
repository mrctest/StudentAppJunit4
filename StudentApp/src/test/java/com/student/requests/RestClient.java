package com.student.requests;
//created in S24, Ex 92
//doGetRequest method: encapsulates the RestAssured get request & servers as a wrapper 
//avoids having duplicate code in RequestFactory.java

import java.util.Map;

import com.student.specs.SpecificationFactory;
import com.student.tests.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestClient extends TestBase {
	
	public Response doGetRequest(String requestPath) {   //returns an Object of Type Response
		//copied from RequestFactory:
		return RestAssured.given()
                          .when()
                          .get(requestPath); //request to the list resource
		
	} 
	//doGetRequest encapsulated RestAssured method to get the resource into to doGetRequestMethod
	//doGetRequest is now called from RequestFactory.java 
	//this way you can add anything additional to the GET request here
    
	public Response doPostRequest(String uri, Object body) {
		
		return RestAssured.given()
				           .contentType(ContentType.JSON) 
				           .spec(SpecificationFactory.logPayloadResponseInfo())  //S24, Ex94 @11:10
				           .when() 
				           .body(body) 
				           .post(uri);
	}
	//additional tests:
	public Response doGetRequestWithQueryParam(String res, Map<String, String> params) {
		Response response = RestAssured.given()
				                       .queryParams(params)
				                       .when()
				                       .get(res);
		return response;
	
	}
		
	public Response doGetRequestWithHeader(String res, Map<String, String> headers) {
		Response response = RestAssured.given()
				                       .headers(headers)
				                       .when()
				                       .get(res);
		return response;
		
	}
	
	public Response doPutRequest(String res, Object body) {
		Response response = RestAssured.given()
				                       .when() 
				                       .body(body)
				                            .put(res);
		return response;
		
	}
	
	public Response doPatchRequest(String res, Object body) {
		Response response = RestAssured.given()
				                       .when()
				                       .body(body)
				                            .patch(res);
		return response;
	}
	
	public Response doDeleteRequest(String res) {
		Response response = RestAssured.given()
				                       .when()
				                            .delete(res);
		return response;
	}
	
	
	
}

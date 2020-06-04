package com.student.tests;
//S24 ex 87, 88: execute first build using maven
//see OneNotes S24: AutoFramework with JUnit-4 for details
//baseURI & port for logging in read from property file application.properties
//copied orig getAllStudents to RequestFacotry & refactored the method  below
//   added object RequestFactory
//add @Step annotation for Allure Reports
//Ex 95: Tagging tests using JUnit Categories (makes for better reports)
//Ex 96: Test Listeners - JUnit no direct class available; use TestWatcher 

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.javafaker.Faker;
import com.student.requests.RequestFactory;
import com.student.specs.SpecificationFactory;
import com.student.tags.Regression;
import com.student.tags.Smoke;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;
import io.restassured.RestAssured; 

@Story("This is a CRUD testing story")	
public class CrudTest extends TestBase {
	
	RequestFactory requests = new RequestFactory();
	
    @Category(Smoke.class) //requires editing pom.xml with <groups>${group}</groups> and a property
	//@Category({Regression.class, Smoke.class})
	@Step("Getting all the student info from the db")
	@Story("This is a CRUD testing story")	
	@DisplayName("This is a test to GET all students from database")
	@Feature("This is a test to GET all students from database")
	@Test
	public void getAllStudents() { //just a quick test
		
		//RestAssured.baseURI = "http://localhost/student"; <<--move to TestBaseClass.java
		//RestAssured.port = 8080;
		/*
		 * //re-factoring after creating RequestFactory.java RestAssured.given() .when()
		 * .get("/list") //request to the list resource .then() .log() .body() //log
		 * response body .statusCode(200);
		 */
		//after creating SpecificationFactory in S24/Ex98, re-factor this line: 
		//requests.getAllStudents().then().statusCode(200);
		requests.getAllStudents()
		         .then()
		         .spec(SpecificationFactory.getGenericResponseSpec())
		         .statusCode(200);
	}
	
	//@Category(Regression.class)  <-- run a single test
	@Category({Regression.class,Smoke.class})
	@Story("This is a CRUD testing story")
	@DisplayName("Test to create & verify a new student")
	@Feature("Test to creaste & verify a new student")
	@Tag("Regression, Smoke") //to have tag info appear in allure reports
	@Test 
	public void createNewStudent() {
		//this is used to create our first student, subsequent runs returns status code 500 
		//to resolve this we'll use javaFakerApi to generate fake params for our request
		
		Faker fakeData = new Faker();
		
		
		String firstname = fakeData.name().firstName(); // "John";
		String lastName  = fakeData.name().lastName();   //"Doe";
		String email     = fakeData.internet().emailAddress(); //"JohnDoe@gmail.com";
		String programme = "ComputerScience";
		List<String>courses = new ArrayList<String>();
		courses.add("C++");
		courses.add("Java");
		
		//use methods from our requestFactory
		requests.createNewStudent("", firstname, lastName, email, programme, courses)
		        .then() 
		        //add this line after creating specificationFactory.java
		        .spec(SpecificationFactory.getGenericResponseSpec())
		        .statusCode(201);
			
	}

}

package com.student.requests;
//create in S24, Ex 91: Create areusable mthods for the student app
//copy RestAssured.given() from CrudTest.java & refactor that class file

import java.util.List;

import com.student.pojo.StudentClass;
import com.student.tests.TestBase;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RequestFactory extends TestBase{
	
	//added @S24, Ex 92
	RestClient restClient = new RestClient();  //created after creating RestClient.java wrapper
	
	@Step("Getting all student information from db") //displays in Test Body for report detail
	public Response getAllStudents() {     //method returns a response object
		
		//re-factor after adding instance of RequestFactory above
		/*
		 * Response response = RestAssured.given() .when() .get("/list"); //request to
		 * the list resource return response;
		 */
		
		Response response = restClient.doGetRequest("/list");
		return response;
	}
	
	//after re-factor RestClient.java with more tests...add this section:
	
	//method to create a student (POST), Ex 92: 5:23 marker
	@Step("Creating a new student: {0}, {1}, {2}, {3}, {4}") //comment for our report - show params
	public Response createNewStudent(String url, String firstName, String lastName, String email, 
			String programme, List<String> courses) {
		
		//construct a student object using package com.student.pojo. In Sec 24, Ex98 DownloadCode, get .zip file
		//copy StudentApp\src\main\java\com\student\pojo\StudentClass.java TO your project: 
		//src/main/java/com.student.pojo 
		
		StudentClass body = new StudentClass(); //after creating StudentClass.java & importing the file, create this object
		body.setFirstName(firstName);
		body.setLastName(lastName);
		body.setEmail(email);
		body.setProgramme(programme);
		body.setCourses(courses);
		
		//form Post request & return response: 
		return restClient.doPostRequest(url, body); //return the response received
		//goes to RestClientljava, performs POST request & returns a response
		
	}
	
	//method to update student info
	@Step("Updating info for student: {0}, firstName: {1}, lastname:{2} ")
	public Response updateStudent(int studentid, String firstName, String lastName, String email, 
			String programme, List<String> courses) {
		
		StudentClass student = new StudentClass();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);
		
		return restClient.doPutRequest("/" + studentid, student);
		
	}
	
	//method to delete student
	@Step("Deleting Student info with ID: {0}")
	public Response deleteStudent(int studentId) {
		
		return restClient.doDeleteRequest("/" + studentId);
		
	}
	
	//method to getStudentById
	public Response getStudentById(int studentId) {
		
		return restClient.doGetRequest("/" + studentId);
	}
	

	
	
	
	

}

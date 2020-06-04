package com.student.pojo;
//Sec 24, Ex 92: Created for doPostRequest method in RequestFactory re-factoring for creating Student
//Copied StudentClass.java contents from Ex 98 DownloadCode
//marker 10:54

import java.util.List;

public class StudentClass {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String programme;
	private List<String> courses;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public List<String> getCourses() {
		return courses;
	}
	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

}

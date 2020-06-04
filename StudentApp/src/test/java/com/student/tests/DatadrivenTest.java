package com.student.tests;
//S24, Ex. 97: DataDriven testing w/DataProviders (updated pom w/Maven dep for TNG /junit-dataprovider
//open source TNG https://github.com/TNG/junit-dataprovider/wiki/Getting-started
//Created this class file to demo usage of TNT data provider

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)  //run the class file w/data provider class
public class DatadrivenTest {
	
	@DataProvider    //annotation denotes data provider
	public static Object[] [] dataProviderAdd() { //created so methods can use data provider; returns a 2D array
		return new Object [][] {
			{0,1},
			{1,2},
			{3,4}
		};
	}
	
	@UseDataProvider("dataProviderAdd")   //indicates use of data provider for this method
	@Test
	public void add2Numbers(int num1, int num2) {
		
		//provide numbers from data provider
		System.out.println(num1 + num2);
	}
	
	@DataProvider   //copied from above DP
	public static Object[] [] dataProviderAdd2() { //created so methods can use data provider; returns a 2D array
		return new Object [][] {
			{0,1},
			{1,2},
			{3,4}
		};
	}
	
	@UseDataProvider("dataProviderAdd2")   //indicates use of data provider for this method
	@Test
	public void add2Numbers2(int num1, int num2) {
		
		//provide numbers from data provider
		System.out.println(num1 + num2);
	}
	
	@Test
	public void printStuff() {
		
		//provide numbers from data provider
		System.out.println("Hello");
	}
	
	
	
	

}

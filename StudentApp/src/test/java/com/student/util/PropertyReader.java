package com.student.util;

import java.io.InputStream;
import java.util.Properties;

//S24, Ex 90
// PropertyReader method gets an instance of the PropertyReader class
//Steps:
// 1- create a private constructor: PropertyReader()
// 2- create a private member variable: propInstance
// 3- create a (public) static method to get the instance of the class getInstance
// 4- create a method to read the property file and get value specified

public class PropertyReader {
	
	private static volatile PropertyReader propInstance;  //step 2 - create static method w/in the class
	//keyword volatile used for multi-threading issues
	
	private PropertyReader() {            //step1: make a private constructor method of property class private
	}
	
	//multiple threads accessing a method can cause issues/conflicts...use keyword 'synchronized'
	//synchronized keyword - java takes care of potential multi-threading issues
	
	public static synchronized PropertyReader getInstance() {    //step 3-instantiate object & return it
		if(propInstance ==null) {                 //only initialize if instance is null
			propInstance = new PropertyReader();
		}
			return propInstance;
	}
	
	//create a method to read the property file and get the property specified
	//getClass().getClassLoader().getResourceAsStream: scans the resources folder & if file specified found, rtn contents as input stream
	//we then load the input stream to a property object
	
	public String getProperty(String propertyName) {
		
		Properties prop = new Properties();   //create an instance of property
		
		//read the input file (application.properties) as an input stream:
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties");
		    
			prop.load(inputStream); //now we can access the property name
			
			if(prop.getProperty(propertyName) != null) { //use propertyName passed into the method
				return prop.getProperty(propertyName);
				}
			
		}catch (Exception e) {
			System.out.println("@Method PropertyReader.getProperty: Property not found");
		}
				
		return null;
	}

}

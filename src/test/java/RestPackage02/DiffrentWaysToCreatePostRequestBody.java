package RestPackage02;

//Add manually following imports from restAssured Docs > Get Started > Static imports
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;



public class DiffrentWaysToCreatePostRequestBody{
	
	
	/*
	 * Different ways to create POST request body
	 * 
	 * 
	 * 1] HashMap
	 * 2] Using org.json
	 * 3]Using POJO (Plain Old Java Object)
	 * 4]Using external json file
	 * 
	 */
	
	
	
	//POST request body using HashMap
	
//	@Test(priority = 1)
	void testPostUsingHashMap() {
		
		
		HashMap hashMap=new HashMap<>();
		hashMap.put("name", "Shubham");
		hashMap.put("location", "India");
		hashMap.put("phone", "1234");
		
		String courseArr[]= {"Java","Selenium"};
		
		hashMap.put("courses", courseArr);
		
		
		
		given()
			.contentType("application/json")
			.body(hashMap)
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Shubham"))
			.body("location", equalTo("India"))
			.body("phone", equalTo("1234"))
			.body("courses[0]", equalTo("Java"))
			.body("courses[1]",equalTo("Selenium"))
		    .header("Content-Type", "application/json; charset=utf-8")
		    .log().all();
			
	}
	
	@Test(priority = 2)
	void deletePostuser() {
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/4")
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	
	
	//POST request body using org.json

//	@Test(priority = 1)
	void testPostUserUsingOrgJSON() {
		
		JSONObject jsonObject=new JSONObject();
		
		jsonObject.put("name", "SM");
		jsonObject.put("location", "Maharashtra");
		jsonObject.put("phone", "12345");
		
		String courseArr[]= {"restAssured","Jmeter"};
		
		jsonObject.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(jsonObject.toString())
			
		.when()
			.post("http://localhost:3000/students")
		
		.then()
				.statusCode(201)
				.body("name", equalTo("SM"))
				.body("location", equalTo("Maharashtra"))
				.body("phone", equalTo("12345"))
				.body("courses[0]", equalTo("restAssured"))
				.body("courses[1]", equalTo("Jmeter"))
				.header("Content-Type", "application/json; charset=utf-8")
				.log().all();
	}
	
	
	
	//POST request body using POJO (Plain Old Java Object)
	
//	@Test(priority = 1)
	void testPostUserUsingPOJO() {
		
		
		POJO_POST_RequestData pData=new POJO_POST_RequestData();
		pData.setName("ShubhaM");
		pData.setLocation("France");
		pData.setPhone("4321");
		
		String courseArr[]= {"Maven","TestNG"};
		pData.setCourseArr(courseArr);
		
		
		given()
			.contentType("application/json")
			.body(pData)
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("ShubhaM"))
			.body("location", equalTo("France"))
			.body("phone", equalTo("4321"))
			.body("courseArr[0]", equalTo("Maven"))
			.body("courseArr[1]", equalTo("TestNG"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();

	}
	
	
	//POST request body using external JSON File

	@Test(priority = 1)
	void testPostUserUsingExternalJsonFile() throws FileNotFoundException {
		
		File f=new File(".\\postData.json");
		
		FileReader fileReader=new FileReader(f);
		
		JSONTokener jsonTokener=new JSONTokener(fileReader);
		
		JSONObject jsonObject=new JSONObject(jsonTokener);
		
		
			
		given()
			.contentType("application/json")
			.body(jsonObject.toString())
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("HitMan SM"))
			.body("location", equalTo("Canada"))
			.body("phone", equalTo("4321"))
			.body("courses[0]", equalTo("CI CD"))
			.body("courses[1]", equalTo("DevOps"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
		
	}
	

}

package RestPackage01;

import org.testng.annotations.Test;

//Add manually following imports from restAssured Docs > Get Started > Static imports
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HttpRequests {
	
	/*
	 * given() = content type,cookies,add auth, params, set headers,etc.
	 * 
	 * 
	 * when()= GET, POST, PUT, PATCH , DELETE , etc.
	 * 
	 * 
	 * 
	 * then() = validate status code, extract response, extract headers cookies and response body ,etc
	 * 
	 */
	
	int id;
	
	@Test (priority = 1)
	void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.log().all();
	}
	
	@Test (priority = 2)
	void createUser() {
		
		HashMap hm=new HashMap<>();
		hm.put("name", "Shubham");
		hm.put("job","SDET");
		
		id = given()
			.contentType("application/json")
			.body(hm)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
//		.then()
//			.statusCode(201)
//			.body("name", equalTo("morpheus"))
//			.log().all();
			
		
		
	}
	
	@Test (priority = 3 , dependsOnMethods = {"createUser"})
	void updateUser(){
		
		HashMap hm = new HashMap<>();
		hm.put("name", "SM");
		hm.put("job", "Automation Test Engineer");
		
		
		given()
			.contentType("application/json")
			.body(hm)
		
		.when()
			.put("https://reqres.in/api/users/"+ id)
		
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority = 4)
	void deleteUser(){
		
		given()

		.when()
			.delete("https://reqres.in/api/users/"+ id)
		
		.then()
			.statusCode(204)
			.log().all();
	}

}

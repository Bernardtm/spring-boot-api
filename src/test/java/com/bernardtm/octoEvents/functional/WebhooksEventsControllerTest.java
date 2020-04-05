package com.bernardtm.octoEvents.functional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebhooksEventsControllerTest {
	
	@LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }
		
	@Test
	public void saveEvent() throws JSONException {
		String payload = "{\n \"action\": \"reopened\"\n}";
		
		given().
			contentType(ContentType.JSON).
			header("X-GitHub-Event", "issues").
			header("X-GitHub-Delivery", "83fe6c80-6e36-11ea-9b4a-cdeb2cb93250").
			header("X-Hub-Signature", "sha1=ce7fa9bf56844f4f1f14ccec99b1af3ce1c07192").
			body(payload).
			basePath("/octo-events").
        	post("/events").
	    then().
	        statusCode(201).
	        body("id", not(equalTo(null)));
    }
		
}

package com.bernardtm.octoEvents.functional;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.bernardtm.octoEvents.domain.models.IssueEvent;
import com.bernardtm.octoEvents.domain.repositories.IssueEventsRepository;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EventsControllerFunctionalTest {
	
	@LocalServerPort
    int port;

	private IssueEvent event;
	
	private final Long ISSUE_NUMBER = 9999L;
	private final Long NONEXISTENT_ISSUE_NUMBER = 9998L;

	@Autowired
	IssueEventsRepository repository;


	@Before
    public void setUp() {
        RestAssured.port = port;
        
        event = new IssueEvent();
		event.setAction("opened");
		event.setNumber(ISSUE_NUMBER);
		event = repository.save(event);
    }
	
	@After
    public void tearDown() {
		repository.delete(event);
    }
    
	@Test
	public void getEvent() {
		
		given().
        	basePath("/octo-events").get("/issues/" + ISSUE_NUMBER + "/events").
	    then().
	        statusCode(200).
	        body("issue.number", hasItem(String.valueOf(ISSUE_NUMBER)));
    }
	
	@Test
	public void getNonexistentEvent() {
		
		given().
        	basePath("/octo-events").get("/issues/" + NONEXISTENT_ISSUE_NUMBER + "/events").
	    then().
	        statusCode(204);
    }

}

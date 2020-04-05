package com.bernardtm.octoEvents.base;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class BaseTestRestrictions {
	
	@BeforeClass(alwaysRun = true)
    public void preCondicao() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        baseURI = "http://localhost:8080";
        basePath = "/octo-events";
    }

}

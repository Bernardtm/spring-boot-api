package com.bernardtm.octoEvents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class OctoEventsApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(OctoEventsApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter("octo-events.pid"));
		springApplication.run(args);
	}

}

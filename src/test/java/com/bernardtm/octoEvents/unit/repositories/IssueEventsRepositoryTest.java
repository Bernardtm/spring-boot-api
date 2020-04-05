package com.bernardtm.octoEvents.unit.repositories;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bernardtm.octoEvents.domain.models.IssueEvent;
import com.bernardtm.octoEvents.domain.repositories.IssueEventsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IssueEventsRepositoryTest {
	
	IssueEvent event;
	
	@Autowired
    IssueEventsRepository repository;
	
	@Before
    public void setUp() {
		event = new IssueEvent();
		event.setAction("opened");
		event.setNumber(9999L);
    }
	
	@Test
    public void saveIssueEvent() {
		IssueEvent createdEvent = repository.save(event);
        Assert.assertTrue(createdEvent.getId() != null);
        repository.delete(createdEvent);
    }
	
	@Test
    public void getIssueEventsByNumber() {
		repository.save(event);
		
		List<Optional<IssueEvent>> foundEvent = repository.findByNumber(9999L);
		
        Assert.assertTrue(foundEvent.get(0).get().getNumber().equals(9999L));
        repository.delete(foundEvent.get(0).get());
    }

}

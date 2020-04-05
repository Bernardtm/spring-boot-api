package com.bernardtm.octoEvents.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardtm.octoEvents.domain.models.dtos.IssueEventDto;
import com.bernardtm.octoEvents.domain.services.IssueEventsService;

@RestController
@RequestMapping("/issues")
public class EventsController {
	
	@Autowired
	private IssueEventsService service;

	@Cacheable(value="event", key="#issueId")
	@GetMapping(value = "/{issueId}/events")
	public List<IssueEventDto> events (@PathVariable Long issueId) {
		return service.getIssueEvent(issueId);
	}
}

package com.bernardtm.octoEvents.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bernardtm.octoEvents.domain.models.dtos.IssueEventDto;
import com.bernardtm.octoEvents.domain.services.IssueEventsService;

@RestController
@RequestMapping("/events")
public class WebhooksEventsController {
	
	@Autowired
	private IssueEventsService service;
	
	// TODO improve cache eviction, clear by issue number
	@CacheEvict(value="event", allEntries = true)
	@PostMapping
	public ResponseEntity<IssueEventDto> webhook (
			@RequestHeader(name = "X-GitHub-Event", required = true) String eventType,
			@RequestHeader(name = "X-GitHub-Delivery", required = true) String globallyUniqueIdentifier,
			@RequestHeader(name = "X-Hub-Signature", required = true) String secret, 
			@RequestBody IssueEventDto payload) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createIssueEvent(payload));
	}
}

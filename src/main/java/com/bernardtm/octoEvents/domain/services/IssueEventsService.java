package com.bernardtm.octoEvents.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardtm.octoEvents.domain.converters.IssueEventsConverter;
import com.bernardtm.octoEvents.domain.models.dtos.IssueEventDto;
import com.bernardtm.octoEvents.domain.repositories.IssueEventsRepository;

@Service
public class IssueEventsService {
	
	@Autowired
	private IssueEventsRepository repository;
	
	@Autowired
	private IssueEventsConverter converter;

	public IssueEventDto createIssueEvent(IssueEventDto payload) {
		return converter.toDto(repository.save(converter.toModel(payload)));
	}

	public List<IssueEventDto> getIssueEvent(Long issueId) {
		return converter.optionalListModeltoDto(repository.findByNumber(issueId));
	}
}
